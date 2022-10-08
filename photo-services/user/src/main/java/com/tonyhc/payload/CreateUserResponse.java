package com.tonyhc.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CreateUserResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
}