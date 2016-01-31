package com.insightfullogic.java8.examples.chapter4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDefaultSubClassing {

    @Test
    public void parentDefaultUsed() {
        Parent parent = new ParentImpl();
        parent.welcome(); //Parent 인터페이스로부터 welcome 메서드를 상속받는다
        assertEquals("IParent: Hi!", parent.getLastMessage());
    }

    @Test
    public void childOverrideDefault() {
        Child child = new ChildImpl();
        child.welcome();
        assertEquals("IChild: Hi!", child.getLastMessage());
    }

    @Test
    public void concreteBeatsDefault() {
        //note: 더는 디폴트 메소드가 아닌 구체화한 메소드가 호출되는 코드
        Parent parent = new OverridingParent();
        parent.welcome();
        assertEquals("Class Parent: Hi!", parent.getLastMessage());
    }

    @Test
    public void concreteBeatsCloserDefault() {
        //note: 구체화 메서드가 디폴트 메서드보다 우선 호출되는 조금 더 자세한 코드
        Child child = new OverridingChild();
        child.welcome();
        assertEquals("Class Parent: Hi!", child.getLastMessage());
    }

}
