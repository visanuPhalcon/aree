package com.aree.restaurant.app.model;

import com.aree.restaurant.app.model.RequsetModel.RegisterRequestModel;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 8/10/2560.
 */

public class openDailyModel {

    @SerializedName("open")
    private String open;
    @SerializedName("close")
    private String close;
    @SerializedName("day")
    private String day;

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
