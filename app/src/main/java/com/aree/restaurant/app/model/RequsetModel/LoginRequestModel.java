package com.aree.restaurant.app.model.RequsetModel;

/**
 * Created by Admin on 16/6/2560.
 */

public class LoginRequestModel {

    String username;
    String password;
    String pushToken;

    public LoginRequestModel(String username, String password,String pushToken) {
        this.username = username;
        this.password = password;
        this.pushToken = pushToken;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
