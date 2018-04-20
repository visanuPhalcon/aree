package com.aree.restaurant.app.views;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatRadioButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.databinding.MenuDialogBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.PromotionModel;
import com.aree.restaurant.app.model.Singleton.ShoppingCart;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Admin on 14/5/2560.
 */

public class FoodMenuDialog
{


    private String TAG = "jay";
    private Context context;
    private String title;
    private String photo;
    private String description;
    private MenuDialogBinding dialogBinding;
    protected MenuModel item;
    private AppCompatRadioButton rbn;
    private RadioButton radioButton;
    private int idx;
    private MenuModel newItem;

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



    public FoodMenuDialog( Context context , MenuModel item  )
    {
        this.context = context;
        this.item = item;

    }


    public FoodMenuDialog(Context context , String title , String description , String photo  )
    {
        this.context = context;
        this.title = title;
        this.description = description;
        this.photo = photo;
    }

    public FoodMenuDialog(Context context , String title , String description  )
    {
        this.context = context;
        this.title = title;
        this.description = description;
    }


    public void initDialog()
    {

        dialogBinding = MenuDialogBinding.inflate(LayoutInflater.from(context), (ViewGroup) null, false);
        final Dialog dialog = new Dialog(context, R.style.Theme_AppCompat_Dialog);
        dialog.setCancelable(true);
        dialog.setContentView(dialogBinding.getRoot());
        dialog.show();
//        dialogBinding.tvNameMenu.setText(title);
//        dialogBinding.tvMenuDescription.setText(description);
        dialogBinding.tvNameMenu.setText(item.getMenuName());
        Glide.with(context).load(item.getPhoto()).diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgFoodPhoto);



//        Log.e(TAG, "getMenuName: "+item.getMenuName() );
        if( item.getMenuName().equals("ไข่เจียว") || item.getMenuName().equals("ไข่ดาว")  )
            dialogBinding.pickEgg.setVisibility(View.INVISIBLE);
        else if( item.getType() !=1  )
            dialogBinding.pickEgg.setVisibility(View.INVISIBLE);






        if(ShoppingCart.getInstance().getMenuList().containsKey(item.getMenuName()))
            dialogBinding.etAmount.setText( ShoppingCart.getInstance().getMenuList().get(item.getMenuName()).getAmount()+""  );

