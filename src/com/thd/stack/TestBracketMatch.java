package com.thd.stack;

/**
 * 检查表达式中的括弧是否匹配
 */
public class TestBracketMatch {
    public static void main(String[] args) {
        System.out.println(bracketMatch("{([])}"));
    }

    //检测expriession表达式中的括弧是否匹配
    public static boolean bracketMatch(String expression){
        //保存左括弧
        MyArrayStack stack = new MyArrayStack();
        //遍历整个表达式，如果是左括弧就入栈，如果是右括弧就出栈判断是否匹配
        for (int i = 0; i < expression.length(); i++) {
            //取出表达式的每一个字符
            char cc = expression.charAt(i);
            switch (cc){
                case '(':
                case '[':
                case '{':
                    //左括弧入栈，Java可以自动装箱与拆箱
                    stack.push(cc);
                    break;
                case '}' :
                    if (!stack.isEmpty() && stack.pop().equals('{')){
                        break;
                    }else {
                        return false;
                    }
                case ']' :
                    if (!stack.isEmpty() && stack.pop().equals('[')){
                        break;
                    }else {
                        return false;
                    }
                case ')' :
                    if (!stack.isEmpty() && stack.pop().equals('(')){
                        break;
                    }else {
                        return false;
                    }
            }
        }
        //遍历后，如果栈是空的，表示括弧匹配成功
        if(stack.isEmpty()){
            return true;
        }else {
            return false;
        }
    }
}
