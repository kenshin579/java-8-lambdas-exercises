package com.insightfullogic.java8.examples.chapter8.lambdabehave;

import com.insightfullogic.java8.examples.chapter8.lambdabehave.expectations.Expect;

public final class Description {

    private final String suite;

    Description(String suite) {
        this.suite = suite;
    }

    public void should(String description, Specification specification) {
        try {
            Expect expect = new Expect();
            specification.specifyBehaviour(expect);
            Runner.current.recordSuccess(suite, description);
        } catch (AssertionError cause) {
            //Specification이 실패하면 자바 표준 AssertionError 예외를 발생시키며, 이를 테스트 실패로 처리한다
            //assertEquals할때 오류가 발생하면 여기서 catch를 해서 처리함
            System.out.println("AssertionError: recordFailure");
            Runner.current.recordFailure(suite, description, cause);
        } catch (Throwable cause) {
            System.out.println("AssertionError: Throwable");
            Runner.current.recordError(suite, description, cause);
        }
    }
}
