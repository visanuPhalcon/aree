package com.aree.restaurant.app.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.databinding.ActivityTermAndConBinding;

public class TermAndConActivity extends BaseActivity {

    SharedPreferences sp;
    SharedPreferences.Editor editor;
    ActivityTermAndConBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_term_and_con);
        initView();
    }

    private void initView()
    {



        binding.chkAccept.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               binding.btnRegister.setEnabled(isChecked);
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sp = getSharedPreferences("first", Context.MODE_PRIVATE);
                editor = sp.edit();
                editor.putBoolean("first", false);
                editor.commit();

                startActivity(new Intent(TermAndConActivity.this, MainActivity.class));
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }
}
