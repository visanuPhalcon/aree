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
import android.view.animation.RotateAnimation;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.databinding.ListMenuDialog2Binding;
import com.aree.restaurant.app.databinding.ListMenuDialogBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.Singleton.ShoppingCart;
import com.aree.restaurant.app.utils.FormatUtility;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by Admin on 16/5/2560.
 */

public class MenuListDialog2
{

    private String TAG = "jay";
    private Context context;
    private int price;
    private ListMenuDialog2Binding dialogBinding;
    private ArrayList<MenuModel> food = new ArrayList<MenuModel>() ;
    private ArrayList<MenuModel> beverage = new ArrayList<MenuModel>() ;
    private Dialog dialog;
    private View radioButton;
    private int radioButtonID;
    private int idxFood;
    private int idxBeverage;
    private boolean expand=false;
    private int idxGeneralFood;
    private int idxSnack;


    private ArrayList<MenuModel> seaFood = new ArrayList<MenuModel>() ;
    private ArrayList<MenuModel> snack = new ArrayList<MenuModel>() ;
    private ArrayList<MenuModel> generalfood = new ArrayList<MenuModel>() ;
    private String foodStr="";


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





    public MenuListDialog2(Context context , ArrayList<MenuModel> food , ArrayList<MenuModel> beverage , int price , int typePromotion    )
    {

        this.price = price;
        this.context = context;
        this.food = food;
        this.beverage = beverage;

    }

