package com.yourcaryourway.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginDTO {
    @JsonProperty("email")
    private String emailOrUsername;
    private String password;
}