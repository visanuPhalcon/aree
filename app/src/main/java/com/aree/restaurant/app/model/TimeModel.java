package com.aree.restaurant.app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 8/10/2560.
 */

public class TimeModel {

    @SerializedName("timeInMillis")
    private long timeInMillis;

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }


}
