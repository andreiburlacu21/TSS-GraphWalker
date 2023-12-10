package org.example.authapi.service;

import lombok.RequiredArgsConstructor;
import org.example.authapi.dto.AccountRegistrationDTO;
import org.example.authapi.dto.LoginCredentialsDTO;
import org.example.authapi.model.Account;
import org.example.authapi.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@RequiredArgsConstructor
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public Boolean isUserLoggedIn(String username) {
        var accountOptional = accountRepository.findAccountByUsername(username);
        return accountOptional.isPresent() && accountOptional.get().isLoggedIn();
    }

    public Account login(LoginCredentialsDTO loginCredentialsDTO) {
        if (!areCredentialsValid(loginCredentialsDTO)) {
            return null;
        }

        var accountOptional = accountRepository.findAccount(loginCredentialsDTO.getUsername(), loginCredentialsDTO.getPassword());
        Account foundAccount = null;

        if (accountOptional.isPresent()) {
            foundAccount = accountOptional.get();
            foundAccount.setLoggedIn(true);
        }

        return foundAccount;
    }

    public Boolean logout(String username) {
        var accountOptional = accountRepository.findAccountByUsername(username);

        if (accountOptional.isPresent()) {
            accountOptional.get().setLoggedIn(false);
            return true;
        }

        return false;
    }

    public Account register(AccountRegistrationDTO accountRegistrationDTO) {
        if (accountRegistrationDTO == null) {
            return null;
        }

        Account newAccount = Account.builder()
                .username(accountRegistrationDTO.getUsername())
                .password(accountRegistrationDTO.getPassword())
                .registrationDate(Timestamp.from(Instant.now()))
                .noOfPosts(0)
                .isLoggedIn(false)
                .build();

        return accountRepository.addAccount(newAccount);
    }

    private boolean areCredentialsValid(LoginCredentialsDTO loginCredentialsDTO) {
        return loginCredentialsDTO != null &&
                !loginCredentialsDTO.getUsername().isEmpty() &&
                !loginCredentialsDTO.getPassword().isEmpty();
    }
}
