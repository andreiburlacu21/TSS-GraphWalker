package org.example.authapi.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginCredentialsDTO {
    private String username;
    private String password;
}
