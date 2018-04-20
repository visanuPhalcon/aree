package com.aree.restaurant.app.adapter.admin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.aree.restaurant.app.adapter.CellMenuViewHolder;
import com.aree.restaurant.app.databinding.CellMenuBinding;
import com.aree.restaurant.app.databinding.RowSummarySaleAdminBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;

import java.util.ArrayList;

/**
 * Created by Admin on 14/5/2560.
 */

public class SaleSummaryAdapter extends RecyclerView.Adapter<SaleSummaryViewHolder> {

    private String TAG="JAY";
    RowSummarySaleAdminBinding binding;
    Context context;
    ArrayList<AllOrderForAdminModel> list ;
    private final OnItemClickListener listener;



    public interface OnItemClickListener
    {
        void onItemClick(AllOrderForAdminModel item, int position);
    }





    public SaleSummaryAdapter(Context context , ArrayList<AllOrderForAdminModel> list , OnItemClickListener listener )
    {
        this.list = list;
        this.context = context;
        this.listener = listener;

    }



    @Override
    public SaleSummaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = RowSummarySaleAdminBinding.inflate(inflater, parent, false);
        return new SaleSummaryViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(SaleSummaryViewHolder holder, int position)
    {
        if (list != null)
        {
            AllOrderForAdminModel model = list.get(holder.getAdapterPosition());
            holder.bind(model,context,listener,position);
        }


    }

    public void addList( ArrayList<AllOrderForAdminModel> list )
    {
        this.list = list ;
    }


    @Override
    public int getItemCount()
    {
        return  list.size();
    }
}
