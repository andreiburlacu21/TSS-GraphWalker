package org.example.authapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.authapi.dto.AccountRegistrationDTO;
import org.example.authapi.dto.LoginCredentialsDTO;
import org.example.authapi.model.Account;
import org.example.authapi.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/is-logged-in/{username}")
    public ResponseEntity<Boolean> isUserLoggedIn(@PathVariable String username) {
        return ResponseEntity.ok(accountService.isUserLoggedIn(username));
    }

    @PutMapping("/log-out/{username}")
    public ResponseEntity<Boolean> logOut(@PathVariable String username) {
        return ResponseEntity.ok(accountService.logout(username));
    }

    @PostMapping("/login")
    public ResponseEntity<Account> logIn(@RequestBody LoginCredentialsDTO loginCredentialsDTO) {
        return ResponseEntity.ok(accountService.login(loginCredentialsDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody AccountRegistrationDTO accountRegistrationDTO) {
        return ResponseEntity.ok(accountService.register(accountRegistrationDTO));
    }
}
