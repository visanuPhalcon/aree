package com.aree.restaurant.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.adapter.MenuAdapter;
import com.aree.restaurant.app.adapter.PointAdapter;
import com.aree.restaurant.app.adapter.ShoppingCartAdapter;
import com.aree.restaurant.app.databinding.ActivityMainBinding;
import com.aree.restaurant.app.databinding.ActivityRedeemRewardBinding;
import com.aree.restaurant.app.databinding.DialogShowRewardBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.aree.restaurant.app.model.RequsetModel.GetMenuRequestModel;
import com.aree.restaurant.app.model.RequsetModel.GetRewardRequestModel;
import com.aree.restaurant.app.model.ResponseModel.GetMenuResponseModel;
import com.aree.restaurant.app.model.ResponseModel.GetRewardResposeModel;
import com.aree.restaurant.app.model.Singleton.RewardList;
import com.aree.restaurant.app.model.Singleton.RewardModel;
import com.aree.restaurant.app.model.Singleton.ShoppingCart;
import com.aree.restaurant.app.model.Singleton.Singleton;
import com.aree.restaurant.app.model.Singleton.UserInformation;
import com.aree.restaurant.app.network.OnResponseFromServer;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.utils.FormatUtility;
import com.aree.restaurant.app.utils.UtilView;
import com.aree.restaurant.app.utils.Utils;
import com.aree.restaurant.app.views.CustomActionBar;
import com.aree.restaurant.app.views.DialogMessageOneButton;
import com.aree.restaurant.app.views.FoodMenuDialog;
import com.aree.restaurant.app.views.RewardMenuDialog;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;

import okhttp3.ResponseBody;

public class RedeemRewardActivity extends BaseActivity {


    ActivityRedeemRewardBinding binding;
    static CustomActionBar actionBar;
    private GetRewardResposeModel responseModel;
    private PointAdapter adapter;
    private View inflatedView;
    private boolean showDialog = false;
    private DialogShowRewardBinding viewBinding;
    private int point;
    private int amount;
    private ShoppingCartAdapter order ;
    private RewardList reward;
    private int pointForUser;


