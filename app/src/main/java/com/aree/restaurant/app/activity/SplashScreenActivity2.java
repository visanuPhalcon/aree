package com.aree.restaurant.app.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.model.Singleton.Singleton;
import com.aree.restaurant.app.utils.Utils;
import com.aree.restaurant.app.views.DialogMessageOneButton;

public class SplashScreenActivity2 extends BaseActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if( Utils.checkConnection(SplashScreenActivity2.this)==true   )
        {
            new Handler().postDelayed(new Runnable()
            {
                @Override
                public void run() {

                    if(isFirstTime())
                    {
                        startActivity(new Intent(SplashScreenActivity2.this, TermAndConActivity.class));
                        finish();
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    }
                    else
                    {
                        startActivity(new Intent(SplashScreenActivity2.this, MainActivity.class));
                        finish();
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }

                }
            }, 3000);
        }
        else
        {
            DialogMessageOneButton dialog = new DialogMessageOneButton( SplashScreenActivity2.this  );
            dialog.showDialog(getString(R.string.label_internet_isnot_available),getString(R.string.label_error_internet_isnot_available));
            dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                @Override
                public void onClickConfirm() {
                    finishAffinity();

                }
            });

        }





    }



    private boolean isFirstTime()
    {
        sharedPreferences = getSharedPreferences("first", MODE_PRIVATE);
        return sharedPreferences.getBoolean("first", true);
    }











}
