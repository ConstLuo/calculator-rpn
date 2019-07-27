package org.test.luo.jin.calculator.common.util;

import org.test.luo.jin.calculator.common.exception.FormatException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author luo_j
 * @date 2019/7/26 12:54 PM
 **/
public class CommonUtils {
    /**
     * 该方法获取栈中数据，将其存在List集合中
     *
     * @param stk
     */
    public static List<Double> getStack(Stack<Double> stk) {
        List<Double> getStk = new ArrayList<>();
        for (Double x : stk) {
            getStk.add(x);
        }
        return getStk;
    }

    /**
     * 除法计算法则
     *
     * @param a 操作数1
     * @param b 操作数2
     */
    public static double div(double a, double b) throws FormatException {
        if (b == 0) {
            throw new FormatException("除数不能为0!");
        }
        return a / b;
    }

    /**
     * 该方法将字符串转换为数字类型Double
     *
     * @param str
     */
    public static Double strToDigit(String str) {
        try {
            double num = Double.valueOf(str);
            return num;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 该方法将显示计算结果
     * 将栈中的数据显示出来，从底层开始
     *
     * @param stk
     */
    public static String displayStack(Stack<Double> stk) {
        StringBuilder sb = new StringBuilder();
        if (stk.size() != 0) {
            sb.append("stack:");
            for (Double x : stk) {
                sb.append(" ");
                sb.append(outputFormat(x));
            }
        } else {
            sb.append("stack:");
        }
        System.out.println(sb.toString());
        return sb.toString();
    }


    /**
     * 该方法设置运算结果的显示格式，最多显示10位精度
     *
     * @param value 运算结果
     */
    private static String outputFormat(double value) {
        DecimalFormat numformat = new DecimalFormat("##########.##########");
        String output = numformat.format(value);
        return output;
    }


}
