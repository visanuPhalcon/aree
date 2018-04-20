package com.aree.restaurant.app.model.Singleton;

import com.aree.restaurant.app.model.ResponseModel.admin.GetAllMenuResponseModel;
import com.aree.restaurant.app.model.ResponseModel.admin.GetAllOrderResponseModel;

/**
 * Created by Admin on 3/7/2560.
 */

public class Singleton {

    private static final Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {
    }

    private UserInformation userInformation;
    private GetAllOrderResponseModel allOrder;
    private GetAllMenuResponseModel allMenu;

//    public static Singleton getOurInstance() {
//        return ourInstance;
//    }

    public UserInformation getUserInformation() {
        return userInformation;
    }


    public Singleton setUserInformation(UserInformation userInformation) {
        this.userInformation = userInformation;
        return this;
    }

    public GetAllMenuResponseModel getAllMenu() {
        return allMenu;
    }

    public void setAllMenu(GetAllMenuResponseModel allMenu) {
        this.allMenu = allMenu;
    }

    public GetAllOrderResponseModel getAllOrder() {return allOrder;}

    public void setAllOrder(GetAllOrderResponseModel allOrder) {
        this.allOrder = allOrder;
    }
}
