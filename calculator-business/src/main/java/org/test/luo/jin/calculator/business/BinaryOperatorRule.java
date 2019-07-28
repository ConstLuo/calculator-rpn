package org.test.luo.jin.calculator.business;

import org.springframework.stereotype.Component;
import org.test.luo.jin.calculator.common.exception.FormatException;
import org.test.luo.jin.calculator.common.util.CommonUtils;
import org.test.luo.jin.calculator.model.OperatorsEnums;

import java.util.List;
import java.util.Stack;

/**
 * @author luo_j
 * @date 2019/7/26 12:46 PM
 **/
@Component
public class BinaryOperatorRule implements CalculatorRules<Stack<Double>, Stack<List<Double>>, OperatorsEnums> {

    /**
     * 方法封装了二元运算符计算法则，可在此方法中增加更多操作符，实现动态扩展。
     *
     * @param stk1 操作符栈
     * @param stk2 日志栈
     * @param opt  操作符
     * @throws FormatException
     */
    @Override
    public void rules(Stack<Double> stk1, Stack<List<Double>> stk2, OperatorsEnums opt) throws FormatException {
        double num2 = stk1.pop();
        double num1 = stk1.pop();
        switch (opt) {
            case ADD:
                stk1.push(num1 + num2);
                stk2.push(CommonUtils.getStack(stk1));
                break;
            case SUBTRACT:
                stk1.push(num1 - num2);
                stk2.push(CommonUtils.getStack(stk1));
                break;
            case MULTIPLY:
                stk1.push(num1 * num2);
                stk2.push(CommonUtils.getStack(stk1));
                break;
            case DIVISION:
                stk1.push(CommonUtils.div(num1, num2));
                stk2.push(CommonUtils.getStack(stk1));
                break;
            default:
                throw new FormatException("ERROR");
        }
    }
}
