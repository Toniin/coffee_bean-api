package com.coffee_bean.controllers;

import com.coffee_bean.SignInResponse;
import com.coffee_bean.dtos.SignInUserDto;
import com.coffee_bean.dtos.SignUpUserDto;
import com.coffee_bean.models.User;
import com.coffee_bean.services.AuthenticationService;
import com.coffee_bean.services.JwtServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final JwtServiceImp jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody SignUpUserDto signUpUserDto) {
        User registeredUser = authenticationService.signup(signUpUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> signin(@RequestBody SignInUserDto signInUserDto) {
        User authenticatedUser = authenticationService.authenticate(signInUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        SignInResponse signInResponse = new SignInResponse();
        signInResponse.setToken(jwtToken);
        signInResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(signInResponse);
    }
}
