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
 * @date 2019/7/26 10:52 AM
 **/
@Component
public class Calculator {

    private UnaryOperatorRule unaryOperatorRule = new UnaryOperatorRule();

    private BinaryOperatorRule binaryOperatorRule = new BinaryOperatorRule();

    private FunctionRule functionRule = new FunctionRule();

    private Stack<BigDecimal> numsStack = new Stack<>();

    private Stack<List<BigDecimal>> logsStack = new Stack<>();

    private String logErrorMessage;

    public Stack<BigDecimal> getNumsStack() {
        return numsStack;
    }

    public void setLogErrorMessage(String logErrorMessage) {
        this.logErrorMessage = logErrorMessage;
    }

    public String getLogErrorMessage() {
        return logErrorMessage;
    }

    private static boolean flag = true;

    /**
     * 计算结果
     *
     * @throws FormatException
     */
    public void calculate(String expression) throws FormatException {
        if (expression == null || expression.length() == NumberConstant.NUMBER_0) {
            return;
        }
        if (!numsStack.empty()) {
            numsStack.pop();
        }
        handleCalculate(expression);
        CommonUtils.displayStack(numsStack);
    }

    /**
     * 处理计算过程
     *
     * @param expression
     * @throws FormatException
     */
    private void handleCalculate(String expression) throws FormatException {
        String[] exp = expression.split(" ");
        for (int i = NumberConstant.NUMBER_0; i < exp.length; i++) {
            if (flag) {
                BigDecimal temp = CommonUtils.strToDigit(exp[i]);
                if (temp != null) {
                    numsStack.push(temp);
                    logsStack.push(CommonUtils.getStack(numsStack));
                } else {
                    OperatorsEnums opt = OperatorsEnums.convert(exp[i]);
                    switch (opt) {
                        case UNDO:
                        case CLEAR:
                            functionRule.rules(numsStack, logsStack, opt);
                            break;
                        case SQUARE:
                            handleUnaryOptCalculate(opt, exp, i);
                            break;
                        case ADD:
                        case SUBTRACT:
                        case MULTIPLY:
                        case DIVISION:
                            handleBinaryOptCalculate(opt, exp, i);
                            break;
                        default:
                            throw new FormatException("输入的RPN表达式不合法！");
                    }
                }
            }
        }
    }

    /**
     * 处理 一元运算符计算法则
     *
     * @param opt
     * @param exp
     * @param i
     * @throws FormatException
     */
    private void handleUnaryOptCalculate(OperatorsEnums opt, String[] exp, int i) throws FormatException {
        if (numsStack.size() > NumberConstant.NUMBER_0) {
            unaryOperatorRule.rules(numsStack, logsStack, opt);
        } else {
            setLogErrorMessage("operator" + exp[i] + "(position:" +
                    (NumberConstant.NUMBER_2 * i - NumberConstant.NUMBER_1) + "):insufficient parameters ");
            System.out.println("operator" + exp[i] + "(position:" +
                    (NumberConstant.NUMBER_2 * i - NumberConstant.NUMBER_1) + "):insufficient parameters ");
            flag = false;
        }
    }

    /**
     * 处理 二元运算符计算法则
     *
     * @param opt
     * @param exp
     * @param i
     * @throws FormatException
     */
    private void handleBinaryOptCalculate(OperatorsEnums opt, String[] exp, int i) throws FormatException {
        if (numsStack.size() > NumberConstant.NUMBER_1) {
            binaryOperatorRule.rules(numsStack, logsStack, opt);
        } else {
            setLogErrorMessage("operator" + exp[i] + "(position:" +
                    (NumberConstant.NUMBER_2 * i + NumberConstant.NUMBER_1) + "):insufficient parameters ");
            System.out.println("operator" + exp[i] + "(position:" +
                    (NumberConstant.NUMBER_2 * i + NumberConstant.NUMBER_1) + "):insufficient parameters ");
            flag = false;
        }
    }
}
