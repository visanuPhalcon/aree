package com.aree.restaurant.app.model.RequsetModel;

import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.Singleton.ShoppingCart;
import com.aree.restaurant.app.model.Singleton.Singleton;
import com.aree.restaurant.app.model.Singleton.UserInformation;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Admin on 29/6/2560.
 */

public class PaymentRequestModel {

    @SerializedName("userInformation")
    UserInformation user = Singleton.getInstance().getUserInformation();


    @SerializedName("shoppingCart")
    ArrayList<MenuModel> menuList = new ArrayList<MenuModel>(ShoppingCart.getInstance().getMenuList().values());

    @SerializedName("getPointFromBuying")
    int point ;


    @SerializedName("totalCostPoint")
    int costPoint ;

    public int getCostPoint() {
        return costPoint;
    }

    public void setCostPoint(int costPoint) {
        this.costPoint = costPoint;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public ArrayList<MenuModel> getMenuList() {
        return menuList;
    }

    public void setMenuList(ArrayList<MenuModel> menuList) {
        this.menuList = menuList;
    }
}
