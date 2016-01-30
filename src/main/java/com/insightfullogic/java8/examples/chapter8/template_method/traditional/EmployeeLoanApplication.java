package com.insightfullogic.java8.examples.chapter8.template_method.traditional;

public class EmployeeLoanApplication extends PersonalLoanApplication {

    @Override
    protected void checkIncomeHistory() {
        // They work for us!
        System.out.println(this.getClass().getName() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

}
