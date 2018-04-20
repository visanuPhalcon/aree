package com.aree.restaurant.app.views;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.databinding.MenuDialogBinding;
import com.aree.restaurant.app.databinding.PromotionDialogBinding;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Admin on 14/5/2560.
 */

public class PromotionDialog
{


    private String TAG = "jay";
    private Context context;
    private String title;
    private String photo;
    private String description;
    private PromotionDialogBinding dialogBinding;


    public PromotionDialog(Context context , String title , String description , String photo  )
    {
        this.context = context;
        this.title = title;
        this.description = description;
        this.photo = photo;
    }

    public PromotionDialog(Context context , String title , String description  )
    {
        this.context = context;
        this.title = title;
        this.description = description;
    }


    public void initDialog()
    {

        dialogBinding = PromotionDialogBinding.inflate(LayoutInflater.from(context), (ViewGroup) null, false);
        final Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Dialog);
        dialog.setCancelable(true);
        dialog.setContentView(dialogBinding.getRoot());
        dialog.show();


        dialogBinding.tvNameMenu.setText(title);
//        dialogBinding.tvMenuDescription.setText(description);
        Glide.with(context).load(photo).diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgFoodPhoto);


        dialogBinding.imgClose.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });


        dialogBinding.btnOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();

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
