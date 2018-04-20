package com.aree.restaurant.app.model.ResponseModel.admin;

import com.aree.restaurant.app.model.ResponseModel.CommonResponseModel;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Admin on 16/7/2560.
 */

public class GetHistoryResponseModel extends CommonResponseModel {

    @SerializedName("allOrder")
    ArrayList<AllOrderForAdminModel> order = null;

    public ArrayList<AllOrderForAdminModel> getOrder() {
        return order;
    }

    public void setOrder(ArrayList<AllOrderForAdminModel> order) {
        this.order = order;
    }


}
