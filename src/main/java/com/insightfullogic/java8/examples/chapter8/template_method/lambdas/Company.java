package com.insightfullogic.java8.examples.chapter8.template_method.lambdas;

import com.insightfullogic.java8.examples.chapter8.template_method.ApplicationDenied;

public class Company {

    public void checkIdentity() throws ApplicationDenied {
        System.out.println(this.getClass().getName() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public void checkProfitAndLoss() throws ApplicationDenied {
        System.out.println(this.getClass().getName() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    public void checkHistoricalDebt() throws ApplicationDenied {
        System.out.println(this.getClass().getName() + ":" + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

}
