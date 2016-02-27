package com.madhusudhan.java8.article.methodrefs;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.Supplier;

/**
 * Created by ykoh on 2016. 2. 27..
 */
public class MethodRefExample {
    public int add(int a, int b) {
        return a + b;
    }

    public static int mul(int a, int b) {
        return a * b;
    }

    public String lower(String a) {
        return a.toLowerCase();
    }

    public void printDate(Date date) {
        System.out.println(date);
    }

    public void oper(IntBinaryOperator operator, int a, int b) {
        System.out.println(operator.applyAsInt(a, b));
    }

    public void operS(Function<String, String> stringOperator, String a) {
        System.out.println(stringOperator.apply(a));
    }

    public GregorianCalendar operC(Supplier<GregorianCalendar> supplier) {
        return supplier.get();
    }

    public static void main(String[] args) {
        MethodRefExample ex = new MethodRefExample();

        //1.Referencing static methods using Class Name
        ex.oper((a, b) -> MethodRefExample.mul(a, b), 1, 1);
        ex.oper(MethodRefExample::mul, 1, 2);

        //2. Referencing Instance methods using Object Instance
        ex.oper((a, b) -> ex.add(a, b), 1, 2);
        ex.oper(ex::add, 1, 2);

        //3. Referencing Instance methods using Class Name
        ex.operS(s -> s.toLowerCase(), "STRING");
        ex.operS(String::toLowerCase, "STRING");

        //4. Referencing the constructor
        ex.operC(() -> {
            return new GregorianCalendar();
        });
        ex.operC(GregorianCalendar::new);
    }
}
