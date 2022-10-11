package com.tonyhc.clients.album;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(
        name = "album",
        url = "${clients.album.url}"
)
public interface AlbumClient {
    @GetMapping("/api/v1/albums/{userId}")
    List<AlbumResponse> getUserAlbums(@PathVariable("userId") String userId);
}
