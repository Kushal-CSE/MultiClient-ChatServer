package com.makaut_cse.sumit_kushal.multiclient_chatserver.service;

import com.makaut_cse.sumit_kushal.multiclient_chatserver.dto.LoginDto;
import com.makaut_cse.sumit_kushal.multiclient_chatserver.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        return token;
    }

}
