package com.aree.restaurant.app.views;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.databinding.ListMenuDialog2Binding;
import com.aree.restaurant.app.databinding.ListMenuDialog3Binding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.Singleton.ShoppingCart;
import com.aree.restaurant.app.utils.FormatUtility;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

/**
 * Created by Admin on 16/5/2560.
 */

public class MenuListDialog3
{

    private String TAG = "jay";
    private Context context;
    private int price;
    private ListMenuDialog3Binding dialogBinding;
    private ArrayList<MenuModel> food = new ArrayList<MenuModel>() ;
    private ArrayList<MenuModel> beverage = new ArrayList<MenuModel>() ;
    private Dialog dialog;
    private boolean expand=false;
    private  CheckBox ch;

    private List<CheckBox> beveragItems = new ArrayList<CheckBox>();
    private List<CheckBox> selectedChecks = new ArrayList<CheckBox>();

    private List<CheckBox> secFoodItems = new ArrayList<CheckBox>();
    private List<CheckBox> secFoodselectedChecks = new ArrayList<CheckBox>();

    private List<CheckBox> generalFoodItems = new ArrayList<CheckBox>();
    private List<CheckBox> generalFoodselectedChecks = new ArrayList<CheckBox>();

    private List<CheckBox> snackItems = new ArrayList<CheckBox>();
    private List<CheckBox> snackSelectedChecks = new ArrayList<CheckBox>();

    private ArrayList<MenuModel> seaFood = new ArrayList<MenuModel>() ;
    private ArrayList<MenuModel> snack = new ArrayList<MenuModel>() ;
    private ArrayList<MenuModel> generalfood = new ArrayList<MenuModel>() ;
    private String foodStr="";
    private String beverageStr="";
    private int choice_seaFood=1;
    private int choice_generalFood=1;
    private int choice_snackFood=1;
    private int choice_beverage=1;


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

    public void addCheckBox()
    {
        int size = secFoodItems.size();
        for(int i = 0  ; i<size ; i++)
            secFoodItems.get(i).setOnClickListener(onClickSeaFood);


        size = generalFoodItems.size();
        for(int i = 0  ; i<size ; i++)
            generalFoodItems.get(i).setOnClickListener(onClickGeneralFood);


        size = snackItems.size();
        for(int i = 0  ; i<size ; i++)
            snackItems.get(i).setOnClickListener(onClickSnack);





    }





    public MenuListDialog3(Context context , ArrayList<MenuModel> food , ArrayList<MenuModel> beverage , int price , int typePromotion    )
    {

        this.price = price;
        this.context = context;
        this.food = food;
        this.beverage = beverage;

        if(typePromotion==2)
        {
            choice_seaFood=2;
            choice_generalFood=1;
            choice_snackFood=1;
            choice_beverage=2;
        }

    }

