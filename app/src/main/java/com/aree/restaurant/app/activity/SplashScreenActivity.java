package com.aree.restaurant.app.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.databinding.DialogOneButtonBinding;
import com.aree.restaurant.app.model.RequsetModel.GetOpenningTimeRequestModel;
import com.aree.restaurant.app.model.RequsetModel.admin.GetHolidayRequsetModel;
import com.aree.restaurant.app.model.ResponseModel.GetOpenningTimeResponseModel;
import com.aree.restaurant.app.model.ResponseModel.admin.GetHistoryResponseModel;
import com.aree.restaurant.app.model.ResponseModel.admin.GetHolidayResponseModel;
import com.aree.restaurant.app.model.Singleton.Singleton;
import com.aree.restaurant.app.network.OnResponseFromServer;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.utils.FormatUtility;
import com.aree.restaurant.app.utils.UtilView;
import com.aree.restaurant.app.utils.Utils;
import com.aree.restaurant.app.views.DialogMessageOneButton;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import okhttp3.ResponseBody;

public class SplashScreenActivity extends BaseActivity {

    SharedPreferences sharedPreferences;
    Calendar currentDate;
    Calendar HolidayDate;
    GetOpenningTimeResponseModel openningDaily;
    GetHolidayResponseModel holiday;
    int index;
    boolean checkHoliday=true;


    public void initView()
    {

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
                closeApp();

            }
        }


        if(checkHoliday==true)
        checkOpenningTime();



    }



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        new Timer().schedule(new TimerTask() {
            @Override
            public void run()
            {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        }, 3000);

//        if( Utils.checkConnection(SplashScreenActivity.this)==true   )
//        {
//            GetOpenningTimeRequestModel requestModel = new GetOpenningTimeRequestModel();
//            new Restful<>().createService(SplashScreenActivity.this , requestModel   , getOpenTime);
//        }
//        else
//        {
//            DialogMessageOneButton dialog = new DialogMessageOneButton( SplashScreenActivity.this  );
//            dialog.showDialog(getString(R.string.label_internet_isnot_available),getString(R.string.label_error_internet_isnot_available));
//            dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
//                @Override
//                public void onClickConfirm() {
//                    finishAffinity();
//
//                }
//            });
//
//        }



    }

    public void checkOpenningTime()
    {
        //        long second = cal.get(Calendar.SECOND) ;
        //        long walkIn = TimeUnit.HOURS.toMillis(hour)+
        //                TimeUnit.MINUTES.toMillis(min)
        //                +TimeUnit.SECONDS.toMillis(second);

        long currentHour = currentDate.get(Calendar.HOUR_OF_DAY);
        long currentMin  = currentDate.get(Calendar.MINUTE) ;

//        Log.e("jay", "HOUR_OF_DAY: "+currentDate.get(Calendar.HOUR_OF_DAY) );
//        Log.e("jay", "HOUR: "+currentDate.get(Calendar.HOUR) );
//        Log.e("jay", "MINUTE: "+currentDate.get(Calendar.MINUTE) );

        long walkIn = TimeUnit.HOURS.toMillis(currentHour)+ TimeUnit.MINUTES.toMillis(currentMin);


        int day = currentDate.get(Calendar.DAY_OF_WEEK);
        switch(day)
        {
            case Calendar.SUNDAY:
                index=0;
//                Log.e("jay", "SUNDAY: "+openningDaily.getDaily().get(0).getDay() );
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
        {
            Log.e("jay", "if" );
            closeApp();
        }
        else
        {
            Log.e("jay", "else" );
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
                Log.e("jay", "openningTime && walkIn<=closingTime: " );
                new Handler().postDelayed(new Runnable()
                {
                    @Override
                    public void run() {

                        if(isFirstTime())
                        {
                            startActivity(new Intent(SplashScreenActivity.this, TermAndConActivity.class));
                            finish();
                            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }
                        else
                        {
                            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                            finish();
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                        }

                    }
                }, 3000);
            }
            else
            {
                Log.e("jay", "closeApp: " );
                closeApp();

            }
        }





    }

    public void closeApp()
    {

        final DialogMessageOneButton dialog = new DialogMessageOneButton( SplashScreenActivity.this  );
        dialog.showDialog(SplashScreenActivity.this.getString(R.string.label_title_closed_shop),SplashScreenActivity.this.getString(R.string.label_message_closed_shop));
        dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener()
        {
            @Override
            public void onClickConfirm()
            {
                finishAffinity();

            }
        });

    }


    private boolean isFirstTime()
    {
        sharedPreferences = getSharedPreferences("first", MODE_PRIVATE);
        return sharedPreferences.getBoolean("first", true);
    }


    // call back from restful service
    OnResponseFromServer getOpenTime = new OnResponseFromServer()
    {
        @Override
        public void onResponse()
        {
        }

        @Override
        public void onResponse(Object object)
        {
            openningDaily = (GetOpenningTimeResponseModel) object;
//            for( int i = 0 ;i<openningDaily.getDaily().size(); i++ )
//            {
//                Log.e("openningDaily", "getDay: "+openningDaily.getDaily().get(i).getDay() );
//                Log.e("openningDaily", "getOpen: "+openningDaily.getDaily().get(i).getOpen() );
//
//            }


            GetHolidayRequsetModel requestModel2 = new GetHolidayRequsetModel();
            new Restful<>().createService(SplashScreenActivity.this , requestModel2   , getHolidayTime);

        }


        @Override
        public void onBodyError(ResponseBody responseBodyError) {
        }

        @Override
        public void onBodyErrorIsNull() {
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

        }

        @Override
        public void onResponse(Object object)
        {
            holiday = (GetHolidayResponseModel) object;
            initView();

//            for( int i = 0 ;i<holiday.getHoliday().size(); i++ )
//            {
//                Log.e("openningDaily", "getDay: "+holiday.getHoliday().get(i).getTimeInMillis() );
//            }


        }


        @Override
        public void onBodyError(ResponseBody responseBodyError) {
        }

        @Override
        public void onBodyErrorIsNull() {
        }

        @Override
        public void onFailure(Throwable t) {

            Log.e("MainActivity", "onFailure: "+t.toString() );
        }

        @Override
        public void onFailedConnection() {

        }


    };



}
