package com.insightfullogic.java8.examples.chapter8.template_method;

public interface Company {

    public void checkIdentity() throws ApplicationDenied;

    public void checkProfitAndLoss() throws ApplicationDenied;

    public void checkHistoricalDebt() throws ApplicationDenied;

}
