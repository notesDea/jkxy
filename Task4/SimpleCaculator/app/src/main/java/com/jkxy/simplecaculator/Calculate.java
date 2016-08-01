package com.jkxy.simplecaculator;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by notes on 2016/7/18.
 */
public class Calculate {

    public static String calculator(String exp) throws Exception {
        ArrayList<String> getStringList = getStringList(exp);
        ArrayList<String> postOrderExp = getPostOrder(getStringList);
        double result = calPostOrderExp(postOrderExp);
        if (result == Math.floor(result)) {
            return (long) result + "";
        }
        return result + "";
    }

    private static double calPostOrderExp(ArrayList<String> postOrderExp) throws Exception {
        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < postOrderExp.size(); i++) {
            String cur = postOrderExp.get(i);

            if (isOperator(cur)) {
                double num2 = stack.pop();
                double num1 = stack.pop();
                double curResult = 0;

                switch (cur.charAt(0)) {
                    case '*':
                        curResult = num1 * num2;
                        break;
                    case '/':
                        if (num2 == 0) throw new Exception();
                        curResult = num1 / num2;
                        break;
                    case '+':
                        curResult = num1 + num2;
                        break;
                    case '-':
                        curResult = num1 - num2;
                        break;
                    default:
                        break;
                }
                stack.push(curResult);
            } else {
                stack.push(Double.parseDouble(cur));
            }
        }
        return stack.pop();
    }

    private static ArrayList<String> getPostOrder(ArrayList<String> stringList) {
        ArrayList<String> postOrder = new ArrayList<>();
        Stack<String> operator = new Stack<>();

        for (int i = 0; i < stringList.size(); i++) {
            String cur = stringList.get(i);

            if (isOperator(cur)) {
                while (!operator.isEmpty() && compareOperatorPriority(operator.peek(), cur)) {
                    postOrder.add(operator.pop());
                }
                operator.push(cur);
            } else {
                postOrder.add(cur);
            }
        }

        while (!operator.isEmpty()) {
            postOrder.add(operator.pop());
        }

        return postOrder;
    }

    //表达式转换成list
    private static ArrayList<String> getStringList(String exp) {
        ArrayList<String> stringList = new ArrayList<>();
        String num = "";

        for (int i = 0; i < exp.length(); i++) {
            if (Character.isDigit(exp.charAt(i)) || '.' == exp.charAt(i)) {
                num += exp.charAt(i);
                if (i == exp.length() - 1 && !num.equals("")) {
                    stringList.add(num);

                }
            } else {
                if (!num.equals("")) {
                    stringList.add(num);
                    num = "";
                }
                stringList.add(exp.charAt(i) + "");
            }
        }

        return stringList;
    }

    private static boolean isOperator(String s) {
        if (s.matches("\\+|\\-|\\*|\\/")) {
            return true;
        }
        return false;
    }

    //比较运算符优先级
    private static boolean compareOperatorPriority(String peek, String cur) {
        if ((peek.matches("\\*|\\/") && cur.matches("\\*|\\/|\\+|\\-")) ||
                (peek.matches("\\+|\\-") && cur.matches("\\+|\\-"))) {
            return true;
        }
        return false;
    }
}
