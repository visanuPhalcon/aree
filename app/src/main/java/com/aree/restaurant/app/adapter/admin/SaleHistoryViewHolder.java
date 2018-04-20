package com.aree.restaurant.app.adapter.admin;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.adapter.MenuAdapter;
import com.aree.restaurant.app.databinding.RowHistoryAdminBinding;
import com.aree.restaurant.app.databinding.RowOrderAdminBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.aree.restaurant.app.utils.FormatUtility;

/**
 * Created by Nanthakorn on 2/26/2017.
 */

public class SaleHistoryViewHolder extends RecyclerView.ViewHolder {

    public RowHistoryAdminBinding binding;

    public SaleHistoryViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(final AllOrderForAdminModel model, Context context, final SaleHistoryAdapter.OnItemClickListener listener, final int position )
    {

        binding.tvDate.setText("ยอดขายประจำวันที่ "+model.getDate());
        binding.tvTotal.setText("฿ "+ FormatUtility.currencyFormat(model.getTotal()));


    }
}
