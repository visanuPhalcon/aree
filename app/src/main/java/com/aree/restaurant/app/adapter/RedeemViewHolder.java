package com.aree.restaurant.app.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.databinding.RewardCell2Binding;
import com.aree.restaurant.app.databinding.RewardCellBinding;
import com.aree.restaurant.app.model.Singleton.RewardModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Admin on 15/5/2560.
 */

public class RedeemViewHolder extends RecyclerView.ViewHolder {

    public RewardCell2Binding binding;



    public RedeemViewHolder(View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(final RewardModel model, Context context,
                     final RedeemAdapter.OnItemClickListener listener,
                     final int position )
    {

//        Log.e("RedeemViewHolder", "model.getPoint(): "+model.getPoint() );
//        Log.e("RedeemViewHolder", "model.getId(): "+model.getId() );
//        Log.e("RedeemViewHolder", "model.getStatus(): "+model.getStatus() );
//        Log.e("RedeemViewHolder", "model.getName(): "+model.getName() );

        binding.tvName.setText(model.getName());


        if(model.getStatus()==0)
        {
                binding.tvPoint.setBackgroundColor( context.getColor(R.color.colorOrange) ) ;
                binding.tvPoint.setText(  "ปิดการแสดงผล" );
        }
        else
        {
                binding.tvPoint.setBackgroundColor( context.getColor(R.color.colorGreen) ) ;
                binding.tvPoint.setText(  "แสดงผล" );
        }
            binding.tvPoint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(model,position);

                }
            });

        Glide.with(context).load(model.getPhoto()).diskCacheStrategy(DiskCacheStrategy.RESULT).crossFade().into(binding.imgFoodMenu);




    }

}
