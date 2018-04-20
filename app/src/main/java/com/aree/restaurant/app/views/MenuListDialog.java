package com.aree.restaurant.app.views;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.databinding.ListMenuDialogBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.Singleton.ShoppingCart;
import com.aree.restaurant.app.utils.FormatUtility;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by Admin on 16/5/2560.
 */

public class MenuListDialog
{

    private String TAG = "jay";
    private Context context;
    private int price;
    private ListMenuDialogBinding dialogBinding;
    private ArrayList<MenuModel> food = new ArrayList<MenuModel>() ;
    private ArrayList<MenuModel> beverage = new ArrayList<MenuModel>() ;
    private Dialog dialog;
    private View radioButton;
    private int radioButtonID;
    private int idxFood;
    private int idxBeverage;

    private AppCompatRadioButton rbn;
    private ColorStateList colorStateList = new ColorStateList(
            new int[][]{
                    new int[]{-android.R.attr.state_checked},
                    new int[]{android.R.attr.state_checked}
            },
            new int[]{

                    Color.parseColor("#77bd1f")
                    , Color.parseColor("#77bd1f"),
            }
    );





    public MenuListDialog(Context context , ArrayList<MenuModel> food , ArrayList<MenuModel> beverage , int price , int typePromotion    )
    {
        this.price = price;
        this.context = context;
        this.food = food;
        this.beverage = beverage;
    }

    public void initDialog()
    {

        dialogBinding = ListMenuDialogBinding.inflate(LayoutInflater.from(context), (ViewGroup) null, false);
        dialog = new Dialog(context, R.style.Theme_AppCompat_Dialog);
        dialog.setCancelable(true);
        dialog.setContentView(dialogBinding.getRoot());

        dialogBinding.tvTotal.setText("฿ "+ FormatUtility.currencyFormat(price+"") );
        initData();


        dialogBinding.btnOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                int amount  = Integer.parseInt( dialogBinding.tvAmount.getText().toString() );
                MenuModel dish = new MenuModel(food.get(idxFood).getMenuName()+" + "+beverage.get(idxBeverage).getMenuName()
                        ,String.valueOf(price) ,amount ,price*amount );

                food.clear();
                beverage.clear();
                dialog.dismiss();

                if( onClick != null )
                    onClick.onClickConfirm(dish,amount);

            }
        });

        dialogBinding.imgClose.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                food.clear();
                beverage.clear();
                dialog.dismiss();

            }
        });


        dialogBinding.btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                int amount = Integer.parseInt( dialogBinding.tvAmount.getText().toString() );
                if(amount>=1)
                {
                    amount = amount + 1 ;
                    dialogBinding.tvAmount.setText( String.valueOf( amount )  );
                    int total = amount*price;
                    dialogBinding.tvTotal.setText("฿ "+FormatUtility.currencyFormat(total+""));
                }
            }
        });


        dialogBinding.btnDecrease.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                int amount = Integer.parseInt( dialogBinding.tvAmount.getText().toString() );
                if(amount>1)
                {
                    amount = amount - 1 ;
                    int total = amount*price;
                    dialogBinding.tvAmount.setText(  String.valueOf( amount ) );
                    dialogBinding.tvTotal.setText("฿ "+FormatUtility.currencyFormat(total+""));

                }
            }
        });

        dialogBinding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId)
            {
                radioButtonID = group.getCheckedRadioButtonId();
                radioButton = group.findViewById(radioButtonID);
                idxFood = group.indexOfChild(radioButton);
                Glide.with(context).load(food.get(idxFood).getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgFoodPhoto);
                updateAmount();
            }
        });

        dialogBinding.radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId)
            {
                radioButtonID = group.getCheckedRadioButtonId();
                radioButton = group.findViewById(radioButtonID);
                idxBeverage = group.indexOfChild(radioButton);
                Glide.with(context).load(beverage.get(idxBeverage).getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgBeveragePhoto);
                updateAmount();
            }
        });



        dialog.show();

    }

    public void setTitle(String title)
    {
        dialogBinding.tvTitle.setText(title);
    }


    public void initData()
    {

        int size = 0;
        dialogBinding.radioGroup.removeAllViewsInLayout();
        size =  food.size();



        if(size>0)
        {

            dialogBinding.line1.setVisibility(View.VISIBLE);
            dialogBinding.seaFoodSection.setVisibility(View.VISIBLE);

            //TODO:add filter

            for(int i = 0 ; i<size ; i++)
            {
                Log.e(TAG, "menu name: "+food.get(i).getMenuName() );
                Log.e(TAG, "index: "+food.get(i).getMenuName().indexOf("ซีฟู๊ต") );
                Log.e(TAG, "index: "+food.get(i).getMenuName().indexOf("กุ้ง") );
            }

            //

            for (int i = 0; i < size; i++) {

                rbn = new AppCompatRadioButton(context);
                rbn.setId(i);
                rbn.setText(food.get(i).getMenuName());
                rbn.setSupportButtonTintList(colorStateList);

                dialogBinding.radioGroup.addView(rbn);
            }


            Glide.with(context).load(food.get(0).getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgFoodPhoto);
            ((RadioButton) dialogBinding.radioGroup.getChildAt(0)).setChecked(true);
        }

        size = beverage.size();
        if(size > 0 )
        {
            dialogBinding.line2.setVisibility(View.VISIBLE);
            dialogBinding.BeveragesSection.setVisibility(View.VISIBLE);
            for (int i = 0; i < size; i++)
            {
                rbn = new AppCompatRadioButton(context);
                rbn.setId(i);
                rbn.setText(beverage.get(i).getMenuName());
                rbn.setSupportButtonTintList(colorStateList);
                dialogBinding.radioGroup2.addView(rbn);

            }

            Glide.with(context).load(beverage.get(0).getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgBeveragePhoto);
            ((RadioButton) dialogBinding.radioGroup2.getChildAt(0)).setChecked(true);


        }


        updateAmount();

    }


    public void updateAmount()
    {
        if(ShoppingCart.getInstance().getMenuList().containsKey( food.get(idxFood).getMenuName()+" + "+beverage.get(idxBeverage).getMenuName() ))
        {

            int amount = ShoppingCart.getInstance().getMenuList()
                    .get(food.get(idxFood).getMenuName() + " + " + beverage.get(idxBeverage).getMenuName()).getAmount();

            dialogBinding.tvAmount.setText(ShoppingCart.getInstance().getMenuList()
                    .get(food.get(idxFood).getMenuName() + " + " + beverage.get(idxBeverage).getMenuName()).getAmount() + "");

            dialogBinding.tvTotal.setText("฿ "+FormatUtility.currencyFormat( (amount*price)+"" ));

        }
        else {
            dialogBinding.tvTotal.setText("฿ "+FormatUtility.currencyFormat(price+""));
            dialogBinding.tvAmount.setText("1");
        }
    }





    MenuListDialog.OnDialogClickListener onClick;


    public interface OnDialogClickListener
    {
        void onClickConfirm(MenuModel menu,int amount);
    }


    public void setOnConfirmListener(MenuListDialog.OnDialogClickListener onClick)
    {
        this.onClick = onClick;
    }





}
