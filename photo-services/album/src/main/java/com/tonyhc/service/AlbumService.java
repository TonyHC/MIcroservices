package com.tonyhc.service;

import com.tonyhc.domain.Album;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlbumService {
    public List<Album> getAlbums(String userId) {
        List<Album> albums = new ArrayList<>();

        Album album = Album.builder()
                .albumId("albumId")
                .userId(userId)
                .id(1L)
                .description("Album 1 description")
                .name("Album 1 name")
                .build();

        Album albumTwo = Album.builder()
                .albumId("album2Id")
                .userId(userId)
                .id(2L)
                .description("Album 2 description")
                .name("Album 2 name")
                .build();

        albums.add(album);
        albums.add(albumTwo);

        return albums;
    }
}