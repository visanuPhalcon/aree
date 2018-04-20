package com.aree.restaurant.app.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.databinding.ActivityShoppingCartBinding;
import com.aree.restaurant.app.fragment.ShoppingCart.StepOneFragment;
import com.aree.restaurant.app.fragment.ShoppingCart.StepThreeFragment;
import com.aree.restaurant.app.fragment.ShoppingCart.StepTwoFragment;
import com.aree.restaurant.app.views.CustomActionBar;

public class ShoppingCartActivity extends BaseActivity {

    ActivityShoppingCartBinding binding;
    static CustomActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_shopping_cart);
        initInstance();
    }

    public void initInstance()
    {
        setSupportActionBar(binding.included.toolbar);
        actionBar = new CustomActionBar(this, getSupportActionBar());
        actionBar.setTitleBar(getString(R.string.label_cart_shop));
        actionBar.setDisplayHomeAsUpEnabled();
        replaceFragment(StepTwoFragment.newInstance(),"StepTwoFragment");
    }

}
