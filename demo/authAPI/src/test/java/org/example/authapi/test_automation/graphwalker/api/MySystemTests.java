package org.example.authapi.test_automation.graphwalker.api;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.example.authapi.dto.AccountRegistrationDTO;
import org.example.authapi.dto.LoginCredentialsDTO;
import org.example.authapi.model.Account;
import org.example.authapi.model.Post;
import org.graphwalker.core.machine.ExecutionContext;
import org.graphwalker.java.annotation.GraphWalker;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
//@GraphWalker(value = "random(edge_coverage(100))")
//@GraphWalker(value = "random(reached_vertex(v_ViewingPosts))")
//@GraphWalker(value = "random(time_duration(30))")
@GraphWalker(value = "a_star(reached_edge(e_ValidAccountDetails))")
public class MySystemTests extends ExecutionContext implements MySystem {
    private static final String BASE_URL = "http://localhost:8080/api";
    private boolean areCredentialsValid = false;
    private boolean areAccountDetailsValid = false;
    private boolean isLoggedIn = false;
    private int numberOfPostsAddedDuringTests = 0;

    private Account userAccount = Account.builder()
            .username("andrei")
            .password("password")
            .noOfPosts(3)
            .registrationDate(Timestamp.from(Instant.now()))
            .isLoggedIn(false)
            .build();

    private Post newPostToBeAdded = Post.builder()
            .accountUsername("andrei")
            .content("Content")
            .build();

    private LoginCredentialsDTO loginCredentials = LoginCredentialsDTO.builder()
            .username("andrei")
            .password("password")
            .build();

    private AccountRegistrationDTO accountRegistration = AccountRegistrationDTO.builder()
            .username("andrei")
            .password("password")
            .confirmPassword("password")
            .build();

    private final HttpClient client = HttpClient.newHttpClient();

    @Override
    public boolean areCredentialsValid() {
        return areCredentialsValid;
    }

    @Override
    public void setCredentialsValid(boolean value) {
        areCredentialsValid = value;
    }

    @Override
    public boolean areAccountDetailsValid() {
        return areAccountDetailsValid;
    }

    @Override
    public void setAccountDetailsValid(boolean value) {
        areAccountDetailsValid = value;
    }

    @Override
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    @Override
    public void setLoggedIn(boolean value) {
        isLoggedIn = value;
    }

    @Override
    public void v_LoggedIn() {
        log.info("RUNNING: v_LoggedIn();");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/account/is-logged-in/" + loginCredentials.getUsername()))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                boolean result = Boolean.parseBoolean(response.body());

                assertTrue(result);
                assertTrue(isLoggedIn);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void v_RegistrationPrompt() {
        log.info("RUNNING: v_RegistrationPrompt();");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/account/is-logged-in/" + loginCredentials.getUsername()))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                boolean result = Boolean.parseBoolean(response.body());

                assertFalse(result);
                assertFalse(isLoggedIn);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void v_ViewingPosts() {
        log.info("RUNNING: v_ViewingPosts();");
        // assert the number of posts available
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/post/get-posts" + loginCredentials.getUsername()))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                int result = Integer.getInteger(response.body());
                assertEquals(numberOfPostsAddedDuringTests, result);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        // assert the login status
        request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/account/is-logged-in/" + loginCredentials.getUsername()))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                boolean result = Boolean.parseBoolean(response.body());

                assertTrue(result);
                assertTrue(isLoggedIn);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void v_LoggedOut() {
        log.info("RUNNING: v_LoggedOut();");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/account/is-logged-in/" + loginCredentials.getUsername()))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                boolean result = Boolean.parseBoolean(response.body());

                assertFalse(result);
                assertFalse(isLoggedIn);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void v_Posting() {
        log.info("RUNNING: v_Posting();");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/account/is-logged-in/" + loginCredentials.getUsername()))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                boolean result = Boolean.parseBoolean(response.body());

                assertTrue(result);
                assertTrue(isLoggedIn);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void v_LoginPrompt() {
        log.info("RUNNING: v_LoginPrompt();");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/account/is-logged-in/" + loginCredentials.getUsername()))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                boolean result = Boolean.parseBoolean(response.body());

                assertFalse(result);
                assertFalse(isLoggedIn);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void e_UserWantsToRegister() {
        log.info("RUNNING: e_UserWantsToRegister();");
        setLoggedIn(false);
        setCredentialsValid(false);
        setAccountDetailsValid(false);
    }

    @SneakyThrows
    @Override
    public void e_Login() {
        log.info("RUNNING: e_Login();");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/account/login"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(this.loginCredentials)))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            userAccount = null;

            if (response.statusCode() == 200) {
                userAccount = new Gson().fromJson(response.body(), Account.class);
                setLoggedIn(true);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void e_AccountRegistered() {
        log.info("RUNNING: e_AccountRegistered();");

    }

    @Override
    public void e_Logout() {
        log.info("RUNNING: e_Logout();");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/account/log-out/" + loginCredentials.getUsername()))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.noBody())
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                setLoggedIn(false);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void e_RequestPosts() {
        log.info("RUNNING: e_RequestPosts();");

    }

    @Override
    public void e_DoneViewingPosts() {
        log.info("RUNNING: e_DoneViewingPosts();");

    }

    @Override
    public void e_CanLogBackIn() {
        log.info("RUNNING: e_CanLogBackIn();");

    }

    @Override
    public void e_InvalidCredentials() {
        log.info("RUNNING: e_InvalidCredentials();");
        setCredentialsValid(false);
    }

    @Override
    public void e_InvalidAccountDetails() {
        log.info("RUNNING: e_InvalidAccountDetails();");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/account/register"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(null)))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            this.userAccount = null;

            if (response.statusCode() == 200) {
                this.userAccount = new Gson().fromJson(response.body(), Account.class);
                setAccountDetailsValid(false);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void e_ValidCredentials() {
        log.info("RUNNING: e_ValidCredentials();");
        setCredentialsValid(true);
    }

    @Override
    public void e_ValidAccountDetails() {
        log.info("RUNNING: e_ValidAccountDetails();");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/account/register"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(this.accountRegistration)))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            this.userAccount = null;

            if (response.statusCode() == 200) {
                this.userAccount = new Gson().fromJson(response.body(), Account.class);
                setAccountDetailsValid(true);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void e_CreatePost() {
        log.info("RUNNING: e_CreatePost();");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/post/add-post"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(new Gson().toJson(newPostToBeAdded)))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                numberOfPostsAddedDuringTests++;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void e_PostCreated() {
        log.info("RUNNING: e_PostCreated();");
    }
}
