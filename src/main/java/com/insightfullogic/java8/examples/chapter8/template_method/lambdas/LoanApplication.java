package com.insightfullogic.java8.examples.chapter8.template_method.lambdas;

import com.insightfullogic.java8.examples.chapter8.template_method.ApplicationDenied;

public class LoanApplication {

    private final Criteria identity;
    private final Criteria creditHistory;
    private final Criteria incomeHistory;

    public LoanApplication(Criteria identity,
                           Criteria creditHistory,
                           Criteria incomeHistory) {

        this.identity = identity;
        this.creditHistory = creditHistory;
        this.incomeHistory = incomeHistory;
    }

    /**
     * 전체 알고리즘을 담고 있다.
     *
     * @throws ApplicationDenied
     */
    public void checkLoanApplication() throws ApplicationDenied {
        identity.check();
        creditHistory.check();
        incomeHistory.check();
        reportFindings();
    }

    private void reportFindings() {
    }
}
