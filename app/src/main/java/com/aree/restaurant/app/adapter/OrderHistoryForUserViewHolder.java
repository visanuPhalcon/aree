package com.aree.restaurant.app.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.adapter.admin.AllOrderAdapter;
import com.aree.restaurant.app.databinding.RowOrderAdminBinding;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.aree.restaurant.app.utils.FormatUtility;

/**
 * Created by Nanthakorn on 2/26/2017.
 */


public class OrderHistoryForUserViewHolder extends RecyclerView.ViewHolder {

    public RowOrderAdminBinding binding;

    public OrderHistoryForUserViewHolder(View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(final AllOrderForAdminModel model, Context context,
                     final AllOrderAdapter.OnItemClickListener listener,
                     final int position )
    {
        String[]total = model.getTotal().split(",");
        int size = total.length;
        int totalPrice = 0 ;
        for(int i = 0 ; i<size ; i++)
            totalPrice = totalPrice+Integer.parseInt(total[i]);

        binding.tvTotal.setText("฿ "+ FormatUtility.currencyFormat(String.valueOf(totalPrice)));
        binding.tvCustomerName.setText("คุณ "+model.getName()+" "+model.getLastname());
        binding.tvID.setText("หมายเลขสั่งซื้อ "+"#"+model.getOrder_id());

        if(model.getStatus().equals("0"))
            binding.btnOrder.setText(R.string.label_get_order);
        else if( model.getStatus().equals("1") )
            binding.btnOrder.setText(R.string.label_shipping);
        else
        {
            binding.btnOrder.setEnabled(false);
            binding.btnOrder.setText(R.string.label_delivery_successfully);
        }

        binding.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(model,position);
            }
        });


        binding.btnOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onUpdateStatus(model,position);
            }
        });




    }

}
