package com.insightfullogic.java8.examples.chapter8.lambdabehave;

import com.insightfullogic.java8.examples.chapter8.lambdabehave.example.StackSpec;
import com.insightfullogic.java8.examples.chapter8.lambdabehave.reporting.*;

/**
 * todo: enum Runner는 뭐하는 역할을 하나?
 */
public enum Runner {

    current; //todo: 이건 뭔가?

    private final Report report;

    Runner() {
        report = new Report();
    }

    void recordSuccess(String suite, String specification) {
        report.newSpecification(suite, new SpecificationReport(specification));
    }

    void recordFailure(String suite, String specification, AssertionError cause) {
        SpecificationReport specificationReport = new SpecificationReport(specification, Result.FAILURE, cause.getMessage());
        report.newSpecification(suite, specificationReport);
    }

    void recordError(String suite, String specification, Throwable cause) {
        cause.printStackTrace();
        SpecificationReport specificationReport = new SpecificationReport(specification, Result.ERROR, cause.getMessage());
        report.newSpecification(suite, specificationReport);
    }

    private void printReport() {
        ReportFormatter formatter = new ConsoleFormatter();
        formatter.format(report);
    }

    private void run(Class<StackSpec> stackSpecClass) {
        try {
            stackSpecClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        current.run(StackSpec.class);
        current.printReport();
    }

}