    public void initDialog()
    {

        dialogBinding = ListMenuDialog3Binding.inflate(LayoutInflater.from(context), (ViewGroup) null, false);
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
                MenuModel dish = new MenuModel(foodStr+beverageStr
                        ,String.valueOf(price) ,amount ,price*amount );

                Log.e(TAG, "getMenuName: "+dish.getMenuName() );


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
        size =  food.size();
        if(size>0)
        {

            dialogBinding.line1.setVisibility(View.VISIBLE);
            dialogBinding.seaFoodSection.setVisibility(View.VISIBLE);


            for (int i = 0; i < size; i++)
            {


                ch = new CheckBox(context);
                ch.setText(food.get(i).getMenuName());
                CompoundButtonCompat.setButtonTintList(ch, colorStateList);



                if( food.get(i).getMenuName().indexOf("ซีฟู๊ต")!=-1 )
                {
                    seaFood.add(food.get(i));
                    dialogBinding.llSeaFood.addView(ch);
                    secFoodItems.add(ch);

                }
                else if( food.get(i).getMenuName().indexOf("กุ้ง")!=-1 )
                {
                    seaFood.add(food.get(i));
                    dialogBinding.llSeaFood.addView(ch);
                    secFoodItems.add(ch);

                }
                else if( food.get(i).getMenuName().indexOf("ปลาหมึก")!=-1 )
                {
                    seaFood.add(food.get(i));
                    dialogBinding.llSeaFood.addView(ch);
                    secFoodItems.add(ch);

                }
                else if( food.get(i).getMenuName().indexOf("ปลา")!=-1 )
                {
                    seaFood.add(food.get(i));
                    dialogBinding.llSeaFood.addView(ch);
                    secFoodItems.add(ch);

                }
                else if( food.get(i).getMenuName().indexOf("หอย")!=-1 )
                {
                    seaFood.add(food.get(i));
                    dialogBinding.llSeaFood.addView(ch);
                    secFoodItems.add(ch);

                }
                else if( food.get(i).getMenuName().indexOf("ปู")!=-1 )
                {
                    seaFood.add(food.get(i));
                    dialogBinding.llSeaFood.addView(ch);
                    secFoodItems.add(ch);

                }
                else if( food.get(i).getMenuName().indexOf("ทะเล")!=-1 )
                {
                    seaFood.add(food.get(i));
                    dialogBinding.llSeaFood.addView(ch);
                    secFoodItems.add(ch);
                }
                else if( food.get(i).getType()==3  )
                {
                    snack.add(food.get(i));
                    dialogBinding.llSnack.addView(ch);
                    snackItems.add(ch);
                }
                else
                {
                    generalfood.add(food.get(i));
                    dialogBinding.llGeneralFood.addView(ch);
                    generalFoodItems.add(ch);

                }

            }

            addCheckBox();

            if(snack.size()!=0)
            {
                dialogBinding.tvSnackMenuList.setText("รายการอาหารว่าง"+" ( เลือกได้ "+choice_snackFood+" รายการ )");
                dialogBinding.snackSection.setVisibility(View.VISIBLE);
                dialogBinding.line3.setVisibility(View.VISIBLE);
                dialogBinding.imgSnackPhoto.setVisibility(View.VISIBLE);
                Glide.with(context).load(snack.get(choice_snackFood-1).getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgSnackPhoto);
                for(int i = 0  ; i<choice_snackFood ; i++)
                    snackItems.get(i).setChecked(true);
                for(int i = 0  ; i<choice_seaFood ; i++)
                    secFoodItems.get(i).setChecked(true);
            }

            if(generalfood.size()!=0)
            {
                dialogBinding.tvMenuList.setText("รายการอาหารทะเล"+" ( เลือกได้ "+choice_seaFood+" รายการ )");
                dialogBinding.tvGeneralMenuList.setText("รายการอาหารทั่วไป"+" ( เลือกได้ "+choice_generalFood+" รายการ )");
                dialogBinding.generalFoodSection.setVisibility(View.VISIBLE);
                dialogBinding.line4.setVisibility(View.VISIBLE);
                dialogBinding.imgGeneralFoodPhoto.setVisibility(View.VISIBLE);
                Glide.with(context).load(generalfood.get(choice_generalFood-1).getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgGeneralFoodPhoto);
                for(int i = 0  ; i<choice_generalFood ; i++)
                    generalFoodItems.get(i).setChecked(true);
            }

            secFoodItems.get(0).setChecked(true);
            Glide.with(context).load(seaFood.get(choice_seaFood-1).getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgFoodPhoto);
        }


        size = beverage.size();
        if(size > 0 )
        {
            dialogBinding.tvBeverageList.setText("รายการเครื่องดื่ม"+" ( เลือกได้ "+choice_beverage+" รายการ )");
            dialogBinding.line2.setVisibility(View.VISIBLE);
            dialogBinding.BeveragesSection.setVisibility(View.VISIBLE);

            for (int i = 0; i < size; i++)
            {

                ch = new CheckBox(context);
                ch.setText(beverage.get(i).getMenuName());
                CompoundButtonCompat.setButtonTintList(ch, colorStateList);
                dialogBinding.llBeverage.addView(ch);
                beveragItems.add(ch);
                beveragItems.get(i).setOnClickListener(onClickBeverage);

            }

            Glide.with(context).load(beverage.get(choice_beverage-1).getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT).placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgBeveragePhoto);
            for(int i = 0  ; i<choice_beverage ; i++)
                beveragItems.get(i).setChecked(true);
        }


