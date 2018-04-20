package com.aree.restaurant.app.model.RequsetModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 16/6/2560.
 */

public class ForgetPasswordRequestModel {

    @SerializedName("email")
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
