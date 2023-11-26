package com.boot.springbootthymleaf.entity;

import jakarta.validation.constraints.NotBlank;

public class UserData {

    @NotBlank(message="username cannot be null") 
    private String userName ;

    @NotBlank(message="Email cannot be null")
    private String email ;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public UserData(String userName, String email) {
        this.userName = userName;
        this.email = email;
    } 
     public UserData() {
       
    } 
}
