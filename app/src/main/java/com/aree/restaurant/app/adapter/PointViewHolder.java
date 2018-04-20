package com.aree.restaurant.app.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.aree.restaurant.app.databinding.CellMenuBinding;
import com.aree.restaurant.app.databinding.RewardCellBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.Singleton.RewardModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Admin on 15/5/2560.
 */

public class PointViewHolder extends RecyclerView.ViewHolder {

    public RewardCellBinding binding;



    public PointViewHolder(View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(final RewardModel model, Context context,
                     final PointAdapter.OnItemClickListener listener,
                     final int position )
    {


//            binding.tvDescription.setVisibility(View.GONE);
//            binding.tvPoint.setText(  "Open" );
//            binding.tvPoint.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    listener.onItemClick(model,position);
//
//                }
//            });


            binding.tvPoint.setText(  model.getPoint() +" Points" );
            binding.tvDescription.setVisibility(View.VISIBLE);
            binding.tvPoint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(model,position);

                }
            });

            binding.imgFoodMenu.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    listener.onItemClick(model,position);

                }
            });

        binding.tvName.setText(model.getName());
        Glide.with(context).load(model.getPhoto()).diskCacheStrategy(DiskCacheStrategy.RESULT).crossFade().into(binding.imgFoodMenu);



//        Glide.with(context).load(model.getPhoto()).into(binding.imgFoodMenu);
//        binding.imgFoodMenu.setBackground( ContextCompat.getDrawable( context, model.getImgFood() ) );


    }

}
