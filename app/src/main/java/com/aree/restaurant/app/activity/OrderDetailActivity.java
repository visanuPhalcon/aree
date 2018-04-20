package com.aree.restaurant.app.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.databinding.ActivityOrderDetailBinding;
import com.aree.restaurant.app.model.RequsetModel.admin.UpdateStatusRequestModel;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.aree.restaurant.app.model.Singleton.ShoppingCart;
import com.aree.restaurant.app.model.Singleton.Singleton;
import com.aree.restaurant.app.network.OnResponseFromServer;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.utils.FormatUtility;
import com.aree.restaurant.app.views.CustomActionBar;
import com.aree.restaurant.app.views.DialogMessageOneButton;
import com.aree.restaurant.app.views.OrderItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.parceler.Parcels;

import okhttp3.ResponseBody;

public class OrderDetailActivity extends BaseActivity {

    private static final String TAG = "OrderDetailActivity";
    private CustomActionBar actionBar;
    ActivityOrderDetailBinding binding;
    private AllOrderForAdminModel model;
    private UpdateStatusRequestModel requestModel;
    private Handler handler = new Handler();
    private  ProgressDialog progressDialog;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_detail);
        initView();
    }

    public void initView()
    {

        binding.btnOrder.setEnabled(false);
        model = Parcels.unwrap(getIntent().getParcelableExtra("model"));
        setSupportActionBar(binding.included.toolbar);
        actionBar = new CustomActionBar(this, getSupportActionBar());
        actionBar.setTitleBar(R.string.label_order_detail);
        actionBar.setDisplayHomeAsUpEnabled();


        binding.tvName.setText("คุณ "+model.getName()+" "+model.getLastname() );
        binding.tvAddress.setText( model.getCompanyName()+" "+model.getFloorNumber() );
        binding.tvPhoneNo.setText( model.getPhoneNumber() );
        Glide.with(OrderDetailActivity.this).load("http://areedang.com/aree/"+model.getPhoto()).diskCacheStrategy(DiskCacheStrategy.RESULT)
                .crossFade().into(binding.imgProfile);

        String [] menuName  = model.getFoodName().split(",");
        String [] totalPrice  = model.getTotal().split(",");
        String [] type  = model.getFoodType().split(",");
        String [] amount  = model.getFoodAmount().split(",");


        int size  = menuName.length;
        int toltalCost=0 ;
        OrderItem order;

        for(int i = 0 ; i < size ; i++ )
        {
            order = new OrderItem(amount[i],OrderDetailActivity.this, menuName[i] , totalPrice[i] , type[i] );


            if(type[i].equals("5"))
            {
                binding.ExchangePointForFoodList.addView(order);
            }
            else
            {
                binding.orderList.addView(order);
                toltalCost = toltalCost + Integer.parseInt( totalPrice[i] );

            }
        }

        binding.tvShippingCost.setText("฿ "+ FormatUtility.currencyFormat( "0" ));
        binding.tvTotalCost.setText("฿ "+ FormatUtility.currencyFormat(String.valueOf( toltalCost ) ));


        if(model.getStatus().equals("1")) {
            binding.btnOrder.setText(R.string.label_shipping);
            binding.btnOrder.setEnabled(true);
        }
        else if(model.getStatus().equals("0")) {
            binding.btnOrder.setText(R.string.label_get_order);
            binding.btnOrder.setEnabled(true);
        }
        else
        {
            binding.btnOrder.setEnabled(false);
            binding.btnOrder.setText(R.string.label_delivery_successfully);
           // binding.btnConfirm.setEnabled(false);
        }


        binding.btnOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                progressDialog = new ProgressDialog(OrderDetailActivity.this);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("กรุณารอสักครู่...");
                progressDialog.setTitle("กำลังโหลดข้อมูล");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                requestModel = new UpdateStatusRequestModel(model.getOid(), Integer.parseInt(model.getStatus()));
                new Restful<>().createService(OrderDetailActivity.this, requestModel, callback);

            }
        });


//        binding.btnConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                requestModel = new UpdateStatusRequestModel(model.getOid(), Integer.parseInt(model.getStatus()));
//                new Restful<>().createService(OrderDetailActivity.this, requestModel, callback);
//            }
//        });




    }



    // call back from updating status
    OnResponseFromServer callback = new OnResponseFromServer()
    {
        @Override
        public void onResponse()
        {
            if(model.getStatus().equals("1")) {
                binding.btnOrder.setText(R.string.label_delivery_successfully);
//                binding.btnConfirm.setEnabled(false);
                binding.btnOrder.setEnabled(false);
            }
            else if(model.getStatus().equals("0")) {
                model.setStatus("1");
                binding.btnOrder.setText(R.string.label_shipping);
//                binding.btnConfirm.setEnabled(true);
            }

            handler.postDelayed(new Runnable() {
                public void run()
                {
                    progressDialog.dismiss();
                }
            }, 1000);




        }

        @Override
        public void onResponse(Object object) {
            handler.postDelayed(new Runnable() {
                public void run()
                {
                    progressDialog.dismiss();
                }
            }, 1000);
        }


        @Override
        public void onBodyError(ResponseBody responseBodyError) {
            handler.postDelayed(new Runnable() {
                public void run()
                {
                    progressDialog.dismiss();
                }
            }, 1000);
        }

        @Override
        public void onBodyErrorIsNull() {
            handler.postDelayed(new Runnable() {
                public void run()
                {
                    progressDialog.dismiss();
                }
            }, 1000);
        }

        @Override
        public void onFailure(Throwable t) {
            handler.postDelayed(new Runnable() {
                public void run()
                {
                    progressDialog.dismiss();
                }
            }, 1000);
        }

        @Override
        public void onFailedConnection() {
            handler.postDelayed(new Runnable() {
                public void run()
                {
                    progressDialog.dismiss();
                }
            }, 1000);

        }


    };



}
