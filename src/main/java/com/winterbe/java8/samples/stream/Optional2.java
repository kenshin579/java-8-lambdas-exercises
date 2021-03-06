package com.winterbe.java8.samples.stream;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Examples how to avoid null checks with Optional:
 * <p/>
 * http://winterbe.com/posts/2015/03/15/avoid-null-checks-in-java/
 *
 * @author Benjamin Winterberg
 */
public class Optional2 {

    static class Outer {
        Nested nested = new Nested();

        public Nested getNested() {
            return nested;
        }
    }

    static class Nested {
        //        Inner inner = new Inner();
        Inner inner = null;

        public Inner getInner() {
            return inner;
        }
    }

    static class Inner {
        String foo = "boo";
//        String foo = null;

        public String getFoo() {
            return foo;
        }
    }

    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    private static void test3() {
        Outer outer = new Outer();
        resolve(() -> outer.getNested().getInner().getFoo())
                .ifPresent(System.out::println);
    }

    private static void test2() {
        Optional.of(new Outer())
                .map(Outer::getNested)
                .map(Nested::getInner)
                .map(Inner::getFoo)
                .ifPresent(System.out::println);
    }

    /**
     * Each call to flatMap은 Optional을 반환한다
     * - 객체가 있으면 wrapped해서 아니면 null을 반환함
     */
    private static void test1() {
        Optional.of(new Outer())
                .flatMap(o -> Optional.ofNullable(o.nested))
                .flatMap(n -> Optional.ofNullable(n.inner))
                .flatMap(i -> Optional.ofNullable(i.foo))
                .ifPresent(System.out::println);
    }

    /**
     * NullPointerExceptions이 일어날 수 있도 있어서 null checks를 여러번 해줘야 함
     */
    private static void test0() {
        Outer outer = new Outer();
        if (outer != null && outer.nested != null && outer.nested.inner != null) {
            System.out.println(outer.nested.inner.foo);
        }
    }

    public static void main(String[] args) {
//        test0();
        test1();
//        test2();
//        test3();
    }
}
