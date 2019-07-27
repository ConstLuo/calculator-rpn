package org.test.luo.jin.calculator.model;

/**
 * @author luo_j
 * @date 2019/7/26 5:35 PM
 **/
public enum OperatorsEnums {
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("*"),
    DIVISION("/"),
    SQUARE("sqrt"),
    UNDO("undo"),
    CLEAR("clear"),
    UNKNOW("unknow");

    private String operators;


    OperatorsEnums(String operators) {
        this.operators = operators;
    }

    public static OperatorsEnums convert(String v) {
        switch (v) {
            case "+":
                return ADD;
            case "-":
                return SUBTRACT;
            case "*":
                return MULTIPLY;
            case "/":
                return DIVISION;
            case "sqrt":
                return SQUARE;
            case "undo":
                return UNDO;
            case "clear":
                return CLEAR;
            default:
                return UNKNOW;

        }
    }

}
