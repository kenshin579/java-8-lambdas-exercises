package com.insightfullogic.java8.examples.chapter9;

import com.insightfullogic.java8.examples.chapter1.Album;
import com.insightfullogic.java8.examples.chapter1.Artist;
import com.insightfullogic.java8.examples.chapter1.Track;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableAlbumLookup implements AlbumLookup {

    private static final ExecutorService service = Executors.newFixedThreadPool(4);

    private final List<Track> tracks;
    private final List<Artist> artists;

    public CompletableAlbumLookup(List<Track> tracks, List<Artist> artists) {
        this.tracks = tracks;
        this.artists = artists;
    }

    /**
     * todo: 이 코드 완벽하게 이해되지 않음.
     *
     * @param albumName
     * @return
     */
    @Override
    public Album lookupByName(String albumName) {
        CompletableFuture<List<Artist>> artistLookup
                = loginTo("artist")
                .thenCompose(artistLogin -> lookupArtists(albumName, artistLogin));  // <1>
        //<1>: note: thenCompose 메소드는 로그인으로 얻은 자격 정보를 음악가를 포함하는 CompletableFuture로 반환함

        return loginTo("track")
                .thenCompose(trackLogin -> lookupTracks(albumName, trackLogin)) // <2>
                .thenCombine(artistLookup, (tracks, artists)
                        -> new Album(albumName, tracks, artists)) // <3>
                .join(); // <4>: note: Future.get() 대신에 결과값을
    }

    // Variables Exists to satisfy code sample below
    private Track track;
    private Artist artist;

    CompletableFuture<Track> lookupTrack(String id) {
        return CompletableFuture.supplyAsync(() -> {
            // Some expensive work is done here <1>
            // ...
            return track; // <2>
        }, service); // <3>
    }

    /**
     * 생성한 객체를 앞으로 수행할 많은 연산을 위해서 클라이언트 코드로 넘김
     *
     * @param id
     * @return
     */
    CompletableFuture<Artist> createFuture(String id) {
        CompletableFuture<Artist> future = new CompletableFuture<>();
        startJob(future);
        return future;
    }

    private void startJob(CompletableFuture<Artist> future) {
        //사용 가능한 값이 준비되었을 때 complete 메서드를 호출하여 CompleteableFuture 객체에 알림
        future.complete(artist);
    }

    private void processExceptionally(CompletableFuture<Album> future, String name) {
        future.completeExceptionally(new AlbumLookupException("Unable to find " + name));
    }

    // ----------------- FAKE LOOKUP METHODS -----------------
    //         Represent API lookup on external services

    private CompletableFuture<List<Artist>> lookupArtists(String albumName, Credentials credentials) {
        return CompletableFuture.completedFuture(artists);
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private CompletableFuture<List<Track>> lookupTracks(String albumName, Credentials credentials) {
        return CompletableFuture.supplyAsync(() -> {
            sleep(1000);
            return tracks;
        }, service);
    }

    private CompletableFuture<Credentials> loginTo(String serviceName) {
        return CompletableFuture.supplyAsync(() -> {
            if ("artist".equals(serviceName)) {
                sleep(1000);
            }
            return new Credentials();
        }, service);
    }

}
