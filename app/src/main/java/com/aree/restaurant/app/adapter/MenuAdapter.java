package com.aree.restaurant.app.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.aree.restaurant.app.databinding.CellMenuBinding;
import com.aree.restaurant.app.databinding.RowPromotionBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.PromotionModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Admin on 14/5/2560.
 */

public class MenuAdapter extends RecyclerView.Adapter<CellMenuViewHolder> {

    private String TAG="JAY";
    CellMenuBinding binding;
    Context context;
    ArrayList<MenuModel> list = new ArrayList<MenuModel>();
    private final OnItemClickListener listener;



    public interface OnItemClickListener
    {
        void onItemClick(MenuModel item, int position);
    }





    public MenuAdapter(Context context , ArrayList<MenuModel> list , OnItemClickListener listener )
    {
        this.list = list;
        this.context = context;
        this.listener = listener;

    }



    @Override
    public CellMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = CellMenuBinding.inflate(inflater, parent, false);
        return new CellMenuViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(CellMenuViewHolder holder, int position)
    {
        if (list != null)
        {
            MenuModel model = list.get(holder.getAdapterPosition());
            holder.bind(model,context,listener,position);
        }


    }


    @Override
    public int getItemCount()
    {
        return  list.size();
    }
}
