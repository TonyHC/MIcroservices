package com.tonyhc.payload;

import com.tonyhc.clients.album.AlbumResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserDetailsResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private List<AlbumResponse> albums;
}