        updateAmount();

    }


    public void updateAmount()
    {

        foodStr="";
        beverageStr="";
        int size = 0;
        boolean first = false;

        if(!seaFood.isEmpty())
        {
            size = secFoodItems.size();
            for(int i = 0 ; i<size ; i++)
            {
                if (first==false)
                {
                    if( secFoodItems.get(i).isChecked()==true )
                    {
                        foodStr = foodStr + secFoodItems.get(i).getText().toString();
                        first=true;

                    }
                }
                else
                    if( secFoodItems.get(i).isChecked()==true )
                        foodStr = foodStr+" + "+secFoodItems.get(i).getText().toString();


            }
        }

        if(!generalfood.isEmpty())
        {
            size = generalFoodItems.size();
            for(int i = 0 ; i<size ; i++)
            {
                if( generalFoodItems.get(i).isChecked()==true )
                {
                    foodStr = foodStr+" + "+generalFoodItems.get(i).getText().toString();
                }
            }
        }

        if(!snack.isEmpty())
        {
            size = snackItems.size();
            for(int i = 0 ; i<size ; i++)
            {
                if( snackItems.get(i).isChecked()==true )
                {
                    foodStr = foodStr+" + "+snackItems.get(i).getText().toString();
                }
            }
        }

        if(!beverage.isEmpty())
        {
            size = beveragItems.size();
            for(int i = 0 ; i<size ; i++)
            {
                if( beveragItems.get(i).isChecked()==true )
                {
                    beverageStr = beverageStr+" + "+beveragItems.get(i).getText().toString();
                }
            }
        }



        if(ShoppingCart.getInstance().getMenuList().containsKey( foodStr+beverageStr ))
//        if(ShoppingCart.getInstance().getMenuList().containsKey( food.get(idxFood).getMenuName()+" + "+beverage.get(idxBeverage).getMenuName() ))
        {

            int amount = ShoppingCart.getInstance().getMenuList()
                    .get( foodStr + beverageStr ).getAmount();

            dialogBinding.tvAmount.setText(ShoppingCart.getInstance().getMenuList()
                    .get( foodStr + beverageStr ).getAmount() + "");

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
        RotateAnimation rotate = new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
//                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);

        if(v.getId()==R.id.tvMenuList || v.getId()==R.id.ArrowForFood )
        {
            dialogBinding.llSeaFood.setVisibility(View.GONE);
            dialogBinding.imgFoodPhoto.setVisibility(View.GONE);
            dialogBinding.ArrowForFood.setAnimation(rotate);

        }
        else if(v.getId()==R.id.tvGeneralMenuList || v.getId()==R.id.ArrowForGeneralFood)
        {
            dialogBinding.llGeneralFood.setVisibility(View.GONE);
            dialogBinding.imgGeneralFoodPhoto.setVisibility(View.GONE);
            dialogBinding.ArrowForGeneralFood.setAnimation(rotate);

        }
        else if(v.getId()==R.id.tvSnackMenuList || v.getId()==R.id.ArrowForSnack)
        {
            dialogBinding.llSnack.setVisibility(View.GONE);
            dialogBinding.imgSnackPhoto.setVisibility(View.GONE);
            dialogBinding.ArrowForSnack.setAnimation(rotate);

        }





    }

    public void collapse(View v)
    {

        expand = false;
        RotateAnimation rotate = new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);

//                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);

        if(v.getId()==R.id.tvMenuList || v.getId()==R.id.ArrowForFood )
        {
            dialogBinding.llSeaFood.setVisibility(View.VISIBLE);
            dialogBinding.imgFoodPhoto.setVisibility(View.VISIBLE);
            dialogBinding.ArrowForFood.setAnimation(rotate);

        }
        else if(v.getId()==R.id.tvGeneralMenuList || v.getId()==R.id.ArrowForGeneralFood)
        {
            dialogBinding.llGeneralFood.setVisibility(View.VISIBLE);
            dialogBinding.imgGeneralFoodPhoto.setVisibility(View.VISIBLE);
            dialogBinding.ArrowForGeneralFood.setAnimation(rotate);

        }
        else if(v.getId()==R.id.tvSnackMenuList || v.getId()==R.id.ArrowForSnack)
        {
            dialogBinding.llSnack.setVisibility(View.VISIBLE);
            dialogBinding.imgSnackPhoto.setVisibility(View.VISIBLE);
            dialogBinding.ArrowForSnack.setAnimation(rotate);

        }


    }

    MenuListDialog3.OnDialogClickListener onClick;


    public interface OnDialogClickListener
    {
        void onClickConfirm(MenuModel menu, int amount);
    }


    public void setOnConfirmListener(MenuListDialog3.OnDialogClickListener onClick) {
        this.onClick = onClick;

    }




    View.OnClickListener onClickSeaFood = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {

            CheckBox c = (CheckBox)v;
            int size = seaFood.size() ;

            for(int i = 0 ; i<size;i++)
            {
                if(seaFood.get(i).getMenuName().equals(c.getText().toString()))
                {
                    Glide.with(context).load(seaFood.get(i).getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT)
                            .placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgFoodPhoto);
                }
            }

            if(secFoodselectedChecks.contains(c))
            {
                secFoodselectedChecks.remove(c);
            }
            else
            {
                if(secFoodselectedChecks.size() < choice_seaFood)
                {
                    secFoodselectedChecks.add(c);
                }
                else
                {
                    secFoodselectedChecks.remove(0);
                    secFoodselectedChecks.add(c);
                }
            }

            drawResults(onClickSeaFood);
            updateAmount();


        }};


    View.OnClickListener onClickGeneralFood = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {

            CheckBox c = (CheckBox)v;
            int size = generalfood.size() ;

            for(int i = 0 ; i<size;i++)
            {
                if(generalfood.get(i).getMenuName().equals(c.getText().toString()))
                {
                    Glide.with(context).load(generalfood.get(i).getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT)
                            .placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgGeneralFoodPhoto);
                }
            }

            if(generalFoodselectedChecks.contains(c))
            {
                generalFoodselectedChecks.remove(c);
            }
            else
            {
                if(generalFoodselectedChecks.size() < choice_generalFood)
                {
                    generalFoodselectedChecks.add(c);
                }
                else
                {
                    generalFoodselectedChecks.remove(0);
                    generalFoodselectedChecks.add(c);
                }
            }

            drawResults(onClickGeneralFood);
            updateAmount();


        }};



    View.OnClickListener onClickSnack = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {

            CheckBox c = (CheckBox)v;
            int size = snack.size() ;

            for(int i = 0 ; i<size;i++)
            {
                if(snack.get(i).getMenuName().equals(c.getText().toString()))
                {
                    Glide.with(context).load(snack.get(i).getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT)
                            .placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgSnackPhoto);
                }
            }

            if(snackSelectedChecks.contains(c))
            {
                snackSelectedChecks.remove(c);
            }
            else
            {
                if(snackSelectedChecks.size() < choice_snackFood)
                {
                    snackSelectedChecks.add(c);
                }
                else
                {
                    snackSelectedChecks.remove(0);
                    snackSelectedChecks.add(c);
                }
            }

            drawResults(onClickSnack);
            updateAmount();


        }};


    View.OnClickListener onClickBeverage = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {

            CheckBox c = (CheckBox)v;
            int size = beverage.size() ;

            for(int i = 0 ; i<size;i++)
            {
                if(beverage.get(i).getMenuName().equals(c.getText().toString()))
                {
                    Glide.with(context).load(beverage.get(i).getPhoto()).asBitmap().diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .placeholder(R.drawable.bg_null_photo).into(dialogBinding.imgBeveragePhoto);
                }
            }

            if(selectedChecks.contains(c))
            {
                selectedChecks.remove(c);
            }
            else
            {
                if(selectedChecks.size() < choice_beverage)
                {
                    selectedChecks.add(c);
                }
                else
                {
                    selectedChecks.remove(0);
                    selectedChecks.add(c);
                }
            }

            drawResults(onClickBeverage);
            updateAmount();


        }};


    public void drawResults(View.OnClickListener onClick)
    {

        if(onClick.equals(onClickBeverage))
            for(CheckBox c : beveragItems)
                c.setChecked(selectedChecks.contains(c));

        else if(onClick.equals(onClickSeaFood))
            for(CheckBox c : secFoodItems)
                c.setChecked(secFoodselectedChecks.contains(c));

        else if(onClick.equals(onClickSnack))
            for(CheckBox c : snackItems)
                c.setChecked(snackSelectedChecks.contains(c));

        else if(onClick.equals(onClickGeneralFood))
            for(CheckBox c : generalFoodItems)
                c.setChecked(generalFoodselectedChecks.contains(c));


    }





}
