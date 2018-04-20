package com.aree.restaurant.app.fragment.ShoppingCart;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.activity.OrderActivity;
import com.aree.restaurant.app.activity.ShoppingCartActivity;
import com.aree.restaurant.app.databinding.FragmentStepTwoBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.RequsetModel.GetMenuRequestModel;
import com.aree.restaurant.app.model.RequsetModel.PaymentRequestModel;
import com.aree.restaurant.app.model.RequsetModel.RegisterRequestModel;
import com.aree.restaurant.app.model.ResponseModel.GetMenuResponseModel;
import com.aree.restaurant.app.model.ResponseModel.PaymentResponseModel;
import com.aree.restaurant.app.model.Singleton.ShoppingCart;
import com.aree.restaurant.app.model.Singleton.Singleton;
import com.aree.restaurant.app.model.Singleton.UserInformation;
import com.aree.restaurant.app.network.OnResponseFromServer;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.views.DialogMessageOneButton;
import com.aree.restaurant.app.views.OrderItem;

import java.util.Iterator;

import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepTwoFragment extends Fragment {

    FragmentStepTwoBinding binding;
    PaymentResponseModel responseModel;

    public static StepTwoFragment newInstance()
    {

        Bundle args = new Bundle();

        StepTwoFragment fragment = new StepTwoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_step_two, container, false);
        View rootview = binding.getRoot();
        initInstance();

        //@TODO: send point to server
        Log.e("point", "getTotalCost: "+ShoppingCart.getInstance().getTotalCost() );
        Log.e("point", "getTotalCost: "+ShoppingCart.getInstance().getTotalCost()/50 );
        return rootview;
    }


    public void initInstance()
    {

        Iterator myVeryOwnIterator;
        String key="";
        myVeryOwnIterator =  ShoppingCart.getInstance().getMenuList().keySet().iterator();

        // add item into order
        while(myVeryOwnIterator.hasNext())
        {
            key = (String) myVeryOwnIterator.next();
            if(ShoppingCart.getInstance().getMenuList().get(key).getType()!=5)
            {
                OrderItem order = new OrderItem(getActivity(), ShoppingCart.getInstance().getMenuList().get(key));
                binding.orderList.addView(order);
            }
        }

        // add item into redeem
        myVeryOwnIterator =  ShoppingCart.getInstance().getMenuList().keySet().iterator();
        while(myVeryOwnIterator.hasNext())
        {
            key = (String) myVeryOwnIterator.next();
            if(ShoppingCart.getInstance().getMenuList().get(key).getType()==5)
            {
                OrderItem order = new OrderItem(getActivity(), ShoppingCart.getInstance().getMenuList().get(key));
                binding.ExchangePointForFoodList.addView(order);
            }
        }


        if(ShoppingCart.getInstance().getMenuList()!=null)
            binding.tvTotal.setText("฿ "+ ShoppingCart.getInstance().getTotalCost()+".00" );

        binding.btnConfirmOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                PaymentRequestModel requestModel = new PaymentRequestModel();
                requestModel.setPoint(ShoppingCart.getInstance().getTotalCost()/50);
                requestModel.setCostPoint(ShoppingCart.getInstance().getTotalPoint());
                new Restful<>().createService(getActivity() , requestModel , callback);
            }
        });

        binding.tvAddress.setText("ข้อมูลที่อยู่\nคุณ "+ Singleton.getInstance().getUserInformation().getName()+" "+Singleton.getInstance().getUserInformation().getLastName()
            + "\nอาคาร " + Singleton.getInstance().getUserInformation().getCompanyName()
            +"\nชั้น "+Singleton.getInstance().getUserInformation().getFloorNumber());






    }














    // call back from restful service
    OnResponseFromServer callback = new OnResponseFromServer()
    {
        @Override
        public void onResponse() {}

        @Override
        public void onResponse(Object object) {

            responseModel = (PaymentResponseModel) object;
//            Log.e("stepTwo", "getPoint: "+responseModel.getPoint() );
//            Singleton.getInstance().getUserInformation().setPoint( responseModel.getPoint()+"" );
            ((ShoppingCartActivity) getActivity()  ).replaceFragment(StepThreeFragment.newInstance(responseModel.getOid()),"StepThreeFragment");

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

        }


    };


}


