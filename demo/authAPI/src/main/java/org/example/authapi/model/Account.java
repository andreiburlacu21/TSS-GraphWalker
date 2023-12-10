package org.example.authapi.model;

import lombok.Builder;
import lombok.Data;
import org.example.authapi.dto.LoginCredentialsDTO;

import java.sql.Timestamp;

@Builder
@Data
public class Account {
    private String username;
    private String password;
    private Timestamp registrationDate;
    private int noOfPosts;
    private boolean isLoggedIn;
}
