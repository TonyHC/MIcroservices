package com.tonyhc.error;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

@Component
public class FeignErrorDecoder implements ErrorDecoder {
    @Value("${album.exceptions.albums-not-found}")
    private String albumsNotFoundExceptionMessage;

    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()) {
            case 400:
                return new FeignException.BadRequest("", response.request(), response.request().body(), Collections.emptyMap());
            case 404:
                if (methodKey.contains("getUserAlbums")) {
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), albumsNotFoundExceptionMessage);
                }
            default:
                return new Exception(response.reason());
        }
    }
}
