package com.aree.restaurant.app.adapter.HistoryForCustomer;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.adapter.admin.AllOrderAdapter;
import com.aree.restaurant.app.adapter.admin.AllOrderViewHolder;
import com.aree.restaurant.app.databinding.RowHistoryCustomerBinding;
import com.aree.restaurant.app.databinding.RowOrderAdminBinding;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.aree.restaurant.app.model.Singleton.Singleton;
import com.aree.restaurant.app.utils.FormatUtility;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Admin on 14/5/2560.
 */
public class HistoryForCustomerViewHolder extends RecyclerView.ViewHolder  {

    public RowHistoryCustomerBinding binding;

    public HistoryForCustomerViewHolder(View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(final AllOrderForAdminModel model, final Context context,
                     final HistoryForCustomerAdapter.OnItemClickListener listener,
                     final int position )
    {

        String[]total = model.getTotal().split(",");
        String[]type = model.getFoodType().split(",");
//        Log.e("HistoryForCustomer", "total: "+model.getTotal() );

        int size = total.length;
        int totalPrice = 0 ;
        int totalPoint = 0 ;
        int temp;


        for(int i = 0 ; i<size ; i++)
        {
            if(!type[i].equals("5"))
            {
                if(total[i].equals(""))
                    temp = 0;
                else
                    temp = Integer.parseInt(total[i] );

                totalPrice = totalPrice+temp;

            }
            else
            {
                if(total[i].equals(""))
                    temp = 0;
                else
                    temp = Integer.parseInt(total[i] );

                totalPoint = totalPoint+temp;
            }

        }



        binding.tvDate.setText("วันที่ "+model.getDate()+" | "+model.getTime());
        binding.tvTotal.setText("ยอดบิล "+ FormatUtility.currencyFormat(String.valueOf(totalPrice))+" ฿");
        binding.tvPoint.setText("แต้มแลกของ "+FormatUtility.currencyFormat(String.valueOf(totalPoint))+" P");

        binding.tvID.setText("หมายเลขสั่งซื้อ "+"#"+model.getOrder_id());

        if(model.getStatus().equals("0"))
            binding.btnOrder.setText(R.string.label_wait_to_get_order);
        else if( model.getStatus().equals("1") )
        {
            binding.btnOrder.setTextColor(ContextCompat.getColor(context,R.color.colorGreen));
            binding.btnOrder.setText(R.string.label_ship_to_customer);
        }
        else if( model.getStatus().equals("2") )
        {
            binding.btnOrder.setTextColor(ContextCompat.getColor(context,R.color.colorBlue));
            binding.btnOrder.setText(R.string.label_delivery_successfully);
        }
        else
        {
            binding.btnOrder.setTextColor(ContextCompat.getColor(context,R.color.colorOrange));
            binding.btnOrder.setText(R.string.label_cancel_order);
        }

        binding.row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(model,position);
            }
        });






    }




}
