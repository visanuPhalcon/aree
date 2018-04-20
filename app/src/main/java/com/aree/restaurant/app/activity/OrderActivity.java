package com.aree.restaurant.app.activity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.adapter.OrderAdapter;
import com.aree.restaurant.app.adapter.ShoppingCartAdapter;
import com.aree.restaurant.app.databinding.ActivityOrderBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.RequsetModel.GetOpenningTimeRequestModel;
import com.aree.restaurant.app.model.RequsetModel.admin.GetHolidayRequsetModel;
import com.aree.restaurant.app.model.ResponseModel.GetOpenningTimeResponseModel;
import com.aree.restaurant.app.model.ResponseModel.admin.GetHolidayResponseModel;
import com.aree.restaurant.app.model.Singleton.ShoppingCart;
import com.aree.restaurant.app.model.Singleton.Singleton;
import com.aree.restaurant.app.model.Singleton.UserInformation;
import com.aree.restaurant.app.network.OnResponseFromServer;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.utils.FormatUtility;
import com.aree.restaurant.app.utils.Utils;
import com.aree.restaurant.app.views.CustomActionBar;
import com.aree.restaurant.app.views.DialogMessageOneButton;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.Calendar;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import okhttp3.ResponseBody;

public class OrderActivity extends BaseActivity
{

    private static final String TAG = "OrderActivity";
    private int TabPosition=0;
    ActivityOrderBinding  binding;
    private String[] menu_list;
    private CustomActionBar actionBar;
    private ShoppingCartAdapter order ;
    private Iterator myVeryOwnIterator;
    private int price;
    private int amount;
    private int pointForUser;
    private String key;
    private ProgressDialog progressDoalog;

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        if (Singleton.getInstance().getUserInformation()!=null)
            pointForUser =Integer.parseInt( Singleton.getInstance().getUserInformation().getPoint() );



        binding.slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        if(ShoppingCart.getInstance().isRemove == true)
        {
            Log.e(TAG, "onResume: " );
            order.notifyItemRangeRemoved(0,ShoppingCart.getInstance().sizeBeforeRemove);
            ShoppingCart.getInstance().isRemove = false ;
            price = 0 ;
            setBasket(price);

        }

