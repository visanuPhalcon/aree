package com.aree.restaurant.app.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.activity.OrderActivity;
import com.aree.restaurant.app.adapter.MenuAdapter;
import com.aree.restaurant.app.databinding.FragmentNoodlesMenuBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.RequsetModel.GetMenuRequestModel;
import com.aree.restaurant.app.model.ResponseModel.GetMenuResponseModel;
import com.aree.restaurant.app.model.Singleton.ShoppingCart;
import com.aree.restaurant.app.network.OnResponseFromServer;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.views.DialogMessageOneButton;
import com.aree.restaurant.app.views.FoodMenuDialog;

import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class NoodlesMenuFragment extends Fragment {


    FragmentNoodlesMenuBinding binding;
    GetMenuResponseModel responseModel ;

    public static NoodlesMenuFragment newInstance() {

        Bundle args = new Bundle();

        NoodlesMenuFragment fragment = new NoodlesMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_noodles_menu, container, false);
        View rootview = binding.getRoot();
        initView();
        return rootview ;
    }

    public void initView()
    {

        //        String imgFood, String nameMenu, String description, String price, int amount
//        MenuList = new ArrayList<MenuModel>();
//        MenuList.add(new MenuModel(R.drawable.test21,"ยำมาม่า",temp,"70",0));
//        MenuList.add(new MenuModel(R.drawable.test22,"ยำวุ้นเส้น",temp,"70",0));
//        MenuList.add(new MenuModel(R.drawable.test27,"ผัดซีอิ๊ว หมู",temp,"58",0));
//        MenuList.add(new MenuModel(R.drawable.test28,"ผัดซีอิ๊ว ไก่",temp,"58",0));
//        MenuList.add(new MenuModel(R.drawable.test29,"ผัดซีอิ๊ว ซีฟู๊ด",temp,"78",0));
//
//        MenuList.add(new MenuModel(R.drawable.test44,"คั่วไก่",temp,"58",0));
//        MenuList.add(new MenuModel(R.drawable.test45,"คั่วทะเล",temp,"78",0));
//        MenuList.add(new MenuModel(R.drawable.test47,"สปาเกตตี้ผัดขี้เมา",temp,"78",0));
//        MenuList.add(new MenuModel(R.drawable.test50,"สปาเกตตี้ซอสกุ้ง",temp,"88",0));
//        MenuList.add(new MenuModel(R.drawable.test55,"สุกี้แห้งรวมมิตร",temp,"78",0));

        if(responseModel==null) {
            GetMenuRequestModel requestModel  = new GetMenuRequestModel();
            requestModel.setType(2);
            new Restful<>().createService(getActivity(), requestModel, callback);
        }
        else
            initRecycleView();

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
                        ((OrderActivity)getActivity()).addList(item , amount);
                        ((OrderActivity)getActivity()).setEnableBtnConfirm();

                    }

                    @Override
                    public void onClickConfirm(int amount, MenuModel item) {

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
        public void onResponse(Object object) {

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
