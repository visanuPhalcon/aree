package com.aree.restaurant.app.fragment.Admin;


import android.content.Intent;
import android.databinding.DataBindingUtil;
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

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.activity.OrderDetailActivity;
import com.aree.restaurant.app.adapter.admin.AllMenuAdapter;
import com.aree.restaurant.app.adapter.admin.AllOrderAdapter;
import com.aree.restaurant.app.databinding.FragmentMenuListBinding;
import com.aree.restaurant.app.fragment.FragmentLifeCycle;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.RequsetModel.admin.GetAllMenuRequestModel;
import com.aree.restaurant.app.model.RequsetModel.admin.UpdateStatusRequestModel;
import com.aree.restaurant.app.model.ResponseModel.admin.GetAllMenuResponseModel;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.aree.restaurant.app.model.Singleton.ShoppingCart;
import com.aree.restaurant.app.model.Singleton.Singleton;
import com.aree.restaurant.app.network.OnResponseFromServer;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.utils.Utils;
import com.aree.restaurant.app.views.DialogMessageOneButton;
import com.aree.restaurant.app.views.DialogMessageTwoButton;
import com.aree.restaurant.app.views.LinearLayoutWrapContent;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuListFragment extends Fragment  {

    FragmentMenuListBinding binding;
    private GetAllMenuResponseModel responseModel;
    private AllMenuAdapter adapter;
    private HashMap<String,AllOrderForAdminModel> menuList = new HashMap<>();
    GetAllMenuRequestModel requestModel = new GetAllMenuRequestModel();
    static MenuListFragment fragment;
    private Restful restful = new Restful();

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

    public static MenuListFragment newInstance()
    {
        Bundle args = new Bundle();
        fragment = new MenuListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static MenuListFragment getInstance() {
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_menu_list, container, false);
        View rootview = binding.getRoot();
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutWrapContent(getActivity(), LinearLayoutWrapContent.VERTICAL, false) );
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        binding.SwipeRefresh.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.colorOrange));
        binding.SwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                binding.SwipeRefresh.setRefreshing(false);
                adapter.clearAllItem();
                refreshView();

            }
        });

        return rootview ;
    }



    public void refreshView()
    {
        Utils.showProgressDialog(getActivity(),"ดาวน์โหลดข้อมูล....");
        if(menuList.size()!=0)
            adapter.clearAllItem();
        restful.createService(getActivity() , requestModel  , callback);

    }


    public void initView()
    {
        if(menuList.size()!=0)
        {
                adapter = new AllMenuAdapter(getActivity(), menuList, new AllMenuAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(AllOrderForAdminModel item, int position) {
                    }


                });

                binding.recyclerView.setAdapter(adapter);
        }

    }


    // call back from restful service
    OnResponseFromServer callback = new OnResponseFromServer()
    {
        @Override
        public void onResponse()
        {

            initView();
            oneSecBeforeDismiss();

        }

        @Override
        public void onResponse(Object object)
        {
            responseModel = (GetAllMenuResponseModel) object;
            ArrayList<AllOrderForAdminModel> menu = responseModel.getAllMenu();

            int amount;
            int size  = menu.size();
            int size2 = 0;

            String[] food_id;
            String[] food_type ;
            String[] food_name ;
            String[] food_amount ;
            String[] food_price ;
            String[] food_totalPrice ;

            for(int i  = 0 ; i<size ; i++)
            {

                food_id = menu.get(i).getFoodId().split(",");
                food_type = menu.get(i).getFoodType().trim().split(",");
                food_name = menu.get(i).getFoodName().split(",");
                food_amount = menu.get(i).getFoodAmount().trim().split(",");
                food_price = menu.get(i).getFoodPrice().trim().split(",");
                food_totalPrice = menu.get(i).getTotal().trim().split(",");
                size2 = food_name.length;


                for(int z = 0 ; z < size2 ; z++)
                {
                    if(!menuList.containsKey( food_name[z]) )
                        menuList.put(food_name[z],new AllOrderForAdminModel( food_id[z],food_type[z],food_name[z],food_amount[z],food_price[z] , food_totalPrice[z] ) );
                    else
                    {
                        amount =  Integer.parseInt( menuList.get(food_name[z]).getFoodAmount() );
                        amount =  amount+Integer.parseInt( food_amount[z] );
                        menuList.get(food_name[z]).setFoodAmount(String.valueOf(amount));
                    }

                }

            }

//            Iterator entries = menuList.keySet().iterator();

//            while (entries.hasNext())
//            {
//                String key=(String)entries.next();
//                Log.e("Iterator", "getFoodName: "+menuList.get(key).getFoodName() );
//                Log.e("Iterator", "getFoodAmount: "+menuList.get(key).getFoodAmount() );
//            }


            initView();
            oneSecBeforeDismiss();



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



}
