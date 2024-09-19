package com.yourcaryourway.backend.service;

import com.yourcaryourway.backend.dto.LoginDTO;
import com.yourcaryourway.backend.dto.UserDTO;
import com.yourcaryourway.backend.model.User;
import com.yourcaryourway.backend.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    public UserService(UserRepository userRepository, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
    }

    public Authentication authenticate(LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmailOrUsername());
        if (user == null) {
            user = userRepository.findByUsername(loginDTO.getEmailOrUsername());
        }
        if (user == null) {
            logger.error("User not found: {}", loginDTO.getEmailOrUsername());
            throw new UsernameNotFoundException("Invalid email or username");
        }
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), loginDTO.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            user.setOnline(true);
            userRepository.save(user);
            return authentication;
        } catch (AuthenticationException e) {
            logger.error("Authentication failed for user: {}", loginDTO.getEmailOrUsername());
            throw new BadCredentialsException("Invalid password");
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<String> getOnlineUsers() {
        return userRepository.findByOnline(true)
                .stream()
                .map(User::getUsername)
                .collect(Collectors.toList());
    }

    public UserDTO getUserProfile(String identifier) {
        User user = userRepository.findByEmail(identifier);
        if (user == null) {
            user = userRepository.findByUsername(identifier);
        }
        if (user == null) {
            logger.error("User profile not found: {}", identifier);
            throw new UsernameNotFoundException("User not found");
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

    public void logout(String username) {
        User user = userRepository.findByUsername(username);
        user.setOnline(false);
        userRepository.save(user);
    }
}
