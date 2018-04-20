package com.aree.restaurant.app.activity;


import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.activity.HistoryForCustomer.HistoryForCustomerActivity;
import com.aree.restaurant.app.databinding.ActivityMainBinding;
import com.aree.restaurant.app.databinding.DialogForgetPasswordBinding;
import com.aree.restaurant.app.fragment.CatalogFragment;
import com.aree.restaurant.app.model.RequsetModel.ForgetPasswordRequestModel;
import com.aree.restaurant.app.model.RequsetModel.GetOpenningTimeRequestModel;
import com.aree.restaurant.app.model.RequsetModel.LoginRequestModel;
import com.aree.restaurant.app.model.RequsetModel.SetPhotoProfileRequestModel;
import com.aree.restaurant.app.model.RequsetModel.UpdatePointRequestModel;
import com.aree.restaurant.app.model.RequsetModel.admin.GetHolidayRequsetModel;
import com.aree.restaurant.app.model.ResponseModel.GetOpenningTimeResponseModel;
import com.aree.restaurant.app.model.ResponseModel.LoginResponseModel;
import com.aree.restaurant.app.model.ResponseModel.admin.GetHolidayResponseModel;
import com.aree.restaurant.app.model.Singleton.Singleton;
import com.aree.restaurant.app.model.Singleton.UserInformation;
import com.aree.restaurant.app.network.OnResponseFromServer;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.utils.FormatUtility;
import com.aree.restaurant.app.utils.Utils;
import com.aree.restaurant.app.views.CustomActionBar;
import com.aree.restaurant.app.views.DialogMessageOneButton;
import com.aree.restaurant.app.views.DialogMessageTwoButton;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import okhttp3.ResponseBody;

public class MainActivity extends BaseActivity {


    ActivityMainBinding binding;
    private static final int PICK_IMAGE_REQUEST = 12345;
    public static final int REQUEST_CODE_CROP_IMAGE = 12;
    private final int REQUEST_CODE_ASK_PERMISSIONS = 123;
    private String photoPath="";
    private Uri imageUri;
    CustomActionBar actionBar;
    String [] usernameAutoComplete;
    String usernameTemp="";
    SharedPreferences sharePre;
    SharedPreferences.Editor editor;
    private boolean getOut = false ;
    private String json;

    private Calendar currentDate;
    private Calendar HolidayDate;
    private GetOpenningTimeResponseModel openningDaily;
    private GetHolidayResponseModel holiday;
    private int index;
    boolean checkHoliday=true;
    private ProgressDialog progressDoalog;



    private static final String PREFS_Username = "username";


    @Override
    public void onBackPressed()
    {

        if( Singleton.getInstance().getUserInformation()!=null)
        {

            DialogMessageTwoButton dialog = new DialogMessageTwoButton( MainActivity.this  );
            dialog.showDialog(getString(R.string.label_log_out),getString(R.string.label_log_out_message));
            dialog.setOnConfirmListener(new DialogMessageTwoButton.OnDialogClickListener()
            {
                @Override
                public void onClickConfirm()
                {
                    sharePre = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                    editor = sharePre.edit();
                    editor.remove("user");
                    editor.apply();
                    binding.imgAdmin.setVisibility(View.GONE);
                    binding.loginFrame.setVisibility(View.VISIBLE);
                    binding.profileFrame.setVisibility(View.GONE);
                    Singleton.getInstance().setAllOrder(null);
                    Singleton.getInstance().setUserInformation(null);
                    Singleton.getInstance().setAllMenu(null);
                }

                @Override
                public void onCancel()
                {

                }
            });

        }
        else
        {
            if(getOut==false)
            {
                getOut = true;
                Toast.makeText(MainActivity.this, "กดอีกครั้ง เพื่อออกจากแอฟ", Toast.LENGTH_LONG).show();
            }
            else
            {
                finishAffinity();
            }
        }


    }

