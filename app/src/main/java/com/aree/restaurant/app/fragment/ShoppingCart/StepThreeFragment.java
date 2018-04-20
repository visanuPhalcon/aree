package com.aree.restaurant.app.fragment.ShoppingCart;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.activity.ShoppingCartActivity;
import com.aree.restaurant.app.databinding.FragmentStepThreeBinding;
import com.aree.restaurant.app.model.Singleton.ShoppingCart;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepThreeFragment extends Fragment {

    FragmentStepThreeBinding binding;
    String oid;

    public static StepThreeFragment newInstance(String oid)
    {

        Bundle args = new Bundle();
        StepThreeFragment fragment = new StepThreeFragment();
        fragment.oid = oid;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_step_three, container, false);
        View rootview = binding.getRoot();
        initInstance();
        return rootview;
    }

    public void initInstance()
    {

        ShoppingCart.getInstance().clearAllData();
        binding.btnGoToMain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getActivity().finish();
            }
        });
        binding.tvOrderID.setText( String.format(getString( R.string.label_call_center  ), oid ) );


    }



}
