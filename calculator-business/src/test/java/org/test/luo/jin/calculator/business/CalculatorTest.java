package org.test.luo.jin.calculator.business;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.test.luo.jin.calculator.common.exception.FormatException;
import org.test.luo.jin.calculator.common.util.CommonUtils;


/**
 * @author luo_j
 * @date 2019/7/26 6:15 PM
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculatorTest {

    @Autowired
    Calculator calculator;

    @Test
    public void exampleOneTest() throws FormatException {
        String expression = "5 2";
        calculator.calculate(expression);
        Assert.assertTrue(CommonUtils.displayStack(calculator.getNumsStack()).contentEquals("stack: 5 2"));
    }

    @Test
    public void exampleTwoTest() throws FormatException {
        String expression = "2 sqrt clear 9 sqrt";
        calculator.calculate(expression);
        Assert.assertTrue(CommonUtils.displayStack(calculator.getNumsStack()).contentEquals("stack: 3"));
    }

    @Test
    public void exampleThreeTest() throws FormatException {
        String expression = "5 2 - 3 - clear";
        calculator.calculate(expression);
        Assert.assertTrue(CommonUtils.displayStack(calculator.getNumsStack()).contentEquals("stack:"));
    }

    @Test
    public void exampleFourTest() throws FormatException {
        String expression = "5 4 3 2 undo undo * 5 * undo";
        calculator.calculate(expression);
        Assert.assertTrue(CommonUtils.displayStack(calculator.getNumsStack()).contentEquals("stack: 20 5"));
    }

    @Test
    public void exampleFiveTest() throws FormatException {
        String expression = "7 12 2 / * 4 /";
        calculator.calculate(expression);
        Assert.assertTrue(CommonUtils.displayStack(calculator.getNumsStack()).contentEquals("stack: 10.5"));
    }

    @Test
    public void exampleSixTest() throws FormatException {
        String expression = "1 2 3 4 5 * clear 3 4 -";
        calculator.calculate(expression);
        Assert.assertTrue(CommonUtils.displayStack(calculator.getNumsStack()).contentEquals("stack: -1"));
    }

    @Test
    public void exampleSevenTest() throws FormatException {
        String expression = "1 2 3 4 5 * * * *";
        calculator.calculate(expression);
        Assert.assertTrue(CommonUtils.displayStack(calculator.getNumsStack()).contentEquals("stack: 120"));
    }

    @Test
    public void exampleEightTest() throws FormatException {
        String expression = "1 2 3 * 5 + * * 6 5";
        calculator.calculate(expression);
        Assert.assertTrue(calculator.getLogErrorMessage().contains("operator*(position:15):insufficient parameters"));
    }


}
