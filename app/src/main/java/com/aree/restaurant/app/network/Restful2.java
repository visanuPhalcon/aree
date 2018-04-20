package com.aree.restaurant.app.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.model.RequsetModel.EditProfileRequestModel;
import com.aree.restaurant.app.model.RequsetModel.ForgetPasswordRequestModel;
import com.aree.restaurant.app.model.RequsetModel.GetMenuFromPromotionRequestModel;
import com.aree.restaurant.app.model.RequsetModel.GetMenuRequestModel;
import com.aree.restaurant.app.model.RequsetModel.GetOpenningTimeRequestModel;
import com.aree.restaurant.app.model.RequsetModel.GetPromotionRequestModel;
import com.aree.restaurant.app.model.RequsetModel.GetRewardRequestModel;
import com.aree.restaurant.app.model.RequsetModel.LoginRequestModel;
import com.aree.restaurant.app.model.RequsetModel.PaymentRequestModel;
import com.aree.restaurant.app.model.RequsetModel.RegisterRequestModel;
import com.aree.restaurant.app.model.RequsetModel.SetPhotoProfileRequestModel;
import com.aree.restaurant.app.model.RequsetModel.UpdatePointRequestModel;
import com.aree.restaurant.app.model.RequsetModel.admin.DeleteOrderRequestModel;
import com.aree.restaurant.app.model.RequsetModel.admin.GetAllMenuRequestModel;
import com.aree.restaurant.app.model.RequsetModel.admin.GetAllOrderRequestModel;
import com.aree.restaurant.app.model.RequsetModel.admin.GetHistoryRequestModel;
import com.aree.restaurant.app.model.RequsetModel.admin.GetHolidayRequsetModel;
import com.aree.restaurant.app.model.RequsetModel.admin.GetSaleSummaryRequestModel;
import com.aree.restaurant.app.model.RequsetModel.admin.SetHolidayRequsetModel;
import com.aree.restaurant.app.model.RequsetModel.admin.SetRewardStatusRequestModel;
import com.aree.restaurant.app.model.RequsetModel.admin.UpdateStatusRequestModel;
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
import com.aree.restaurant.app.model.ResponseModel.admin.SetHolidayResponseModel;
import com.aree.restaurant.app.model.ResponseModel.admin.SetRewardStatusResponseModel;
import com.aree.restaurant.app.model.ResponseModel.admin.UpdateStatusResponseModel;
import com.aree.restaurant.app.model.Singleton.Singleton;
import com.aree.restaurant.app.utils.Utils;
import com.aree.restaurant.app.views.DialogMessageOneButton;

import java.io.File;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Admin on 13/6/2560.
 */

public class Restful2<T>    {

    private HttpLoggingInterceptor logging;
    private OkHttpClient.Builder httpClient;
    private Context context;
    private String className;
    private String TAG="Restful";
    private OnResponseFromServer callBack;
    private ProgressDialog progressDoalog;
    private int code;
    private Call<T> call;


    public void createService(Context context ,T object ,final T CallbackListener )
    {

        callBack = (OnResponseFromServer) CallbackListener;
        logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder();
        httpClient.retryOnConnectionFailure(false);
        httpClient.connectTimeout(30, TimeUnit.SECONDS);
        httpClient.readTimeout(30, TimeUnit.SECONDS);
        httpClient.writeTimeout(30, TimeUnit.SECONDS);
        httpClient.addInterceptor(logging);
        className = context.getClass().getSimpleName();
        this.context=context;


        Log.e(TAG, "className: "+className );

        switch(className)
        {
//            case "SplashScreenActivity":
//
//                if(object instanceof GetOpenningTimeRequestModel)
//                    GetOpenDailyTime(GetOpenningTimeRequestModel.class.cast(object));
//                else if(object instanceof GetHolidayRequsetModel)
//                    GetHoliday(GetHolidayRequsetModel.class.cast(object));
//                break;

            case "RegisterActivity":
                RegisterService(RegisterRequestModel.class.cast(object));
                break;
            case "EditProfileActivity":
                EditProfileService( EditProfileRequestModel.class.cast(object) );
                break;
            case "MainActivity":
                if(object instanceof LoginRequestModel)
                    LoginService(LoginRequestModel.class.cast(object));
                else if(object instanceof ForgetPasswordRequestModel)
                    ForgetpasswordService(ForgetPasswordRequestModel.class.cast(object));
                else if(object instanceof SetPhotoProfileRequestModel )
                    SetPhotoService( SetPhotoProfileRequestModel.class.cast(object)  );
                else if(object instanceof GetOpenningTimeRequestModel)
                    GetOpenDailyTime(GetOpenningTimeRequestModel.class.cast(object));
                else if(object instanceof GetHolidayRequsetModel)
                    GetHoliday(GetHolidayRequsetModel.class.cast(object));

                else if(object instanceof UpdatePointRequestModel)
                    updatePoint(UpdatePointRequestModel.class.cast(object));

                break;
            case "OrderActivity":
                if(object instanceof GetMenuRequestModel)
                    GetAllMenuService(GetMenuRequestModel.class.cast(object));
                else if( object instanceof GetPromotionRequestModel)
                    GetPromotionService();
                else if(object instanceof GetOpenningTimeRequestModel)
                    GetOpenDailyTime(GetOpenningTimeRequestModel.class.cast(object));
                else if(object instanceof GetHolidayRequsetModel)
                    GetHoliday(GetHolidayRequsetModel.class.cast(object));
                else
                    GetMenuFromPromotionService(GetMenuFromPromotionRequestModel.class.cast(object));


                break;
            case "ShoppingCartActivity":
                PaymentService(PaymentRequestModel.class.cast(object));
                break;

            case  "AdminActivity":
                if(object instanceof GetAllOrderRequestModel)
                    // กำลังแก้
                    GetAllOrderForAdmin(GetAllOrderRequestModel.class.cast(object));
                else if(object instanceof GetAllMenuRequestModel)
                    //กำลังแก้
                    GetAllMenuForAdmin(GetAllMenuRequestModel.class.cast(object));
                else if(object instanceof GetSaleSummaryRequestModel)
                    //กำลังแก้
                    GetSaleSummaryForAdmin(GetSaleSummaryRequestModel.class.cast(object));
                else if(object instanceof GetHistoryRequestModel)
                    //กำลังแก้
                    GetSaleHistoryForAdmin(GetHistoryRequestModel.class.cast(object));
                else if(object instanceof UpdateStatusRequestModel)
                    //กำลังแก้
                    UpdateStatus(UpdateStatusRequestModel.class.cast(object));
                else if(object instanceof DeleteOrderRequestModel)
                    //กำลังแก้
                    DeleteOrder(DeleteOrderRequestModel.class.cast(object));
                else if(object instanceof SetHolidayRequsetModel)
                    //กำลังแก้
                    SetHoliday(SetHolidayRequsetModel.class.cast(object));
                else if(object instanceof GetHolidayRequsetModel)
                    //กำลังแก้
                    GetHoliday(GetHolidayRequsetModel.class.cast(object));
                else if((object instanceof GetRewardRequestModel))
                    //กำลังแก้
                    GetRewardForAdmin(GetRewardRequestModel.class.cast(object));
                else if((object instanceof SetRewardStatusRequestModel))
                    //กำลังแก้
                    UpdateRewardStatus(SetRewardStatusRequestModel.class.cast(object));
                break;

            case "OrderDetailActivity":
                if(object instanceof UpdateStatusRequestModel)
                    UpdateStatus(UpdateStatusRequestModel.class.cast(object));
                break;

            case "HistoryForCustomerActivity":
                GetHistoryForCustomer(GetAllOrderRequestModel.class.cast(object));
                break;
            case "RedeemRewardActivity":
                GetReward(GetRewardRequestModel.class.cast(object));
                break;





        }



    }


