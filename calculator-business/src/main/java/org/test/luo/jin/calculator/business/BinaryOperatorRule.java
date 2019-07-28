package org.test.luo.jin.calculator.business;

import org.springframework.stereotype.Component;
import org.test.luo.jin.calculator.common.constants.NumberConstant;
import org.test.luo.jin.calculator.common.exception.FormatException;
import org.test.luo.jin.calculator.common.util.CommonUtils;
import org.test.luo.jin.calculator.model.OperatorsEnums;

import java.math.BigDecimal;
import java.util.List;
import java.util.Stack;

/**
 * @author luo_j
 * @date 2019/7/26 12:46 PM
 **/
@Component
public class BinaryOperatorRule implements CalculatorRules<Stack<BigDecimal>, Stack<List<BigDecimal>>, OperatorsEnums> {

    /**
     * 方法封装了二元运算符计算法则，可在此方法中增加更多操作符，实现动态扩展。
     *
     * @param stk1 操作符栈
     * @param stk2 日志栈
     * @param opt  操作符
     * @throws FormatException
     */
    @Override
    public void rules(Stack<BigDecimal> stk1, Stack<List<BigDecimal>> stk2, OperatorsEnums opt) throws FormatException {
        BigDecimal num2= stk1.pop();
        BigDecimal num1 = stk1.pop();
        switch (opt) {
            case ADD:
                stk1.push(num1.add(num2));
                stk2.push(CommonUtils.getStack(stk1));
                break;
            case SUBTRACT:
                stk1.push(num1.subtract(num2));
                stk2.push(CommonUtils.getStack(stk1));
                break;
            case MULTIPLY:
                stk1.push(num1.multiply(num2));
                stk2.push(CommonUtils.getStack(stk1));
                break;
            case DIVISION:
                stk1.push(num1.divide(num2, NumberConstant.NUMBER_15, BigDecimal.ROUND_HALF_UP));
                stk2.push(CommonUtils.getStack(stk1));
                break;
            default:
                throw new FormatException("ERROR");

        }
    }
}
