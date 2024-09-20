package com.yourcaryourway.backend.dto;

import com.yourcaryourway.backend.model.User.Role;
import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private Role role;
}
