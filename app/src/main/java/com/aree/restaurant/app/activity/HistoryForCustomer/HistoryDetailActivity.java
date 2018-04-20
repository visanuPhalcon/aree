package com.aree.restaurant.app.activity.HistoryForCustomer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.activity.BaseActivity;
import com.aree.restaurant.app.activity.OrderDetailActivity;
import com.aree.restaurant.app.databinding.ActivityHistoryDetailBinding;
import com.aree.restaurant.app.model.RequsetModel.admin.UpdateStatusRequestModel;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.utils.FormatUtility;
import com.aree.restaurant.app.views.CustomActionBar;
import com.aree.restaurant.app.views.OrderItem;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.parceler.Parcels;

public class HistoryDetailActivity extends BaseActivity {

    private CustomActionBar actionBar;
    ActivityHistoryDetailBinding binding;
    private AllOrderForAdminModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history_detail);
        initView();

    }

    public void initView()
    {



        model = Parcels.unwrap(getIntent().getParcelableExtra("model"));
        setSupportActionBar(binding.included.toolbar);
        actionBar = new CustomActionBar(this, getSupportActionBar());
        actionBar.setTitleBar(R.string.label_order_detail);
        actionBar.setDisplayHomeAsUpEnabled();


        String [] menuName  = model.getFoodName().split(",");
        String [] totalPrice  = model.getTotal().split(",");
        String [] type  = model.getFoodType().split(",");

        int size  = menuName.length;
        int toltalCost=0 ;
        OrderItem order;

        for(int i = 0 ; i < size ; i++ )
        {

            order = new OrderItem(HistoryDetailActivity.this, menuName[i] , totalPrice[i] ,type[i] );
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


    }

}
