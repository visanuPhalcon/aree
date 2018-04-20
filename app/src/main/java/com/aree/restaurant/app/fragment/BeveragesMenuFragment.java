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
import com.aree.restaurant.app.databinding.FragmentBeveragesMenuBinding;
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
public class BeveragesMenuFragment extends Fragment  {

    FragmentBeveragesMenuBinding binding;
    GetMenuResponseModel responseModel ;


    public static BeveragesMenuFragment newInstance()
    {
        Bundle args = new Bundle();
        BeveragesMenuFragment fragment = new BeveragesMenuFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_beverages_menu, container, false);
        View rootview = binding.getRoot();
        initView();
        return rootview ;
    }


    public void initView()
    {

        ((OrderActivity)getActivity()).setEnableBtnConfirm( );
        if(responseModel==null) {
            GetMenuRequestModel requestModel = new GetMenuRequestModel();
            requestModel.setType(4);
            new Restful<>().createService(getActivity() , requestModel  , callback);
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
                        ((OrderActivity)getActivity()).addList(item, amount);
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
