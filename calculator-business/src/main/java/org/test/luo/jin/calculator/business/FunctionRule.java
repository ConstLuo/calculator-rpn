package org.test.luo.jin.calculator.business;

import org.springframework.stereotype.Component;
import org.test.luo.jin.calculator.common.constants.NumberConstant;
import org.test.luo.jin.calculator.common.exception.FormatException;
import org.test.luo.jin.calculator.model.OperatorsEnums;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author luo_j
 * @date 2019/7/26 1:04 PM
 **/
@Component
public class FunctionRule implements CalculatorRules<Stack<BigDecimal>, Stack<List<BigDecimal>>, OperatorsEnums> {
    @Override
    public void rules(Stack<BigDecimal> stk1, Stack<List<BigDecimal>> stk2, OperatorsEnums opt) throws FormatException {
        switch (opt) {
            case UNDO:
                handleUndoOpt(stk1, stk2);
                break;
            case CLEAR:
                handleClearOpt(stk1, stk2);
                break;
            default:
                throw new FormatException("ERROR");
        }
    }

    private void handleUndoOpt(Stack<BigDecimal> stk1, Stack<List<BigDecimal>> stk2) {
        while (!stk1.empty()) {
            stk1.pop();
        }
        if (!stk2.empty()) {
            stk2.pop();
            if (!stk2.empty()) {
                List<BigDecimal> list1 = stk2.peek();
                for (int i = NumberConstant.NUMBER_0; i < list1.size(); i++) {
                    if (list1.get(i) != null) {
                        stk1.push(list1.get(i));
                    }
                }
            }
        }
    }

    private void handleClearOpt(Stack<BigDecimal> stk1, Stack<List<BigDecimal>> stk2) {
        while (!stk1.empty()) {
            stk1.pop();
        }
        List<BigDecimal> list2 = new ArrayList<>();
        list2.add(null);
        stk2.push(list2);
    }
}
