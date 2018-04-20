package com.aree.restaurant.app.activity;

import android.app.DatePickerDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.databinding.ActivityRegisterBinding;
import com.aree.restaurant.app.model.RequsetModel.EditProfileRequestModel;
import com.aree.restaurant.app.model.RequsetModel.RegisterRequestModel;
import com.aree.restaurant.app.model.Singleton.Singleton;
import com.aree.restaurant.app.network.OnResponseFromServer;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.utils.FormatUtility;
import com.aree.restaurant.app.views.CustomActionBar;
import com.aree.restaurant.app.views.DialogMessageOneButton;

import java.util.Calendar;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;

/**
 * Created by Admin on 13/6/2560.
 */

public class EditProfileActivity extends BaseActivity
{

    ActivityRegisterBinding binding;
    private int dDay;
    private int dMonth;
    private int dYear;
    private Pattern passwordPattern = Pattern.compile("^[a-zA-Z0-9]{4,20}$");
    private Pattern checkThai = Pattern.compile("^[ก-๙][^0-9๑๒๓๔๕๖๗๘๙a-zA-z]{2,20}$");
    private Pattern checkEnglish = Pattern.compile("^[a-zA-z][^0-9ก-๙]{2,20}$");


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        initView();
    }

    public void initView()
    {
        binding.tvInformation.setVisibility(View.GONE);
        binding.tvPasswordRepeat.setVisibility(View.GONE);
        binding.etPasswordRepeat.setVisibility(View.GONE);
        binding.etPassword.setVisibility(View.GONE);
        binding.tvPassword.setVisibility(View.GONE);
        binding.etEmail.setVisibility(View.GONE);
        binding.tvEmail.setVisibility(View.GONE);
        binding.etEmail.setEnabled(false);
//        binding.btnRegister.setEnabled(false);
        Calendar calendar = Calendar.getInstance();
        dYear = calendar.get(Calendar.YEAR);
        dMonth = calendar.get(Calendar.MONTH);
        dDay = calendar.get(Calendar.DAY_OF_MONTH);

        setSupportActionBar(binding.included.toolbar);
        CustomActionBar actionBar = new CustomActionBar(this, getSupportActionBar());
        actionBar.setImgButtonRight(R.drawable.ic_close);
        actionBar.setTitleBar(getString(R.string.label_edit_profile));
        actionBar.setDisplayHomeAsUpEnabled();

        binding.btnRegister.setText("บันทึกข้อมูลส่วนตัว");


        binding.etFirstName.setText(Singleton.getInstance().getUserInformation().getName());
        binding.etLastName.setText(Singleton.getInstance().getUserInformation().getLastName());
        binding.etCompanyName.setText(Singleton.getInstance().getUserInformation().getCompanyName());
        binding.etFloorNo.setText(Singleton.getInstance().getUserInformation().getFloorNumber());
        binding.etPhoneNo.setText(Singleton.getInstance().getUserInformation().getPhoneNumber());
        binding.etPhoneNoCompany.setText(Singleton.getInstance().getUserInformation().getCompanyPhoneNumber());

        binding.etBirthDay.setText(Singleton.getInstance().getUserInformation().getDate());
        binding.etEmail.setText(Singleton.getInstance().getUserInformation().getEmail());


        binding.etFirstName.addTextChangedListener( check );
        binding.etLastName.addTextChangedListener( check );
        binding.etCompanyName.addTextChangedListener( check );
        binding.etFloorNo.addTextChangedListener( check );
        binding.etPhoneNo.addTextChangedListener( check );
        binding.etPhoneNoCompany.addTextChangedListener( check );
//        binding.etBirthDay.addTextChangedListener( check );
        binding.etEmail.addTextChangedListener( check );
        binding.etPassword.addTextChangedListener( check );
        binding.etPasswordRepeat.addTextChangedListener( check );



        binding.btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditProfileRequestModel requestModel = new EditProfileRequestModel(
                        binding.etFirstName.getText().toString(),
                        binding.etLastName.getText().toString(),
                        binding.etCompanyName.getText().toString(),
                        binding.etFloorNo.getText().toString(),
                        binding.etPhoneNo.getText().toString(),
                        binding.etPhoneNoCompany.getText().toString(),
                        binding.etBirthDay.getText().toString(),
                        binding.etEmail.getText().toString(),
                        binding.etPassword.getText().toString(),
                        "",
                        "0",
                        "1"  );

                new Restful<>().createService(EditProfileActivity.this , requestModel,callback);
            }
        });
        binding.etBirthDay.setOnClickListener(onBirthDayClickListener);

    }

    View.OnClickListener onBirthDayClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            showDatePickerDialog();
        }
    };

    public void showDatePickerDialog()
    {


        new DatePickerDialog(EditProfileActivity.this,
//                android.R.style.Theme_Holo_Light_Panel,
                R.style.DialogTheme,

                onDatePickerSetListener(),
                dYear,
                dMonth,
                dDay)
                .show();

    }

    @NonNull
    private DatePickerDialog.OnDateSetListener onDatePickerSetListener() {
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                setDateFormat(year, month+1, dayOfMonth);
            }
        };
    }

    private void setDateFormat(int year, int month, int day){
//        binding.etBirthDay.setText(String.valueOf(year)+ "-" + String.valueOf(month < 9 ? "0"+month : month)+ "-" + String.valueOf(day < 9 ? "0"+day : day));
        binding.etBirthDay.setText(String.valueOf(day < 9 ? "0"+day : day)+ "/" + String.valueOf(month < 9 ? "0"+month : month)+ "/" + String.valueOf(year+543));

    }


    OnResponseFromServer callback = new OnResponseFromServer() {
        @Override
        public void onResponse()
        {
            //@:TODO get new data into singleton

            Singleton.getInstance().getUserInformation().setName(binding.etFirstName.getText().toString());
            Singleton.getInstance().getUserInformation().setLastName(binding.etLastName.getText().toString());
            Singleton.getInstance().getUserInformation().setCompanyName(binding.etCompanyName.getText().toString());
            Singleton.getInstance().getUserInformation().setFloorNumber(binding.etFloorNo.getText().toString());
            Singleton.getInstance().getUserInformation().setPhoneNumber(binding.etPhoneNo.getText().toString());
            Singleton.getInstance().getUserInformation().setCompanyPhoneNumber(binding.etPhoneNoCompany.getText().toString());
            Singleton.getInstance().getUserInformation().setDate(binding.etBirthDay.getText().toString());

        }

        @Override
        public void onResponse(Object objecc) {

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




    TextWatcher check = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s)
        {
            binding.btnRegister.setEnabled( validateInput() );
        }
    };


    public boolean validateInput()
    {
        return
//                FormatUtility.isEmailFormat( binding.etUsername.getText().toString() ) &&
                ( checkThai.matcher( binding.etFirstName.getText().toString() ).find() || checkEnglish.matcher( binding.etFirstName.getText().toString() ).find() )&&
                ( checkThai.matcher( binding.etLastName.getText().toString() ).find() || checkEnglish.matcher( binding.etLastName.getText().toString() ).find() )&&
                ( checkThai.matcher( binding.etCompanyName.getText().toString() ).find() || checkEnglish.matcher( binding.etCompanyName.getText().toString() ).find() ) &&
                ( binding.etFloorNo.length()!=0 ) &&
                ( binding.etBirthDay.length()!=0 ) &&
                (binding.etPhoneNoCompany.length()==10)  &&
                (binding.etPhoneNo.length()==10) ;
//                passwordPattern.matcher( binding.etPassword.getText().toString() ).find() &&
//                passwordPattern.matcher( binding.etPasswordRepeat.getText().toString() ).find() &&
//                ( binding.etPassword.getText().toString().equals( binding.etPasswordRepeat.getText().toString() ) )

    }






}
