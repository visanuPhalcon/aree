package com.aree.restaurant.app.model.ResponseModel.admin;

import com.aree.restaurant.app.model.ResponseModel.CommonResponseModel;
import com.aree.restaurant.app.model.TimeModel;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Admin on 8/10/2560.
 */

public class GetHolidayResponseModel extends CommonResponseModel {

    @SerializedName("holiday")
    ArrayList<TimeModel> holiday = new ArrayList<TimeModel>();

    public ArrayList<TimeModel> getHoliday() {
        return holiday;
    }


    public void setHoliday(ArrayList<TimeModel> holiday) {
        this.holiday = holiday;
    }
}
