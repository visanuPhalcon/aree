package com.aree.restaurant.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.aree.restaurant.app.databinding.RewardCell2Binding;
import com.aree.restaurant.app.databinding.RewardCellBinding;
import com.aree.restaurant.app.model.Singleton.RewardModel;

import java.util.ArrayList;

/**
 * Created by Admin on 14/5/2560.
 */

public class RedeemAdapter extends RecyclerView.Adapter<RedeemViewHolder> {

    private String TAG="JAY";
    RewardCell2Binding binding;
    Context context;
    ArrayList<RewardModel> list = new ArrayList<RewardModel>();
    private final OnItemClickListener listener;



    public interface OnItemClickListener
    {
        void onItemClick(RewardModel item, int position);
    }


    public RedeemAdapter(Context context, ArrayList<RewardModel> reward , OnItemClickListener listener  )
    {
        list = reward;
        this.context = context;
        this.listener = listener;
    }



    @Override
    public RedeemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = RewardCell2Binding.inflate(inflater, parent, false);
        return new RedeemViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(RedeemViewHolder holder, int position)
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


    public void updateRow(int pos)
    {
        if(list.get(pos).getStatus()==0 )
            list.get(pos).setStatus(1);
        else
            list.get(pos).setStatus(0);

        notifyDataSetChanged();

    }
}
