package com.aree.restaurant.app.model.ResponseModel.admin;

import com.aree.restaurant.app.model.ResponseModel.CommonResponseModel;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Admin on 16/7/2560.
 */

public class GetAllMenuResponseModel extends CommonResponseModel {

    @SerializedName("menu")
    ArrayList<AllOrderForAdminModel> allMenu ;

    public ArrayList<AllOrderForAdminModel> getAllMenu() {
        return allMenu;
    }

    public void setAllMenu(ArrayList<AllOrderForAdminModel> allMenu) {
        this.allMenu = allMenu;
    }
}
