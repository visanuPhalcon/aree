package com.aree.restaurant.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.aree.restaurant.app.databinding.RowPromotionBinding;
import com.aree.restaurant.app.model.PromotionModel;

import java.util.ArrayList;

/**
 * Created by Admin on 14/5/2560.
 */

public class PromotionAdapter extends RecyclerView.Adapter<PromotionViewHolder> {

    private String TAG="JAY";
    RowPromotionBinding binding;
    Context context;
    ArrayList<PromotionModel> list = new ArrayList<PromotionModel>();
    private final OnItemClickListener listener;


    public interface OnItemClickListener
    {
        void onItemClick(PromotionModel item , int position);
    }





    public PromotionAdapter (Context context , ArrayList<PromotionModel> list , OnItemClickListener listener )
    {
        this.list = list;
        this.context = context;
        this.listener = listener;

    }



    @Override
    public PromotionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = RowPromotionBinding.inflate(inflater, parent, false);
        return new PromotionViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(PromotionViewHolder holder, int position)
    {

        if (list != null)
        {
            PromotionModel model = list.get(holder.getAdapterPosition());
            holder.bind(model,context,listener,position);
        }
    }

    @Override
    public int getItemCount()
    {
        return  list.size();
    }
}
