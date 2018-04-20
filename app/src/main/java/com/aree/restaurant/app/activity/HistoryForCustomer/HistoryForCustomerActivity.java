package com.aree.restaurant.app.activity.HistoryForCustomer;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.activity.BaseActivity;
import com.aree.restaurant.app.activity.OrderDetailActivity;
import com.aree.restaurant.app.adapter.HistoryForCustomer.HistoryForCustomerAdapter;
import com.aree.restaurant.app.adapter.admin.AllOrderAdapter;
import com.aree.restaurant.app.databinding.ActivityHistoryForCustomerBinding;
import com.aree.restaurant.app.databinding.FragmentAllOrderBinding;
import com.aree.restaurant.app.fragment.Admin.AllOrderFragment;
import com.aree.restaurant.app.model.RequsetModel.admin.DeleteOrderRequestModel;
import com.aree.restaurant.app.model.RequsetModel.admin.GetAllOrderRequestModel;
import com.aree.restaurant.app.model.RequsetModel.admin.UpdateStatusRequestModel;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.aree.restaurant.app.model.Singleton.Singleton;
import com.aree.restaurant.app.network.OnResponseFromServer;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.views.CustomActionBar;
import com.aree.restaurant.app.views.DialogMessageTwoButton;
import com.aree.restaurant.app.views.LinearLayoutWrapContent;

import org.parceler.Parcels;

import okhttp3.ResponseBody;

public class HistoryForCustomerActivity extends BaseActivity
{

    private CustomActionBar actionBar;
    ActivityHistoryForCustomerBinding binding;
    private HistoryForCustomerAdapter adapter;
    GetAllOrderRequestModel requestModel = new GetAllOrderRequestModel();

    int pos ;
    private Handler handler = new Handler();


    @Override
    protected void onResume()
    {
        super.onResume();
        binding.SwipeRefresh.setRefreshing(true);
        new Restful<>().createService(HistoryForCustomerActivity.this , requestModel  , callback);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_history_for_customer);
        initView();


    }

    public void initView()
    {
        setSupportActionBar(binding.included.toolbar);
        actionBar = new CustomActionBar(this, getSupportActionBar());
        actionBar.setTitleBar(getString(R.string.label_order_history));
        actionBar.setDisplayHomeAsUpEnabled();
        actionBar.setTextSize(18f);

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutWrapContent(HistoryForCustomerActivity.this, LinearLayoutWrapContent.VERTICAL, false) );
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(HistoryForCustomerActivity.this, DividerItemDecoration.VERTICAL));
        binding.SwipeRefresh.setColorSchemeColors(ContextCompat.getColor(HistoryForCustomerActivity.this, R.color.colorOrange));

//        binding.SwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh()
//            {
//
//                if (adapter.getSize()!=0 && adapter!=null)
//                    adapter.clearAllItem();
//
//                new Restful<>().createService(HistoryForCustomerActivity.this , requestModel  , callback);
//
//            }
//        });

    }

    public void initRecyclerView()
    {

        if(Singleton.getInstance().getAllOrder()!=null)
            adapter = new HistoryForCustomerAdapter(HistoryForCustomerActivity.this, new HistoryForCustomerAdapter.OnItemClickListener()
            {
                @Override
                public void onItemClick(AllOrderForAdminModel item, int position)
                {
                    Intent intent = new Intent(HistoryForCustomerActivity.this, HistoryDetailActivity.class);
                    intent.putExtra("model", Parcels.wrap(item));
                    startActivity(intent);
                }



            });



        Drawable drawable = ContextCompat.getDrawable(HistoryForCustomerActivity.this,R.drawable.ic_search_black_24dp);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(HistoryForCustomerActivity.this,R.color.colorGreen));
        binding.etSearchID.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);

        adapter.setHasStableIds(true);
        binding.recyclerView.setAdapter(adapter);
        binding.etSearchID.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                adapter.filter( s.toString()  );
            }
        });


    }



    // call back from restful service
    OnResponseFromServer callback = new OnResponseFromServer()
    {
        @Override
        public void onResponse()
        {

            initRecyclerView();
            handler.postDelayed(new Runnable() {
                public void run()
                {
                    binding.SwipeRefresh.setEnabled(false);
                    binding.SwipeRefresh.setRefreshing(false);
                }
            }, 1000);
        }

        @Override
        public void onResponse(Object object) {

            handler.postDelayed(new Runnable() {
                public void run()
                {
                    binding.SwipeRefresh.setEnabled(false);
                    binding.SwipeRefresh.setRefreshing(false);
                }
            }, 1000);
        }


        @Override
        public void onBodyError(ResponseBody responseBodyError) {

            handler.postDelayed(new Runnable() {
                public void run()
                {
                    binding.SwipeRefresh.setRefreshing(false);
                }
            }, 1000);
        }

        @Override
        public void onBodyErrorIsNull() {

            handler.postDelayed(new Runnable() {
                public void run()
                {
                    binding.SwipeRefresh.setEnabled(false);
                    binding.SwipeRefresh.setRefreshing(false);
                }
            }, 1000);
        }

        @Override
        public void onFailure(Throwable t) {
            handler.postDelayed(new Runnable() {
                public void run()
                {
                    binding.SwipeRefresh.setRefreshing(false);
                }
            }, 1000);
        }

        @Override
        public void onFailedConnection() {
            handler.postDelayed(new Runnable() {
                public void run()
                {
                    binding.SwipeRefresh.setEnabled(false);
                    binding.SwipeRefresh.setRefreshing(false);
                }
            }, 1000);

        }


    };

}
