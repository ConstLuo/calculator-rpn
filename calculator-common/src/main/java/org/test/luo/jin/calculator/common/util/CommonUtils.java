package org.test.luo.jin.calculator.common.util;

import org.test.luo.jin.calculator.common.constants.NumberConstant;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
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
    public static List<BigDecimal> getStack(Stack<BigDecimal> stk) {
        List<BigDecimal> getStk = new ArrayList<>();
        for (BigDecimal x : stk) {
            getStk.add(x);
        }
        return getStk;
    }

    /**
     * 该方法将字符串转换为数字类型
     *
     * @param str
     */
    public static BigDecimal strToDigit(String str) {
        try {
            BigDecimal num = new BigDecimal(str);
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
    public static String displayStack(Stack<BigDecimal> stk) {
        StringBuilder sb = new StringBuilder();
        if (stk != null && stk.size() != NumberConstant.NUMBER_0) {
            sb.append("stack:");
            for (BigDecimal x : stk) {
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
    private static String outputFormat(BigDecimal value) {
        double tempVale = value.doubleValue();
        DecimalFormat numformat = new DecimalFormat("##########.##########");
        String output = numformat.format(tempVale);
        return output;
    }

    /**
     * 牛顿迭代法 开平方
     *
     * @param value
     * @param scale
     * @return
     */
    public static BigDecimal sqrt(BigDecimal value, int scale) {
        BigDecimal num2 = BigDecimal.valueOf(NumberConstant.NUMBER_2);
        int precision = NumberConstant.NUMBER_100;
        MathContext mc = new MathContext(precision, RoundingMode.HALF_UP);
        BigDecimal deviation = value;
        int cnt = NumberConstant.NUMBER_0;
        while (cnt < precision) {
            deviation = (deviation.add(value.divide(deviation, mc))).divide(num2, mc);
            cnt++;
        }
        deviation = deviation.setScale(scale, BigDecimal.ROUND_HALF_UP);
        return deviation;
    }

}
