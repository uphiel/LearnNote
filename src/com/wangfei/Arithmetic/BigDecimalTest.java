package com.wangfei.Arithmetic;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2016/3/17.
 */
public class BigDecimalTest {
    public  static void main(String[] args){
        BigDecimal number1 = new BigDecimal("25.23");
        BigDecimal number2 = new BigDecimal(25);
        System.out.println("number1:" + number1);
        System.out.println("number1+number2:" + number1.add(number2));
        number1 = number1.add(number2);
        System.out.println("number1:" + number1);
        System.out.println("number1:我们" + number1);

    }
}