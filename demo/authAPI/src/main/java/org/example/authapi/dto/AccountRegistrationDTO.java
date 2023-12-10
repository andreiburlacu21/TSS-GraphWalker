package org.example.authapi.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AccountRegistrationDTO {
    private String username;
    private String password;
    private String confirmPassword;
}
