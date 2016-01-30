package com.insightfullogic.java8.examples.chapter8.template_method.traditional;

public class PersonalLoanApplication extends LoanApplication {

    @Override
    protected void checkIdentity() {
        System.out.println(this.getClass().getName() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Override
    protected void checkIncomeHistory() {
        System.out.println(this.getClass().getName() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @Override
    protected void checkCreditHistory() {
        System.out.println(this.getClass().getName() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}
