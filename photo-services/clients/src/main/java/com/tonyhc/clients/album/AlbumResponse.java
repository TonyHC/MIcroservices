package com.tonyhc.clients.album;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AlbumResponse {
    private String albumId;
    private String userId;
    private String name;
    private String description;
}
