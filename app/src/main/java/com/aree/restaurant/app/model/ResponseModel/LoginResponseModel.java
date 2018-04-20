package com.aree.restaurant.app.model.ResponseModel;

import com.aree.restaurant.app.model.Singleton.UserInformation;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 16/6/2560.
 */

public class LoginResponseModel extends CommonResponseModel    {


    @SerializedName("uid")
    @Expose
    private int uid;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("companyName")
    @Expose
    private String companyName;
    @SerializedName("floorNumber")
    @Expose
    private String floorNumber;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("companyPhoneNumber")
    @Expose
    private String companyPhoneNumber;
    @SerializedName("birthDate")
    @Expose
    private String birthDate;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("point")
    @Expose
    private String point;
    @SerializedName("type")
    @Expose
    private int type;


    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
