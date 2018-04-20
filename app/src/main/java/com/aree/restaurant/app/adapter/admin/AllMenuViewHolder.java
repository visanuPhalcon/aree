package com.aree.restaurant.app.adapter.admin;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.adapter.MenuAdapter;
import com.aree.restaurant.app.databinding.RowMenuAdminBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;

/**
 * Created by Nanthakorn on 2/26/2017.
 */

public class AllMenuViewHolder extends RecyclerView.ViewHolder {

    RowMenuAdminBinding binding;


    public AllMenuViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(final AllOrderForAdminModel model, Context context, final int listener, final AllMenuAdapter.OnItemClickListener position )
    {

        binding.tvNameMenu.setText(model.getFoodName());
        binding.tvAmount.setText("จำนวน "+model.getFoodAmount()+" ชุด");


    }
}
