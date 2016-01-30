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

    /**
     * todo: 이 코드 잘 이해가 안됨.
     */
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
