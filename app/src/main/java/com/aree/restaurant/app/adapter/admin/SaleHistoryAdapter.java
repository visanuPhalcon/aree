package com.aree.restaurant.app.adapter.admin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.aree.restaurant.app.adapter.CellMenuViewHolder;
import com.aree.restaurant.app.databinding.CellMenuBinding;
import com.aree.restaurant.app.databinding.RowHistoryAdminBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;

import java.util.ArrayList;

/**
 * Created by Admin on 14/5/2560.
 */

public class SaleHistoryAdapter extends RecyclerView.Adapter<SaleHistoryViewHolder> {

    private String TAG="JAY";
    RowHistoryAdminBinding binding;
    Context context;
    ArrayList<AllOrderForAdminModel> list ;
    private final OnItemClickListener listener;



    public interface OnItemClickListener
    {
        void onItemClick(AllOrderForAdminModel item, int position);
    }





    public SaleHistoryAdapter(Context context , ArrayList<AllOrderForAdminModel> list , OnItemClickListener listener )
    {
        this.list = list;
        this.context = context;
        this.listener = listener;

    }



    @Override
    public SaleHistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = RowHistoryAdminBinding.inflate(inflater, parent, false);
        return new SaleHistoryViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(SaleHistoryViewHolder holder, int position)
    {
        if (list != null)
        {
            AllOrderForAdminModel model = list.get(holder.getAdapterPosition());
            holder.bind(model,context,listener,position);
        }


    }

    public void addNewData( ArrayList<AllOrderForAdminModel> list )
    {
        this.list = list ;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount()
    {
        return  list.size();
    }
}
