package com.aree.restaurant.app.model.ResponseModel.admin;

import com.aree.restaurant.app.model.ResponseModel.CommonResponseModel;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Admin on 16/7/2560.
 */

public class GetSaleSummaryResponseModel extends CommonResponseModel {

    @SerializedName("allOrder")
    ArrayList<AllOrderForAdminModel> allOrder = null;

    public ArrayList<AllOrderForAdminModel> getAllOrder() {
        return allOrder;
    }

    public void setAllOrder(ArrayList<AllOrderForAdminModel> allOrder) {
        this.allOrder = allOrder;
    }
}
