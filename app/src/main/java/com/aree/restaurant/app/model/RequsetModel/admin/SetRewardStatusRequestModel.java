package com.aree.restaurant.app.model.RequsetModel.admin;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 7/11/2560.
 */

public class SetRewardStatusRequestModel {

    @SerializedName("status")
    int status;
    @SerializedName("id")
    int id ;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
