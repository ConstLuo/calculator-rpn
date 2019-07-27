package org.test.luo.jin.calculator.business;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author luo_j
 * @date 2019/7/28 12:26 AM
 **/
@SpringBootApplication(scanBasePackages = {
        "org.test.luo.jin.calculator.model",
        "org.test.luo.jin.calculator.common",
        "org.test.luo.jin.calculator.business"})
public class Application {
    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    }


}
