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
 * @date 2019/7/26 12:23 PM
 **/
@Component
public class UnaryOperatorRule implements CalculatorRules<Stack<BigDecimal>, Stack<List<BigDecimal>>, OperatorsEnums> {

    /**
     * 该方法封装了一元运算符计算法则,可在该方法中扩展操作符功能
     *
     * @param stk1 操作符栈
     * @param stk2 日志栈
     * @param opt  操作符
     * @throws FormatException
     */
    @Override
    public void rules(Stack<BigDecimal> stk1, Stack<List<BigDecimal>> stk2, OperatorsEnums opt) throws FormatException {
        BigDecimal num = stk1.pop();
        switch (opt) {
            case SQUARE:
                stk1.push(CommonUtils.sqrt(num, NumberConstant.NUMBER_15));
                stk2.push(CommonUtils.getStack(stk1));
                break;
            default:
                throw new FormatException("ERROR");
        }
    }
}
