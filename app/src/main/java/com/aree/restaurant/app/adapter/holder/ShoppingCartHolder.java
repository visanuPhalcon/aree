package com.aree.restaurant.app.adapter.holder;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aree.restaurant.app.adapter.ShoppingCartAdapter;
import com.aree.restaurant.app.databinding.ViewOrderItemBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.Singleton.ShoppingCart;
import com.aree.restaurant.app.utils.FormatUtility;


public class ShoppingCartHolder extends RecyclerView.ViewHolder  {

    private String TAG="jay";
    private int amount;
    private int total;
    public ViewOrderItemBinding binding;
    private  Context context;

    public ShoppingCartHolder(View itemView , Context context)
    {
        super(itemView);
        this.context = context;
        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(final MenuModel menu, final Context context, final int position, final ShoppingCartAdapter.OnItemClickListener listener)
    {

            binding.tvFoodName.setText(menu.getMenuName());
            binding.tvQuantity.setText(String.valueOf(menu.getAmount()));

            if(menu.getType()!=5)
                binding.tvPrice.setText("฿ " + FormatUtility.currencyFormat(String.valueOf(menu.getTotal())));
            else
                binding.tvPrice.setText("P " + FormatUtility.currencyFormat(String.valueOf(menu.getTotal())));


        binding.imgAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(menu.getType()!=5)
                    {
                        amount = ShoppingCart.getInstance().getMenuList().get(menu.getMenuName()).getAmount();
                        amount = amount + 1;
                        ShoppingCart.getInstance().getMenuList().get(menu.getMenuName()).setAmount(amount);
                        total = amount * Integer.parseInt(ShoppingCart.getInstance().getMenuList().get(menu.getMenuName()).getPrice());
                        ShoppingCart.getInstance().getMenuList().get(menu.getMenuName()).setTotal(total);
                        binding.tvQuantity.setText(String.valueOf(amount));
                        binding.tvPrice.setText("฿ " + FormatUtility.currencyFormat(String.valueOf(total)));
                        listener.OnIncreaseAmount(menu, getAdapterPosition());
                    }
                    else
                    {
                        amount = ShoppingCart.getInstance().getMenuList().get(menu.getMenuName()).getAmount();
                        if(amount<99)
                            amount = amount + 1;

                        ShoppingCart.getInstance().getMenuList().get(menu.getMenuName()).setAmount(amount);
                        total = amount * ShoppingCart.getInstance().getMenuList().get(menu.getMenuName()).getPoint() ;
                        ShoppingCart.getInstance().getMenuList().get(menu.getMenuName()).setTotal(total);
                        binding.tvQuantity.setText(String.valueOf(amount));
                        binding.tvPrice.setText("P " + FormatUtility.currencyFormat(String.valueOf(total)));
                        listener.OnIncreaseAmount(menu, getAdapterPosition());
                    }

                }
            });

            binding.imgRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(menu.getType()!=5)
                    {
                        amount = ShoppingCart.getInstance().getMenuList().get(menu.getMenuName()).getAmount();
                        if (amount > 1) {
                            amount = amount - 1;
                            ShoppingCart.getInstance().getMenuList().get(menu.getMenuName()).setAmount(amount);
                            total = amount * Integer.parseInt(ShoppingCart.getInstance().getMenuList().get(menu.getMenuName()).getPrice());
                            ShoppingCart.getInstance().getMenuList().get(menu.getMenuName()).setTotal(total);
                            binding.tvQuantity.setText(String.valueOf(amount));
                            binding.tvPrice.setText("฿ " + FormatUtility.currencyFormat(String.valueOf(total)));
                            listener.OnDecreaseAmount(menu, getAdapterPosition());

                        }
                    }
                    else
                    {
                        amount = ShoppingCart.getInstance().getMenuList().get(menu.getMenuName()).getAmount();
                        if (amount > 1) {
                            amount = amount - 1;
                            ShoppingCart.getInstance().getMenuList().get(menu.getMenuName()).setAmount(amount);
                            total = amount * ShoppingCart.getInstance().getMenuList().get(menu.getMenuName()).getPoint();
                            ShoppingCart.getInstance().getMenuList().get(menu.getMenuName()).setTotal(total);
                            binding.tvQuantity.setText(String.valueOf(amount));
                            binding.tvPrice.setText("P " + FormatUtility.currencyFormat(String.valueOf(total)));
                            listener.OnDecreaseAmount(menu, getAdapterPosition());

                        }
                    }

                }

            });

            binding.imgCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    listener.OnRemoveItemAt(menu, getAdapterPosition());
                }
            });


    }



}
