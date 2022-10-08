package com.tonyhc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -2959781262193398538L;

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
}