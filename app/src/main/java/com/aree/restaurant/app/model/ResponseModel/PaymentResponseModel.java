package com.aree.restaurant.app.model.ResponseModel;

import com.aree.restaurant.app.model.MenuModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Admin on 28/6/2560.
 */

public class PaymentResponseModel extends CommonResponseModel {



    @SerializedName("oid")
    String oid ;

    @SerializedName("remainPoint")
    int point ;


    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getOid() {
        return oid;
    }

    public PaymentResponseModel setOid(String oid) {
        this.oid = oid;
        return this;
    }
}
