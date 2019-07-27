package domain;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.test.luo.jin.calculator.business.Calculator;
import org.test.luo.jin.calculator.common.exception.FormatException;

import java.util.Scanner;

@SpringBootApplication(scanBasePackages = {
        "org.test.luo.jin.calculator.model",
        "org.test.luo.jin.calculator.common",
        "org.test.luo.jin.calculator.business"})
public class CalculatorApplication {
	public static void main(String[] args) {
		System.out.print("输入RPN表达式：");
		Scanner scanner = new Scanner(System.in);
		String express = scanner.nextLine();
		Calculator calculator = new Calculator();
		try {
			calculator.calculate(express);
		} catch (FormatException e) {
			e.printStackTrace();
		}
	}
}


