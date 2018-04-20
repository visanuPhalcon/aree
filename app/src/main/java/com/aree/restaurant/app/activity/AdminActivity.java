package com.aree.restaurant.app.activity;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.telephony.SignalStrength;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.adapter.admin.AdminAdapter;
import com.aree.restaurant.app.databinding.ActivityAdminBinding;
import com.aree.restaurant.app.fragment.FragmentLifeCycle;
import com.aree.restaurant.app.model.Singleton.Singleton;
import com.aree.restaurant.app.model.Singleton.UserInformation;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.utils.Utils;
import com.aree.restaurant.app.views.CustomActionBar;
import com.aree.restaurant.app.views.DialogMessageOneButton;
import com.ogaclejapan.smarttablayout.SmartTabLayout;

public class AdminActivity extends BaseActivity {


    ActivityAdminBinding binding;
    private CustomActionBar actionBar;
    private int TabPosition=0;
    private String[] menu_list;
    private AdminAdapter adapter;
    static boolean firstTimeRequest=false;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin);
        firstTimeRequest=false;

        if( Utils.checkConnection(AdminActivity.this)==true   )
        {
            initView();
        }
        else
        {
            if(Utils.isProgressDialogShowing()==false) {
                final DialogMessageOneButton dialog = new DialogMessageOneButton(AdminActivity.this);
                dialog.showDialog(getString(R.string.label_internet_isnot_available), getString(R.string.label_error_internet_isnot_available));
                dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                    @Override
                    public void onClickConfirm() {

                        finish();

                    }
                });
            }

        }


    }

    private void initView()
    {
        adapter = new AdminAdapter(getSupportFragmentManager());

        if(Singleton.getInstance().getUserInformation().getType_id()==2)
            menu_list = getResources().getStringArray(R.array.menu_staff);
        else
            menu_list = getResources().getStringArray(R.array.menu_admin);

        TabPosition = getIntent().getIntExtra("TabPosition",0);
        setSupportActionBar(binding.included.toolbar);
        actionBar = new CustomActionBar(this, getSupportActionBar());
        actionBar.setTitleBar(R.string.label_welcome);
        actionBar.setDisplayHomeAsUpEnabled();
        actionBar.setTextSize(18f);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setCurrentItem(TabPosition);
        binding.viewPager.setOffscreenPageLimit(1);
        initCustomTab();

    }



    private void initCustomTab()
    {
        binding.viewPagerTab.setCustomTabView(new SmartTabLayout.TabProvider()
        {
            @Override
            public View createTabView(ViewGroup container, int position, PagerAdapter adapter)
            {
                RelativeLayout layout = (RelativeLayout) LayoutInflater.from(AdminActivity.this).inflate(R.layout.custom_tab_icon_and_text, container, false);
                ImageView imageView = (ImageView) layout.findViewById(R.id.custom_tab_icon);
                TextView textView = (TextView) layout.findViewById(R.id.custom_tab_text);
                textView.setText(menu_list[position]);

//                Log.e("admin", "getType"+Singleton.getInstance().getUserInformation().getType_id()+"" );


                if(Singleton.getInstance().getUserInformation().getType_id()==2)
                {
                    switch (position)
                    {
                        case 0:
                            imageView.setImageResource(R.drawable.list);
                            break;
                        case 1:
                            imageView.setImageResource(R.drawable.menu_list);
                            break;

                    }
                }
                else
                {
                    switch (position) {
                        case 0:
                            imageView.setImageResource(R.drawable.list);
                            break;
                        case 1:
                            imageView.setImageResource(R.drawable.menu_list);
                            break;
                        case 2:
                            imageView.setImageResource(R.drawable.total);
                            break;
                        case 3:
                            imageView.setImageResource(R.drawable.history);
                            break;
                        case 4:
                            imageView.setImageResource(R.drawable.ic_date_range_white_36dp);
                            break;
                        case 5:
                            imageView.setImageResource(R.drawable.ic_redeem_white_36dp);
                            break;
                    }

                }



                return layout;
            }
        });


        binding.viewPagerTab.setViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }

            @Override
            public void onPageSelected(int position)
            {
                adapter.getInstance(position);
            }

            @Override
            public void onPageScrollStateChanged(int state)
            {
            }
        });


    }


    public void Logout()
    {
        Singleton.getInstance().setAllOrder(null);
        Singleton.getInstance().setAllMenu(null);
        finish();
    }




    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Logout();
        finish();


    }
}
