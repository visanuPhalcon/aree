package com.aree.restaurant.app.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.activity.OrderActivity;
import com.aree.restaurant.app.adapter.MenuAdapter;
import com.aree.restaurant.app.databinding.FragmentRiceBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.RequsetModel.GetMenuRequestModel;
import com.aree.restaurant.app.model.ResponseModel.GetMenuResponseModel;
import com.aree.restaurant.app.model.Singleton.ShoppingCart;
import com.aree.restaurant.app.network.OnResponseFromServer;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.views.DialogMessageOneButton;
import com.aree.restaurant.app.views.FoodMenuDialog;


import org.parceler.Parcels;

import okhttp3.ResponseBody;


public class RiceMenuFragment extends Fragment {

    final String  TAG ="Jay" ;
    FragmentRiceBinding binding ;
    GetMenuResponseModel responseModel ;


    public static RiceMenuFragment newInstance()
    {
        RiceMenuFragment fragment = new RiceMenuFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_rice, container, false);
        View rootview = binding.getRoot();
        initView();
        return rootview ;

    }



    public void initView()
    {

        ((OrderActivity)getActivity()).setEnableBtnConfirm( );

        if(responseModel==null)
        {
            GetMenuRequestModel requestModel = new GetMenuRequestModel();
            requestModel.setType(1);
            new Restful<>().createService(getActivity(), requestModel, callback);
        }
        else
            initRecycleView();


//        MenuList.add(new MenuModel(R.drawable.test4,"ข้าวหมูเกาหลี",temp,"55",0));
//        MenuList.add(new MenuModel(R.drawable.test5,"ข้าวกระเพรา หมูสับ",temp,"55",0));
//        MenuList.add(new MenuModel(R.drawable.test6,"ข้าวกระเพรา ไก่",temp,"55",0));
//        MenuList.add(new MenuModel(R.drawable.test7,"ข้าวกระเพรา กุ้ง",temp,"77",0));
//        MenuList.add(new MenuModel(R.drawable.test8,"ข้าวกระเพรา ซีฟู๊ต",temp,"78",0));
//        MenuList.add(new MenuModel(R.drawable.test9,"ข้าวผัด หมู",temp,"55",0));
//        MenuList.add(new MenuModel(R.drawable.test10,"ข้าวผัด ไก่",temp,"55",0));
//        MenuList.add(new MenuModel(R.drawable.test11,"ข้าวผัด ปู",temp,"78",0));
//        MenuList.add(new MenuModel(R.drawable.test12,"ข้าวผัด ซีฟู๊ต",temp,"78",0));
//        MenuList.add(new MenuModel(R.drawable.test43,"ข้าวผัด กุ้ง",temp,"78",0));
//        MenuList.add(new MenuModel(R.drawable.test15,"ข้าวหมูน้ำตก",temp,"68",0));
//        MenuList.add(new MenuModel(R.drawable.test16,"ข้าวผัดอารีย์",temp,"68",0));


//        MenuList.add(new MenuModel(R.drawable.test17,"ข้าวผัดมันกุ้ง",temp,"88",0));
//        MenuList.add(new MenuModel(R.drawable.test18,"ข้าวกุ้งผัดผงกะหรี่",temp,"88",0));
//        MenuList.add(new MenuModel(R.drawable.test19,"ไข่ดาว",temp,"10",0));
//        MenuList.add(new MenuModel(R.drawable.test20,"ไข่เจียว",temp,"15",0));
//        MenuList.add(new MenuModel(R.drawable.test42,"ข้าวไก่ผัดพริก",temp,"55",0));


    }

    public void initRecycleView()
    {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2) );
        binding.recyclerView.setAdapter(new MenuAdapter(getActivity(),responseModel.getAllMenu(), new MenuAdapter.OnItemClickListener()
        {

            @Override
            public void onItemClick(final MenuModel item, int position)
            {

                FoodMenuDialog dialog = new FoodMenuDialog( getActivity(),item );
                dialog.initDialog();
                dialog.setOnConfirmListener(new FoodMenuDialog.OnDialogClickListener()
                {
                    @Override
                    public void onClickConfirm(int amount)
                    {
                        ((OrderActivity)getActivity()).addList(item,amount);
                        ((OrderActivity)getActivity()).setEnableBtnConfirm( );
                    }

                    @Override
                    public void onClickConfirm(int amount, MenuModel item)
                    {
                        ((OrderActivity)getActivity()).addList(item,amount);
                        ((OrderActivity)getActivity()).setEnableBtnConfirm( );
                    }
                });
            }
        }));

    }




    // call back from restful service
    OnResponseFromServer callback = new OnResponseFromServer()
    {
        @Override
        public void onResponse() {}

        @Override
        public void onResponse(Object object)
        {
            responseModel = (GetMenuResponseModel) object ;
            initRecycleView();
        }


        @Override
        public void onBodyError(ResponseBody responseBodyError) {
        }

        @Override
        public void onBodyErrorIsNull() {
        }

        @Override
        public void onFailure(Throwable t) {
        }

        @Override
        public void onFailedConnection() {
            getActivity().finish();
        }


    };









}
