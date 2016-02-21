package com.insightfullogic.java8.examples.chapter4;

import java.util.function.Supplier;

public class Logger {

    private boolean debug = true;

    public boolean isDebugEnabled() {
        return debug;
    }

    public void debug(String message) {
        if (isDebugEnabled()) {
            System.out.println(message);
        }
    }

    public void example() {
        Logger logger = new Logger();
        if (logger.isDebugEnabled()) { //note: if가 없으면 expensiveOpeation()가 실행이 됨
            logger.debug("Look at this: " + expensiveOperation());
        }
    }

    private String expensiveOperation() {
        return "expensiveOperation";
    }


    public void debug(Supplier<String> message) {
        if (isDebugEnabled()) {
            debug(message.get()); //note: expensiveOperation()이 여기서만 실행됨
        }
    }

    public void exampleWithLambda() {
        Logger logger = new Logger();
        logger.debug(() -> "Look at this: " + expensiveOperation()); //note: 여기 expensiveOperation()는 실행이 안됨.
        
    }

    public static void main(String[] args) {
        Logger logger = new Logger();

        logger.example();
        logger.exampleWithLambda();
    }

}
