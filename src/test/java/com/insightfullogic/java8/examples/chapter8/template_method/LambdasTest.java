package com.insightfullogic.java8.examples.chapter8.template_method;

import com.insightfullogic.java8.examples.chapter8.template_method.lambdas.Company;
import com.insightfullogic.java8.examples.chapter8.template_method.lambdas.CompanyLoanApplication;
import org.junit.Test;

public class LambdasTest {

    @Test
    public void fitsTogether() throws ApplicationDenied {
        new CompanyLoanApplication(new Company()).checkLoanApplication();
    }
}