    public void RegisterService(RegisterRequestModel requestModel )
    {

        Utils.showProgressDialog(context,"กำลังดาวโหลดข้อมูล");
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.register).addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);

        Call<RegisterResponseModel> call = requestService.register(
                requestModel.getName(), requestModel.getLastName(),
                requestModel.getCompanyName(), requestModel.getFloorNumber(),
                requestModel.getPhoneNumber(), requestModel.getCompanyPhoneNumber(),
                requestModel.getDate(), requestModel.getUserName(),
                requestModel.getPassword(), requestModel.getPhoto(),
                "0","1"
        );




        call.enqueue(new Callback<RegisterResponseModel>()
        {
            @Override
            public void onResponse(Call<RegisterResponseModel> call, final Response<RegisterResponseModel> response)
            {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable()
                {
                    public void run()
                    {
                        Utils.dismissDialog();
                        code = response.code();
                        if (code >= 200 && code < 300)
                        {
                            RegisterResponseModel model = response.body();
                            if(model.getCode().equals("0") )
                            {

                                DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                                dialog.showDialog(context.getString(R.string.label_regist),context.getString(R.string.label_regist_success));
                                dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                                    @Override
                                    public void onClickConfirm() {
                                        callBack.onResponse();
                                    }
                                });

                            }
                            else
                            {

                                DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                                dialog.showDialog(context.getString(R.string.label_regist),context.getString(R.string.label_email_exist) );
                                dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                                    @Override
                                    public void onClickConfirm() {
                                        callBack.onBodyErrorIsNull();
                                    }
                                });

                            }
                        }
                        else if (code >= 400 && code < 500)
                        {
                            DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                            dialog.showDialog(context.getString(R.string.label_cilent_fail)
                                    ,context.getString(R.string.label_fail_to_connect_server));
                            dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                                @Override
                                public void onClickConfirm() {
                                    callBack.onBodyErrorIsNull();
                                }
                            });
                        }
                        else if (code >= 500 && code < 600)
                        {
                            DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                            dialog.showDialog(context.getString(R.string.label_server_fail)
                                    ,context.getString(R.string.label_server_fail_to_response));
                            dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                                @Override
                                public void onClickConfirm() {
                                    callBack.onBodyErrorIsNull();
                                }
                            });
                        }
                    }
                }, 2000);




            }

            @Override
            public void onFailure(Call<RegisterResponseModel> call, Throwable t)
            {
                Utils.dismissDialog();
                Log.e("Restful", "onFailure RegisterService :" + t.toString());
                checkException( t );
            }


        });


    }


    public void LoginService(final LoginRequestModel requestModel )
    {

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.login)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        Call<LoginResponseModel> call = requestService.login(requestModel.getUsername().toString()
                , requestModel.getPassword().toString() , requestModel.getPushToken()  );

//        progressDoalog = new ProgressDialog(context);
//        progressDoalog.setCancelable(false);
//        progressDoalog.setMessage("กรุณารอสักครู่...");
//        progressDoalog.setTitle("กำลังโหลดข้อมูล");
//        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        progressDoalog.show();




        call.enqueue(new Callback<LoginResponseModel>()
        {
            @Override
            public void onResponse(Call<LoginResponseModel> call, final Response<LoginResponseModel> response)
            {
//                Handler handler = new Handler();
//                handler.postDelayed(new Runnable()
//                {
//                    public void run()
//                    {
//                        progressDoalog.dismiss();

                        code = response.code();
                        if (code >= 200 && code < 300)
                        {

                            LoginResponseModel model  ;
                            model = response.body();

//                            String mockModel = new Gson().toJson(model);
//                            UserInformation list = new Gson().fromJson(mockModel,UserInformation.class);
//                            Singleton.getInstance().setUserInformation(list);
//                            Singleton.getInstance().getUserInformation().setEmail(requestModel.getUsername());

                            if(model.getCode().equals("0") || model.getCode().equals("1")  )
                                callBack.onResponse( model );
                            else
                            {

                                DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                                dialog.showDialog(context.getString(R.string.label_login),context.getString(R.string.label_login_unsuccessfully) );
                                dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                                    @Override
                                    public void onClickConfirm() {
                                        callBack.onBodyErrorIsNull();
                                    }
                                });

                            }
                        }
                        else if (code >= 400 && code < 500)
                        {
                            DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                            dialog.showDialog(context.getString(R.string.label_cilent_fail)
                                    ,context.getString(R.string.label_fail_to_connect_server));
                            dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                                @Override
                                public void onClickConfirm() {
                                    callBack.onBodyErrorIsNull();
                                }
                            });
                        }
                        else if (code >= 500 && code < 600)
                        {
                            DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                            dialog.showDialog(context.getString(R.string.label_server_fail)
                                    ,context.getString(R.string.label_server_fail_to_response));
                            dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                                @Override
                                public void onClickConfirm() {
                                    callBack.onBodyErrorIsNull();
                                }
                            });
                        }


