package com.aree.restaurant.app.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aree.restaurant.app.R;

/**
 * Created by Nanthakorn on 2/27/2017.
 */

public class BasketView extends FrameLayout {
    private Context mContext;
    private RelativeLayout layout;
    private TextView tvPrice;

    public BasketView(Context context) {
        super(context);
        this.mContext = context;
    }

    public BasketView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initView(context, attrs, 0);
    }

    public BasketView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyle){
        layout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.view_basket, null);
        tvPrice = (TextView) layout.findViewById(R.id.tvPrice);
        addView(layout,  new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
    }

    public void setPriceText(String text){
        tvPrice.setText(text);
    }
}
