package com.yourcaryourway.backend.controller;

import com.yourcaryourway.backend.dto.LoginDTO;
import com.yourcaryourway.backend.dto.UserDTO;
import com.yourcaryourway.backend.service.JwtService;
import com.yourcaryourway.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import com.yourcaryourway.backend.model.User;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @GetMapping("/online-users")
    public List<String> getOnlineUsers() {
        return userService.getOnlineUsers();
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/profile")
    public ResponseEntity<UserDTO> getProfile(Authentication authentication) {
        String identifier = authentication.getName();
        logger.debug("Fetching profile for: {}", identifier);
        UserDTO userDTO = userService.getUserProfile(identifier);
        logger.debug("Fetching profile for dto: {}", userDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
        try {
            logger.debug("Login attempt for user: {}", loginDTO.getEmailOrUsername());
            Authentication authentication = userService.authenticate(loginDTO);
            String token = jwtService.generateToken(authentication);
            logger.debug("LOGGING in user with USERNAME: {}", authentication.getName());
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } catch (AuthenticationException e) {
            logger.error("Authentication failed for user: {}", loginDTO.getEmailOrUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(Authentication authentication) {
        String username = authentication.getName();
        logger.debug("Logging out user: {}", username);
        userService.logout(username);
    }
}