//                    }
//                }, 2000);


            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t)
            {
//                progressDoalog.dismiss();
                Log.e(TAG, "onFailure LoginService: "+t.toString() );
                checkException( t );

            }


        });

    }

    public void EditProfileService(EditProfileRequestModel requestModel )
    {

        Utils.showProgressDialog(context,"กำลังดาวโหลดข้อมูล");

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.editProfile).
                addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        Call<EditProfileResponseModel> call = requestService.editProfile(
                requestModel.getName(), requestModel.getLastName(),
                requestModel.getCompanyName(), requestModel.getFloorNumber(),
                requestModel.getPhoneNumber(), requestModel.getCompanyPhoneNumber(),
                requestModel.getDate(), requestModel.getUserName(),
                requestModel.getPassword(), requestModel.getPhoto(),
                requestModel.getPoint(), requestModel.getTypeID()
        );


        call.enqueue(new Callback<EditProfileResponseModel>()
        {
            @Override
            public void onResponse(Call<EditProfileResponseModel> call, final Response<EditProfileResponseModel> response)
            {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable()
                {
                    public void run()
                    {
                        code = response.code();
                        if (code >= 200 && code < 300)
                        {
                            Utils.dismissDialog();
                            EditProfileResponseModel model = response.body();
                            if(model.getCode().equals("0") )
                            {

                                DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                                dialog.showDialog(context.getString(R.string.label_title_edit_profile),context.getString(R.string.label_edit_succesfully));
                                dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                                    @Override
                                    public void onClickConfirm() {
                                        callBack.onResponse();
                                    }
                                });

                            }
                            else
                            {

                                DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                                dialog.showDialog(context.getString(R.string.label_regist),context.getString(R.string.label_email_exist) );
                                dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                                    @Override
                                    public void onClickConfirm() {
                                        callBack.onBodyErrorIsNull();
                                    }
                                });

                            }
                        }
                        else if (code >= 400 && code < 500)
                        {
                            Utils.dismissDialog();
                            DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                            dialog.showDialog(context.getString(R.string.label_cilent_fail)
                                    ,context.getString(R.string.label_fail_to_connect_server));
                            dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                                @Override
                                public void onClickConfirm() {
                                    callBack.onBodyErrorIsNull();
                                }
                            });
                        }
                        else if (code >= 500 && code < 600)
                        {
                            Utils.dismissDialog();
                            DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                            dialog.showDialog(context.getString(R.string.label_server_fail)
                                    ,context.getString(R.string.label_server_fail_to_response));
                            dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                                @Override
                                public void onClickConfirm() {
                                    callBack.onBodyErrorIsNull();
                                }
                            });
                        }


                    }
                }, 2000);



            }

            @Override
            public void onFailure(Call<EditProfileResponseModel> call, Throwable t)
            {
                Utils.dismissDialog();
                Log.e(TAG, "onFailure EditProfileService: "+t.toString() );
                checkException( t );

            }


        });

    }



    public void ForgetpasswordService(ForgetPasswordRequestModel requestModel )
    {
        Utils.showProgressDialog(context,"กำลังส่งโหลดข้อมูล");
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.forget).
                addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        Call<ForgetPasswordResponseModel> call = requestService.forgetPassword( requestModel.getEmail().toString() );



        call.enqueue(new Callback<ForgetPasswordResponseModel>()
        {
            @Override
            public void onResponse(Call<ForgetPasswordResponseModel> call, Response<ForgetPasswordResponseModel> response)
            {

                code = response.code();
                if (code >= 200 && code < 300)
                {
                    ForgetPasswordResponseModel model = response.body();
                    Utils.dismissDialog();
                    if(model.getCode().equals("0") )
                    {

                        DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                        dialog.showDialog("รหัสผ่านของคุณ","รหัสผ่านถูกส่งไปยังอีเมล์ของคุณแล้ว");
                        dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                            @Override
                            public void onClickConfirm() {
                                callBack.onResponse();
                            }
                        });

                    }
                    else
                    {

                        DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                        dialog.showDialog("รหัสผ่านของคุณ","ไม่พบบัญชีนี้อยู่ในระบบ");
                        dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                            @Override
                            public void onClickConfirm() {
                                callBack.onBodyErrorIsNull();
                            }
                        });

                    }
                }
                else if (code >= 400 && code < 500)
                {
                    Utils.dismissDialog();
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_cilent_fail)
                            ,context.getString(R.string.label_fail_to_connect_server));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }
                else if (code >= 500 && code < 600)
                {
                    Utils.dismissDialog();
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_server_fail)
                            ,context.getString(R.string.label_server_fail_to_response));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<ForgetPasswordResponseModel> call, Throwable t)
            {
                Utils.dismissDialog();
                Log.e(TAG, "onFailure ForgetpasswordService: "+t.toString() );
                checkException( t );

            }


        });

    }


    public void GetAllMenuService(GetMenuRequestModel requestModel )
    {


        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.getMenu)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        Call<GetMenuResponseModel> call = requestService.getAllMenu(requestModel.getType());


        call.enqueue(new Callback<GetMenuResponseModel>()
        {
            @Override
            public void onResponse(Call<GetMenuResponseModel> call, Response<GetMenuResponseModel> response)
            {

                // @:TODO catch null from server
                code = response.code();
                if (code >= 200 && code < 300)
                {
                    if( !response.body().getAllMenu().isEmpty() )
                    {
                        GetMenuResponseModel model = new GetMenuResponseModel() ;
                        model.setAllMenu(response.body().getAllMenu());
                        callBack.onResponse(model);
                    }

                }
                else if (code >= 400 && code < 500)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_cilent_fail)
                            ,context.getString(R.string.label_fail_to_connect_server));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }
                else if (code >= 500 && code < 600)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_server_fail)
                            ,context.getString(R.string.label_server_fail_to_response));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }


            }

            @Override
            public void onFailure(Call<GetMenuResponseModel> call, Throwable t)
            {


                Log.e(TAG, "onFailure GetAllMenuService: "+t.toString() );
                checkException( t );

            }


        });

    }


    public void GetPromotionService()
    {

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.getPromotion)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        Call<GetPromotionResponseModel> call = requestService.getPromotion( );


        call.enqueue(new Callback<GetPromotionResponseModel>()
        {
            @Override
            public void onResponse(Call<GetPromotionResponseModel> call, Response<GetPromotionResponseModel> response)
            {


                code = response.code();

                if (code >= 200 && code < 300)
                {
                    if(!response.body().getPromotion().isEmpty()) {
                        GetPromotionResponseModel model = new GetPromotionResponseModel();
                        model.setPromotion(response.body().getPromotion());
                        callBack.onResponse(model);
                    }


                }
                else if (code >= 400 && code < 500)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_cilent_fail)
                            ,context.getString(R.string.label_fail_to_connect_server));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }
                else if (code >= 500 && code < 600)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_server_fail)
                            ,context.getString(R.string.label_server_fail_to_response));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }


            }

            @Override
            public void onFailure(Call<GetPromotionResponseModel> call, Throwable t)
            {
                Log.e(TAG, "onFailure GetPromotionService: "+t.toString() );
                checkException( t );


            }


        });
    }


    public void GetMenuFromPromotionService(GetMenuFromPromotionRequestModel requestModel)
    {


        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.getPromotionMenu)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        Call<GetMenuFromPromotionResponseModel> call = requestService.getMenuFromPromotion( requestModel.getId() );


        call.enqueue(new Callback<GetMenuFromPromotionResponseModel>()
        {
            @Override
            public void onResponse(Call<GetMenuFromPromotionResponseModel> call, Response<GetMenuFromPromotionResponseModel> response)
            {

                code = response.code();

                Log.e(TAG, "GetMenuFromPromotionService: "+code );

                if (code >= 200 && code < 300)
                {
                    if( !response.body().getAllMenu().isEmpty() ) {
                        GetMenuFromPromotionResponseModel model = new GetMenuFromPromotionResponseModel();
                        model.setAllMenu(response.body().getAllMenu());
                        callBack.onResponse(model);
                    }


                }
                else if (code >= 400 && code < 500)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_cilent_fail)
                            ,context.getString(R.string.label_fail_to_connect_server));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }
                else if (code >= 500 && code < 600)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_server_fail)
                            ,context.getString(R.string.label_server_fail_to_response));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }


            }

            @Override
            public void onFailure(Call<GetMenuFromPromotionResponseModel> call, Throwable t)
            {
//                progressDoalog.dismiss();
                Log.e(TAG, "onFailure GetMenuFromPromotionService: "+t.toString() );
                checkException( t );

            }


        });
    }



    public void PaymentService(PaymentRequestModel requestModel)
    {
        progressDoalog = new ProgressDialog(context);
        progressDoalog.setCancelable(false);
        progressDoalog.setMessage("กรุณารอสักครู่...");
        progressDoalog.setTitle("กำลังโหลดข้อมูล");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.payment)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
