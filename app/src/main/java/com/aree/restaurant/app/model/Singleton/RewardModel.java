package com.aree.restaurant.app.model.Singleton;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 17/9/2560.
 */

public class RewardModel {

    @SerializedName("id")
    int id;
    @SerializedName("point")
    int point;
    @SerializedName("description")
    String description;
    @SerializedName("name")
    String name;
    @SerializedName("photo")
    String photo;

    @SerializedName("amonut")
    int amonut;

    @SerializedName("total")
    int total;


    @SerializedName("status")
    int status;



    public RewardModel(int id, int point, String description, String name, String photo, int amonut)
    {
        this.id = id;
        this.point = point;
        this.description = description;
        this.name = name;
        this.photo = photo;
        this.amonut = amonut;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getAmonut() {
        return amonut;
    }

    public void setAmonut(int amonut) {
        this.amonut = amonut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
