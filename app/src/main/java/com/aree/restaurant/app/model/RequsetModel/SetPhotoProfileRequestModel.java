package com.aree.restaurant.app.model.RequsetModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 9/7/2560.
 */

public class SetPhotoProfileRequestModel {

    @SerializedName("photo")
    String base64Image;
    @SerializedName("uid")
    int uid ;

    public String getBase64Image() {
        return base64Image;
    }
    public void setPhotoProfile(String base64Image) {
        this.base64Image = base64Image;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
