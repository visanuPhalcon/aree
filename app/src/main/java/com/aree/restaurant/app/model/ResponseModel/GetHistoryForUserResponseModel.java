package com.aree.restaurant.app.model.ResponseModel;

import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Admin on 25/7/2560.
 */

public class GetHistoryForUserResponseModel extends CommonResponseModel {

    @SerializedName("history")
    ArrayList<AllOrderForAdminModel> history = null;

    public ArrayList<AllOrderForAdminModel> getOrder() {
        return history;
    }

    public void setOrder(ArrayList<AllOrderForAdminModel> order) {
        this.history = order;
    }

}
