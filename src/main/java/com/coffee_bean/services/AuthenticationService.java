package com.coffee_bean.services;

import com.coffee_bean.dtos.SignInUserDto;
import com.coffee_bean.dtos.SignUpUserDto;
import com.coffee_bean.models.Role;
import com.coffee_bean.models.RoleEnum;
import com.coffee_bean.models.User;
import com.coffee_bean.repositories.RoleRepository;
import com.coffee_bean.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public User signup(SignUpUserDto input) {
        Optional<Role> optionalRole;

        if (Role.isValueOfRoleEnum(input.getRole())) {
            optionalRole = roleRepository.findByName(RoleEnum.valueOf(input.getRole()));
        } else {
            optionalRole = roleRepository.findByName(RoleEnum.USER);
        }

        var user = new User();
        user.setFullName(input.getFullName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setRole(optionalRole.get());

        return userRepository.save(user);
    }

    public User authenticate(SignInUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
