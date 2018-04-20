package com.aree.restaurant.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.aree.restaurant.app.activity.OrderActivity;
import com.aree.restaurant.app.fragment.BeveragesMenuFragment;
import com.aree.restaurant.app.fragment.DessertMenuFragment;
import com.aree.restaurant.app.fragment.NoodlesMenuFragment;
import com.aree.restaurant.app.fragment.PromotionMenuFragment;
import com.aree.restaurant.app.fragment.RiceMenuFragment;

/**
 * Created by Nanthakorn on 2/26/2017.
 */

public class OrderAdapter extends FragmentPagerAdapter {

    Fragment fragment;
    public OrderAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        Log.e("getItem", "position: "+position );



        if(position==0)
            fragment = PromotionMenuFragment.newInstance();
        else if(position==1)
            fragment = RiceMenuFragment.newInstance();
        else if(position==2)
            fragment = NoodlesMenuFragment.newInstance();
        else if(position==3)
            fragment = DessertMenuFragment.newInstance();
        else if(position==4)
            fragment = BeveragesMenuFragment.newInstance();


        return fragment;

    }

    @Override
    public int getCount() {
        return 5;
    }
}
