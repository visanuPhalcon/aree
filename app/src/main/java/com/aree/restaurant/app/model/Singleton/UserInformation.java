package com.aree.restaurant.app.model.Singleton;

import com.aree.restaurant.app.model.ResponseModel.CommonResponseModel;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 16/6/2560.
 */

public class UserInformation  {

    @SerializedName("name")
    String name;
    @SerializedName("lastName")
    String lastName;
    @SerializedName("companyName")
    String companyName;
    @SerializedName("floorNumber")
     String floorNumber;
    @SerializedName("phoneNumber")
    String phoneNumber;
    @SerializedName("companyPhoneNumber")
    String companyPhoneNumber;
    @SerializedName("birthDate")
    String date;
    @SerializedName("photo")
    String photo;
    @SerializedName("point")
    String point ;
    @SerializedName ("uid")
    int uid ;
    @SerializedName("type")
    int type_id ;

    @SerializedName("shipping")
    int shipping;

    String email;


    @SerializedName("pushToken")
    String pushToken;

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public int getShipping() {
        return shipping;
    }

    public void setShipping(int shipping) {
        this.shipping = shipping;
    }


//    public static UserInformation getInstance()
//    {
//        if (userModel == null)
//        {
//            userModel = new UserInformation();
//        }
//        return userModel;
//    }
//
//
//    public static UserInformation getUserModel() {
//        return userModel;
//    }
//
//    public static void setUserModel(UserInformation userModel) {
//        UserInformation.userModel = userModel;
//    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
