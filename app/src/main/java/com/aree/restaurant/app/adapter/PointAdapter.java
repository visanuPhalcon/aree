package com.aree.restaurant.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.aree.restaurant.app.databinding.CellMenuBinding;
import com.aree.restaurant.app.databinding.RewardCellBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.Singleton.RewardModel;

import java.util.ArrayList;

/**
 * Created by Admin on 14/5/2560.
 */

public class PointAdapter extends RecyclerView.Adapter<PointViewHolder> {

    private String TAG="JAY";
    RewardCellBinding binding;
    Context context;
    ArrayList<RewardModel> list = new ArrayList<RewardModel>();
    private final OnItemClickListener listener;



    public interface OnItemClickListener
    {
        void onItemClick(RewardModel item, int position);
    }


    public PointAdapter(Context context,ArrayList<RewardModel> reward , OnItemClickListener listener  )
    {
        list = reward;
        this.context = context;
        this.listener = listener;
    }



    @Override
    public PointViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = RewardCellBinding.inflate(inflater, parent, false);
        return new PointViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(PointViewHolder holder, int position)
    {
        if (list != null)
        {
            RewardModel model = list.get(holder.getAdapterPosition());
            holder.bind(model,context,listener,position);
        }
    }


    @Override
    public int getItemCount()
    {
        return  list.size();
    }
}