        dialogBinding.imgClose.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();
            }
        });


        dialogBinding.btnOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialog.dismiss();

                if( onClick != null && item.getType()==1 )
                {
                    if( !item.getMenuName().equals("ไข่เจียว") && !item.getMenuName().equals("ไข่ดาว")  )
                    {

                        newItem = new MenuModel();
                        newItem.setAmount(item.getAmount());
                        newItem.setPrice(item.getPrice());
                        newItem.setMenuName(item.getMenuName());
                        newItem.setTotal(item.getTotal());
                        newItem.setDescription(item.getDescription());
                        newItem.setPoint(item.getPoint());
                        newItem.setType(item.getType());
                        newItem.setId(item.getId());

                        int plusPrice = Integer.parseInt( newItem.getPrice() );

                        if( idx==1 )
                        {
                            newItem.setMenuName(newItem.getMenuName() + " + " + "ไข่ดาว");
                            plusPrice = plusPrice+10;
                        }
                        else if( idx==2 )
                        {
                            newItem.setMenuName(newItem.getMenuName() + " + " + "ไข่เจียว");
                            plusPrice = plusPrice+15;

                        }

                        newItem.setPrice(plusPrice+"");
//                        Log.e(TAG, "getPrice: "+newItem.getPrice() );
                        onClick.onClickConfirm(Integer.parseInt(dialogBinding.etAmount.getText().toString()), newItem);


                    }
                    else
                    {
                        onClick.onClickConfirm( Integer.parseInt( dialogBinding.etAmount.getText().toString()  ) );
                    }
//                    Log.e(TAG, "getMenuName: "+newItem.getMenuName() );
//                    onClick.onClickConfirm(Integer.parseInt(dialogBinding.etAmount.getText().toString()), newItem);
                }

                if( onClick != null && item.getType()!=1 )
                    onClick.onClickConfirm( Integer.parseInt( dialogBinding.etAmount.getText().toString()  ) );

            }
        });



        dialogBinding.imgAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                int amount = Integer.parseInt( dialogBinding.etAmount.getText().toString() );
                if(amount>=1)
                {
                    amount = amount + 1 ;
                    dialogBinding.etAmount.setText( String.valueOf( amount )  );
                    int total = amount*Integer.parseInt( item.getPrice() );
                }
            }
        });


        dialogBinding.imgRemove.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int amount = Integer.parseInt( dialogBinding.etAmount.getText().toString() );
                if(amount>1)
                {
                    amount = amount - 1 ;
                    int total = amount*Integer.parseInt( item.getPrice() );
                    dialogBinding.etAmount.setText(  String.valueOf( amount ) );


                }
            }
        });



        dialogBinding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) 
            {


                radioButton =(RadioButton)  group.findViewById(checkedId);
                idx = group.indexOfChild(radioButton);

                switch(idx)
                {
                    case 0:
                        if(ShoppingCart.getInstance().getMenuList().containsKey(item.getMenuName()))
                            dialogBinding.etAmount.setText( ShoppingCart.getInstance().getMenuList().get(item.getMenuName()).getAmount()+""  );
                        else
                            dialogBinding.etAmount.setText( "1" );
                        break;
                    case 1:
                        if(ShoppingCart.getInstance().getMenuList().containsKey(item.getMenuName() + " + " + "ไข่ดาว"))
                        {
//                            Log.e(TAG, "ไข่ดาว: " );
//                            Log.e(TAG, "จำนวน: "+ShoppingCart.getInstance().getMenuList().get(item.getMenuName() + " + " + "ไข่ดาว").getAmount() );
                            dialogBinding.etAmount.setText( ShoppingCart.getInstance().getMenuList().get(item.getMenuName() + " + " + "ไข่ดาว").getAmount()+""  );
                        }
                        else
                            dialogBinding.etAmount.setText( "1" );

                        break;
                    case 2:
                        if(ShoppingCart.getInstance().getMenuList().containsKey(item.getMenuName()+ " + " + "ไข่เจียว"))
                        {
//                            Log.e(TAG, "ไข่เจียม: " );
//                            Log.e(TAG, "จำนวน: "+ShoppingCart.getInstance().getMenuList().get(item.getMenuName() + " + " + "ไข่เจียม").getAmount() );
                            dialogBinding.etAmount.setText( ShoppingCart.getInstance().getMenuList().get(item.getMenuName() + " + " + "ไข่เจียว").getAmount()+""  );
                        }
                        else
                            dialogBinding.etAmount.setText( "1" );

                        break;
                    default:
                        dialogBinding.etAmount.setText( "1" );
                        break;


                }






            }
        });




        // init radio button
        rbn = new AppCompatRadioButton(context);
        rbn.setId(0);
        rbn.setText("ไม่มีไข่");
        rbn.setSupportButtonTintList(colorStateList);
        dialogBinding.radioGroup.addView(rbn);


        rbn = new AppCompatRadioButton(context);
        rbn.setId(1);
        rbn.setText("ไข่ดาว\n10 บ.");
        rbn.setSupportButtonTintList(colorStateList);
        dialogBinding.radioGroup.addView(rbn);

        rbn = new AppCompatRadioButton(context);
        rbn.setId(2);
        rbn.setText("ไข่เจียว\n15 บ.");
        rbn.setSupportButtonTintList(colorStateList);
        dialogBinding.radioGroup.addView(rbn);


        ((RadioButton) dialogBinding.radioGroup.getChildAt(0)).setChecked(true);




    }


    OnDialogClickListener onClick;


    public interface OnDialogClickListener
    {
        void onClickConfirm(int amount);
        void onClickConfirm(int amount,MenuModel item);
    }


    public void setOnConfirmListener(OnDialogClickListener onClick) {
        this.onClick = onClick;

    }









}
