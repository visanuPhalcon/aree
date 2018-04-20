package com.aree.restaurant.app.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Admin on 14/5/2560.
 */

public class PromotionModel {

    @SerializedName("id")
    String id ;
    @SerializedName("photo")
    String imgPromotion;
    String setName;
    @SerializedName("promotionName")
    String promotionName;
    @SerializedName("description")
    String description;
    @SerializedName("price")
    int price;
    @SerializedName("type")
    int type;


    public PromotionModel(String imgPromotion, String promotionName, String description ,String setName ,int price)
    {
        this.imgPromotion = imgPromotion;
        this.setName = setName;
        this.description = description;
        this.promotionName = promotionName;
        this.price = price;
    }

    public String getImgPromotion() {
        return imgPromotion;
    }

    public void setImgPromotion(String imgPromotion) {
        this.imgPromotion = imgPromotion;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
