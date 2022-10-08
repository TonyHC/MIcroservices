package com.tonyhc.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Username cannot be blank")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    private String password;
}