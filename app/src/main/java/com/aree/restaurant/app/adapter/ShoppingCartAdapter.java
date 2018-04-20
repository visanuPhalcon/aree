package com.aree.restaurant.app.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.aree.restaurant.app.adapter.holder.ShoppingCartHolder;
import com.aree.restaurant.app.databinding.ViewOrderItemBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.PromotionModel;
import com.aree.restaurant.app.model.Singleton.ShoppingCart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCartAdapter extends RecyclerView.Adapter<ShoppingCartHolder> {

    private Context context;
    private static final String TAG = "JAY";
    ViewOrderItemBinding binding;
    private List listKey ;
    private  String key;
    private  MenuModel model;
    private OnItemClickListener listener  ;


    public ShoppingCartAdapter(Context context)
    {
        this.context = context ;

    }

    public ShoppingCartAdapter(Context context,HashMap<String,MenuModel> list , OnItemClickListener listener)
    {
        this.context = context ;
        this.listener = listener;


    }



    @Override
    public ShoppingCartHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = ViewOrderItemBinding.inflate(inflater, parent, false);
        return new ShoppingCartHolder(binding.getRoot() , context );
    }

    @Override
    public void onBindViewHolder(ShoppingCartHolder holder, int position)
    {
        if (ShoppingCart.getInstance().getMenuList() != null)
        {
            listKey = new ArrayList(ShoppingCart.getInstance().getMenuList().keySet());
            key = String.valueOf( listKey.get(position) );
            model = ShoppingCart.getInstance().getMenuList().get(key);
            holder.bind(model,context,position,listener);
        }
    }

    @Override
    public int getItemCount()
    {
        return ShoppingCart.getInstance().getMenuList().size();
    }

    public interface OnItemClickListener
    {
        void OnRemoveItemAt(MenuModel item , int position);
        void OnIncreaseAmount(MenuModel item , int position);
        void OnDecreaseAmount(MenuModel item , int position);
    }








}
