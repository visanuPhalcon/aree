package com.aree.restaurant.app.model.RequsetModel.admin;

/**
 * Created by Admin on 23/7/2560.
 */

public class DeleteOrderRequestModel {

    int oid;
    int status;

    public DeleteOrderRequestModel(int oid, int status) {
        this.oid = oid;
        this.status = status;
    }

    public DeleteOrderRequestModel(int oid)
    {
        this.oid = oid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }


}
