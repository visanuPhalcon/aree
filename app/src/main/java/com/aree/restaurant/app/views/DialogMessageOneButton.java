package com.aree.restaurant.app.views;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.databinding.DialogOneButtonBinding;
import com.aree.restaurant.app.databinding.MenuDialogBinding;
import com.bumptech.glide.Glide;

/**
 * Created by Admin on 14/6/2560.
 */

public class DialogMessageOneButton {


    private Context context;
    private DialogOneButtonBinding dialogBinding;


    public DialogMessageOneButton(Context context )
    {
        this.context = context;
    }


    public void showDialog(String title, String description)
    {

        dialogBinding = DialogOneButtonBinding.inflate(LayoutInflater.from(context), (ViewGroup) null, false);
        final Dialog dialog_one_button = new Dialog(context, R.style.Theme_AppCompat_Dialog);
        dialog_one_button.setCancelable(false);
        dialog_one_button.setContentView(dialogBinding.getRoot());
        dialog_one_button.show();
        dialogBinding.tvTitle.setText(title);
        dialogBinding.tvDescription.setText(description);


        dialogBinding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_one_button.dismiss();
                if( onClick != null )
                    onClick.onClickConfirm();
            }
        });


    }


    OnDialogClickListener onClick;


    public interface OnDialogClickListener
    {
        void onClickConfirm();
    }


    public void setOnConfirmListener(OnDialogClickListener onClick) {
        this.onClick = onClick;

    }



}