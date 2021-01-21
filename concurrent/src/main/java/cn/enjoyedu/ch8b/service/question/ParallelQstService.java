package cn.enjoyedu.ch8b.service.question;

import cn.enjoyedu.ch8b.assist.SL_QuestionBank;
import cn.enjoyedu.ch8b.vo.QuestionInCacheVo;
import cn.enjoyedu.ch8b.vo.QuestionInDBVo;
import cn.enjoyedu.ch8b.vo.TaskResultVo;

import java.util.concurrent.*;

/**
 * 类说明：
 */
public class ParallelQstService {

    //题目在本地中的缓存
    private static ConcurrentHashMap<Integer,QuestionInCacheVo> questionCache
            = new ConcurrentHashMap<>();

    //正在处理题目的缓存
    private static ConcurrentHashMap<Integer,Future<QuestionInCacheVo>> processingQuesttionCache
            = new ConcurrentHashMap<>();

    ThreadFactory threadFactory;
    private static ExecutorService makeQuestionExector
            = Executors.newCachedThreadPool();

    public static TaskResultVo makeQuestion(Integer questionId){
        QuestionInCacheVo questionInCacheVo = questionCache.get(questionId);
        if (null == questionInCacheVo){
            System.out.println("题目"+questionId+"在缓存不存在，准备启动");
            return new TaskResultVo(getQuestionFutrue(questionId));
        }else {
            //从题库中查询摘要
            String questionSha = SL_QuestionBank.getQuestionSha(questionId);
            if (questionInCacheVo.getQuestionSha().equals(questionSha)){
                System.out.println("题目"+questionSha+"在缓存中已经存在，可直接使用");
                return new TaskResultVo(questionInCacheVo.getQuestionDetail());
            }else {
                System.out.println("题目"+questionSha+"在缓存中已经过期，准备重置更新");
                return new TaskResultVo(getQuestionFutrue(questionId));
            }
        }
    }

    private static Future<QuestionInCacheVo> getQuestionFutrue(Integer questionId) {
        Future<QuestionInCacheVo> questionFutrue
                = processingQuesttionCache.get(questionId);
        //如果题目在缓存中没有
        try {
            if (questionFutrue == null){
                QuestionInDBVo questionInDBVo
                        = SL_QuestionBank.getQuetion(questionId);
                QuestionTask questionTask = new QuestionTask(questionInDBVo,questionId);
    //            questionFutrue = makeQuestionExector.submit(questionTask);
    //            processingQuesttionCache.putIfAbsent(questionId,questionFutrue);
    //            processingQuesttionCache.putIfAbsent(questionId,questionFutrue);
    //            questionFutrue = makeQuestionExector.submit(questionTask);
                //如何在不加锁的情况下避免一个线程对多个线程启动
                FutureTask<QuestionInCacheVo> ft
                        = new FutureTask<>(questionTask);
                questionFutrue = processingQuesttionCache.putIfAbsent(questionId,ft);
                if (questionFutrue == null){
                    //当前线程成功了占位了
                    questionFutrue = ft;
                    makeQuestionExector.execute(ft);
                    System.out.println("当前任务已启动，请等待完成");
                }else {
                    System.out.println("有其他线程开启了题目的计算任务，本任务无需开启");
                }


            }else {
                System.out.println("当前已经有了题目的计算任务，不必重复开启");
            }

            return  questionFutrue;
        } catch (Exception e) {
            processingQuesttionCache.remove(questionId);
            e.printStackTrace();
            throw e;
        }
    }

    //根据题目的任务类，调用最基础的题目生成服务即可
    private static class QuestionTask implements Callable<QuestionInCacheVo>{

        QuestionInDBVo questionInDBVo;
        Integer questionId;

        public QuestionTask(QuestionInDBVo questionInDBVo, Integer questionId) {
            this.questionInDBVo = questionInDBVo;
            this.questionId = questionId;
        }

        @Override
        public QuestionInCacheVo call() throws Exception {
            try {
                String makeQuestion
                        = QstService.makeQuestion(questionId, questionInDBVo.getDetail());
                String sha = questionInDBVo.getSha();
                QuestionInCacheVo questionInCacheVo = new QuestionInCacheVo(makeQuestion,sha);
                questionCache.put(questionId, questionInCacheVo);
                return  questionInCacheVo;
            } finally {
                //无论正常还是异常，均要将生成题目移除
                processingQuesttionCache.remove(questionId);
            }
        }
    }

}
