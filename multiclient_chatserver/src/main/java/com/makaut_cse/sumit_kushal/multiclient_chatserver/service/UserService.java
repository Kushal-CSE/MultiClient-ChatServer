package com.makaut_cse.sumit_kushal.multiclient_chatserver.service;

import com.makaut_cse.sumit_kushal.multiclient_chatserver.dto.LoginDto;
import com.makaut_cse.sumit_kushal.multiclient_chatserver.dto.SignUpDto;
import com.makaut_cse.sumit_kushal.multiclient_chatserver.dto.UserDto;
import com.makaut_cse.sumit_kushal.multiclient_chatserver.entity.User;
import com.makaut_cse.sumit_kushal.multiclient_chatserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository
                .findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User with the email " + username + " not found"));
    }

    public User getUserByUserId(Long userId)
            throws UsernameNotFoundException {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "User with the Id " + userId + " not found"));
    }

    public UserDto sighup(SignUpDto signUpDto) {
        Optional<User> user =  userRepository.findUserByEmail(signUpDto.getEmail());
        if(user.isPresent()){
            throw new BadCredentialsException("User with the email " + signUpDto.getEmail() + " already exists");
        }
        User toBeCreatedUser = modelMapper.map(signUpDto, User.class);
        toBeCreatedUser.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        User savedUser = userRepository.save(toBeCreatedUser);
        return modelMapper.map(savedUser, UserDto.class);
    }


}
