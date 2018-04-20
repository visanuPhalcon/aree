package com.aree.restaurant.app.model.RequsetModel.admin;

import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.Singleton.ShoppingCart;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Admin on 8/10/2560.
 */

public class SetHolidayRequsetModel {


    @SerializedName("holiday")
    ArrayList<Event> holiday = new ArrayList<Event>();

    public ArrayList<Event> getHoliday() {
        return holiday;
    }

    public void setHoliday(ArrayList<Event> holiday) {
        this.holiday = holiday;
    }
}