        setEnableBtnConfirm( );


    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order);

        if( Utils.checkConnection(OrderActivity.this)==true   )
        {
            initView();
        }
        else
        {
            final DialogMessageOneButton dialog = new DialogMessageOneButton( OrderActivity.this );
            dialog.showDialog( getString(R.string.label_internet_isnot_available), getString(R.string.label_error_internet_isnot_available));
            dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener()
            {
                @Override
                public void onClickConfirm() {

                    finish();

                }
            });

        }


    }

    private void initView()
    {

        if(Utils.isProgressDialogShowing()==false) {
            progressDoalog = new ProgressDialog(OrderActivity.this);
            progressDoalog.setCancelable(false);
            progressDoalog.setMessage("กรุณารอสักครู่...");
            progressDoalog.setTitle("กำลังโหลดข้อมูล");
            progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDoalog.show();
        }

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(OrderActivity.this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(order);


        TabPosition = getIntent().getIntExtra("TabPosition",0);
        setSupportActionBar(binding.included.toolbar);
        actionBar = new CustomActionBar(this, getSupportActionBar());
        actionBar.setTitleBar(R.string.label_food_list);
        actionBar.setDisplayHomeAsUpEnabled();

        menu_list = getResources().getStringArray(R.array.menu_list);
        binding.viewPager.setAdapter(new OrderAdapter(getSupportFragmentManager()));
        binding.viewPager.setCurrentItem(TabPosition);
//        binding.viewPager.setOffscreenPageLimit(5);
        initCustomTab();
        initSliding();
        binding.basketView.setPriceText("฿ "+"0");



        binding.basketView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(binding.slidingLayout.getPanelState()==SlidingUpPanelLayout.PanelState.COLLAPSED)
                    binding.slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                else
                    binding.slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);

            }
        });

        initShoppingCart();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable()
        {
                    public void run()
                    {
                        progressDoalog.dismiss();
                    }
        }, 2000);



    }

    private void initCustomTab()
    {
        binding.viewPagerTab.setCustomTabView(new SmartTabLayout.TabProvider()
        {
            @Override
            public View createTabView(ViewGroup container, int position, PagerAdapter adapter)
            {
                RelativeLayout layout = (RelativeLayout) LayoutInflater.from(OrderActivity.this).inflate(R.layout.custom_tab_icon_and_text, container, false);
                ImageView imageView = (ImageView) layout.findViewById(R.id.custom_tab_icon);
                TextView textView = (TextView) layout.findViewById(R.id.custom_tab_text);
                textView.setText(menu_list[position]);

                switch (position)
                {
                    case 0:
                        imageView.setImageResource(R.drawable.ic_promotion);
                        break;
                    case 1:
                        imageView.setImageResource(R.drawable.ic_rice);
                        break;
                    case 2:
                        imageView.setImageResource(R.drawable.ic_noodles);
                        break;
                    case 3:
                        imageView.setImageResource(R.drawable.ic_dessert);
                        break;
                    case 4:
                        imageView.setImageResource(R.drawable.ic_beverages);
                        break;
                }
                return layout;
            }
        });

        binding.viewPagerTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==0)
                    actionBar.setTitleBar(getString(R.string.label_promotion));
                else if(position==1)
                    actionBar.setTitleBar(getString(R.string.label_menu_rice));
                else if(position==2)
                    actionBar.setTitleBar(getString(R.string.label_menu_noodles));
                else if(position==3)
                    actionBar.setTitleBar(getString(R.string.label_menu_dessert));
                else if(position==4)
                    actionBar.setTitleBar(getString(R.string.label_menu_beverages));
                closeSliding();
            }

            @Override
            public void onPageSelected(int position)
            {
//                if(position==0)
//                    actionBar.setTitleBar(getString(R.string.label_promotion));
//                else if(position==1)
//                    actionBar.setTitleBar(getString(R.string.label_menu_rice));
//                else if(position==2)
//                    actionBar.setTitleBar(getString(R.string.label_menu_noodles));
//                else if(position==3)
//                    actionBar.setTitleBar(getString(R.string.label_menu_dessert));
//                else if(position==4)
//                    actionBar.setTitleBar(getString(R.string.label_menu_beverages));

                closeSliding();

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                closeSliding();

            }
        });

        binding.viewPagerTab.setViewPager(binding.viewPager);
    }

    private void initSliding()
    {

        binding.btnConfirmOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                    if(Singleton.getInstance().getUserInformation()!=null)
                    {
                        if(Singleton.getInstance().getUserInformation().getType_id() != 0)
                        {
                            Utils.showProgressDialog(OrderActivity.this, getString(R.string.downloading));
                            GetOpenningTimeRequestModel requestModel = new GetOpenningTimeRequestModel();
                            new Restful<>().createService(OrderActivity.this, requestModel, getOpenTime);
                        }
                        else
                        {
                            binding.btnConfirmOrder.setEnabled(false);
                            startActivity(new Intent(OrderActivity.this, ShoppingCartActivity.class));
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }

                    }
                    else
                    {
                        DialogMessageOneButton dialog = new DialogMessageOneButton( OrderActivity.this  );
                        dialog.showDialog(getString(R.string.label_log_in),getString(R.string.label_please_login_before_buying));
                        dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                            @Override
                            public void onClickConfirm() {
                                finish();
//                                startActivity(new Intent(OrderActivity.this, MainActivity.class));
                            }
                        });
                    }
            }
        });


        binding.slidingLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener()
        {
            @Override
            public void onPanelSlide(View panel, float slideOffset)
            {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState)
            {

                if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED)
                {
                    binding.imgArrow.setImageResource(R.drawable.ic_arrow_up);
                }
                else if (newState == SlidingUpPanelLayout.PanelState.EXPANDED)
                {
                    binding.imgArrow.setImageResource(R.drawable.ic_arrow_down);
                }
            }
        });
    }




