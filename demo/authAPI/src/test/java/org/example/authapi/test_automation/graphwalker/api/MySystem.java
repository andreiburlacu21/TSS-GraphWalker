package org.example.authapi.test_automation.graphwalker.api;

import org.graphwalker.java.annotation.Edge;
import org.graphwalker.java.annotation.Model;
import org.graphwalker.java.annotation.Vertex;

@Model(file = "src/test/resources/models/APIModel.json")
public interface MySystem {
    // Vertices (States)
    @Vertex
    void v_LoginPrompt();

    @Vertex
    void v_RegistrationPrompt();

    @Vertex
    void v_LoggedIn();

    @Vertex
    void v_ViewingPosts();

    @Vertex
    void v_LoggedOut();
    @Vertex
    void v_Posting();

    // Edges (Transitions)
    @Edge
    void e_UserWantsToRegister();

    @Edge
    void e_Login();

    @Edge
    void e_AccountRegistered();

    @Edge
    void e_Logout();

    @Edge
    void e_RequestPosts();

    @Edge
    void e_DoneViewingPosts();

    @Edge
    void e_CanLogBackIn();

    @Edge
    void e_InvalidCredentials();

    @Edge
    void e_InvalidAccountDetails();

    @Edge
    void e_ValidCredentials();

    @Edge
    void e_ValidAccountDetails();

    @Edge
    void e_CreatePost();

    @Edge
    void e_PostCreated();

    boolean areCredentialsValid();

    void setCredentialsValid(boolean value);

    boolean areAccountDetailsValid();

    void setAccountDetailsValid(boolean value);

    boolean isLoggedIn();

    void setLoggedIn(boolean value);
}
