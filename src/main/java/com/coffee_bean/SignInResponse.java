package com.coffee_bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponse {
    private String token;
    private long expiresIn;
}
