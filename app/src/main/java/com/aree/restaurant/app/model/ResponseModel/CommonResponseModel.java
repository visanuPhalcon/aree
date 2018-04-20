package com.aree.restaurant.app.model.ResponseModel;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Admin on 14/6/2560.
 */
public class CommonResponseModel {

    @SerializedName("error")
    String error;
    @SerializedName("code")
    String code ;
    @SerializedName("message")
    String message ;



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
