package com.aree.restaurant.app.adapter.HistoryForCustomer;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aree.restaurant.app.adapter.admin.AllOrderViewHolder;
import com.aree.restaurant.app.databinding.RowHistoryCustomerBinding;
import com.aree.restaurant.app.databinding.RowOrderAdminBinding;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.aree.restaurant.app.model.Singleton.Singleton;

import java.util.ArrayList;
import java.util.Locale;



public class HistoryForCustomerAdapter extends RecyclerView.Adapter<HistoryForCustomerViewHolder>
{

    private String TAG="JAY";
    RowHistoryCustomerBinding binding;
    Context context;
    private final OnItemClickListener listener;
    private ArrayList<AllOrderForAdminModel> allItem ;



    public interface OnItemClickListener
    {
        void onItemClick(AllOrderForAdminModel item, int position);
    }





    public HistoryForCustomerAdapter(Context context , OnItemClickListener listener   )
    {
        this.context = context;
        this.listener = listener;
        this.allItem = (ArrayList<AllOrderForAdminModel>) Singleton.getInstance().getAllOrder().getOrder().clone();

    }



    @Override
    public HistoryForCustomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = RowHistoryCustomerBinding.inflate(inflater, parent, false);

        //add something
        View view = binding.getRoot();
        //
        return new HistoryForCustomerViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(HistoryForCustomerViewHolder holder, int position)
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

    public void clearAllItem()
    {
        allItem.clear();
        notifyDataSetChanged();

    }

    public int getSize()
    {
        return allItem.size();
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
