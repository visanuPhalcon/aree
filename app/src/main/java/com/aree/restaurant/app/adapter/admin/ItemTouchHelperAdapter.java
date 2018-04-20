package com.aree.restaurant.app.adapter.admin;

/**
 * Created by Admin on 20/8/2560.
 */

public interface ItemTouchHelperAdapter
{

    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}