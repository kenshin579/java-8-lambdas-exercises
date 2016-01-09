package com.insightfullogic.java8.examples.chapter4;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestDefaultSubClassing {

    // BEGIN parent_default_used
    @Test
    public void parentDefaultUsed() {
        Parent parent = new ParentImpl();
        parent.welcome(); //Parent 인터페이스로부터 welcome 메서드를 상속받는다
        assertEquals("Parent: Hi!", parent.getLastMessage());
    }
    // END parent_default_used

    // BEGIN child_override_default
    @Test
    public void childOverrideDefault() {
        Child child = new ChildImpl();
        child.welcome();
        assertEquals("Child: Hi!", child.getLastMessage());
    }
    // END child_override_default

    // BEGIN concrete_beats_default
    @Test
    public void concreteBeatsDefault() {
        Parent parent = new OverridingParent(); //note: 더는 디폴트 메소드가 아닌 구체화한 메소드가 호출되는 코드
        parent.welcome();
        assertEquals("Class Parent: Hi!", parent.getLastMessage());
    }
    // END concrete_beats_default

    // BEGIN concrete_beats_closer_default
    @Test
    public void concreteBeatsCloserDefault() {
        Child child = new OverridingChild(); //note: 구체화 메서드가 디폴트 메서드보다 우선 호출되는 조금 더 자세한 코드
        child.welcome();
        assertEquals("Class Parent: Hi!", child.getLastMessage());
    }
    // END concrete_beats_closer_default

}
