package com.aree.restaurant.app.network;

import com.aree.restaurant.app.model.RequsetModel.PaymentRequestModel;
import com.aree.restaurant.app.model.RequsetModel.admin.SetHolidayRequsetModel;
import com.aree.restaurant.app.model.ResponseModel.EditProfileResponseModel;
import com.aree.restaurant.app.model.ResponseModel.ForgetPasswordResponseModel;
import com.aree.restaurant.app.model.ResponseModel.GetMenuFromPromotionResponseModel;
import com.aree.restaurant.app.model.ResponseModel.GetMenuResponseModel;
import com.aree.restaurant.app.model.ResponseModel.GetOpenningTimeResponseModel;
import com.aree.restaurant.app.model.ResponseModel.GetPromotionResponseModel;
import com.aree.restaurant.app.model.ResponseModel.GetRewardResposeModel;
import com.aree.restaurant.app.model.ResponseModel.LoginResponseModel;
import com.aree.restaurant.app.model.ResponseModel.PaymentResponseModel;
import com.aree.restaurant.app.model.ResponseModel.RegisterResponseModel;
import com.aree.restaurant.app.model.ResponseModel.SetProfilePhotoResponseModel;
import com.aree.restaurant.app.model.ResponseModel.admin.DeleteOrderResponseModel;
import com.aree.restaurant.app.model.ResponseModel.admin.GetAllMenuResponseModel;
import com.aree.restaurant.app.model.ResponseModel.admin.GetAllOrderResponseModel;
import com.aree.restaurant.app.model.ResponseModel.admin.GetHistoryResponseModel;
import com.aree.restaurant.app.model.ResponseModel.admin.GetHolidayResponseModel;
import com.aree.restaurant.app.model.ResponseModel.admin.GetSaleSummaryResponseModel;
import com.aree.restaurant.app.model.ResponseModel.admin.RollBackStatusResponseModel;
import com.aree.restaurant.app.model.ResponseModel.admin.SetHolidayResponseModel;
import com.aree.restaurant.app.model.ResponseModel.admin.SetRewardStatusResponseModel;
import com.aree.restaurant.app.model.ResponseModel.admin.UpdateStatusResponseModel;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by Admin on 13/6/2560.
 */

public interface ApiEndpointInterface <T>{


    @FormUrlEncoded
    @POST("./")
    Call<RegisterResponseModel> register(
            @Field("name") String name ,
            @Field("lastName") String lastName,
            @Field("companyName") String companyName,
            @Field("floorNumber") String floorNumber,
            @Field("phoneNumber") String phoneNumber,
            @Field("companyPhoneNumber") String companyPhoneNumber,
            @Field("date") String date,
            @Field("email") String email,
            @Field("userName") String userName,
            @Field("password") String password,
            @Field("photo") String photo,
            @Field("point") String point,
            @Field("typeID") String typeID
    );



    @FormUrlEncoded
    @POST("./")
    Call<EditProfileResponseModel> editProfile(
            @Field("name") String name ,
            @Field("lastName") String lastName,
            @Field("companyName") String companyName,
            @Field("floorNumber") String floorNumber,
            @Field("phoneNumber") String phoneNumber,
            @Field("companyPhoneNumber") String companyPhoneNumber,
            @Field("date") String date,
            @Field("userName") String userName,
            @Field("password") String password,
            @Field("photo") String photo,
            @Field("point") String point,
            @Field("typeID") String typeID
    );


    @FormUrlEncoded
    @POST("./")
    Call<ForgetPasswordResponseModel> forgetPassword(
            @Field("email") String email

    );


    @FormUrlEncoded
    @POST("./")
    Call<LoginResponseModel> login(
            @Field("userName") String userName,
            @Field("password") String password,
            @Field("pushToken") String pushToken

    );

    @FormUrlEncoded
    @POST("./")
    Call<GetMenuResponseModel> getAllMenu(@Field("type") int type );

    @POST("./")
    Call<GetPromotionResponseModel> getPromotion();

    @FormUrlEncoded
    @POST("./")
    Call<GetMenuFromPromotionResponseModel> getMenuFromPromotion(@Field("id") int id);



    @POST("./")
    Call<PaymentResponseModel> payment(@Body PaymentRequestModel requestModel );


    @Multipart
    @POST("./")
    Call< SetProfilePhotoResponseModel > uploadProfilePhoto(@Part("uid") int id, @Part MultipartBody.Part image  );



    @POST("./")
    Call<GetAllOrderResponseModel> getAllOrderForAdmin();


    @POST("./")
    Call<GetAllMenuResponseModel> getAllMenuForAdmin();


    @POST("./")
    Call<GetHistoryResponseModel> getSaleHistoryForAdminForAdmin();


    @POST("./")
    Call<GetSaleSummaryResponseModel> getSaleSummaryForAdmin();


    @FormUrlEncoded
    @POST("./")
    Call<UpdateStatusResponseModel> updateStatusForAdmin(@Field("oid") int oid , @Field("status") int status);


    @FormUrlEncoded
    @POST("./")
    Call<SetRewardStatusResponseModel> updateRewardStatusForAdmin(@Field("id") int id , @Field("status") int status);



    @FormUrlEncoded
    @POST("./")
    Call<DeleteOrderResponseModel> deleteOrderForAdmin(@Field("oid") int oid , @Field("status") int status  );


    @FormUrlEncoded
    @POST("./")
    Call<GetAllOrderResponseModel> getHistoryForCustomer(@Field("uid") int uid );


    @POST("./")
    Call<GetRewardResposeModel> getReward( );

    @POST("./")
    Call<SetHolidayResponseModel> setHoliday(@Body SetHolidayRequsetModel requestModel  );

    @POST("./")
    Call<GetHolidayResponseModel> getHoliday(   );


    @POST("./")
    Call<GetOpenningTimeResponseModel> getOpenningTime(   );


    @FormUrlEncoded
    @POST("./")
    Call<LoginResponseModel> getProfile( @Field("uid") int uid );








}
