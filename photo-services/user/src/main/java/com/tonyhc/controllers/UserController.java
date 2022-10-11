package com.tonyhc.controllers;

import com.tonyhc.dto.UserDTO;
import com.tonyhc.payload.CreateUserRequest;
import com.tonyhc.payload.CreateUserResponse;
import com.tonyhc.payload.UserDetailsResponse;
import com.tonyhc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/users")
@RefreshScope
@Slf4j
public class UserController {
    @Value("${dummy.secret}")
    private String dummySecret;

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getStatus() {
        return "Good secret: " + dummySecret;
    }
    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest userDetails) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDTO userDto = modelMapper.map(userDetails, UserDTO.class);
        UserDTO createdUser = userService.createUser(userDto);
        CreateUserResponse createUserResponse = modelMapper.map(createdUser, CreateUserResponse.class);

        return new ResponseEntity<>(createUserResponse, HttpStatus.CREATED);
    }

    @GetMapping(value = "{userId}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserDetailsResponse> getUserDetails(@PathVariable("userId") String userId) {
        UserDTO userDTO = userService.getUserByUserId(userId);
        UserDetailsResponse userDetailsResponse = new ModelMapper().map(userDTO, UserDetailsResponse.class);
        return new ResponseEntity<>(userDetailsResponse, HttpStatus.OK);
    }
}