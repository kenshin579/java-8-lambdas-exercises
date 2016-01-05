/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.madhusudhan.java8.article.interfaces.inheritance;

/**
 * @author mkonda
 */
public class InheritanceTest {

    interface Person {
        default String getName() {
            return "Person";
        }
    }

//    interface Faculty {
//        default public String getName() {
//            return "Faculty";
//        }
//    }

    interface Faculty extends Person {
        default public String getName() {
            return "Faculty";
        }
    }

    //The Student inherits Faculty's name rather than Person
    class Student implements Faculty, Person {

//        @Override
//        public String getName() {
////            return getName();
//            return Person.super.getName();
//        }
    }

    private void test() {
        String name = new Student().getName();
        System.out.println("Name: " + name);
    }

    public static void main(String[] args) {
        InheritanceTest test = new InheritanceTest();
        test.test();
    }
}
