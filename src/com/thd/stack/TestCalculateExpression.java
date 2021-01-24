package com.thd.stack;


/**
 * 使用栈来计算算术表达式的值
 */
public class TestCalculateExpression {
    public static void main(String[] args) {
        String expression = "4-+3+(6-10+2*3)*4";
        double result = calculate(expression);
        System.out.println(result);
    }

    //定义方法，指定表达式的值：4+3+(6-10+2*3)*4
    private static double calculate(String expression) {
        //存储操作符
        MyArrayStack operatorStack = new MyArrayStack();
        //存储操作数
        MyArrayStack operandStack = new MyArrayStack();
        //遍历表达式中的操作数与操作符
        for (int i = 0; i < expression.length(); i++) {
            char cc = expression.charAt(i);
            //如果cc是数字
            if (Character.isDigit(cc)){
                //取出操作数-->考虑操作数位数的问题
                StringBuilder sb = new StringBuilder();
                while (Character.isDigit(cc)){
                    sb.append(cc);
                    //取下个字符
                    i++;
                    if (i >= expression.length()){
                        break;
                    }
                    cc = expression.charAt(i);
                }
                //操作数入栈
                operandStack.push(sb.toString());
                //修正i变量的值
                i--;
               // System.out.println(sb);
            }else {
                //操作符4+3+(6-10+2*3)*4
                //1.如果栈为空
                if (operatorStack.isEmpty()){
                    operatorStack.push(cc);
                    continue;
                }
                //栈不为空要一直判断
                while (!operatorStack.isEmpty()) {
                    //2.操作符栈不为空的情况
                    char op1 = (char) operatorStack.peek();
                    //判断栈中运算符与当前预算符的优先级
                    if (compareOperator(op1, cc) < 0) {
                        //当前运算符的优先级高于栈顶运算符
                        operatorStack.push(cc);
                        break;
                    } else if (compareOperator(op1, cc) == 0) {
                        //当前运算符的优先级deng于栈顶运算符,只有一种情况，'（ ' 遇到->  '）' 的情况
                        operatorStack.pop();
                        break;
                    } else {
                        ///当前运算符的优先级di于栈顶运算符
                        //取出两个操作数
                        if (operandStack.isEmpty()){
                            throw new RuntimeException("表达式错误");
                        }
                        double num1 = Double.parseDouble(operandStack.pop().toString());
                        if (operandStack.isEmpty()){
                            throw new RuntimeException("表达式错误");
                        }
                        double num2 = Double.parseDouble(operandStack.pop().toString());
                        //取栈顶运算符
                        char operator = (char) operatorStack.pop();
                        //计算  num2 operator num1
                        double result = compute(operator,num2,num1);
                        //把结果存储到操作数栈中
                        operandStack.push(result);
                        //如果当前操作符栈是空的，新的操作符应该入栈
                        if (operatorStack.isEmpty()){
                            operatorStack.push(cc);
                            break;
                        }
                    }
                }

            }
        }
        //当表达式遍历完后，如果操作符栈不为空，需要继续计算
        while (!operatorStack.isEmpty()){
            char operator = (char) operatorStack.pop();
            //如果操作数栈为空，表达式错误
            if (operandStack.isEmpty()){
                throw new RuntimeException("表达式错误");
            }
            double num1 = Double.parseDouble(operandStack.pop().toString());
            if (operandStack.isEmpty()){
                throw new RuntimeException("表达式错误");
            }
            double num2 = Double.parseDouble(operandStack.pop().toString());
            double result = compute(operator,num2,num1);
            operandStack.push(result);
        }
        //当操作符栈为空，操作数栈中多余一个数，表达式错误
        if (operatorStack.getSize() > 1){
            throw new RuntimeException("表达式错误");
        }
        return Double.parseDouble(operandStack.pop().toString());
    }

    private static double compute(char operator, double num1, double num2) {
        switch (operator){
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
        }
        return 0;
    }

    //判断俩个运算符的优先级 op1高为正，op2高为负数，一样为0
    private static int compareOperator(char op1, char op2) {
        //因为是char 所以用单引号
        if (op1 == '+' || op1 == '-'){
            if (op2 == '*' || op2 == '/' || op2 == '('){
                return -1;
            }
        }
        if (op1 == '*' || op1 == '/'){
            if (op2 == '('){
                return -1;
            }
        }
        if (op1 == '('){
            if (op2 == ')'){
                return 0;
            }else {
                return -1;
            }
        }
        return 1;

    }


}
