package com.aree.restaurant.app.model.ResponseModel;

import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.PromotionModel;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Admin on 24/6/2560.
 */

public class GetPromotionResponseModel extends CommonResponseModel {

    @SerializedName("promotion")
    ArrayList<PromotionModel> allPromotion = new ArrayList<PromotionModel>();


    public ArrayList<PromotionModel> getPromotion() {
        return allPromotion;
    }
    public void setPromotion(ArrayList<PromotionModel> allMenu) {
        this.allPromotion = allMenu;
    }
}
