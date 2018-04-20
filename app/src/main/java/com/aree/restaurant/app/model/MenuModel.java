package com.aree.restaurant.app.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by Admin on 14/5/2560.
 */


public class MenuModel {

    @SerializedName("fid")
    int id;
    @SerializedName("type")
    int type;

    @SerializedName("menuName")
    String menuName;
    @SerializedName("description")
    String description;
    @SerializedName("price")
    String price;
    @SerializedName("photo")
    String photo;
    @SerializedName("point")
    int point;
    int amount;
    int total;



    public MenuModel()
    {

    }


    public MenuModel(String menuName, String price, int amount) {
        this.menuName = menuName;
        this.price = price;
        this.amount = amount;
    }

    public MenuModel(String menuName, String price, int amount , int total) {
        this.menuName = menuName;
        this.price = price;
        this.amount = amount;
        this.total = total;
    }



    public MenuModel(String menuName, String price, int amount , int total , int type , int fid) {
        this.menuName = menuName;
        this.price = price;
        this.amount = amount;
        this.total = total;
        this.type = type;
        this.id = fid;
    }

    public MenuModel(String menuName, int point, int amount , int total , int type , int fid) {
        this.menuName = menuName;
        this.point = point;
        this.amount = amount;
        this.total = total;
        this.type = type;
        this.id = fid;
    }

    public String getMenuName()
    {

        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public int getTotal() {
        return total;
    }

    public MenuModel setTotal(int total) {
        this.total = total;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
