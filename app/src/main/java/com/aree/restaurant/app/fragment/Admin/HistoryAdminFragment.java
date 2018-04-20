package com.aree.restaurant.app.fragment.Admin;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.adapter.admin.AllOrderAdapter;
import com.aree.restaurant.app.adapter.admin.SaleHistoryAdapter;
import com.aree.restaurant.app.databinding.FragmentHistoryAdminBinding;
import com.aree.restaurant.app.fragment.FragmentLifeCycle;
import com.aree.restaurant.app.model.RequsetModel.admin.GetAllMenuRequestModel;
import com.aree.restaurant.app.model.RequsetModel.admin.GetHistoryRequestModel;
import com.aree.restaurant.app.model.ResponseModel.admin.GetHistoryResponseModel;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.aree.restaurant.app.network.OnResponseFromServer;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.utils.FormatUtility;
import com.aree.restaurant.app.utils.Utils;
import com.aree.restaurant.app.views.DialogMessageOneButton;
import com.aree.restaurant.app.views.LinearLayoutWrapContent;

import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryAdminFragment extends Fragment  {

    FragmentHistoryAdminBinding binding;
    GetHistoryResponseModel responseModel;
    GetHistoryRequestModel requestModel = new GetHistoryRequestModel();
    static  HistoryAdminFragment fragment;
    SaleHistoryAdapter adapter ;
    private Restful restful = new Restful();


    public static HistoryAdminFragment newInstance() {

        Bundle args = new Bundle();
        fragment = new HistoryAdminFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static HistoryAdminFragment getInstance() {
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_history_admin, container, false);
        View rootview = binding.getRoot();

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutWrapContent(getActivity(), LinearLayoutWrapContent.VERTICAL, false) );
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
//        binding.tvTotal.setText("฿ "+"0");
        binding.SwipeRefresh.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.colorOrange));
//        binding.SwipeRefresh.setRefreshing(true);
        binding.SwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                refreshView();
            }
        });
        return rootview ;
    }


    public void initView()
    {


        adapter = new SaleHistoryAdapter(getActivity() ,responseModel.getOrder() , new SaleHistoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(AllOrderForAdminModel item, int position) {

            }
        });

        binding.recyclerView.setAdapter(adapter);


    }



    public void refreshView()
    {
        Utils.showProgressDialog(getActivity(),"ดาวน์โหลดข้อมูล...");
        binding.SwipeRefresh.setRefreshing(false);
        restful.createService(getActivity() , requestModel  , callback);

    }


    // call back from restful service
    OnResponseFromServer callback = new OnResponseFromServer()
    {
        @Override
        public void onResponse() {
            oneSecBeforeDismiss();
        }

        @Override
        public void onResponse(Object object) {

            responseModel = (GetHistoryResponseModel) object;
            int size  = responseModel.getOrder().size();
            int total = 0 ;

            for(int i  = 0 ; i<size ; i++) {
//                Log.e("onResponse", "responseModel.getOrder().get(i).getFoodType(): "
//                        +responseModel.getOrder().get(i).getFoodType() );
//                if( Integer.parseInt( responseModel.getOrder().get(i).getFoodType() )!=5 )
                total = total + Integer.parseInt(responseModel.getOrder().get(i).getTotal());
            }

            binding.tvTotal.setText("");
            binding.tvTotal.setText("฿ "+ FormatUtility.currencyFormat(String.valueOf(total)));
            initView();

            oneSecBeforeDismiss();
            adapter.notifyItemRangeChanged(0,responseModel.getOrder().size());
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



}
