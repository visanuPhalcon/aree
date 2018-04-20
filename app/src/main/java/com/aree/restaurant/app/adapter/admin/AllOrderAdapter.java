package com.aree.restaurant.app.adapter.admin;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.databinding.RowOrderAdminBinding;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.aree.restaurant.app.model.Singleton.Singleton;
import com.loopeer.itemtouchhelperextension.Extension;
import com.loopeer.itemtouchhelperextension.ItemTouchHelperExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

/**
 * Created by Admin on 14/5/2560.
 */
//public class AllOrderAdapter extends RecyclerView.Adapter<AllOrderViewHolder> implements ItemTouchHelperAdapter
public class AllOrderAdapter extends RecyclerView.Adapter<AllOrderViewHolder> {

    private String TAG="JAY";
    RowOrderAdminBinding binding;
    Context context;
    private final OnItemClickListener listener;
    private ArrayList<AllOrderForAdminModel> allItem ;


    public interface OnItemClickListener
    {
        void onItemClick(AllOrderForAdminModel item, int position);
        void onUpdateStatus(AllOrderForAdminModel item, int position);
        void onItemLongClick(AllOrderForAdminModel item, int position);
    }





    public AllOrderAdapter(Context context , OnItemClickListener listener   )
    {
        this.context = context;
        this.listener = listener;
        this.allItem = (ArrayList<AllOrderForAdminModel>) Singleton.getInstance().getAllOrder().getOrder().clone();

    }


    @Override
    public AllOrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = RowOrderAdminBinding.inflate(inflater, parent, false);

        //add something
        View view = binding.getRoot();
        //
        return new AllOrderViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(AllOrderViewHolder holder, int position)
    {
        if (Singleton.getInstance().getAllOrder().getOrder() != null)
        {
            AllOrderForAdminModel model =  allItem.get(holder.getAdapterPosition());
            holder.bind(model,context,listener,position );
        }


    }


    @Override
    public int getItemCount()
    {
        return  allItem.size() ;
    }

//    public void clearAllItem()
//    {
//        allItem.clear();
//
//    }

    public void addItem(   )
    {
        if(allItem.size()!=0)
            allItem.clear();

        this.allItem = (ArrayList<AllOrderForAdminModel>) Singleton.getInstance().getAllOrder().getOrder().clone();
        notifyDataSetChanged();
    }


    public void filter( String keywordForSearch  )
    {

        keywordForSearch = keywordForSearch.toLowerCase(Locale.getDefault());


        if (keywordForSearch.isEmpty())
        {
            allItem.clear();
            allItem = (ArrayList<AllOrderForAdminModel>) Singleton.getInstance().getAllOrder().getOrder().clone();
        }
        else
        {
            int size = Singleton.getInstance().getAllOrder().getOrder().size();
            allItem.clear();
//            Log.e("SingleTon ","size = "+size);
//            Log.e("allItem","size = "+allItem.size() );


            for(int i = 0 ; i < size ; i++)
            {
//                Log.e(TAG, "filter: "+Singleton.getInstance().getAllOrder().getOrder().get(i).getOrder_id() );
                if(Singleton.getInstance().getAllOrder().getOrder().get(i).getOrder_id().toLowerCase().startsWith(keywordForSearch.toString().toLowerCase() ) )
                    allItem.add(Singleton.getInstance().getAllOrder().getOrder().get(i) );
            }


        }

        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        return allItem.get(position).hashCode();
    }


    public void removeItem(int position)
    {
        Singleton.getInstance().getAllOrder().getOrder().remove(position);
        allItem.remove(position);
        notifyDataSetChanged();
    }


















}
