package com.tonyhc.payload;

import com.tonyhc.validator.MatchingPassword;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@MatchingPassword.List({@MatchingPassword(first = "password", second = "confirmPassword")})
public class CreateUserRequest {
    @NotNull(message = "First name cannot be null")
    @Size(min = 2, message = "First name cannot be less than two characters")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, message = "Last name cannot be less than two characters")
    private String lastName;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 16, message = "The password must be at least 8 characters and less than or equal to 16 characters")
    private String password;

    @NotNull(message = "Confirm Password cannot be null")
    @Size(min = 8, max = 16, message = "The confirm password must be at least 8 characters and less than or equal to 16 characters")
    private String confirmPassword;

    @NotNull(message = "Email cannot be null")
    @Email
    private String email;
}