    @Override
    protected void onResume()
    {
        super.onResume();




        if(Singleton.getInstance().getUserInformation()!=null)
            binding.tvPoints.setText(FormatUtility.currencyFormat(Singleton.getInstance().getUserInformation().getPoint())+" Points");


        if(ShoppingCart.getInstance().isRemove == true)
        {
            order.notifyItemRangeRemoved(0,ShoppingCart.getInstance().sizeBeforeRemove);
            ShoppingCart.getInstance().isRemove = false ;
            setBasket(ShoppingCart.getInstance().getTotalCost());
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_redeem_reward);




        if( Utils.checkConnection(RedeemRewardActivity.this)==true   )
        {
            setSupportActionBar(binding.included.toolbar);
            actionBar = new CustomActionBar(this, getSupportActionBar());
            actionBar.setTitleBar(getString(R.string.label_redeem_reward));
            actionBar.setDisplayHomeAsUpEnabled();
            binding.tvPoints.setText(FormatUtility.currencyFormat(Singleton.getInstance().getUserInformation().getPoint())+" Points");
            binding.recyclerView.setHasFixedSize(true);
            binding.recyclerView.setLayoutManager(new GridLayoutManager(RedeemRewardActivity.this, 2) );
            getReward();

//        if(ShoppingCart.getInstance()!=null)
//        {

            binding.basketView.setPriceText("฿ "+ FormatUtility.currencyFormat(ShoppingCart.getInstance().getTotalCost()+""));
//        }


            binding.basketView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if(inflatedView==null)
                        initDialog();

                    if(showDialog==false) {
                        showDialog();
                    }
                    else {
                        closeDialog();
                    }

                }
            });
        }
        else
        {
            final DialogMessageOneButton dialog = new DialogMessageOneButton( RedeemRewardActivity.this );
            dialog.showDialog( getString(R.string.label_internet_isnot_available), getString(R.string.label_error_internet_isnot_available));
            dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                @Override
                public void onClickConfirm() {

                    finish();

                }
            });

        }





    }

    public void getReward()
    {
        GetRewardRequestModel requestModel = new GetRewardRequestModel();
        new Restful<>().createService(RedeemRewardActivity.this , requestModel  , callback);
    }


    public void initDialog()
    {

        pointForUser =Integer.parseInt( Singleton.getInstance().getUserInformation().getPoint() );
        inflatedView = binding.viewStubRewardList.getViewStub().inflate();
        viewBinding = DataBindingUtil.bind(inflatedView);

        Log.e("jay", "initDialog: "+(ShoppingCart.getInstance().getTotalPoint()<=pointForUser)  );
        Log.e("jay", "initDialog: "+  ( ShoppingCart.getInstance().getMenuList().size()>0) );


        setEnableBtnConfirm( (ShoppingCart.getInstance().getMenuList().size()>0) &&
                ( ShoppingCart.getInstance().getTotalPoint()<=pointForUser  ) );

        viewBinding.recyclerView.setHasFixedSize(true);
        viewBinding.recyclerView.setLayoutManager(new LinearLayoutManager(RedeemRewardActivity.this, LinearLayoutManager.VERTICAL, false));
//        viewBinding.recyclerView.setAdapter(order);
    }

    public void showDialog()
    {

        pointForUser =Integer.parseInt( Singleton.getInstance().getUserInformation().getPoint() );
        showDialog=true;
        int size = ShoppingCart.getInstance().getMenuList().size();
        setEnableBtnConfirm( (ShoppingCart.getInstance().getMenuList().size()>0) &&
                ( ShoppingCart.getInstance().getTotalPoint()<=pointForUser  ) );

        order = new ShoppingCartAdapter(RedeemRewardActivity.this, ShoppingCart.getInstance().getMenuList(), new ShoppingCartAdapter.OnItemClickListener()
        {
            @Override
            public void OnRemoveItemAt(MenuModel item, int position)
            {

                ShoppingCart.getInstance().getMenuList().remove(item.getMenuName());
                order.notifyItemRemoved(position);
                viewBinding.tvTotalMenu.setText(" "+ShoppingCart.getInstance().getMenuList().size()+" รายการ");
                setBasket(ShoppingCart.getInstance().getTotalCost());
                setEnableBtnConfirm(ShoppingCart.getInstance().getMenuList().size()>0&&
                        ( ShoppingCart.getInstance().getTotalPoint()<=pointForUser  ) );





            }

            @Override
            public void OnIncreaseAmount(MenuModel item, int position)
            {

//                Log.e("OnIncreaseAmount", "item: getMenuName "+item.getMenuName() );
//                Log.e("OnIncreaseAmount", "item: getAmount "+item.getAmount() );
//                Log.e("OnIncreaseAmount", "item: getType "+item.getType() );



                setBasket(ShoppingCart.getInstance().getTotalCost());
                setEnableBtnConfirm(ShoppingCart.getInstance().getMenuList().size()>0&&
                        ( ShoppingCart.getInstance().getTotalPoint()<=pointForUser  ) );
            }

            @Override
            public void OnDecreaseAmount(MenuModel item, int position) {
                setBasket(ShoppingCart.getInstance().getTotalCost());
                setEnableBtnConfirm(ShoppingCart.getInstance().getMenuList().size()>0&&
                        ( ShoppingCart.getInstance().getTotalPoint()<=pointForUser  ) );


            }
        });


        viewBinding.btnConfirmOrder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(Singleton.getInstance().getUserInformation()!=null)
                {
                    closeDialog();
                    viewBinding.btnConfirmOrder.setEnabled(false);
                    startActivity(new Intent(RedeemRewardActivity.this, ShoppingCartActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
                else
                {
                    DialogMessageOneButton dialog = new DialogMessageOneButton( RedeemRewardActivity.this  );
                    dialog.showDialog(getString(R.string.label_log_in),getString(R.string.label_please_login_before_buying));
                    dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
                        @Override
                        public void onClickConfirm() {
                            finish();
//                                startActivity(new Intent(OrderActivity.this, MainActivity.class));
                        }
                    });
                }
            }
        });

        viewBinding.recyclerView.setAdapter(order);
        order.notifyItemInserted( size );
        setBasket(ShoppingCart.getInstance().getTotalCost());
        UtilView.showElements(inflatedView);

    }

    public void  closeDialog()
    {
        showDialog = false;
        UtilView.hideElements(inflatedView);
    }



    // call back from restful service
    OnResponseFromServer callback = new OnResponseFromServer()
    {
        @Override
        public void onResponse() {}

        @Override
        public void onResponse(Object object)
        {
            responseModel = (GetRewardResposeModel) object ;
            adapter = new PointAdapter(RedeemRewardActivity.this, responseModel.getReward(), new PointAdapter.OnItemClickListener()
            {
                @Override
                public void onItemClick(final RewardModel item, int position)
                {
                    RewardMenuDialog dialog = new RewardMenuDialog( RedeemRewardActivity.this , item );
                    dialog.initDialog();
                    dialog.setOnConfirmListener(new RewardMenuDialog.OnDialogClickListener()
                    {
                        @Override
                        public void onClickConfirm(int amount)
                        {

                            addReward( item ,amount );
//                            ((OrderActivity)getActivity()).setEnableBtnConfirm(ShoppingCart.getInstance().getMenuList().size()>0);
                        }
                    });
                }
            });
            binding.recyclerView.setAdapter( adapter );

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
            finish();

        }

    };


    public void setBasket(int price)
    {

        viewBinding.tvTotalMenu.setText(" "+ShoppingCart.getInstance().getMenuList().size()+" รายการ");
        viewBinding.tvTotalCost.setText("฿ "+ FormatUtility.currencyFormat(price+""));
        viewBinding.tvTotalPoint.setText("P "+ FormatUtility.currencyFormat( ShoppingCart.getInstance().getTotalPoint()+"" ) );
        binding.basketView.setPriceText("฿ "+FormatUtility.currencyFormat(price+""));

    }

    public void setEnableBtnConfirm(boolean enable)
    {
        viewBinding.btnConfirmOrder.setEnabled(enable);
    }




    public void addReward( RewardModel menuModel , int amount )
    {
        point=0;

        if(!ShoppingCart.getInstance().getMenuList().containsKey(menuModel.getName()))
        {

            // reward == 5
            point= menuModel.getPoint()*amount ;
            ShoppingCart.getInstance().getMenuList().put(menuModel.getName()
                    , new MenuModel( menuModel.getName() , menuModel.getPoint()
                            , amount ,point
                    , 5 , menuModel.getId()  )
            );


        }
        else
        {
            point= menuModel.getPoint()*amount ;
            ShoppingCart.getInstance().getMenuList().get(menuModel.getName()).setTotal( point );
            ShoppingCart.getInstance().getMenuList().get(menuModel.getName()).setAmount( amount );
        }




        if(ShoppingCart.getInstance().getMenuList().containsKey(menuModel.getName()))
        {
            Log.e("reward", "getAmonut: " + ShoppingCart.getInstance().getMenuList().get(menuModel.getName()).getAmount());
            Log.e("reward", "getPoint: " + ShoppingCart.getInstance().getMenuList().get(menuModel.getName()).getPoint());
            Log.e("reward", "getPrice: " + ShoppingCart.getInstance().getMenuList().get(menuModel.getName()).getPrice());
            Log.e("reward", "getTotal: " + ShoppingCart.getInstance().getMenuList().get(menuModel.getName()).getTotal());

        }



    }


    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
    }
}
