package com.makaut_cse.sumit_kushal.multiclient_chatserver.controller;


import com.makaut_cse.sumit_kushal.multiclient_chatserver.dto.LoginDto;
import com.makaut_cse.sumit_kushal.multiclient_chatserver.dto.SignUpDto;
import com.makaut_cse.sumit_kushal.multiclient_chatserver.dto.UserDto;
import com.makaut_cse.sumit_kushal.multiclient_chatserver.service.AuthService;
import com.makaut_cse.sumit_kushal.multiclient_chatserver.service.JwtService;
import com.makaut_cse.sumit_kushal.multiclient_chatserver.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody SignUpDto signUpDto) {
        UserDto userDto = userService.sighup(signUpDto);
        return ResponseEntity.ok(userDto);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletResponse response){
        String token = authService.login(loginDto);

        Cookie cookie  = new Cookie("token", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }

}
