package org.test.luo.jin.calculator.business;

import org.springframework.stereotype.Component;
import org.test.luo.jin.calculator.common.exception.FormatException;
import org.test.luo.jin.calculator.common.util.CommonUtils;
import org.test.luo.jin.calculator.model.OperatorsEnums;

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

    private Stack<Double> numsStack = new Stack<>();

    private Stack<List<Double>> logsStack = new Stack<>();

    private String logErrorMessage;

    public Stack<Double> getNumsStack() {
        return numsStack;
    }

    public void setLogErrorMessage(String logErrorMessage) {
        this.logErrorMessage = logErrorMessage;
    }

    public String getLogErrorMessage() {
        return logErrorMessage;
    }

    /**
     * 计算结果
     *
     * @return 表达式计算结果
     * @throws FormatException
     */
    public void calculate(String expression) throws FormatException {
        if (expression == null || expression.length() < 0) {
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
        boolean flag = true;
        for (int i = 0; i < exp.length; i++) {
            if (flag) {
                Double temp = CommonUtils.strToDigit(exp[i]);
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
                            if (numsStack.size() > 0) {
                                unaryOperatorRule.rules(numsStack, logsStack, opt);
                            } else {
                                setLogErrorMessage("operator" + exp[i] + "(position:" + (2 * i - 1) + "):insufficient parameters ");
                                System.out.println("operator" + exp[i] + "(position:" + (2 * i - 1) + "):insufficient parameters ");
                                flag = false;
                            }
                            break;
                        case ADD:
                        case SUBTRACT:
                        case MULTIPLY:
                        case DIVISION:
                            if (numsStack.size() > 1) {
                                binaryOperatorRule.rules(numsStack, logsStack, opt);
                            } else {
                                setLogErrorMessage("operator" + exp[i] + "(position:" + (2 * i + 1) + "):insufficient parameters ");
                                System.out.println("operator" + exp[i] + "(position:" + (2 * i + 1) + "):insufficient parameters ");
                                flag = false;
                            }
                            break;
                        default:
                            throw new FormatException("输入的RPN表达式不合法！");
                    }
                }
            }
        }
    }
}
