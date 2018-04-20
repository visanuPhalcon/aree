package com.aree.restaurant.app.views;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.databinding.MenuDialogBinding;
import com.aree.restaurant.app.databinding.RewardDialogBinding;
import com.aree.restaurant.app.model.Singleton.RewardModel;
import com.aree.restaurant.app.model.Singleton.ShoppingCart;
import com.aree.restaurant.app.model.Singleton.Singleton;
import com.aree.restaurant.app.model.Singleton.UserInformation;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Admin on 14/5/2560.
 */

public class RewardMenuDialog
{


    private String TAG = "jay";
    private Context context;
    private RewardDialogBinding dialogBinding;
    private RewardModel reward;
    private int temp=ShoppingCart.getInstance().getCurrentPointRemain();
    private int currentPointForUser = ShoppingCart.getInstance().getCurrentPointRemain();



    public RewardMenuDialog(Context context , RewardModel reward)
    {
        this.context = context;
        this.reward = reward;
    }



    public void initDialog()
    {

        dialogBinding = RewardDialogBinding.inflate(LayoutInflater.from(context), (ViewGroup) null, false);
        final Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Dialog);
        dialog.setCancelable(true);
        dialog.setContentView(dialogBinding.getRoot());
        dialog.show();
        dialogBinding.tvNameMenu.setText(reward.getName());
        Glide.with(context).load(reward.getPhoto()).diskCacheStrategy(DiskCacheStrategy.RESULT).
                placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgRewardPhoto);


        dialogBinding.imgClose.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });



        if(ShoppingCart.getInstance().getMenuList().containsKey(reward.getName()))
            dialogBinding.etAmount.setText( ShoppingCart.getInstance().getMenuList().get(reward.getName()).getAmount()+""  );

        if( (ShoppingCart.getInstance().getTotalPoint()+reward.getPoint() )> Integer.parseInt(Singleton.getInstance().getUserInformation().getPoint()) )
            dialogBinding.btnOrder.setEnabled(false);
        else
            dialogBinding.btnOrder.setEnabled(true);

        currentPointForUser=currentPointForUser-reward.getPoint()* Integer.parseInt(dialogBinding.etAmount.getText().toString());
        temp=temp-reward.getPoint()* Integer.parseInt(dialogBinding.etAmount.getText().toString());
        Log.e(TAG, "initDialog: "+ShoppingCart.getInstance().getCurrentPointRemain() );



        dialogBinding.btnOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();

                if( onClick != null )
                    onClick.onClickConfirm( Integer.parseInt( dialogBinding.etAmount.getText().toString()  ) );

            }
        });



        dialogBinding.imgAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                int lostPoint = ShoppingCart.getInstance().getTotalPoint();
                int amount = Integer.parseInt( dialogBinding.etAmount.getText().toString() );
                currentPointForUser = temp;

                if(amount>=1)
                {

                    if(amount<99)
                        amount = amount + 1 ;

                    int total = amount*reward.getPoint();
//                    temp = currentPointForUser - reward.getPoint() ;
//                    Log.e(TAG, "tempAdd: "+temp );
//
//                    if (temp < 0)
//                    {
//                        dialogBinding.btnOrder.setEnabled(false);
//                    }

//                    Log.e(TAG, "amount: "+amount );
                    dialogBinding.etAmount.setText( String.valueOf( amount )  );




                }
            }
        });


        dialogBinding.imgRemove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                int lostPoint = ShoppingCart.getInstance().getTotalPoint();
                int amount = Integer.parseInt( dialogBinding.etAmount.getText().toString() );
                currentPointForUser = temp;

                if(amount>1)
                {
                    amount = amount - 1 ;
                    int total = amount*reward.getPoint();
//                    temp = temp + reward.getPoint() ;
//                    Log.e(TAG, "tempRe: "+temp );
//
//
//                    if (temp >0 )
//                    {
//                        dialogBinding.btnOrder.setEnabled(true);
//                    }

                    dialogBinding.etAmount.setText(  String.valueOf( amount ) );







                }
            }
        });



    }


    OnDialogClickListener onClick;


    public interface OnDialogClickListener
    {
        void onClickConfirm(int amount);
    }


    public void setOnConfirmListener(OnDialogClickListener onClick) {
        this.onClick = onClick;

    }









}
