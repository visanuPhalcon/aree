package com.aree.restaurant.app.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.activity.OrderActivity;
import com.aree.restaurant.app.adapter.PromotionAdapter;
import com.aree.restaurant.app.databinding.FragmentPromotionBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.PromotionModel;
import com.aree.restaurant.app.model.RequsetModel.GetMenuFromPromotionRequestModel;
import com.aree.restaurant.app.model.RequsetModel.GetPromotionRequestModel;
import com.aree.restaurant.app.model.ResponseModel.GetMenuFromPromotionResponseModel;
import com.aree.restaurant.app.model.ResponseModel.GetPromotionResponseModel;
import com.aree.restaurant.app.network.OnResponseFromServer;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.utils.Utils;
import com.aree.restaurant.app.views.MenuListDialog;
import com.aree.restaurant.app.views.MenuListDialog2;
import com.aree.restaurant.app.views.MenuListDialog3;
import com.aree.restaurant.app.views.PromotionDialog;

import java.util.ArrayList;

import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class PromotionMenuFragment extends Fragment
{

    private String TAG = "jay";
    private ArrayList<PromotionModel>  promotionList ;
    private ArrayList<MenuModel> food ;
    private ArrayList<MenuModel> beverage ;
    FragmentPromotionBinding binding;
    private PromotionModel thisMenu ;
    private int promotionIndex=0;



    public static PromotionMenuFragment newInstance() {

        PromotionMenuFragment fragment = new PromotionMenuFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_promotion, container, false);
        View rootview = binding.getRoot();
        initView();
        return rootview ;
    }

    public void initView()
    {

        ((OrderActivity)getActivity()).setEnableBtnConfirm();

        if(promotionList==null) {

            GetPromotionRequestModel requestModel = new GetPromotionRequestModel();
            new Restful<>().createService(getActivity(), requestModel, callback);
        }
        else
            initRecycleView();


    }


    public void initRecycleView()
    {
        //        promotionList.add(new PromotionModel(R.drawable.bg_noodle,"โปรโมชั่นชุด กุ้ง แสนอร่อยทะเลคลั่ง",temp,"Set A",59));
        //        promotionList.add(new PromotionModel(R.drawable.bg_rice,"โปรโมชั่นชุด วัววากิว",temp,"Set B",79));
        //        promotionList.add(new PromotionModel(R.drawable.bg_dessert,"โปรโมชั่นชุด เนื้อหมู่สไลสด",temp,"Set C",99));

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        binding.recyclerView.setAdapter(new PromotionAdapter(getActivity(),promotionList, new PromotionAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(final PromotionModel item, int position)
            {
                promotionIndex=position;

                if(Utils.checkConnection(getActivity())==true)
                {
                    Utils.showProgressDialog(getActivity(),"ดาวโหลดข้อมูล");
                    thisMenu = item;
                    GetMenuFromPromotionRequestModel requestModel = new GetMenuFromPromotionRequestModel();
                    requestModel.setId(Integer.parseInt(item.getId()));
                    new Restful<>().createService(getActivity(), requestModel, getMenuFromPromotion);
                }
                else
                    Utils.internetIsNotAvailableDialog(getActivity());


            }
        }));
    }



    public void getMenu(final PromotionModel item)
    {

        final PromotionDialog FoodDialog= new PromotionDialog( getActivity(),item.getPromotionName(),item.getDescription(),item.getImgPromotion() );
        FoodDialog.initDialog();
        FoodDialog.setOnConfirmListener(new PromotionDialog.OnDialogClickListener()
        {

            @Override
            public void onClickConfirm()
            {


                        if(food!=null && beverage!=null)
                        {

                            MenuListDialog3 dialog = new MenuListDialog3(getActivity(), food, beverage, item.getPrice(),promotionIndex);
                            dialog.initDialog();
                            dialog.setTitle(item.getSetName()+" : "+item.getPromotionName() + " " + item.getPrice() + " บาท");
//                            dialog.setTitle(item.getSetName() + " : สุดคุ้ม " + item.getPrice() + " บาท");
                            dialog.setOnConfirmListener(new MenuListDialog3.OnDialogClickListener()
                            {
                                @Override
                                public void onClickConfirm(MenuModel menu , int amount) {
                                    ((OrderActivity) getActivity()).addPromotionOnList(menu,amount);
                                    ((OrderActivity) getActivity()).setEnableBtnConfirm( );

                                }
                            });


                        }




            }
        });
    }


    // call back from restful service
    OnResponseFromServer callback = new OnResponseFromServer()
    {
        @Override
        public void onResponse() {}

        @Override
        public void onResponse(Object object)
        {
            GetPromotionResponseModel model = (GetPromotionResponseModel) object ;
            promotionList = model.getPromotion();
            initRecycleView();
        }


        @Override
        public void onBodyError(ResponseBody responseBodyError) {
        }

        @Override
        public void onBodyErrorIsNull() {
        }

        @Override
        public void onFailure(Throwable t) {
        }

        @Override
        public void onFailedConnection() {

        }


    };



    // call back from restful service
    OnResponseFromServer getMenuFromPromotion = new OnResponseFromServer()
    {
        @Override
        public void onResponse() {}

        @Override
        public void onResponse(Object object)
        {
            GetMenuFromPromotionResponseModel model = (GetMenuFromPromotionResponseModel) object ;
            int size = model.getAllMenu().size();
            food = new ArrayList<MenuModel>();
            beverage = new ArrayList<MenuModel>();

            for(int i = 0 ; i<size ; i++)
            {
                if(model.getAllMenu().get(i).getType()==4)
                    beverage.add(model.getAllMenu().get(i));
                else
                    food.add(model.getAllMenu().get(i));

            }

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    Utils.dismissDialog();
                    getMenu(thisMenu);

                }
            }, 2000);


        }


        @Override
        public void onBodyError(ResponseBody responseBodyError) {

            Log.e(TAG, "onBodyError: " );
        }

        @Override
        public void onBodyErrorIsNull() {
            Log.e(TAG, "onBodyErrorIsNull: " );
        }

        @Override
        public void onFailure(Throwable t) {
            Log.e(TAG, "onFailure: " );
        }

        @Override
        public void onFailedConnection() {


        }


    };

}
