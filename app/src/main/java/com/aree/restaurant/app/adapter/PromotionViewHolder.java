package com.aree.restaurant.app.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.aree.restaurant.app.databinding.RowPromotionBinding;
import com.aree.restaurant.app.model.PromotionModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


public class    PromotionViewHolder extends RecyclerView.ViewHolder {

    public RowPromotionBinding binding;

    public PromotionViewHolder(View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(final PromotionModel model, Context context,
                     final PromotionAdapter.OnItemClickListener listener,
                     final int position )
    {
        binding.tvSetMenu.setText("Set "+ (char) ('A'+position) );
        model.setSetName("Set "+ (char) ('A'+position));
        Glide.with(context).load(model.getImgPromotion()).diskCacheStrategy(DiskCacheStrategy.RESULT).crossFade().into(binding.imgPromotion);
//        binding.imgSetMenu.setBackground( ContextCompat.getDrawable( context, model.getImgPromotion() ) );
        binding.imgSetMenu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onItemClick(model,position);

            }
        });

    }
}