    @Override
    protected void onResume()
    {
        super.onResume();


        if(Singleton.getInstance().getUserInformation()!=null)
        {

            binding.tvPoints.setText("แต้มสะสม " + FormatUtility.currencyFormat(Singleton.getInstance().getUserInformation().getPoint()) + " Point");
            UpdatePointRequestModel requestModel = new UpdatePointRequestModel( );
            requestModel.setUid(Singleton.getInstance().getUserInformation().getUid());
            new Restful<>().createService(MainActivity.this , requestModel  , updatePoint);
            binding.tvName.setText(Singleton.getInstance().getUserInformation().getName()+" "+Singleton.getInstance().getUserInformation().getLastName());
            binding.tvUsername.setText("อีเมล์ : "+Singleton.getInstance().getUserInformation().getEmail());
            binding.tvPhoneNo.setText("เบอร์โทรศัพท์ : "+Singleton.getInstance().getUserInformation().getPhoneNumber());
            binding.tvCompanyName.setText("บริษัท : "+Singleton.getInstance().getUserInformation().getCompanyName());
            binding.tvFloorNo.setText("ชั้น : "+Singleton.getInstance().getUserInformation().getFloorNumber());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        if(getSession())
        {
            Singleton.getInstance().setUserInformation(new Gson().fromJson(json, UserInformation.class));
            Utils.dismissKeyboard(MainActivity.this);

            if( Singleton.getInstance().getUserInformation().getType_id() == 0 )
                binding.imgAdmin.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.ic_assignment_white_36dp));
            else
                binding.imgAdmin.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.ic_history_white_36dp));

            binding.imgAdmin.setVisibility(View.VISIBLE);
        }
        initView();

    }





    private void initView()
    {


        setAutoComplete();
        setSupportActionBar(binding.included.toolbar);
        actionBar = new CustomActionBar(this, getSupportActionBar());
        actionBar.setTitleBar(R.string.label_main);

        final Drawable backArrow = ContextCompat.getDrawable(MainActivity.this,R.drawable.ic_power_settings_new_white_36dp);
        backArrow.setColorFilter(ContextCompat.getColor(MainActivity.this,R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(backArrow);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(MainActivity.this,R.color.colorPrimary)));
        actionBar.setDisplayHomeAsUpEnabled();
        actionBar.setColorTitle( ContextCompat.getColor(MainActivity.this,R.color.colorWhite) );


        replaceFragment(new CatalogFragment(), "CatalogFragment");
        binding.tvRegister.setText(" "+getString(R.string.label_register));
        binding.tvRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                startActivity(intent);

            }
        });


        if(Singleton.getInstance().getUserInformation()!=null)
            initProfile();


//        binding.etUsername.setText("angrychild001@gmail.com");
//        binding.etPassword.setText("123456");

//        binding.etUsername.setText("admin2");
//        binding.etPassword.setText("123456");

//        binding.etUsername.setText("keajay@gmail.com");
//        binding.etPassword.setText("asdfghj");


        binding.imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                progressDoalog = new ProgressDialog(MainActivity.this);
                progressDoalog.setCancelable(false);
                progressDoalog.setMessage("กรุณารอสักครู่...");
                progressDoalog.setTitle("กำลังโหลดข้อมูล");
                progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDoalog.show();

                saveUsername();
                setAutoComplete();
                LoginRequestModel requestModel = new LoginRequestModel(binding.etUsername.getText().toString()
                        ,binding.etPassword.getText().toString(), FirebaseInstanceId.getInstance().getToken());

                new Restful<>().createService(MainActivity.this , requestModel  , loginCallBack);
            }
        });

        binding.PointSection.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                startActivity(new Intent(MainActivity.this,RedeemRewardActivity.class));

                if(Utils.checkConnection(MainActivity.this)) {
                    startActivity(new Intent(MainActivity.this, RedeemRewardActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                else
                    Utils.internetIsNotAvailableDialog(MainActivity.this);

            }
        });


        binding.imgEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Utils.checkConnection(MainActivity.this)) {
                    startActivity(new Intent(MainActivity.this, EditProfileActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                else
                {
                    Utils.internetIsNotAvailableDialog(MainActivity.this);
                }

            }
        });

        binding.tvforgetPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final DialogForgetPasswordBinding dialogBinding = DialogForgetPasswordBinding.inflate(LayoutInflater.from(MainActivity.this), (ViewGroup) null, false);
                final Dialog forgetPassword = new Dialog(MainActivity.this, R.style.Theme_AppCompat_Dialog);
                forgetPassword.setCancelable(true);
                forgetPassword.setContentView(dialogBinding.getRoot());
                dialogBinding.tvTitle.setText("โปรดป้อนอีเมลของคุณเพื่อค้นหาบัญชีผู้ใช้");
