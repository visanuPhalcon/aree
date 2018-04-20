package com.aree.restaurant.app.model.RequsetModel;

/**
 * Created by Admin on 13/6/2560.
 */

public class RegisterRequestModel
{
    String name;
    String lastName;
    String companyName;
    String floorNumber;
    String phoneNumber;
    String companyPhoneNumber;
    String date;
    String email;
    String userName;
    String password;
    String photo;
    String point ;
    String typeID;

    public RegisterRequestModel(String name, String lastName, String companyName, String floorNumber, String phoneNumber, String companyPhoneNumber, String date
            , String email, String password, String photo, String point, String typeID) {
        this.name = name;
        this.lastName = lastName;
        this.companyName = companyName;
        this.floorNumber = floorNumber;
        this.phoneNumber = phoneNumber;
        this.companyPhoneNumber = companyPhoneNumber;
        this.date = date;
        this.email = email;
        this.password = password;
        this.photo = photo;
        this.point = point;
        this.typeID = typeID;
    }

    public String getName() {
        return name;
    }

    public RegisterRequestModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public RegisterRequestModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCompanyName() {
        return companyName;
    }

    public RegisterRequestModel setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }

    public String getFloorNumber() {
        return floorNumber;
    }

    public RegisterRequestModel setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public RegisterRequestModel setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getCompanyPhoneNumber() {
        return companyPhoneNumber;
    }

    public RegisterRequestModel setCompanyPhoneNumber(String companyPhoneNumber) {
        this.companyPhoneNumber = companyPhoneNumber;
        return this;
    }

    public String getDate() {
        return date;
    }

    public RegisterRequestModel setDate(String date) {
        this.date = date;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public RegisterRequestModel setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterRequestModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPhoto() {
        return photo;
    }

    public RegisterRequestModel setPhoto(String photo) {
        this.photo = photo;
        return this;
    }

    public String getPoint() {
        return point;
    }

    public RegisterRequestModel setPoint(String point) {
        this.point = point;
        return this;
    }

    public String getTypeID() {
        return typeID;
    }

    public RegisterRequestModel setTypeID(String typeID) {
        this.typeID = typeID;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
