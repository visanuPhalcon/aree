package com.aree.restaurant.app.adapter.admin;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.activity.AdminActivity;
import com.aree.restaurant.app.adapter.MenuAdapter;
import com.aree.restaurant.app.databinding.CellMenuBinding;
import com.aree.restaurant.app.databinding.RowOrderAdminBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.aree.restaurant.app.utils.FormatUtility;
import com.aree.restaurant.app.utils.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.loopeer.itemtouchhelperextension.ItemTouchHelperExtension;

/**
 * Created by Nanthakorn on 2/26/2017.
 */

//public class AllOrderViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder
public class AllOrderViewHolder extends RecyclerView.ViewHolder  {

    public RowOrderAdminBinding binding;

    public AllOrderViewHolder(View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(final AllOrderForAdminModel model, final Context context,
                     final AllOrderAdapter.OnItemClickListener listener,
                     final int position )
    {
        String[]total = model.getTotal().split(",");
        String[]type = model.getFoodType().split(",");

        int temp;
        int size = total.length;
        int totalPrice = 0 ;
        int totalPoint = 0 ;


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


//        for(int i = 0 ; i<size ; i++)
//            totalPrice = totalPrice+Integer.parseInt(total[i]);


        binding.tvTotal.setText("฿ "+ FormatUtility.currencyFormat(String.valueOf(totalPrice)));
        binding.tvCustomerName.setText("คุณ "+model.getName()+" "+model.getLastname());
        binding.tvID.setText("หมายเลขสั่งซื้อ "+"#"+model.getOrder_id());
        binding.tvPoint.setText("P "+FormatUtility.currencyFormat(String.valueOf(totalPoint)) );


//        Log.e("AllOrderViewHolder", " model.getOrder_id(): "+ model.getOrder_id() );
//        Log.e("AllOrderViewHolder", " model.getStatus(): "+ model.getStatus() );


        if(model.getStatus().equals("0")) {
            binding.btnOrder.setEnabled(true);
            binding.btnOrder.setText(R.string.label_get_order);
        }
        else if( model.getStatus().equals("1") )
        {
            binding.btnOrder.setEnabled(true);
            binding.btnOrder.setText(R.string.label_shipping);
        }
        else if( model.getStatus().equals("2") )
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


        binding.row.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                listener.onItemLongClick(model,position);
                return true;
            }
        });

//        binding.row.setOnTouchListener(new View.OnTouchListener()
//        {
//            @Override
//            public boolean onTouch(View v, MotionEvent event)
//            {
//                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN)
//                {
//                    onDrag.onStartDrag();
//                }
//                else if(MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_MOVE)
//                {
//                    Log.e("setOnTouchListener", "onTouch: " );
//                    ((AdminActivity)context).setIntercept(false);
//                }
//                return true;
//            }
//        });


        binding.btnOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onUpdateStatus(model,position);
            }
        });




    }

//    @Override
//    public void onItemSelected() {
//        itemView.setBackgroundColor(Color.RED);
//    }
//
//    @Override
//    public void onItemClear() {
//        itemView.setBackgroundColor(0);
//
//    }
}
