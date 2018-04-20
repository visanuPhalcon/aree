package com.aree.restaurant.app.model.ResponseModel;

import com.aree.restaurant.app.model.openDailyModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Admin on 8/10/2560.
 */

public class GetOpenningTimeResponseModel extends CommonResponseModel {

    @SerializedName("openDaily")
    ArrayList<openDailyModel> daily ;

    public ArrayList<openDailyModel> getDaily() {
        return daily;
    }

    public void setDaily(ArrayList<openDailyModel> daily) {
        this.daily = daily;
    }
}
