package com.tonyhc.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Album {
    private Long id;
    private String albumId;
    private String userId;
    private String name;
    private String description;
}
