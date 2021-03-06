package com.hbue.homeworkapp.dao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table USER.
 */
public class User {

    private String userAccount;
    private String userName;
    private String userPassword;

    public User() {
    }

    public User(String userAccount) {
        this.userAccount = userAccount;
    }

    public User(String userAccount, String userName, String userPassword) {
        this.userAccount = userAccount;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