    public void initDialog()
    {

        dialogBinding = ListMenuDialog2Binding.inflate(LayoutInflater.from(context), (ViewGroup) null, false);
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
                MenuModel dish = new MenuModel(foodStr+" + "+beverage.get(idxBeverage).getMenuName()
                        ,String.valueOf(price) ,amount ,price*amount );

                Log.e(TAG, "dish: "+dish.getMenuName() );

                food.clear();
                beverage.clear();
                dialog.dismiss();
                foodStr="";

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


        // seafood
        dialogBinding.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId)
            {
                foodStr="";
                radioButtonID = group.getCheckedRadioButtonId();
                radioButton = group.findViewById(radioButtonID);
                idxFood = group.indexOfChild(radioButton);
                Glide.with(context).load(seaFood.get(idxFood).getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgFoodPhoto);
                updateAmount();
            }
        });

        // beverage
        dialogBinding.radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId)
            {
                foodStr="";
                radioButtonID = group.getCheckedRadioButtonId();
                radioButton = group.findViewById(radioButtonID);
                idxBeverage = group.indexOfChild(radioButton);
                Glide.with(context).load(beverage.get(idxBeverage).getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgBeveragePhoto);
                updateAmount();
            }
        });

        // general food
        dialogBinding.radioGroupGeneralFood.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                foodStr="";
                radioButtonID = group.getCheckedRadioButtonId();
                radioButton = group.findViewById(radioButtonID);
                idxGeneralFood = group.indexOfChild(radioButton);

                Glide.with(context).load(generalfood.get(idxGeneralFood).getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgGeneralFoodPhoto);
                updateAmount();

            }
        });

        // snack
        dialogBinding.radioGroupSnack.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                foodStr="";
                radioButtonID = group.getCheckedRadioButtonId();
                radioButton = group.findViewById(radioButtonID);
                idxSnack = group.indexOfChild(radioButton);
                Glide.with(context).load(snack.get(idxSnack).getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgSnackPhoto);
                updateAmount();

            }
        });

        dialogBinding.tvMenuList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (expand==false) {
                    expand(dialogBinding.tvMenuList);
                }
                else {
                    collapse(dialogBinding.tvMenuList);
                }

            }
        });

        dialogBinding.tvSnackMenuList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (expand==false)
                {
                    expand(dialogBinding.tvSnackMenuList);
                }
                else {
                    collapse(dialogBinding.tvSnackMenuList);
                }

            }
        });

        dialogBinding.tvGeneralMenuList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (expand==false) {
                    expand(dialogBinding.tvGeneralMenuList);
                }
                else {
                    collapse(dialogBinding.tvGeneralMenuList);
                }

            }
        });

        dialogBinding.ArrowForFood.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (expand==false) {
                    expand(dialogBinding.ArrowForFood);
                }
                else {
                    collapse(dialogBinding.ArrowForFood);
                }

            }
        });


        dialogBinding.ArrowForSnack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expand==false) {
                    expand(dialogBinding.ArrowForSnack);
                }
                else {
                    collapse(dialogBinding.ArrowForSnack);
                }
            }
        });

        dialogBinding.ArrowForGeneralFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expand==false) {
                    expand(dialogBinding.ArrowForGeneralFood);
                }
                else {
                    collapse(dialogBinding.ArrowForGeneralFood);
                }
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


            for (int i = 0; i < size; i++)
            {

                rbn = new AppCompatRadioButton(context);
                rbn.setId(i);
                rbn.setText(food.get(i).getMenuName());
                rbn.setSupportButtonTintList(colorStateList);

                if( food.get(i).getMenuName().indexOf("ซีฟู๊ต")!=-1 )
                {
                    dialogBinding.radioGroup.addView(rbn);
                    seaFood.add(food.get(i));
                }
                else if( food.get(i).getMenuName().indexOf("กุ้ง")!=-1 )
                {
                    dialogBinding.radioGroup.addView(rbn);
                    seaFood.add(food.get(i));
                }
                else if( food.get(i).getMenuName().indexOf("ปลาหมึก")!=-1 )
                {
                    dialogBinding.radioGroup.addView(rbn);
                    seaFood.add(food.get(i));
                }
                else if( food.get(i).getMenuName().indexOf("ปลา")!=-1 )
                {
                    dialogBinding.radioGroup.addView(rbn);
                    seaFood.add(food.get(i));
                }
                else if( food.get(i).getMenuName().indexOf("หอย")!=-1 )
                {
                    dialogBinding.radioGroup.addView(rbn);
                    seaFood.add(food.get(i));
                }
                else if( food.get(i).getMenuName().indexOf("ปู")!=-1 )
                {
                    dialogBinding.radioGroup.addView(rbn);
                    seaFood.add(food.get(i));
                }
                else if( food.get(i).getMenuName().indexOf("ทะเล")!=-1 )
                {
                    dialogBinding.radioGroup.addView(rbn);
                    seaFood.add(food.get(i));
                }
                else if( food.get(i).getType()==3  )
                {
                    dialogBinding.radioGroupSnack.addView(rbn);
                    snack.add(food.get(i));
                }
                else
                {
                    dialogBinding.radioGroupGeneralFood.addView(rbn);
                    generalfood.add(food.get(i));
                }

            }


            //TODO:add filter
            if(snack.size()!=0)
            {
                dialogBinding.tvSnackMenuList.setText("รายการอาหารว่าง");
                dialogBinding.snackSection.setVisibility(View.VISIBLE);
                dialogBinding.line3.setVisibility(View.VISIBLE);
                dialogBinding.imgSnackPhoto.setVisibility(View.VISIBLE);
                Glide.with(context).load(snack.get(0).getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgSnackPhoto);
                ((RadioButton) dialogBinding.radioGroupSnack.getChildAt(0)).setChecked(true);

            }

            if(generalfood.size()!=0)
            {
                dialogBinding.tvGeneralMenuList.setText("รายการอาหารทั่วไป");
                dialogBinding.generalFoodSection.setVisibility(View.VISIBLE);
                dialogBinding.line4.setVisibility(View.VISIBLE);
                dialogBinding.imgGeneralFoodPhoto.setVisibility(View.VISIBLE);
                Glide.with(context).load(generalfood.get(0).getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgGeneralFoodPhoto);
                ((RadioButton) dialogBinding.radioGroupGeneralFood.getChildAt(0)).setChecked(true);

            }
            //TODO:add filter

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
//                RadioButton rbn=new RadioButton(context);

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

        if(!seaFood.isEmpty())
            foodStr = seaFood.get(idxFood).getMenuName();

        if(!generalfood.isEmpty())
            foodStr =  foodStr+" + "+generalfood.get(idxGeneralFood).getMenuName();

        if(!snack.isEmpty())
            foodStr =  foodStr+" + "+snack.get(idxSnack).getMenuName();


        if(ShoppingCart.getInstance().getMenuList().containsKey( foodStr+" + "+beverage.get(idxBeverage).getMenuName() ))
