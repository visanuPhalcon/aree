package com.aree.restaurant.app.model.ResponseModel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 9/7/2560.
 */

public class SetProfilePhotoResponseModel extends CommonResponseModel {

    @SerializedName("photo")
    String base64Image;

    @SerializedName("uid")
    int uid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPhoto() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

}
