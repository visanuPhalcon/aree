package com.aree.restaurant.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.aree.restaurant.app.adapter.admin.AllOrderViewHolder;
import com.aree.restaurant.app.databinding.RowHistoryOrderForUserBinding;
import com.aree.restaurant.app.databinding.RowOrderAdminBinding;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.aree.restaurant.app.model.Singleton.Singleton;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Admin on 14/5/2560.
 */

public class OrderHistoryForUserAdapter extends RecyclerView.Adapter<AllOrderViewHolder> {

    private String TAG="JAY";
    RowHistoryOrderForUserBinding binding;
    Context context;
    private final OnItemClickListener listener;
    private ArrayList<AllOrderForAdminModel> allHistory ;



    public interface OnItemClickListener
    {
        void onItemClick(AllOrderForAdminModel item, int position);
    }





    public OrderHistoryForUserAdapter(Context context , OnItemClickListener listener )
    {
        this.context = context;
        this.listener = listener;
        this.allHistory = (ArrayList<AllOrderForAdminModel>) Singleton.getInstance().getAllOrder().getOrder().clone();

    }



    @Override
    public AllOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = RowHistoryOrderForUserBinding.inflate(inflater, parent, false);
        return new AllOrderViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(AllOrderViewHolder holder, int position)
    {
//        if (Singleton.getInstance().getAllOrder().getOrder() != null)
//        {
//            AllOrderForAdminModel model =  allHistory.get(holder.getAdapterPosition());
//            holder.bind(model,context,listener,position);
//        }


    }


    @Override
    public int getItemCount()
    {
        return  allHistory.size() ;
    }


    @Override
    public long getItemId(int position) {
        return allHistory.get(position).hashCode();
    }
}