//        if(ShoppingCart.getInstance().getMenuList().containsKey( food.get(idxFood).getMenuName()+" + "+beverage.get(idxBeverage).getMenuName() ))
        {

            int amount = ShoppingCart.getInstance().getMenuList()
                    .get( foodStr + " + " + beverage.get(idxBeverage).getMenuName()).getAmount();

            dialogBinding.tvAmount.setText(ShoppingCart.getInstance().getMenuList()
                    .get( foodStr + " + " + beverage.get(idxBeverage).getMenuName()).getAmount() + "");

            dialogBinding.tvTotal.setText("฿ "+FormatUtility.currencyFormat( (amount*price)+"" ));

        }
        else {
            dialogBinding.tvTotal.setText("฿ "+FormatUtility.currencyFormat(price+""));
            dialogBinding.tvAmount.setText("1");
        }



    }



    public void expand(View v)
    {
        expand=true;
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);

        if(v.getId()==R.id.tvMenuList || v.getId()==R.id.ArrowForFood )
        {
            dialogBinding.radioGroup.setVisibility(View.GONE);
            dialogBinding.imgFoodPhoto.setVisibility(View.GONE);
            dialogBinding.ArrowForFood.setAnimation(rotate);

        }
        else if(v.getId()==R.id.tvGeneralMenuList || v.getId()==R.id.ArrowForGeneralFood)
        {
            dialogBinding.radioGroupGeneralFood.setVisibility(View.GONE);
            dialogBinding.imgGeneralFoodPhoto.setVisibility(View.GONE);
            dialogBinding.ArrowForGeneralFood.setAnimation(rotate);

        }
        else if(v.getId()==R.id.tvSnackMenuList || v.getId()==R.id.ArrowForSnack)
        {
            dialogBinding.radioGroupSnack.setVisibility(View.GONE);
            dialogBinding.imgSnackPhoto.setVisibility(View.GONE);
            dialogBinding.ArrowForSnack.setAnimation(rotate);

        }





    }

    public void collapse(View v)
    {

        expand = false;
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);

        if(v.getId()==R.id.tvMenuList || v.getId()==R.id.ArrowForFood )
        {
            dialogBinding.radioGroup.setVisibility(View.VISIBLE);
            dialogBinding.imgFoodPhoto.setVisibility(View.VISIBLE);
            dialogBinding.ArrowForFood.setAnimation(rotate);

        }
        else if(v.getId()==R.id.tvGeneralMenuList || v.getId()==R.id.ArrowForGeneralFood)
        {
            dialogBinding.radioGroupGeneralFood.setVisibility(View.VISIBLE);
            dialogBinding.imgGeneralFoodPhoto.setVisibility(View.VISIBLE);
            dialogBinding.ArrowForGeneralFood.setAnimation(rotate);

        }
        else if(v.getId()==R.id.tvSnackMenuList || v.getId()==R.id.ArrowForSnack)
        {
            dialogBinding.radioGroupSnack.setVisibility(View.VISIBLE);
            dialogBinding.imgSnackPhoto.setVisibility(View.VISIBLE);
            dialogBinding.ArrowForSnack.setAnimation(rotate);

        }


    }

    MenuListDialog2.OnDialogClickListener onClick;


    public interface OnDialogClickListener
    {
        void onClickConfirm(MenuModel menu, int amount);
    }


    public void setOnConfirmListener(MenuListDialog2.OnDialogClickListener onClick) {
        this.onClick = onClick;

    }





}
