package com.aree.restaurant.app.model.Singleton;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

/**
 * Created by Admin on 16/7/2560.
 */

@Parcel
public class AllOrderForAdminModel {

    public AllOrderForAdminModel() {
    }

    public AllOrderForAdminModel(String foodId, String foodType, String foodName, String foodAmount, String foodPrice, String total) {
        this.foodId = foodId;
        this.foodType = foodType;
        this.foodName = foodName;
        this.foodAmount = foodAmount;
        this.foodPrice = foodPrice;
        this.total = total;
    }

    public AllOrderForAdminModel(int oid, String foodId, String foodType, String foodName, String foodAmount, String foodPrice, String total, String status, String name, String lastname, String time)
    {
        this.oid = oid;
        this.foodId = foodId;
        this.foodType = foodType;
        this.foodName = foodName;
        this.foodAmount = foodAmount;
        this.foodPrice = foodPrice;
        this.total = total;
        this.status = status;
        this.name = name;
        this.lastname = lastname;
        this.time = time;
    }

    @SerializedName("oid")
    int oid;

    @SerializedName("food_id")
    String foodId;

    @SerializedName("food_type")
    String foodType;

    @SerializedName("food_name")
    String foodName;

    @SerializedName("food_amount")
    String foodAmount;

    @SerializedName("food_price")
     String foodPrice;

    @SerializedName("total")
     String total;

    @SerializedName("status")
     String status;

    @SerializedName("name")
     String name;

    @SerializedName("lastname")
     String lastname;

    @SerializedName("companyName")
     String companyName;

    @SerializedName("floorNumber")
     String floorNumber;

    @SerializedName("phoneNumber")
     String phoneNumber;

    @SerializedName("companyPhoneNumber")
     String companyPhoneNumber;

    @SerializedName("photo")
     String photo;

    @SerializedName("PointExchangeFood_name")
     String pointExchangeFoodName;

    @SerializedName("PointExchangeFood_amount")
     String pointExchangeFoodAmount;

    @SerializedName("PointExchangeFood_id")
     String pointExchangeFoodId;

    @SerializedName("PointExchangeFood_point")
     String pointExchangeFoodPoint;

    @SerializedName("time")
    String time;

    @SerializedName("date")
    String date;

    @SerializedName("order_id")
    String order_id;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(String foodAmount) {
        this.foodAmount = foodAmount;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
    }

    public void setCompanyPhoneNumber(String companyPhoneNumber) {
        this.companyPhoneNumber = companyPhoneNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPointExchangeFoodName() {
        return pointExchangeFoodName;
    }

    public void setPointExchangeFoodName(String pointExchangeFoodName) {
        this.pointExchangeFoodName = pointExchangeFoodName;
    }

    public String getPointExchangeFoodAmount() {
        return pointExchangeFoodAmount;
    }

    public void setPointExchangeFoodAmount(String pointExchangeFoodAmount) {
        this.pointExchangeFoodAmount = pointExchangeFoodAmount;
    }

    public String getPointExchangeFoodId() {
        return pointExchangeFoodId;
    }

    public void setPointExchangeFoodId(String pointExchangeFoodId) {
        this.pointExchangeFoodId = pointExchangeFoodId;
    }

    public String getPointExchangeFoodPoint() {
        return pointExchangeFoodPoint;
    }

    public void setPointExchangeFoodPoint(String pointExchangeFoodPoint) {
        this.pointExchangeFoodPoint = pointExchangeFoodPoint;
    }
}
