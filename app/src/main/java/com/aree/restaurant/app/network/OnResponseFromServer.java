package com.aree.restaurant.app.network;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Retrofit;

/**
 * Created by Admin on 17/6/2560.
 */
public interface OnResponseFromServer<T>
{

    public void onResponse( );
    public void onResponse(T objecc );
    public void onBodyError(ResponseBody responseBodyError);
    public void onBodyErrorIsNull();
    public void onFailure(Throwable t);
    public void onFailedConnection( );

}