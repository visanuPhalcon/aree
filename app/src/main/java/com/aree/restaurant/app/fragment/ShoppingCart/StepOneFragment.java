package com.aree.restaurant.app.fragment.ShoppingCart;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.activity.OrderActivity;
import com.aree.restaurant.app.activity.ShoppingCartActivity;
import com.aree.restaurant.app.databinding.FragmentStepOneBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class StepOneFragment extends Fragment
{
    FragmentStepOneBinding binding;

    public static StepOneFragment newInstance()
    {

        Bundle args = new Bundle();
        StepOneFragment fragment = new StepOneFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_step_one, container, false);
        View rootview = binding.getRoot();
        initInstance();
        return rootview;
    }

    public void initInstance()
    {
        binding.btnPickMore.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getActivity().finish();
            }
        });

        binding.btnConfirmOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ((ShoppingCartActivity) getActivity()  ).replaceFragmentWithBackStack(StepTwoFragment.newInstance(),"","StepTwoFragment");
            }
        });


    }

}
