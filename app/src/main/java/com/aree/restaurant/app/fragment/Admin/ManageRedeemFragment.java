package com.aree.restaurant.app.fragment.Admin;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.adapter.RedeemAdapter;
import com.aree.restaurant.app.databinding.FragmentManageRedeemBinding;
import com.aree.restaurant.app.fragment.FragmentLifeCycle;
import com.aree.restaurant.app.model.RequsetModel.GetRewardRequestModel;
import com.aree.restaurant.app.model.RequsetModel.admin.SetRewardStatusRequestModel;
import com.aree.restaurant.app.model.ResponseModel.GetRewardResposeModel;
import com.aree.restaurant.app.model.Singleton.RewardModel;
import com.aree.restaurant.app.network.OnResponseFromServer;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.utils.Utils;
import com.aree.restaurant.app.views.DialogMessageTwoButton;

import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageRedeemFragment extends Fragment  {


    FragmentManageRedeemBinding binding;
    static  ManageRedeemFragment fragment;
    private GetRewardResposeModel responseModel;
    private RedeemAdapter adapter;
    private Restful restful = new Restful();

    private int pos;


    public static ManageRedeemFragment newInstance()
    {

        Bundle args = new Bundle();
        fragment = new ManageRedeemFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public void oneSecBeforeDismiss()
    {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Utils.dismissDialog();
            }
        }, 1000);
    }

    public static ManageRedeemFragment getInstance() {
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_manage_redeem, container, false);
        View rootview = binding.getRoot();
        binding.recyclerView.setHasFixedSize(true);
        binding.SwipeRefresh.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.colorOrange));

        binding.SwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                refreshView();

            }
        });

        binding.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2) );
//        getReward();
        return rootview ;
    }


    public void getReward()
    {
        GetRewardRequestModel requestModel = new GetRewardRequestModel();
        restful.createService(getActivity() , requestModel  , callback);
    }


    public void refreshView()
    {
        Utils.showProgressDialog(getActivity(),"ดาวน์โหลดข้อมูล...");
        binding.SwipeRefresh.setRefreshing(false);
        getReward();

    }



    // call back from restful service
    OnResponseFromServer callback = new OnResponseFromServer()
    {
        @Override
        public void onResponse()
        {
            oneSecBeforeDismiss();
        }

        @Override
        public void onResponse(Object object) {

            oneSecBeforeDismiss();
            responseModel = (GetRewardResposeModel) object;

            adapter = new RedeemAdapter(getActivity() , responseModel.getReward(), new RedeemAdapter.OnItemClickListener()
            {
                @Override
                public void onItemClick(final RewardModel item, final int position)
                {
                    Log.e("AllOrderFragment", "onItemLongClick: " );
                    final DialogMessageTwoButton dialog = new DialogMessageTwoButton(getActivity());

                    if(item.getStatus()==0)
                        dialog.showDialog(getString(R.string.label_delete_row_order_admin)
                            , "คุณต้องการปิดสถานะการแสดงผล " + item.getName());
                    else
                        dialog.showDialog(getString(R.string.label_delete_row_order_admin)
                                , "คุณต้องการเปิดสถานะการแสดงผล " + item.getName());

                    dialog.setOnConfirmListener(new DialogMessageTwoButton.OnDialogClickListener()
                    {
                        @Override
                        public void onClickConfirm()
                        {
                            Utils.showProgressDialog(getActivity(),"ดาวน์โหลดข้อมูล...");
                            SetRewardStatusRequestModel requestModel = new SetRewardStatusRequestModel(  );
                            requestModel.setId(item.getId());
                            requestModel.setStatus( item.getStatus() );
                            restful.createService(getActivity(), requestModel, updateStatus);
                            pos = position ;
                        }

                        @Override
                        public void onCancel() {
                            Log.e("AllOrderFragment", "onCancel: " );
                        }
                    });

                }
            });
            binding.recyclerView.setAdapter( adapter );



        }


        @Override
        public void onBodyError(ResponseBody responseBodyError) {
            oneSecBeforeDismiss();
        }

        @Override
        public void onBodyErrorIsNull() {
            oneSecBeforeDismiss();
        }

        @Override
        public void onFailure(Throwable t) {
            oneSecBeforeDismiss();
        }

        @Override
        public void onFailedConnection() {
            oneSecBeforeDismiss();

        }


    };






    // call back from restful service
    OnResponseFromServer updateStatus = new OnResponseFromServer()
    {
        @Override
        public void onResponse()
        {
            adapter.updateRow(pos);
            Utils.dismissDialog();

        }

        @Override
        public void onResponse(Object object) {
            Utils.dismissDialog();

        }


        @Override
        public void onBodyError(ResponseBody responseBodyError) {
            Utils.dismissDialog();
        }

        @Override
        public void onBodyErrorIsNull() {
            Utils.dismissDialog();
        }

        @Override
        public void onFailure(Throwable t) {

            Utils.dismissDialog();
        }

        @Override
        public void onFailedConnection() {
            Utils.dismissDialog();

        }


    };



}
