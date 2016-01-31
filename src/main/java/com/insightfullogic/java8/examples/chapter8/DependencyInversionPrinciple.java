package com.insightfullogic.java8.examples.chapter8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class DependencyInversionPrinciple {

    public static interface HeadingFinder {
        public List<String> findHeadings(Reader reader);
    }

    public static class NoDIP implements HeadingFinder {
        /**
         * 문제: 문장을 분석하는 코드와 파일을 다루는 메서드로 분리해야 함
         * 해결방법 읽는 부분과 쓰는 부분 모두 추상화 하고 통합 모듈은 이러한 추상화에 의존적으로 작성함
         * - 추상화를 적용하면 실행 시간에 특정 동작을 한느 구현부를 전달할 수 있다.
         *
         * @param input
         * @return
         */
        public List<String> findHeadings(Reader input) {
            try (BufferedReader reader = new BufferedReader(input)) {
                return reader.lines()
                        .filter(line -> line.endsWith(":"))
                        .map(line -> line.substring(0, line.length() - 1))
                        .collect(toList());
            } catch (IOException e) {
                throw new HeadingLookupException(e);
            }
        }
    }

    public static class ExtractedDIP implements HeadingFinder {
        public List<String> findHeadings(Reader input) {
            return withLinesOf(input,
                    lines -> lines.filter(line -> line.endsWith(":"))
                            .map(line -> line.substring(0, line.length() - 1))
                            .collect(toList()),
                    HeadingLookupException::new);
        }

        private <T> T withLinesOf(Reader input,
                                  Function<Stream<String>, T> handler,
                                  Function<IOException, RuntimeException> error) {
            try (BufferedReader reader = new BufferedReader(input)) {
                return handler.apply(reader.lines());
            } catch (IOException e) {
                throw error.apply(e);
            }
        }

    }

}
