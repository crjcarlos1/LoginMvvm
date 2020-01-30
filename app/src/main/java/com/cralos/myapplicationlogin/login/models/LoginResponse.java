package com.cralos.myapplicationlogin.login.models;

public class LoginResponse {
    private String token;

    /**
     * @param token
     */
    public LoginResponse(String token) {
        super();
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