//                dialogBinding.etEmail.setText("angrychild001@gmail.com");
                forgetPassword.show();


                dialogBinding.etEmail.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s)
                    {
                        dialogBinding.btnOk.setEnabled(FormatUtility.isEmailFormat( dialogBinding.etEmail.getText().toString() ));
                    }
                });

                dialogBinding.btnOk.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        forgetPassword.dismiss();
                        ForgetPasswordRequestModel requestModel = new ForgetPasswordRequestModel();
                        requestModel.setEmail( dialogBinding.etEmail.getText().toString() );
                        new Restful<>().createService(MainActivity.this , requestModel,forgetpassword);
                    }
                });


            }
        });


    }


    public void initProfile()
    {

        binding.loginFrame.setVisibility(View.GONE);
        binding.profileFrame.setVisibility(View.VISIBLE);
        binding.tvName.setText(Singleton.getInstance().getUserInformation().getName()+" "+Singleton.getInstance().getUserInformation().getLastName());
        binding.tvUsername.setText("อีเมล์ : "+Singleton.getInstance().getUserInformation().getEmail());
        binding.tvPhoneNo.setText("เบอร์โทรศัพท์ : "+Singleton.getInstance().getUserInformation().getPhoneNumber());
        binding.tvCompanyName.setText("บริษัท : "+Singleton.getInstance().getUserInformation().getCompanyName());
        binding.tvFloorNo.setText("ชั้น : "+Singleton.getInstance().getUserInformation().getFloorNumber());
        binding.tvPoints.setText("แต้มสะสม "+FormatUtility.currencyFormat(Singleton.getInstance().getUserInformation().getPoint())+" Point");


        if( !Singleton.getInstance().getUserInformation().getPhoto().equals("") )
        {
            Glide.with(MainActivity.this)
                    .load( "http://areedang.com/aree/"+Singleton.getInstance().getUserInformation().getPhoto() )
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .into(binding.imgProfile);
        }
        else
        {
            Glide.with(MainActivity.this)
                    .load( R.drawable.source_human )
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade().placeholder(R.drawable.source_human)
                    .into(binding.imgProfile);
        }

        binding.imgAdmin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                int userType = Singleton.getInstance().getUserInformation().getType_id() ;

                // 0 == staff
                // 2 == admin
                // 1 == customer

                if( Utils.checkConnection(MainActivity.this)==true )
                {
                    if (userType == 0 || userType == 2) {
                        startActivity(new Intent(MainActivity.this, AdminActivity.class));
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                    } else
                        startActivity(new Intent(MainActivity.this, HistoryForCustomerActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                else
                    Utils.internetIsNotAvailableDialog(MainActivity.this);


            }
        });


    }


    private void checkPermission() {
        int hasPermission = ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (hasPermission != PackageManager.PERMISSION_GRANTED) {
            MainActivity.this.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }
        pickImage();

    }

    private void pickImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            imageUri = data.getData();
            photoPath = Utils.getRealPathFromURI(imageUri,MainActivity.this);
            Intent intent = new Intent(MainActivity.this, CropImageActivity.class);
            intent.putExtra("imageUri", imageUri.toString());
            startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
            MainActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
        else if (requestCode == REQUEST_CODE_CROP_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null)
        {
            imageUri = data.getData();
            photoPath = Utils.getRealPathFromURI(imageUri,MainActivity.this);
            setImage();


        }
    }


    public void setImage( )
    {
        if ( !photoPath.equals("") &&  photoPath!=null )
        {

            SetPhotoProfileRequestModel requestModel = new SetPhotoProfileRequestModel();
            requestModel.setPhotoProfile(photoPath);
            requestModel.setUid( Singleton.getInstance().getUserInformation().getUid() );
            new Restful<>().createService(MainActivity.this , requestModel   , setPictureCallBack);

        }
        else
        {
            Glide.with(MainActivity.this)
                    .load( R.drawable.source_human )
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade().placeholder(R.drawable.source_human)
                    .into(binding.imgProfile);
        }

    }



    OnResponseFromServer loginCallBack = new OnResponseFromServer()
    {
        @Override
        public void onResponse()
        {
            progressDoalog.dismiss();
        }

        @Override
        public void onResponse(Object object)
        {
            actionBar.setDisplayHomeAsUpEnabled();
            Utils.dismissKeyboard(MainActivity.this);

            Log.e("loginCallBack", "email: "+binding.etUsername.getText().toString() );

            LoginResponseModel model = (LoginResponseModel) object;
            String mockModel = new Gson().toJson(model);
            UserInformation list = new Gson().fromJson(mockModel,UserInformation.class);
            Singleton.getInstance().setUserInformation(list);
            Singleton.getInstance().getUserInformation().setEmail(binding.etUsername.getText().toString());


            //admin : type = 0

            if( Singleton.getInstance().getUserInformation().getType_id() == 0 )
            {
                binding.imgAdmin.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.ic_assignment_white_36dp));
                initProfile();
                setSession();
                binding.imgAdmin.setVisibility(View.VISIBLE);
                progressDoalog.dismiss();
            }
            else
            {
                GetOpenningTimeRequestModel requestModel = new GetOpenningTimeRequestModel();
                new Restful<>().createService(MainActivity.this , requestModel   , getOpenTime);
            }

        }


        @Override
        public void onBodyError(ResponseBody responseBodyError) {
        }

        @Override
        public void onBodyErrorIsNull() {
            progressDoalog.dismiss();
        }

        @Override
        public void onFailure(Throwable t) {
            progressDoalog.dismiss();

        }

        @Override
        public void onFailedConnection() {
            progressDoalog.dismiss();


        }


    };


    // call back from restful service
    OnResponseFromServer setPictureCallBack = new OnResponseFromServer()
    {
        @Override
        public void onResponse()
        {

            Log.e("setPictureCallBack", "onResponse: " );
            Glide.with(MainActivity.this)
                    .load( imageUri )
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .crossFade()
                    .into(binding.imgProfile);
            setSession();

        }

        @Override
        public void onResponse(Object object) {
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


    public void setAutoComplete()
    {
        sharePre = getApplicationContext().getSharedPreferences(PREFS_Username, Context.MODE_PRIVATE);
        editor = sharePre.edit();
        usernameTemp = sharePre.getString("username", "");
        usernameAutoComplete = usernameTemp.split(",");

        Log.e("usernameAutoComplete : ", usernameAutoComplete.toString() );


        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, usernameAutoComplete);
        binding.etUsername.setAdapter(adapter);
    }

    public void saveUsername( )
    {
        int size;
        boolean exist=false;

        if(usernameAutoComplete!=null)
        {
            size = usernameAutoComplete.length;
            for (int i = 0; i < size; i++)
                if (usernameAutoComplete[i].equals(binding.etUsername.getText().toString()))
                    exist = true;
        }


        if(exist==false)
        {
            usernameTemp = usernameTemp + binding.etUsername.getText().toString() + ",";
            editor.putString("username", usernameTemp);
            editor.apply();
        }



    }





    // call back from restful service
    OnResponseFromServer forgetpassword = new OnResponseFromServer()
    {
        @Override
        public void onResponse()
        {

        }

        @Override
        public void onResponse(Object object) {
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


    public void setSession()
    {

        String json = new Gson().toJson(Singleton.getInstance().getUserInformation());
        sharePre = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        editor = sharePre.edit();
        editor.putString("user", json).apply();
        editor.commit();


    }


    public boolean getSession()
    {
        sharePre = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        json = sharePre.getString("user", "");
        if(!json.equals(""))
        {
            return true;
        }
        else
        {
            return false;
        }

    }





    // call back from restful service
    OnResponseFromServer getOpenTime = new OnResponseFromServer()
    {
        @Override
        public void onResponse()
        {
            progressDoalog.dismiss();
        }

        @Override
        public void onResponse(Object object)
        {

            openningDaily = (GetOpenningTimeResponseModel) object;
            GetHolidayRequsetModel requestModel2 = new GetHolidayRequsetModel();
            new Restful<>().createService(MainActivity.this , requestModel2   , getHolidayTime);

        }


        @Override
        public void onBodyError(ResponseBody responseBodyError) {
        }

        @Override
        public void onBodyErrorIsNull() {
            progressDoalog.dismiss();
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
                progressDoalog.dismiss();
            }

            @Override
            public void onResponse(Object object)
            {
                progressDoalog.dismiss();
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
//                        Singleton.getInstance().setUserInformation(null);
                        showDialogCloseShop();
                    }

                }



                if(checkHoliday==true)
                    checkOpenningTime();

                checkHoliday=true;

            }


            @Override
            public void onBodyError(ResponseBody responseBodyError) {
            }

            @Override
            public void onBodyErrorIsNull() {
                progressDoalog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
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
        {
            //@TODO clear singleton user
            Singleton.getInstance().setUserInformation(null);
            showDialogCloseShop();
        }
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
                binding.imgAdmin.setImageDrawable(ContextCompat.getDrawable(MainActivity.this,R.drawable.ic_history_white_36dp));
                initProfile();
                setSession();
                binding.imgAdmin.setVisibility(View.VISIBLE);
            }
            else
            {
                //@TODO clear singleton user
                Singleton.getInstance().setUserInformation(null);
                showDialogCloseShop();
            }
        }



    }



    public void showDialogCloseShop()
    {
        final DialogMessageOneButton dialog = new DialogMessageOneButton( MainActivity.this  );
        dialog.showDialog(MainActivity.this.getString(R.string.label_title_closed_shop),MainActivity.this.getString(R.string.label_message_closed_shop));
        dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener()
        {
            @Override
            public void onClickConfirm()
            {
//                finishAffinity();

            }
        });
    }



    // call back from restful service
    OnResponseFromServer updatePoint = new OnResponseFromServer()
    {
        @Override
        public void onResponse()
        {
//            progressDoalog.dismiss();
        }

        @Override
        public void onResponse(Object object)
        {
            LoginResponseModel updatePoint = (LoginResponseModel) object;
            Singleton.getInstance().getUserInformation().setPoint(updatePoint.getPoint());
            binding.tvPoints.setText("แต้มสะสม "+FormatUtility.currencyFormat(Singleton.getInstance().getUserInformation().getPoint())+" Point");
        }


        @Override
        public void onBodyError(ResponseBody responseBodyError) {
        }

        @Override
        public void onBodyErrorIsNull() {
//            progressDoalog.dismiss();
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
