package com.aree.restaurant.app.views;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.databinding.DialogOneButtonBinding;
import com.aree.restaurant.app.databinding.DialogTwoButtonBinding;

/**
 * Created by Admin on 14/6/2560.
 */

public class DialogMessageTwoButton {


    private Context context;
    private DialogTwoButtonBinding dialogBinding;


    public DialogMessageTwoButton(Context context )
    {
        this.context = context;
    }


    public void showDialog(String title, String description)
    {

        dialogBinding = DialogTwoButtonBinding.inflate(LayoutInflater.from(context), (ViewGroup) null, false);
        final Dialog dialog_two_button = new Dialog(context, R.style.Theme_AppCompat_Dialog);
        dialog_two_button.setCancelable(false);
        dialog_two_button.setContentView(dialogBinding.getRoot());
        dialog_two_button.show();
        dialogBinding.tvTitle.setText(title);
        dialogBinding.tvDescription.setText(description);


        dialogBinding.btnOk.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                dialog_two_button.dismiss();
                if( onClick != null )
                    onClick.onClickConfirm();

            }
        });

        dialogBinding.btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                dialog_two_button.dismiss();
                if( onClick != null )
                    onClick.onCancel();

            }
        });



    }


    OnDialogClickListener onClick;


    public interface OnDialogClickListener
    {
        void onClickConfirm();
        void onCancel();
    }


    public void setOnConfirmListener(OnDialogClickListener onClick) {
        this.onClick = onClick;

    }



}