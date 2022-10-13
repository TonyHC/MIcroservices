package com.tonyhc.controllers;

import com.tonyhc.clients.album.AlbumResponse;
import com.tonyhc.domain.Album;
import com.tonyhc.service.AlbumService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/albums")
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping(value = "{userId}",
            produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public List<AlbumResponse> getUserAlbums(@PathVariable("userId") String userId) {
        List<Album> albums = albumService.getAlbums(userId);

        if ( albums == null || albums.isEmpty()) {
            return Collections.emptyList();
        }

        Type listOfAlbumResponseType = new TypeToken<List<AlbumResponse>>(){}.getType();

        return new ModelMapper().map(albums, listOfAlbumResponseType);
    }
}