//        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.payment)
//                .addConverterFactory(GsonConverterFactory.create()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        Call<PaymentResponseModel> call = requestService.payment(requestModel);


        call.enqueue(new Callback<PaymentResponseModel>()
        {
            @Override
            public void onResponse(Call<PaymentResponseModel> call, final Response<PaymentResponseModel> response)
            {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable()
                {
                    public void run()
                    {
                        progressDoalog.dismiss();
                        code = response.code();
                        if (code >= 200 && code < 300)
                        {
                            PaymentResponseModel model = new PaymentResponseModel() ;
                            model.setOid(response.body().getOid());
                            model.setPoint(response.body().getPoint());
                            callBack.onResponse(model);

                        }
                        else if (code >= 400 && code < 500)
                        {
                            DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                            dialog.showDialog(context.getString(R.string.label_cilent_fail)
                                    ,context.getString(R.string.label_fail_to_connect_server));
                            dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                                @Override
                                public void onClickConfirm() {
                                    callBack.onBodyErrorIsNull();
                                }
                            });
                        }
                        else if (code >= 500 && code < 600)
                        {
                            DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                            dialog.showDialog(context.getString(R.string.label_server_fail)
                                    ,context.getString(R.string.label_server_fail_to_response));
                            dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                                @Override
                                public void onClickConfirm() {
                                    callBack.onBodyErrorIsNull();
                                }
                            });
                        }

                    }
                }, 1500);


            }

            @Override
            public void onFailure(Call<PaymentResponseModel> call, Throwable t)
            {
                progressDoalog.dismiss();
                Log.e(TAG, "onFailure PaymentService: "+t.toString() );
                checkException( t );

            }


        });
    }


    public void SetPhotoService(SetPhotoProfileRequestModel requestModel)
    {

        progressDoalog = new ProgressDialog(context);
        progressDoalog.setCancelable(false);
        progressDoalog.setMessage("กรุณารอสักครู่...");
        progressDoalog.setTitle("กำลังโหลดข้อมูล");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();

        File file = new File(requestModel.getBase64Image());
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);



        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.setPhotoProfile)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        Call<SetProfilePhotoResponseModel> call = requestService.uploadProfilePhoto(requestModel.getUid() , body );


        call.enqueue(new Callback<SetProfilePhotoResponseModel>()
        {
            @Override
            public void onResponse(Call<SetProfilePhotoResponseModel> call, final Response<SetProfilePhotoResponseModel> response)
            {

                Handler handler = new Handler();
                handler.postDelayed(new Runnable()
                {
                    public void run()
                    {
                        progressDoalog.dismiss();
                        SetProfilePhotoResponseModel model = new SetProfilePhotoResponseModel();





                        code = response.code();
                        model = response.body();

                        if (code >= 200 && code < 300)
                        {

                            if(!response.body().getPhoto().isEmpty())
                            {

//                                Log.e(TAG, "onResponse: "+model.getPhoto() );
                                if(model.getCode().equals("0"))
                                {
                                    model = response.body();
                                    Singleton.getInstance().getUserInformation().setPhoto( model.getPhoto() );
                                    callBack.onResponse();
                                }
                            }



                        }
                        else if (code >= 400 && code < 500)
                        {
                            DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                            dialog.showDialog(context.getString(R.string.label_cilent_fail)
                                    ,context.getString(R.string.label_fail_to_connect_server));
                            dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                                @Override
                                public void onClickConfirm() {
                                    callBack.onBodyErrorIsNull();
                                }
                            });
                        }
                        else if (code >= 500 && code < 600)
                        {
                            DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                            dialog.showDialog(context.getString(R.string.label_server_fail)
                                    ,context.getString(R.string.label_server_fail_to_response));
                            dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                                @Override
                                public void onClickConfirm() {
                                    callBack.onBodyErrorIsNull();
                                }
                            });
                        }
                    }
                }, 2000);




            }

            @Override
            public void onFailure(Call<SetProfilePhotoResponseModel> call, Throwable t)
            {
                progressDoalog.dismiss();
                Log.e(TAG, "onFailure SetPhotoService: "+t.toString() );
                checkException( t );

            }


        });

    }


    public void GetAllOrderForAdmin(GetAllOrderRequestModel requestModel)
    {


        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.getAllOrderForAdmin)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        call = requestService.getAllOrderForAdmin( );


        call.enqueue(new Callback<T>()
        {
            @Override
            public void onResponse(Call<T> call, Response<T> response)
            {

                //GetAllOrderResponseModel
                code = response.code();
                if (code >= 200 && code < 300)
                {

                    GetAllOrderResponseModel responseModel = (GetAllOrderResponseModel)response.body();

                    if(!responseModel.getOrder().isEmpty())
                    {
                        if(responseModel.getCode().equals("0")) {
//                            GetAllOrderResponseModel responseModel = response.body();
                            Singleton.getInstance().setAllOrder(responseModel);
                            callBack.onResponse();
                        }
                    }
                    else
                        callBack.onBodyErrorIsNull();

                }
                else if (code >= 400 && code < 500)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_cilent_fail)
                            ,context.getString(R.string.label_fail_to_connect_server));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }
                else if (code >= 500 && code < 600)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_server_fail)
                            ,context.getString(R.string.label_server_fail_to_response));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }


            }

            @Override
            public void onFailure(Call<T> call, Throwable t)
            {
                Log.e(TAG, "onFailure GetAllOrderForAdmin: "+t.toString() );
                checkException( t );

            }


        });
    }

    public void GetAllMenuForAdmin(GetAllMenuRequestModel requestModel)
    {


        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.getAllMenuForAdmin)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        call = requestService.getAllMenuForAdmin( );


        call.enqueue(new Callback<T>()
        {
            @Override
            public void onResponse(Call<T> call, Response<T> response)
            {

                //GetAllMenuResponseModel
                code = response.code();
                if (code >= 200 && code < 300)
                {
                    if(response!=null)
                    {
                        GetAllMenuResponseModel responseModel = (GetAllMenuResponseModel)response.body();

                        if(responseModel.getCode().equals("0"))
                        {
                            Log.e(TAG, "callBack.onResponse( responseModel )" );
//                            GetAllMenuResponseModel responseModel = response.body();
                            callBack.onResponse( responseModel );
                        }
                        else {
                            Log.e(TAG, "onResponse" );
                            callBack.onResponse();
                        }

                    }
                    else {
                        Log.e(TAG, "onBodyErrorIsNull" );
                        callBack.onBodyErrorIsNull();
                    }



                }
                else if (code >= 400 && code < 500)
                {
                    callBack.onBodyErrorIsNull();
//                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
//                    dialog.showDialog(context.getString(R.string.label_cilent_fail)
//                            ,context.getString(R.string.label_fail_to_connect_server));
//                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
//                        @Override
//                        public void onClickConfirm() {
//                            callBack.onBodyErrorIsNull();
//                        }
//                    });
                }
                else if (code >= 500 && code < 600)
                {
                    callBack.onBodyErrorIsNull();

//                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
//                    dialog.showDialog(context.getString(R.string.label_server_fail)
//                            ,context.getString(R.string.label_server_fail_to_response));
//                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
//                        @Override
//                        public void onClickConfirm() {
//                            callBack.onBodyErrorIsNull();
//                        }
//                    });
                }


            }

            @Override
            public void onFailure(Call<T> call, Throwable t)
            {
                  callBack.onFailure(t);

//                Log.e(TAG, "onFailure GetAllMenuForAdmin: "+t.toString() );
//                checkException( t );

            }


        });
    }

    public  void GetSaleSummaryForAdmin(GetSaleSummaryRequestModel requestModel)
    {

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.getSaleSummaryForAdmin)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        call = requestService.getSaleSummaryForAdmin( );


        call.enqueue(new Callback<T>()
        {
            @Override
            public void onResponse(Call<T> call, Response<T> response)
            {

                code = response.code();
                if (code >= 200 && code < 300)
                {

                    if(response!=null)
                    {
                        GetSaleSummaryResponseModel responseModel =(GetSaleSummaryResponseModel) response.body();

                        if(responseModel.getCode().equals("0"))
                        {
                            callBack.onResponse(responseModel);
                        }
                        else
                            callBack.onResponse();

                    }
                    else
                        callBack.onBodyErrorIsNull();



                }
                else if (code >= 400 && code < 500)
                {
                    callBack.onBodyErrorIsNull();

//                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
//                    dialog.showDialog(context.getString(R.string.label_cilent_fail)
//                            ,context.getString(R.string.label_fail_to_connect_server));
//                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
//                        @Override
//                        public void onClickConfirm() {
//                            callBack.onBodyErrorIsNull();
//                        }
//                    });
                }
                else if (code >= 500 && code < 600)
                {
                    callBack.onBodyErrorIsNull();

//                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
//                    dialog.showDialog(context.getString(R.string.label_server_fail)
//                            ,context.getString(R.string.label_server_fail_to_response));
//                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
//                        @Override
//                        public void onClickConfirm() {
//                            callBack.onBodyErrorIsNull();
//                        }
//                    });
                }

            }

            @Override
            public void onFailure(Call<T> call, Throwable t)
            {
                callBack.onFailure(t);

//                Log.e(TAG, "onFailure GetSaleSummaryForAdmin: "+t.toString() );
//                checkException( t );

            }


        });

    }

    public void GetSaleHistoryForAdmin(GetHistoryRequestModel requestModel)
    {

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.getSaleHistoryForAdmin)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        call = requestService.getSaleHistoryForAdminForAdmin( );


        call.enqueue(new Callback<T>()
        {
            @Override
            public void onResponse(Call<T> call, Response<T> response)
            {

                code = response.code();
                if (code >= 200 && code < 300)
                {

                    if(response!=null)
                    {
                        GetHistoryResponseModel responseModel = (GetHistoryResponseModel) response.body();
                        if(responseModel.getCode().equals("0"))
                        {
                            callBack.onResponse(responseModel);
                        }
                        else
                            callBack.onResponse( );


                    }
                    else
                        callBack.onBodyErrorIsNull();




                }
                else if (code >= 400 && code < 500)
                {
                    callBack.onBodyErrorIsNull();

//                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
//                    dialog.showDialog(context.getString(R.string.label_cilent_fail)
//                            ,context.getString(R.string.label_fail_to_connect_server));
//                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
//                        @Override
//                        public void onClickConfirm() {
//                            callBack.onBodyErrorIsNull();
//                        }
//                    });
                }
                else if (code >= 500 && code < 600)
                {
                    callBack.onBodyErrorIsNull();

//                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
//                    dialog.showDialog(context.getString(R.string.label_server_fail)
//                            ,context.getString(R.string.label_server_fail_to_response));
//                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
//                        @Override
//                        public void onClickConfirm() {
//                            callBack.onBodyErrorIsNull();
//                        }
//                    });
                }


            }

            @Override
            public void onFailure(Call<T> call, Throwable t)
            {
                callBack.onFailure(t);
//                Log.e(TAG, "onFailure GetSaleHistoryForAdmin: "+t.toString() );
//                checkException( t );

            }


        });
    }


    public void UpdateStatus(UpdateStatusRequestModel requestModel)
    {

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.upStatus)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        call = requestService.updateStatusForAdmin( requestModel.getOid() , requestModel.getStatus() );


        call.enqueue(new Callback<T>()
        {
            @Override
            public void onResponse(Call<T> call, Response<T> response)
            {

                code = response.code();
                if (code >= 200 && code < 300)
                {

                    if(response!=null)
                    {
                        UpdateStatusResponseModel responseModel = (UpdateStatusResponseModel) response.body();

                        if (responseModel.getCode().equals("0"))
                            callBack.onResponse();
                        else if (responseModel.getCode().equals("1")) {
                            DialogMessageOneButton dialog = new DialogMessageOneButton(context);
                            dialog.showDialog(context.getString(R.string.label_title_cant_update_status)
                                    , context.getString(R.string.label_message_cant_update_status));
                            dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                                @Override
                                public void onClickConfirm() {
                                    callBack.onBodyErrorIsNull();
                                }
                            });
                        } else
                            callBack.onBodyErrorIsNull();
                    }
                    else
                        callBack.onBodyErrorIsNull();


                }
                else if (code >= 400 && code < 500)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_cilent_fail)
                            ,context.getString(R.string.label_fail_to_connect_server));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }
                else if (code >= 500 && code < 600)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_server_fail)
                            ,context.getString(R.string.label_server_fail_to_response));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }


            }

            @Override
            public void onFailure(Call<T> call, Throwable t)
            {
//                progressDoalog.dismiss();
                Log.e(TAG, "onFailure UpdateStatus: "+t.toString() );
                checkException( t );

            }


        });
    }


    public void DeleteOrder(DeleteOrderRequestModel requestModel)
    {

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.deleteOrder)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        call = requestService.deleteOrderForAdmin( requestModel.getOid(),requestModel.getStatus() );


        call.enqueue(new Callback<T>()
        {
            @Override
            public void onResponse(Call<T> call, Response<T> response)
            {

                code = response.code();
                if (code >= 200 && code < 300)
                {

                    if(response!=null)
                    {
                         DeleteOrderResponseModel responseModel = (DeleteOrderResponseModel) response.body();
//                        progressDoalog.dismiss();
                        if (responseModel.getCode().equals("0"))
                            callBack.onResponse();
                        else
                            callBack.onBodyErrorIsNull();
                    }
                    else
                        callBack.onBodyErrorIsNull();



                }
                else if (code >= 400 && code < 500)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_cilent_fail)
                            ,context.getString(R.string.label_fail_to_connect_server));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }
                else if (code >= 500 && code < 600)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_server_fail)
                            ,context.getString(R.string.label_server_fail_to_response));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }



            }

            @Override
            public void onFailure(Call<T> call, Throwable t)
            {
                progressDoalog.dismiss();
                Log.e(TAG, "onFailure UpdateStatus: "+t.toString() );
                checkException( t );

            }


        });
    }




    public void GetHistoryForCustomer(GetAllOrderRequestModel requestModel)
    {

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.getHistoryForCustomer)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        Call<GetAllOrderResponseModel> call = requestService.getHistoryForCustomer( Singleton.getInstance().getUserInformation().getUid() );


        call.enqueue(new Callback<GetAllOrderResponseModel>()
        {
            @Override
            public void onResponse(Call<GetAllOrderResponseModel> call, Response<GetAllOrderResponseModel> response)
            {

                code = response.code();
                if (code >= 200 && code < 300)
                {

                    if(response!=null)
                    {
                        Log.e(TAG, "getCode: "+response.body().getCode() );
                        if(response.body().getCode().equals("0"))
                        {
                            GetAllOrderResponseModel responseModel = response.body();
                            Singleton.getInstance().setAllOrder(responseModel);
                            Log.e(TAG, "onResponse: "+Singleton.getInstance().getAllOrder().getOrder().size() );
                            callBack.onResponse();
                        }
                        else
                            callBack.onBodyErrorIsNull();
                    }
                    else
                        callBack.onBodyErrorIsNull();

                }
                else if (code >= 400 && code < 500)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_cilent_fail)
                            ,context.getString(R.string.label_fail_to_connect_server));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }
                else if (code >= 500 && code < 600)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_server_fail)
                            ,context.getString(R.string.label_server_fail_to_response));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<GetAllOrderResponseModel> call, Throwable t)
            {
                Log.e(TAG, "onFailure GetAllOrderForAdmin: "+t.toString() );
                checkException( t );

            }






        });
    }



    public void GetReward(GetRewardRequestModel requestModel)
    {

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.getReward)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        Call<GetRewardResposeModel> call = requestService.getReward( );


        call.enqueue(new Callback<GetRewardResposeModel>()
        {
            @Override
            public void onResponse(Call<GetRewardResposeModel> call, Response<GetRewardResposeModel> response)
            {


                code = response.code();
                if (code >= 200 && code < 300)
                {

                    if(response!=null)
                    {
                        if(response.body().getCode().equals("0"))
                        {
                            GetRewardResposeModel responseModel = response.body();
                            callBack.onResponse(responseModel);

                        }
                        else
                            callBack.onBodyErrorIsNull();
                    }
                    else
                        callBack.onBodyErrorIsNull();
                }
                else if (code >= 400 && code < 500)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_cilent_fail)
                            ,context.getString(R.string.label_fail_to_connect_server));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }
                else if (code >= 500 && code < 600)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_server_fail)
                            ,context.getString(R.string.label_server_fail_to_response));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<GetRewardResposeModel> call, Throwable t)
            {
                Log.e(TAG, "onFailure GetAllOrderForAdmin: "+t.toString() );
                checkException( t );

            }






        });
    }


    public void SetHoliday(SetHolidayRequsetModel requsetModel)
    {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.setHoliday)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        call = requestService.setHoliday(requsetModel);


        call.enqueue(new Callback<T>()
        {
            @Override
            public void onResponse(Call<T> call, Response<T> response)
            {


                code = response.code();
                if (code >= 200 && code < 300)
                {

                    if(response!=null)
                    {
                        SetHolidayResponseModel responseModel = (SetHolidayResponseModel) response.body();
                        if(responseModel.getCode().equals("0"))
                        {
//                            SetHolidayResponseModel responseModel = response.body();
                            callBack.onResponse(responseModel);

                        }
                        else
                            callBack.onBodyErrorIsNull();

                    }
                    else
                        callBack.onBodyErrorIsNull();
                }
                else if (code >= 400 && code < 500)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_cilent_fail)
                            ,context.getString(R.string.label_fail_to_connect_server));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }
                else if (code >= 500 && code < 600)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_server_fail)
                            ,context.getString(R.string.label_server_fail_to_response));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }

            }

            @Override
            public void onFailure(Call<T> call, Throwable t)
            {
                Log.e(TAG, "onFailure GetAllOrderForAdmin: "+t.toString() );
                checkException( t );

            }






        });

    }


    public void GetHoliday(GetHolidayRequsetModel requsetModel)
    {

        Log.e(TAG, "GetHoliday: " );
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.getHoliday)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        call = requestService.getHoliday( );


        call.enqueue(new Callback<T>()
        {
            @Override
            public void onResponse(Call<T> call, Response<T> response)
            {

                //GetHolidayResponseModel
                code = response.code();
                if (code >= 200 && code < 300)
                {

                    if(response!=null)
                    {
                        GetHolidayResponseModel responseModel = (GetHolidayResponseModel) response.body();
                        if(responseModel.getCode().equals("0"))
                        {
//                            GetHolidayResponseModel responseModel = response.body();
                            callBack.onResponse(responseModel);

                        }
                        else
                            callBack.onBodyErrorIsNull();

                    }
                    else
                        callBack.onBodyErrorIsNull();
                }
                else if (code >= 400 && code < 500)
                {
//                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
//                    dialog.showDialog(context.getString(R.string.label_cilent_fail)
//                            ,context.getString(R.string.label_fail_to_connect_server));
//                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
//                        @Override
//                        public void onClickConfirm() {
//                            callBack.onBodyErrorIsNull();
//                        }
//                    });
                }
                else if (code >= 500 && code < 600)
                {
//                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
//                    dialog.showDialog(context.getString(R.string.label_server_fail)
//                            ,context.getString(R.string.label_server_fail_to_response));
//                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
//                        @Override
//                        public void onClickConfirm() {
//                            callBack.onBodyErrorIsNull();
//                        }
//                    });
                }

            }

            @Override
            public void onFailure(Call<T> call, Throwable t)
            {
//                Log.e(TAG, "onFailure GetAllOrderForAdmin: "+t.toString() );
//                checkException( t );

            }






        });

    }




    public void GetOpenDailyTime(GetOpenningTimeRequestModel requsetModel)
    {

        Log.e(TAG, "GetHoliday: " );
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.getOpenDaily)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        Call<GetOpenningTimeResponseModel> call = requestService.getOpenningTime( );


        call.enqueue(new Callback<GetOpenningTimeResponseModel>()
        {
            @Override
            public void onResponse(Call<GetOpenningTimeResponseModel> call, Response<GetOpenningTimeResponseModel> response)
            {


                code = response.code();
                if (code >= 200 && code < 300)
                {

                    if(response!=null)
                    {
                        if(response.body().getCode().equals("0"))
                        {
                            GetOpenningTimeResponseModel responseModel = response.body();
                            callBack.onResponse(responseModel);

                        }
                        else
                            callBack.onBodyErrorIsNull();


                    }
                    else
                        callBack.onBodyErrorIsNull();

                }
                else if (code >= 400 && code < 500)
                {
//                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
//                    dialog.showDialog(context.getString(R.string.label_cilent_fail)
//                            ,context.getString(R.string.label_fail_to_connect_server));
//                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
//                        @Override
//                        public void onClickConfirm() {
//                            callBack.onBodyErrorIsNull();
//                        }
//                    });
                }
                else if (code >= 500 && code < 600)
                {
                    if( Utils.isProgressDialogShowing()==false )
                    {
                            DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                            dialog.showDialog(context.getString(R.string.label_server_fail)
                                    ,context.getString(R.string.label_server_fail_to_response));
                            dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                                @Override
                                public void onClickConfirm() {
                                    callBack.onBodyErrorIsNull();
                                }
                            });
                    }

                }

            }

            @Override
            public void onFailure(Call<GetOpenningTimeResponseModel> call, Throwable t)
            {
                checkException( t );

            }






        });

    }





    public void checkException(Throwable t)
    {
        if (t instanceof SocketException)
        {
            if(Utils.isProgressDialogShowing()==false) {
                Utils.showFailConnectionDialog(context);
                callBack.onFailure(t);
            }
        }
        else if (t instanceof SocketTimeoutException)
        {
            if(Utils.isProgressDialogShowing()==false) {

                Utils.showFailConnectionDialog(context);
                callBack.onFailure(t);
            }
        }
        else if (t instanceof ConnectException)
        {
            if(Utils.isProgressDialogShowing()==false) {
                Utils.showFailConnectionDialog(context);
                callBack.onFailure(t);
            }
        }
        else if (t instanceof UnknownHostException)
        {
            if(Utils.isProgressDialogShowing()==false) {
                Utils.internetIsNotAvailableDialog(context);
                callBack.onFailedConnection();
            }
        }
        else
        {
            if(Utils.isProgressDialogShowing()==false) {
                Utils.showFailConnectionDialog(context);
                callBack.onFailure(t);
            }
        }


    }




    public void GetRewardForAdmin(GetRewardRequestModel requestModel)
    {

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.getRewardForAdmin)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        call = requestService.getReward( );


        call.enqueue(new Callback<T>()
        {
            @Override
            public void onResponse(Call<T> call, Response<T> response)
            {


                code = response.code();
                if (code >= 200 && code < 300)
                {

                    if(response!=null)
                    {
                        GetRewardResposeModel responseModel = (GetRewardResposeModel) response.body() ;
                        if(responseModel.getCode().equals("0"))
                        {
//                            GetRewardResposeModel responseModel = response.body();
                            callBack.onResponse(responseModel);

                        }
                        else
                            callBack.onBodyErrorIsNull();
                    }
                    else
                        callBack.onBodyErrorIsNull();
                }
                else if (code >= 400 && code < 500)
                {
//                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
//                    dialog.showDialog(context.getString(R.string.label_cilent_fail)
//                            ,context.getString(R.string.label_fail_to_connect_server));
//                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
//                        @Override
//                        public void onClickConfirm() {
//                            callBack.onBodyErrorIsNull();
//                        }
//                    });
                }
                else if (code >= 500 && code < 600)
                {
//                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
//                    dialog.showDialog(context.getString(R.string.label_server_fail)
//                            ,context.getString(R.string.label_server_fail_to_response));
//                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
//                        @Override
//                        public void onClickConfirm() {
//                            callBack.onBodyErrorIsNull();
//                        }
//                    });
                }

            }

            @Override
            public void onFailure(Call<T> call, Throwable t)
            {
                Log.e(TAG, "onFailure GetAllOrderForAdmin: "+t.toString() );
//                checkException( t );

            }






        });
    }





    public void UpdateRewardStatus(SetRewardStatusRequestModel requestModel)
    {

        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.setRewardStatusForAdmin)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        call = requestService.updateRewardStatusForAdmin( requestModel.getId() , requestModel.getStatus() );


        call.enqueue(new Callback<T>()
        {
            @Override
            public void onResponse(Call<T> call, Response<T> response)
            {

                code = response.code();
                if (code >= 200 && code < 300)
                {
                    if(response!=null)
                    {
                        SetRewardStatusResponseModel responseModel =(SetRewardStatusResponseModel) response.body();
                        if (responseModel.getCode().equals("0"))
                            callBack.onResponse();
                    }

                    else
                        callBack.onBodyErrorIsNull();


                }
                else if (code >= 400 && code < 500)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_cilent_fail)
                            ,context.getString(R.string.label_fail_to_connect_server));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }
                else if (code >= 500 && code < 600)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_server_fail)
                            ,context.getString(R.string.label_server_fail_to_response));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }


            }

            @Override
            public void onFailure(Call<T> call, Throwable t)
            {
//                progressDoalog.dismiss();
                Log.e(TAG, "onFailure UpdateStatus: "+t.toString() );
                checkException( t );

            }


        });
    }

    public void updatePoint(UpdatePointRequestModel requestModel)
    {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(RestfulProperties.getPoint)
                .addConverterFactory(GsonConverterFactory.create()).client(httpClient.build()).build();
        final ApiEndpointInterface requestService = retrofit.create(ApiEndpointInterface.class);
        Call<LoginResponseModel> call = requestService.getProfile( requestModel.getUid()  );


        call.enqueue(new Callback<LoginResponseModel>()
        {
            @Override
            public void onResponse(Call<LoginResponseModel> call, final Response<LoginResponseModel> response)
            {


                code = response.code();
                if (code >= 200 && code < 300)
                {

                    LoginResponseModel model  ;
                    model = response.body();


                    if(model.getCode().equals("0") || model.getCode().equals("1")  )
                        callBack.onResponse( model );
                    else
                    {

                        DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                        dialog.showDialog(context.getString(R.string.label_login),context.getString(R.string.label_login_unsuccessfully) );
                        dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                            @Override
                            public void onClickConfirm() {
                                callBack.onBodyErrorIsNull();
                            }
                        });

                    }
                }
                else if (code >= 400 && code < 500)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_cilent_fail)
                            ,context.getString(R.string.label_fail_to_connect_server));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }
                else if (code >= 500 && code < 600)
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( context );
                    dialog.showDialog(context.getString(R.string.label_server_fail)
                            ,context.getString(R.string.label_server_fail_to_response));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            callBack.onBodyErrorIsNull();
                        }
                    });
                }



            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t)
            {
                Log.e(TAG, "onFailure LoginService: "+t.toString() );
                checkException( t );

            }


        });
    }



    public void isSuccessfully()
    {

    }



















}
