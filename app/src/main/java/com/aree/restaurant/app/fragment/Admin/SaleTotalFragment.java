package com.aree.restaurant.app.fragment.Admin;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.BaseInputConnection;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.adapter.admin.SaleSummaryAdapter;
import com.aree.restaurant.app.databinding.FragmentSaleTotalBinding;
import com.aree.restaurant.app.fragment.FragmentLifeCycle;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.RequsetModel.admin.GetAllMenuRequestModel;
import com.aree.restaurant.app.model.RequsetModel.admin.GetSaleSummaryRequestModel;
import com.aree.restaurant.app.model.ResponseModel.admin.GetSaleSummaryResponseModel;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.aree.restaurant.app.network.OnResponseFromServer;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.utils.FormatUtility;
import com.aree.restaurant.app.utils.Utils;
import com.aree.restaurant.app.views.DialogMessageOneButton;
import com.aree.restaurant.app.views.LinearLayoutWrapContent;

import java.util.Calendar;
import java.util.HashMap;

import okhttp3.ResponseBody;


public class SaleTotalFragment extends Fragment  {



    FragmentSaleTotalBinding binding;
    GetSaleSummaryResponseModel responseModel;
    GetSaleSummaryRequestModel requestModel = new GetSaleSummaryRequestModel();
    static  SaleTotalFragment fragment ;
    SaleSummaryAdapter adapter;
    private Restful restful = new Restful();


    public static SaleTotalFragment newInstance()
    {
        fragment = new SaleTotalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    public static SaleTotalFragment getInstance() {
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_sale_total, container, false);
        View rootview = binding.getRoot();

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutWrapContent(getActivity(), LinearLayoutWrapContent.VERTICAL, false) );
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        binding.tvTotal.setText("฿ "+"0");
        binding.SwipeRefresh.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.colorOrange));
//        binding.SwipeRefresh.setRefreshing(true);
        binding.SwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                refreshView();
            }
        });


        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        binding.tvDate.setText("ยอดขายประจำวันที่ "+day+"/"+(month+1)+"/"+( year+543 ) );


//        new Restful<>().createService(getActivity() , requestModel  , callback);
        return rootview ;
    }

    public void initView()
    {
        if(adapter==null)
        {

            adapter = new SaleSummaryAdapter(getActivity(), responseModel.getAllOrder(), new SaleSummaryAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(AllOrderForAdminModel item, int position) {

                }
            });

        }
        else
        {
            adapter.addList( responseModel.getAllOrder() );
            adapter.notifyDataSetChanged();
        }

        binding.recyclerView.setAdapter(adapter);




    }


    public void refreshView()
    {
        Utils.showProgressDialog(getActivity(),"ดาวน์โหลดข้อมูล...");
        binding.SwipeRefresh.setRefreshing(false);
        restful.createService(getActivity() , requestModel  , callback);

    }


    // call back from restful service
    OnResponseFromServer callback = new OnResponseFromServer()
    {
        @Override
        public void onResponse() {
            oneSecBeforeDismiss();
        }

        @Override
        public void onResponse(Object object) {

            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            int month = calendar.get(Calendar.MONTH);
            int year = calendar.get(Calendar.YEAR);
            binding.tvDate.setText("ยอดขายประจำวันที่ "+day+"/"+(month+1)+"/"+( year+543 ) );


            responseModel = (GetSaleSummaryResponseModel) object ;
            Log.e("jay", "size: "+responseModel.getAllOrder().size() );
            int size  = responseModel.getAllOrder().size();
            int size2 = 0 ;
            int total = 0 ;

            String[] food_totalPrice ;
            String[] food_type ;

            for(int i  = 0 ; i<size ; i++)
            {
                food_type = responseModel.getAllOrder().get(i).getFoodType().trim().split(",");
                food_totalPrice = responseModel.getAllOrder().get(i).getTotal().trim().split(",");
                size2 = food_totalPrice.length;
                for(int z = 0 ; z<size2 ; z++)
                {
                    if(Integer.parseInt(food_type[z])!=5)
                        total = total + Integer.parseInt(food_totalPrice[z]);
                }
            }


            binding.tvTotal.setText("");
            binding.tvTotal.setText("฿ "+FormatUtility.currencyFormat(String.valueOf(total)));
            initView();

            oneSecBeforeDismiss();
            adapter.notifyItemRangeChanged(0,responseModel.getAllOrder().size() );



        }


        @Override
        public void onBodyError(ResponseBody responseBodyError) {
            oneSecBeforeDismiss();
        }

        @Override
        public void onBodyErrorIsNull() {

            oneSecBeforeDismiss();
        }

        @Override
        public void onFailure(Throwable t) {

            oneSecBeforeDismiss();
        }

        @Override
        public void onFailedConnection() {

            oneSecBeforeDismiss();
        }


    };


    public void oneSecBeforeDismiss()
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Utils.dismissDialog();
            }
        }, 1000);
    }



}
