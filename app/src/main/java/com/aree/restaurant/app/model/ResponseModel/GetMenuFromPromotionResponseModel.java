package com.aree.restaurant.app.model.ResponseModel;

import com.aree.restaurant.app.model.MenuModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Admin on 26/6/2560.
 */

public class GetMenuFromPromotionResponseModel extends CommonResponseModel  {

    @SerializedName("menu")
    ArrayList<MenuModel> allMenu = new ArrayList<MenuModel>();


    public ArrayList<MenuModel> getAllMenu() {
        return allMenu;
    }
    public void setAllMenu(ArrayList<MenuModel> allMenu) {
        this.allMenu = allMenu;
    }
}
