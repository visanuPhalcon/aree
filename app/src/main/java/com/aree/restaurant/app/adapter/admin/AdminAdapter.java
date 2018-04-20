package com.aree.restaurant.app.adapter.admin;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.aree.restaurant.app.fragment.Admin.AllOrderFragment;
import com.aree.restaurant.app.fragment.Admin.HistoryAdminFragment;
import com.aree.restaurant.app.fragment.Admin.ManageRedeemFragment;
import com.aree.restaurant.app.fragment.Admin.MenuListFragment;
import com.aree.restaurant.app.fragment.Admin.SaleTotalFragment;
import com.aree.restaurant.app.fragment.Admin.SetTimeFragment;
import com.aree.restaurant.app.model.Singleton.Singleton;

/**
 * Created by Nanthakorn on 2/26/2017.
 */

public class AdminAdapter extends FragmentStatePagerAdapter {

    Fragment fragment;
    public AdminAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position)
    {
        Log.e("getItem", "position: "+position );

        if(Singleton.getInstance().getUserInformation().getType_id()==0)
        {
            if (position == 0) {
                fragment = AllOrderFragment.newInstance();
            }
            else if (position == 1)
                fragment = MenuListFragment.newInstance();
            else if (position == 2)
                fragment = SaleTotalFragment.newInstance();
            else if (position == 3)
                fragment = HistoryAdminFragment.newInstance();
            else if (position == 4)
                fragment = SetTimeFragment.newInstance();
            else if (position == 5)
                fragment = ManageRedeemFragment.newInstance();
        }
        else
        {
            if (position == 0)
                fragment = AllOrderFragment.newInstance();
            else if (position == 1)
                fragment = MenuListFragment.newInstance();
        }


        return fragment;

    }

    public void getInstance(int position)
    {
        Log.e("getInstance", "position: "+position );
        if(Singleton.getInstance().getUserInformation().getType_id()==0)
        {
            if(position==0) {
                AllOrderFragment.getInstance().refreshView();
            }
            else if(position==1)
                MenuListFragment.getInstance().refreshView();
            else if(position==2)
                SaleTotalFragment.getInstance().refreshView();
            else if(position==3)
                HistoryAdminFragment.getInstance().refreshView();
            else if(position==4)
            {
                SetTimeFragment.getInstance().refreshView();
            }
            else if (position == 5)
                ManageRedeemFragment.getInstance().refreshView();
        }
        else
        {
            if(position==0) {
                AllOrderFragment.getInstance().refreshView();
            }
            else if(position==1)
                MenuListFragment.getInstance().refreshView();
        }




    }


//    @Override
//    public int getItemPosition(Object object) {
//        return POSITION_NONE;
//    }



    @Override
    public int getCount()
    {
        int count = 0;
        if(Singleton.getInstance().getUserInformation().getType_id()==0)
            count = 6;
        if(Singleton.getInstance().getUserInformation().getType_id()==2)
            count =  2;

        return count;


    }
}
