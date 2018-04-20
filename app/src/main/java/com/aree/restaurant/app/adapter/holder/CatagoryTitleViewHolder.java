package com.aree.restaurant.app.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.aree.restaurant.app.R;

/**
 * Created by Nanthakorn on 2/26/2017.
 */

public class CatagoryTitleViewHolder extends RecyclerView.ViewHolder {

    public TextView tvTitle;
    public CatagoryTitleViewHolder(View itemView) {
        super(itemView);
        tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
    }
}