//    public void addList(MenuModel menuModel,int amount)
//    {
//
//
//        price=0;
//        amount=0;
//
//        if(!ShoppingCart.getInstance().getMenuList().containsKey(menuModel.getMenuName())) {
//            ShoppingCart.getInstance().getMenuList().put(menuModel.getMenuName(), new MenuModel( menuModel.getMenuName() , menuModel.getPrice() ,
//                    menuModel.getAmount(),Integer.parseInt(menuModel.getPrice())
//                    , menuModel.getType() , menuModel.getId()  )
//            );
//
//            if(ShoppingCart.getInstance().getMenuList().get(menuModel.getMenuName()).getAmount()==0)
//                ShoppingCart.getInstance().getMenuList().get(menuModel.getMenuName()).setAmount(1);
//        }
//        else {
//            amount =  ShoppingCart.getInstance().getMenuList().get(menuModel.getMenuName()).getAmount();
//            amount=amount+1;
//            price=Integer.parseInt( menuModel.getPrice())*amount ;
//            ShoppingCart.getInstance().getMenuList().get(menuModel.getMenuName()).setTotal( price );
//            ShoppingCart.getInstance().getMenuList().get(menuModel.getMenuName()).setAmount( amount );
//        }
//
//
//        int size = ShoppingCart.getInstance().getMenuList().size();
//
//        order = new ShoppingCartAdapter(OrderActivity.this, ShoppingCart.getInstance().getMenuList(), new ShoppingCartAdapter.OnItemClickListener() {
//            @Override
//            public void OnRemoveItemAt(MenuModel item, int position)
//            {
//
//                ShoppingCart.getInstance().getMenuList().remove(item.getMenuName());
//                order.notifyItemRemoved(position);
//                binding.tvTotalMenu.setText(" "+ShoppingCart.getInstance().getMenuList().size()+" รายการ");
//                setBasket(ShoppingCart.getInstance().getTotalCost());
//                setEnableBtnConfirm(ShoppingCart.getInstance().getMenuList().size()>0);
//
//            }
//
//            @Override
//            public void OnIncreaseAmount(MenuModel item, int position) {
//                setBasket(ShoppingCart.getInstance().getTotalCost());
//            }
//
//            @Override
//            public void OnDecreaseAmount(MenuModel item, int position) {
//                setBasket(ShoppingCart.getInstance().getTotalCost());
//            }
//        });
//
//        binding.recyclerView.setAdapter(order);
//        order.notifyItemInserted( size );
//        setBasket(ShoppingCart.getInstance().getTotalCost());
//
//
//
//
//    }



    public void addList(MenuModel menuModel,int amount)
    {


        price=0;

        if(!ShoppingCart.getInstance().getMenuList().containsKey(menuModel.getMenuName()))
        {

            ShoppingCart.getInstance().getMenuList().put(menuModel.getMenuName(), new MenuModel( menuModel.getMenuName() , menuModel.getPrice() ,
                    amount,Integer.parseInt(menuModel.getPrice() )*amount
                    , menuModel.getType() , menuModel.getId()  )
            );

//            if(ShoppingCart.getInstance().getMenuList().get(menuModel.getMenuName()).getAmount()==0)
//                ShoppingCart.getInstance().getMenuList().get(menuModel.getMenuName()).setAmount(1);
        }
        else {

            price=Integer.parseInt( menuModel.getPrice())*amount ;
            ShoppingCart.getInstance().getMenuList().get(menuModel.getMenuName()).setTotal( price );
            ShoppingCart.getInstance().getMenuList().get(menuModel.getMenuName()).setAmount( amount );
        }


        int size = ShoppingCart.getInstance().getMenuList().size();

        order = new ShoppingCartAdapter(OrderActivity.this, ShoppingCart.getInstance().getMenuList(), new ShoppingCartAdapter.OnItemClickListener() {
            @Override
            public void OnRemoveItemAt(MenuModel item, int position)
            {

                ShoppingCart.getInstance().getMenuList().remove(item.getMenuName());
                order.notifyItemRemoved(position);
                binding.tvTotalMenu.setText(" "+ShoppingCart.getInstance().getMenuList().size()+" รายการ");
                setBasket(ShoppingCart.getInstance().getTotalCost());
                setEnableBtnConfirm( );

            }

            @Override
            public void OnIncreaseAmount(MenuModel item, int position) {
                setBasket(ShoppingCart.getInstance().getTotalCost());
                setEnableBtnConfirm( );

            }

            @Override
            public void OnDecreaseAmount(MenuModel item, int position) {
                setBasket(ShoppingCart.getInstance().getTotalCost());
                setEnableBtnConfirm( );

            }
        });

        binding.recyclerView.setAdapter(order);
        order.notifyItemInserted( size );
        setBasket(ShoppingCart.getInstance().getTotalCost());




    }


    public void addPromotionOnList(MenuModel menuModel, int amount)
    {
        Log.e(TAG, "addPromotionOnList: " );
        Log.e(TAG, "getTotal: "+menuModel.getTotal() );
        Log.e(TAG, "getAmount: "+menuModel.getAmount() );
        Log.e(TAG, "getPrice: "+menuModel.getPrice() );


        price=0;
//        amount=0;

        if(!ShoppingCart.getInstance().getMenuList().containsKey(menuModel.getMenuName()))
            ShoppingCart.getInstance().getMenuList().put(menuModel.getMenuName(), new MenuModel( menuModel.getMenuName() , menuModel.getPrice() ,amount,menuModel.getTotal()
                    , menuModel.getType() , menuModel.getId() ) );
        else {
//            amount =  ShoppingCart.getInstance().getMenuList().get(menuModel.getMenuName()).getAmount();
//            amount=amount+menuModel.getAmount();

            price=Integer.parseInt( menuModel.getPrice())*amount ;
            ShoppingCart.getInstance().getMenuList().get(menuModel.getMenuName()).setTotal( price );
            ShoppingCart.getInstance().getMenuList().get(menuModel.getMenuName()).setAmount( amount );

        }



        int size = ShoppingCart.getInstance().getMenuList().size();
        order = new ShoppingCartAdapter(OrderActivity.this, ShoppingCart.getInstance().getMenuList(), new ShoppingCartAdapter.OnItemClickListener() {
            @Override
            public void OnRemoveItemAt(MenuModel item, int position)
            {
                ShoppingCart.getInstance().getMenuList().remove(item.getMenuName());
                order.notifyItemRemoved(position);
                setBasket(ShoppingCart.getInstance().getTotalCost());
                setEnableBtnConfirm();


            }

            @Override
            public void OnIncreaseAmount(MenuModel item, int position) {
                Log.e(TAG, "OnIncreaseAmount: " );
                Log.e(TAG, "getPrice(): "+item.getPrice() );
                Log.e(TAG, "getTotal: "+item.getTotal() );
                Log.e(TAG, "getAmount: "+item.getAmount() );
                Log.e(TAG, "OnIncreaseAmount: " );

                setBasket(ShoppingCart.getInstance().getTotalCost());
                setEnableBtnConfirm();

            }

            @Override
            public void OnDecreaseAmount(MenuModel item, int position) {
                Log.e(TAG, "OnDecreaseAmount: " );
                Log.e(TAG, "getPrice(): "+item.getPrice() );
                Log.e(TAG, "getTotal: "+item.getTotal() );
                Log.e(TAG, "getAmount: "+item.getAmount() );
                Log.e(TAG, "OnIncreaseAmount: " );
                setBasket(ShoppingCart.getInstance().getTotalCost());
                setEnableBtnConfirm();

            }
        });

        binding.recyclerView.setAdapter(order);
        order.notifyItemInserted( size );
        setBasket(ShoppingCart.getInstance().getTotalCost());



    }

    public void initShoppingCart()
    {

        if(ShoppingCart.getInstance().getMenuList().size()!=0)
        {

            int size = ShoppingCart.getInstance().getMenuList().size();
                order = new ShoppingCartAdapter(OrderActivity.this, ShoppingCart.getInstance().getMenuList(), new ShoppingCartAdapter.OnItemClickListener()
            {
                @Override
                public void OnRemoveItemAt(MenuModel item, int position)
                {
                    ShoppingCart.getInstance().getMenuList().remove(item.getMenuName());
                    order.notifyItemRemoved(position);
                    setBasket(ShoppingCart.getInstance().getTotalCost());
                    setEnableBtnConfirm( );

                }

                @Override
                public void OnIncreaseAmount(MenuModel item, int position) {
                    setBasket(ShoppingCart.getInstance().getTotalCost());
                    setEnableBtnConfirm( );

                }

                @Override
                public void OnDecreaseAmount(MenuModel item, int position) {
                    setBasket(ShoppingCart.getInstance().getTotalCost());
                    setEnableBtnConfirm( );

                }
            });

            binding.recyclerView.setAdapter(order);
            order.notifyItemInserted( size );
            setBasket(ShoppingCart.getInstance().getTotalCost());

        }
    }
    
    
    
    public void setBasket(int price)
    {
        binding.tvTotalPoint.setText("P "+ FormatUtility.currencyFormat( ShoppingCart.getInstance().getTotalPoint()+"" ) );
        binding.tvTotalMenu.setText(" "+ShoppingCart.getInstance().getMenuList().size()+" รายการ");
        binding.tvTotalCost.setText("฿ "+ FormatUtility.currencyFormat(price+""));
        binding.basketView.setPriceText("฿ "+FormatUtility.currencyFormat(price+""));
    }


    public void closeSliding()
    {
        binding.slidingLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
    }


    public void setEnableBtnConfirm()
    {
        Log.e(TAG, "setEnableBtnConfirm: "+(ShoppingCart.getInstance().getMenuList().size()>0 && ( ShoppingCart.getInstance().getTotalPoint()<=pointForUser  )) );
        binding.btnConfirmOrder.setEnabled(ShoppingCart.getInstance().getMenuList().size()>0 && ( ShoppingCart.getInstance().getTotalPoint()<=pointForUser  ));
    }







    private Calendar currentDate;
    private Calendar HolidayDate;
    private GetOpenningTimeResponseModel openningDaily;
    private GetHolidayResponseModel holiday;
    private int index;
    boolean checkHoliday=true;



    // call back from restful service
    OnResponseFromServer getOpenTime = new OnResponseFromServer()
    {
        @Override
        public void onResponse()
        {
            Utils.dismissDialog();
        }

        @Override
        public void onResponse(Object object)
        {

            openningDaily = (GetOpenningTimeResponseModel) object;
            GetHolidayRequsetModel requestModel2 = new GetHolidayRequsetModel();
            new Restful<>().createService(OrderActivity.this , requestModel2   , getHolidayTime);

        }


        @Override
        public void onBodyError(ResponseBody responseBodyError) {
        }

        @Override
        public void onBodyErrorIsNull() {
            Utils.dismissDialog();
        }

        @Override
        public void onFailure(Throwable t) {

        }

        @Override
        public void onFailedConnection() {

        }


    };


    // call back from restful service
    OnResponseFromServer getHolidayTime = new OnResponseFromServer()
    {
        @Override
        public void onResponse()
        {
            Utils.dismissDialog();
        }

        @Override
        public void onResponse(Object object)
        {
            Utils.dismissDialog();
            holiday = (GetHolidayResponseModel) object;
            int size ;
            currentDate = Calendar.getInstance( );
            HolidayDate = Calendar.getInstance( );
            size = holiday.getHoliday().size();



            for(int i =  0 ; i<size ; i++)
            {
                HolidayDate.setTimeInMillis( holiday.getHoliday().get(i).getTimeInMillis() );

                if( ( currentDate.get(Calendar.YEAR) == HolidayDate.get(Calendar.YEAR) ) &&( (currentDate.get(Calendar.MONTH)+1)==(HolidayDate.get(Calendar.MONTH)+1) ) &&( currentDate.get(Calendar.DAY_OF_MONTH) == HolidayDate.get(Calendar.DAY_OF_MONTH)) )
                {
                    checkHoliday = false;
                    showDialogCloseShop();

                }


            }



            if(checkHoliday==true)
            {
                Log.e("getHolidayTime", "checkHoliday==true: " );
                checkOpenningTime();
            }

            checkHoliday=true;

        }


        @Override
        public void onBodyError(ResponseBody responseBodyError) {
        }

        @Override
        public void onBodyErrorIsNull() {
            Utils.dismissDialog();
        }

        @Override
        public void onFailure(Throwable t)
        {

            Log.e("MainActivity", "onFailure: "+t.toString() );
        }

        @Override
        public void onFailedConnection() {

        }


    };



    public void checkOpenningTime()
    {

        long currentHour = currentDate.get(Calendar.HOUR_OF_DAY);
        long currentMin  = currentDate.get(Calendar.MINUTE) ;
        long walkIn = TimeUnit.HOURS.toMillis(currentHour)+ TimeUnit.MINUTES.toMillis(currentMin);
        int day = currentDate.get(Calendar.DAY_OF_WEEK);


        switch(day)
        {
            case Calendar.SUNDAY:
                index=0;
                break;
            case Calendar.MONDAY:
                index=1;
                break;
            case Calendar.TUESDAY:
                index=2;
                break;
            case Calendar.WEDNESDAY:
                index=3;
                break;
            case Calendar.THURSDAY:
                index=4;
                break;
            case Calendar.FRIDAY:
                index=5;
                break;
            case Calendar.SATURDAY:
                index=6;
                break;
        }


        if(openningDaily.getDaily().get(index).getOpen().equals("")&&openningDaily.getDaily().get(index).getClose().equals(""))
            showDialogCloseShop();
        else
        {

            String open_hour;
            String open_min;
            open_hour = openningDaily.getDaily().get(index).getOpen().substring(0,openningDaily.getDaily().get(index).getOpen().indexOf('.'));
            int length = openningDaily.getDaily().get(index).getOpen().length();
            open_min = openningDaily.getDaily().get(index).getOpen().substring(openningDaily.getDaily().get(index).getOpen().indexOf('.')+1,length );
            long openningTime = TimeUnit.HOURS.toMillis(Long.parseLong(open_hour))+ TimeUnit.MINUTES.toMillis(Long.parseLong(open_min));

            open_hour = openningDaily.getDaily().get(index).getClose().substring(0,openningDaily.getDaily().get(index).getClose().indexOf('.'));
            length = openningDaily.getDaily().get(index).getClose().length();
            open_min = openningDaily.getDaily().get(index).getClose().substring(openningDaily.getDaily().get(index).getClose().indexOf('.')+1,length );
            long closingTime = TimeUnit.HOURS.toMillis(Long.parseLong(open_hour))+ TimeUnit.MINUTES.toMillis(Long.parseLong(open_min));


            if(walkIn>=openningTime && walkIn<=closingTime)
            {

                binding.btnConfirmOrder.setEnabled(false);
                startActivity(new Intent(OrderActivity.this, ShoppingCartActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
            else
                showDialogCloseShop();

        }



    }



    public void showDialogCloseShop()
    {
        final DialogMessageOneButton dialog = new DialogMessageOneButton( OrderActivity.this  );
        dialog.showDialog(getString(R.string.label_title_closed_shop),getString(R.string.label_message_closed_shop));
        dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener()
        {
            @Override
            public void onClickConfirm()
            {

//                finishAffinity();
            }
        });
    }







}
