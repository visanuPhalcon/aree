package com.aree.restaurant.app.adapter.admin;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.databinding.RowSummarySaleAdminBinding;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.aree.restaurant.app.utils.FormatUtility;

/**
 * Created by Nanthakorn on 2/26/2017.
 */

public class SaleSummaryViewHolder extends RecyclerView.ViewHolder {

    RowSummarySaleAdminBinding binding;
    private String[] food_totalPrice;
    private String[] food_type;

    private int size;
    private int total;


    public SaleSummaryViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(final AllOrderForAdminModel model, Context context, final SaleSummaryAdapter.OnItemClickListener listener,  int position )
    {

        food_totalPrice = model.getTotal().split(",");
        food_type  = model.getFoodType().split(",");

        size = food_totalPrice.length;
        for(int z = 0 ; z<size ; z++) {

            if( Integer.parseInt(food_type[z])!=5 )
                total = total + Integer.parseInt(food_totalPrice[z]);
        }

        binding.tvCustomerName.setText("คุณ "+model.getName()+" "+model.getLastname());
        binding.tvTotal.setText("฿ "+ FormatUtility.currencyFormat(String.valueOf(total) ) ) ;
        binding.tvDate.setText(model.getTime());
        total = 0 ;


    }
}
