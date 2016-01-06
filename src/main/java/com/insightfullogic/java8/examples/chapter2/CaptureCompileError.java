package com.insightfullogic.java8.examples.chapter2;

import javax.swing.*;

public class CaptureCompileError {

    private JButton button;

    public void error() {
        String name = getUserName();
        name = formatUserName(name);
        // Uncommenting this line should cause a compile error:
        //실질적 final 변수의 잘못된 사둉으로 컴파일 에러가 발생하는 코드
//        button.addActionListener(event -> System.out.println("hi " + name));
    }

    private String formatUserName(String name) {
        return name.toLowerCase();
    }

    private String getUserName() {
        return "RICHARD";
    }
}
