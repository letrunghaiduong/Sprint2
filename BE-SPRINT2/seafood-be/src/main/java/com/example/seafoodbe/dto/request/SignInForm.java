package com.example.seafoodbe.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignInForm {
    @Size(min = 3, max = 100)
    private String userName;
    @NotBlank
    @Size(min = 6, max = 100)
    private String password;

    public SignInForm() {
    }

    public SignInForm(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
