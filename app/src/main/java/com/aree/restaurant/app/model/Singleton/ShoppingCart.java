package com.aree.restaurant.app.model.Singleton;

import android.view.Menu;

import com.aree.restaurant.app.model.MenuModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by Admin on 21/5/2560.
 */

public class ShoppingCart {

    public int sizeBeforeRemove;
    public boolean isRemove=false;
    public static ShoppingCart CartModel;
    public HashMap<String,MenuModel> menuList = new HashMap<>();

    public HashMap<String,MenuModel> getMenuList() {
        return menuList;
    }

    public ShoppingCart setMenuList( HashMap<String,MenuModel>menuList) {
        this.menuList = menuList;
        return this;
    }

    public static ShoppingCart getInstance()
    {
        if (CartModel == null)
        {
            CartModel = new ShoppingCart();
        }
        return CartModel;
    }

    public int getTotalCost()
    {
        Iterator myVeryOwnIterator;
        int price=0;
        int amount=0;
        String key="";

        myVeryOwnIterator =  ShoppingCart.getInstance().getMenuList().keySet().iterator();

        while(myVeryOwnIterator.hasNext())
        {
            key = (String) myVeryOwnIterator.next();
            if( ShoppingCart.getInstance().getMenuList().get(key).getType()!=5 )
            {
                amount = ShoppingCart.getInstance().getMenuList().get(key).getAmount();
                price = price + ShoppingCart.getInstance().getMenuList().get(key).getTotal();
            }
        }

        return price;
    }


    public int getTotalPoint()
    {
        Iterator myVeryOwnIterator;
        int point=0;
        int amount=0;
        String key="";

        myVeryOwnIterator =  ShoppingCart.getInstance().getMenuList().keySet().iterator();

        while(myVeryOwnIterator.hasNext())
        {
            key = (String) myVeryOwnIterator.next();
            if( ShoppingCart.getInstance().getMenuList().get(key).getType()==5 )
            {
                amount = ShoppingCart.getInstance().getMenuList().get(key).getAmount();
                point = point + ShoppingCart.getInstance().getMenuList().get(key).getTotal();
            }
        }

        return point;
    }


    public int getCurrentPointRemain()
    {
        Iterator myVeryOwnIterator;
        int point=0;
        int amount=0;
        String key="";

        myVeryOwnIterator =  ShoppingCart.getInstance().getMenuList().keySet().iterator();

        while(myVeryOwnIterator.hasNext())
        {
            key = (String) myVeryOwnIterator.next();
            if( ShoppingCart.getInstance().getMenuList().get(key).getType()==5 )
            {
                amount = ShoppingCart.getInstance().getMenuList().get(key).getAmount();
                point = point + ShoppingCart.getInstance().getMenuList().get(key).getTotal();
            }
        }

        return Integer.parseInt( Singleton.getInstance().getUserInformation().getPoint() )-point;
    }



    public void clearAllData()
    {
        sizeBeforeRemove = ShoppingCart.getInstance().getMenuList().size();
        ShoppingCart.getInstance().getMenuList().clear();
        isRemove = true;
    }


}
