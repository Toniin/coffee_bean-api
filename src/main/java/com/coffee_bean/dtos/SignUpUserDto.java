package com.coffee_bean.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpUserDto {
    private String email;
    private String password;
    private String fullName;
    private String role;
}
