package com.coffee_bean.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInUserDto {
    private String email;
    private String password;
}
