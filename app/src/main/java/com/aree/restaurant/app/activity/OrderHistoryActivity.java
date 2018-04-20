package com.aree.restaurant.app.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.adapter.OrderHistoryForUserAdapter;
import com.aree.restaurant.app.adapter.admin.AllOrderAdapter;
import com.aree.restaurant.app.databinding.ActivityOrderHistoryBinding;
import com.aree.restaurant.app.databinding.FragmentAllOrderBinding;
import com.aree.restaurant.app.model.RequsetModel.GetHistoryForUserRequestModel;
import com.aree.restaurant.app.model.ResponseModel.GetHistoryForUserResponseModel;
import com.aree.restaurant.app.model.ResponseModel.admin.GetHistoryResponseModel;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.aree.restaurant.app.network.OnResponseFromServer;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.views.DialogMessageOneButton;
import com.aree.restaurant.app.views.LinearLayoutWrapContent;

import okhttp3.ResponseBody;

public class OrderHistoryActivity extends BaseActivity {

    ActivityOrderHistoryBinding binding;
    private OrderHistoryForUserAdapter adapter;
    private GetHistoryForUserRequestModel requestModel = new GetHistoryForUserRequestModel();
    private GetHistoryForUserResponseModel responseModel ;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin);
        new Restful<>().createService(OrderHistoryActivity.this , requestModel  , callback);

    }

    public void initView()
    {
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutWrapContent(OrderHistoryActivity.this, LinearLayoutWrapContent.VERTICAL, false) );
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(OrderHistoryActivity.this, DividerItemDecoration.VERTICAL));

        adapter = new OrderHistoryForUserAdapter(OrderHistoryActivity.this, new OrderHistoryForUserAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(AllOrderForAdminModel item, int position)
            {

            }

        });

        binding.recyclerView.setAdapter(adapter);

    }


    // call back from restful service
    OnResponseFromServer callback = new OnResponseFromServer()
    {
        @Override
        public void onResponse()
        {

        }

        @Override
        public void onResponse(Object object)
        {
            responseModel = (GetHistoryForUserResponseModel) object;
            initView();
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


}
