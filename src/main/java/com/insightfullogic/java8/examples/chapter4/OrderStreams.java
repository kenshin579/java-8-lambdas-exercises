package com.insightfullogic.java8.examples.chapter4;


import com.insightfullogic.java8.examples.chapter1.Album;

import java.util.List;

public class OrderStreams extends Order {

    public OrderStreams(List<Album> albums) {
        super(albums);
    }

    // BEGIN body
    public long countRunningTime() {
        return albums.stream()
                .mapToLong(album -> album.getTracks()
                        .mapToLong(track -> track.getLength()) //note: 특정의 함수의 수를 세는 메서드를 추상화하기 어려움
                        .sum())
                .sum();
    }

    public long countMusicians() {
        return albums.stream()
                .mapToLong(album -> album.getMusicians().count()) //note: 특정의 함수의 수를 세는 메서드를 추상화하기 어려움
                .sum();
    }

    public long countTracks() {
        return albums.stream()
                .mapToLong(album -> album.getTracks().count()) //note: 특정의 함수의 수를 세는 메서드를 추상화하기 어려움
                .sum();
    }
    // END body

}
