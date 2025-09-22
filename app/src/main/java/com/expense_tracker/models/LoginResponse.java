package com.expense_tracker.models;

public class LoginResponse {

    String message;

    String username;

    String userid;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserID() {
        return userid;
    }

    public void setUserID(String userID) {
        this.userid = userID;
    }


}
