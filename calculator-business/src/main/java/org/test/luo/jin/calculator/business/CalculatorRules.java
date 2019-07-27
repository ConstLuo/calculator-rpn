package org.test.luo.jin.calculator.business;


import org.test.luo.jin.calculator.common.exception.FormatException;

/**
 * @author luo_j
 * @date 2019/7/26 12:17 PM
 **/
public interface CalculatorRules<P1, P2, P3> {
    void rules(P1 p1, P2 p2, P3 p3) throws FormatException;
}
