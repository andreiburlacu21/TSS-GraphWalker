package org.example.authapi.repository;

import org.example.authapi.model.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AccountRepository {
    private static final List<Account> existingAccounts = new ArrayList<>(List.of(
            Account.builder()
                    .username("marian")
                    .password("parolaMarian")
                    .noOfPosts(5)
                    .isLoggedIn(false)
                    .build()
    ));

    public Optional<Account> findAccount(String username,  String password) {
        return existingAccounts.stream()
                .filter(existingAccount -> existingAccount.getUsername().equals(username) && existingAccount.getPassword().equals(password))
                .findFirst();
    }

    public Optional<Account> findAccountByUsername(String username) {
        return existingAccounts.stream()
                .filter(existingAccount -> existingAccount.getUsername().equals(username))
                .findFirst();
    }

    public Account addAccount(Account accountToBeAdded) {
        existingAccounts.add(accountToBeAdded);
        return accountToBeAdded;
    }
}
