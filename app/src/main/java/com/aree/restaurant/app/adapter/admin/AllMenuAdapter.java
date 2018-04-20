package com.aree.restaurant.app.adapter.admin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.aree.restaurant.app.adapter.CellMenuViewHolder;
import com.aree.restaurant.app.databinding.CellMenuBinding;
import com.aree.restaurant.app.databinding.RowMenuAdminBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.aree.restaurant.app.model.Singleton.ShoppingCart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Admin on 14/5/2560.
 */

public class AllMenuAdapter extends RecyclerView.Adapter<AllMenuViewHolder> {

    private String TAG="JAY";
    RowMenuAdminBinding binding;
    Context context;
    private List listKey ;
    private  String key;
    HashMap<String,AllOrderForAdminModel> list ;
    private final OnItemClickListener listener;


    public interface OnItemClickListener
    {
        void onItemClick(AllOrderForAdminModel item, int position);
    }





    public AllMenuAdapter(Context context , HashMap<String,AllOrderForAdminModel> list , OnItemClickListener listener )
    {
        this.list = list;
        this.context = context;
        this.listener = listener;
    }



    @Override
    public AllMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = RowMenuAdminBinding.inflate(inflater, parent, false);
        return new AllMenuViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(AllMenuViewHolder holder, int position)
    {
        if (list != null)
        {
            listKey = new ArrayList(list.keySet());
            key = String.valueOf( listKey.get(position) );
            AllOrderForAdminModel model = list.get(key);
            holder.bind(model,context,position,listener);

        }


    }


    public void clearAllItem()
    {
        int size = list.size();
        list.clear();
        notifyDataSetChanged();

    }


    @Override
    public int getItemCount()
    {
        return  list.size();
    }
}
