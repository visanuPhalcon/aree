package com.aree.restaurant.app.views;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aree.restaurant.app.R;


/**
 * Created by Benzay on 25/11/2558.
 */
public class CustomActionBar {

    private TextView tvTitleBar;
    private ImageView imgButtonRight;
    private View layoutBar;
    private Context _context;
    private ActionBar _supportActionBar;

    public CustomActionBar(Context context, ActionBar supportActionBar) {
        View customBar = LayoutInflater.from(context).inflate(R.layout.custom_actionbar, null);
        tvTitleBar = (TextView) customBar.findViewById(R.id.tvTitleBar);
        imgButtonRight = (ImageView)customBar.findViewById(R.id.imgButtonRight);
        layoutBar = customBar.findViewById(R.id.layoutBar);
        supportActionBar.setCustomView(customBar);
        /*supportActionBar.setCustomView(customBar, new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER
        ));*/
        supportActionBar.setDisplayShowCustomEnabled(true);
        supportActionBar.setDisplayShowTitleEnabled(false);
        this._supportActionBar = supportActionBar;
        this._context = context;
    }

    public void setColorBar(int color)
    {
        tvTitleBar.setBackgroundColor(color);
        layoutBar.setBackgroundColor(color);
    }

    public void setColorTitle(int color)
    {
        tvTitleBar.setTextColor(color);
    }


    public void setTitleBar(String title){
        tvTitleBar.setText(title);
    }

    public void setTitleBar(int title){
        tvTitleBar.setText(title);
    }

    public void setTextSize(float size){
        tvTitleBar.setTextSize(size);
    }

    public void setImgButtonRight(int imgResID){
        imgButtonRight.setImageResource(imgResID);
    }

    public void setOnClickButtonRight(View.OnClickListener listener){
        imgButtonRight.setOnClickListener(listener);
    }

    public void setDisplayHomeAsUpEnabled(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH){
            _supportActionBar.setHomeButtonEnabled(true);
        }else {
            _supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
