package com.aree.restaurant.app.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;

/**
 * Created by Nanthakorn on 2/26/2017.
 */

public class OrderItem extends FrameLayout{

    private Context context;
    private MenuModel menu ;
    private AllOrderForAdminModel allOrder;
    private String menuName;
    private String totalPrice ;
    private String type;
    private String amount;



    // TODO: get model , custom view ,
    public OrderItem(Context context, MenuModel menu)
    {
        super(context);
        this.context = context;
        this.menu=menu;
        initView(context);
    }


    public OrderItem(String amount,Context context, String menuName , String totalPrice , String type)
    {
        super(context);
        this.context = context ;
        this.menuName = menuName ;
        this.totalPrice = totalPrice ;
        this.type = type;
        this.amount = amount ;
        initViewForOrderDetail( );
    }

    public OrderItem(Context context, String menuName , String totalPrice , String type)
    {
        super(context);
        this.context = context ;
        this.menuName = menuName ;
        this.totalPrice = totalPrice ;
        this.type = type;
        initView( );
    }

    public OrderItem(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        initView(context, attrs, 0);
    }

    public OrderItem(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle){
        LinearLayout layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.view_order_item, null);
        // set text
//        binding.tvMenuName.setText("test");
        addView(layout,  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    private void initView(Context contex)
    {

            LinearLayout layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.view_order_item2, null);
            TextView tvMenuName = (TextView) layout.findViewById(R.id.tvMenuName);
            TextView tvPrice = (TextView) layout.findViewById(R.id.tvPrice);
            TextView tvAmount = (TextView) layout.findViewById(R.id.tvAmount);
            tvMenuName.setText(menu.getMenuName());
            if(menu.getType()!=5)
                tvPrice.setText("฿ " + menu.getTotal());
            else
                tvPrice.setText("P " + menu.getTotal());
            tvAmount.setText(menu.getAmount() + "");
            addView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

    }


    private void initView()
    {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.view_order_item2, null);
        TextView tvMenuName = (TextView) layout.findViewById(R.id.tvMenuName) ;
        TextView tvPrice = (TextView) layout.findViewById(R.id.tvPrice) ;
        tvMenuName.setText(menuName);

        if(type.equals("5"))
            tvPrice.setText("P "+totalPrice );
        else
            tvPrice.setText("฿ "+totalPrice );

        addView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

    }




    private void initViewForOrderDetail()
    {
        LinearLayout layout = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.view_order_item2, null);
        TextView tvMenuName = (TextView) layout.findViewById(R.id.tvMenuName) ;
        TextView tvPrice = (TextView) layout.findViewById(R.id.tvPrice) ;
        TextView tvAmount = (TextView) layout.findViewById(R.id.tvAmount) ;

        tvMenuName.setText(menuName);
        tvAmount.setText(amount);

        if(type.equals("5"))
            tvPrice.setText("P "+totalPrice );
        else
            tvPrice.setText("฿ "+totalPrice );

        addView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

    }








}
