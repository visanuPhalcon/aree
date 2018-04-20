package com.aree.restaurant.app.fragment.Admin;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.activity.AdminActivity;
import com.aree.restaurant.app.activity.OrderActivity;
import com.aree.restaurant.app.activity.OrderDetailActivity;
import com.aree.restaurant.app.adapter.admin.AllOrderAdapter;
import com.aree.restaurant.app.adapter.admin.OnStartDragListener;
import com.aree.restaurant.app.adapter.admin.SimpleItemTouchHelperCallback;
import com.aree.restaurant.app.databinding.FragmentAllOrderBinding;
import com.aree.restaurant.app.fragment.FragmentLifeCycle;
import com.aree.restaurant.app.model.RequsetModel.admin.DeleteOrderRequestModel;
import com.aree.restaurant.app.model.RequsetModel.admin.GetAllOrderRequestModel;
import com.aree.restaurant.app.model.RequsetModel.admin.UpdateStatusRequestModel;
import com.aree.restaurant.app.model.ResponseModel.admin.GetAllOrderResponseModel;
import com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel;
import com.aree.restaurant.app.model.Singleton.ShoppingCart;
import com.aree.restaurant.app.model.Singleton.Singleton;
import com.aree.restaurant.app.model.Singleton.UserInformation;
import com.aree.restaurant.app.network.OnResponseFromServer;
import com.aree.restaurant.app.network.Restful;
import com.aree.restaurant.app.utils.Utils;
import com.aree.restaurant.app.views.DialogMessageOneButton;
import com.aree.restaurant.app.views.DialogMessageTwoButton;
import com.aree.restaurant.app.views.FoodMenuDialog;
import com.aree.restaurant.app.views.LinearLayoutWrapContent;
import com.loopeer.itemtouchhelperextension.ItemTouchHelperExtension;

import org.parceler.Parcels;

import java.util.ArrayList;

import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllOrderFragment extends Fragment  {


    FragmentAllOrderBinding binding;
    GetAllOrderRequestModel requestModel = new GetAllOrderRequestModel();
    private AllOrderAdapter adapter;
    int pos ;
    static AllOrderFragment fragment;
    private Restful restful = new Restful();
    Drawable drawable;

    public static AllOrderFragment newInstance() {
        Bundle args = new Bundle();
        fragment = new AllOrderFragment();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onResume()
    {
        super.onResume();
        restful.createService(getActivity() , requestModel  , callback);
//        Log.e("AllOrderFragment", "onResume: " );

    }

    public static AllOrderFragment getInstance() {
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_all_order, container, false);
        View rootview = binding.getRoot();

        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutWrapContent(getActivity(), LinearLayoutWrapContent.VERTICAL, false) );
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        binding.SwipeRefresh.setColorSchemeColors(ContextCompat.getColor(getActivity(), R.color.colorOrange));

        binding.SwipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh()
            {
                binding.SwipeRefresh.setRefreshing(false);
                refreshView();
            }
        });

//        refreshView();

        return rootview ;
    }

    public void initView()
    {

            if(Singleton.getInstance().getAllOrder()!=null)
                adapter = new AllOrderAdapter(getActivity(), new AllOrderAdapter.OnItemClickListener()
                {

                    @Override
                    public void onItemClick(AllOrderForAdminModel item, int position) {
                        Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                        intent.putExtra("model", Parcels.wrap(item));
                        startActivity(intent);
                    }

                    @Override
                    public void onUpdateStatus(final AllOrderForAdminModel item, final int position) {
                        final DialogMessageTwoButton dialog = new DialogMessageTwoButton(getActivity());

                        if (item.getStatus().equals("0"))
                            dialog.showDialog(getString(R.string.label_admin_confirm_order), getString(R.string.label_admin_confirm_order)
                                    + "\nหมายเลขสั่งซื้อ : #" + item.getOrder_id());
                        else if (item.getStatus().equals("1"))
                            dialog.showDialog(getString(R.string.label_admin_confirm_delivery), getString(R.string.label_admin_confirm_delivery)
                                    + "\nหมายเลขสั่งซื้อ : #" + item.getOrder_id());
                        else if (item.getStatus().equals("2"))
                            dialog.showDialog(getString(R.string.label_admin_confirm_done_delivery), getString(R.string.label_admin_confirm_done_delivery)
                                    + "\nหมายเลขสั่งซื้อ : #" + item.getOrder_id());

                        dialog.setOnConfirmListener(new DialogMessageTwoButton.OnDialogClickListener() {
                            @Override
                            public void onClickConfirm() {
//                                binding.SwipeRefresh.setRefreshing(true);
                                UpdateStatusRequestModel requestModel = new UpdateStatusRequestModel(item.getOid(), Integer.parseInt(item.getStatus()));
                                restful.createService(getActivity(), requestModel, callback2);
                                pos = position;

                            }

                            @Override
                            public void onCancel() {

                            }
                        });


                    }

                    @Override
                    public void onItemLongClick(final AllOrderForAdminModel item, final int position)
                    {
                        Log.e("AllOrderFragment", "onItemLongClick: " );
                        final DialogMessageTwoButton dialog = new DialogMessageTwoButton(getActivity());

                        dialog.showDialog(getString(R.string.label_delete_row_order_admin)
                                , "คุณต้องการลบรายการ"
                                + "\nหมายเลขสั่งซื้อ : #" + item.getOrder_id());

                        dialog.setOnConfirmListener(new DialogMessageTwoButton.OnDialogClickListener()
                        {
                            @Override
                            public void onClickConfirm()
                            {

                                Utils.showProgressDialog(getContext(),"ดาวน์โหลดข้อมูล....");
                                DeleteOrderRequestModel requestModel = new DeleteOrderRequestModel( item.getOid(),Integer.parseInt(item.getStatus()) );
                                restful.createService(getActivity(), requestModel, deleteOrderCallBack);

                                pos = position;
                            }

                            @Override
                            public void onCancel() {
                                Log.e("AllOrderFragment", "onCancel: " );
                            }
                        });

                    }


                });



        drawable = ContextCompat.getDrawable(getActivity(),R.drawable.ic_search_black_24dp);
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, ContextCompat.getColor(getActivity(),R.color.colorGreen));
        binding.etSearchID.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        adapter.setHasStableIds(true);
        binding.recyclerView.setAdapter(adapter);
        binding.etSearchID.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                adapter.filter( s.toString()  );
            }
        });





    }


    public void refreshView()
    {

//            Log.e("AllOrderFragment", "refreshView: ");
            Utils.showProgressDialog(getActivity(), "ดาวน์โหลดข้อมูล...");
            restful.createService(getActivity(), requestModel, callback);

    }





    // call back from restful service
    OnResponseFromServer callback = new OnResponseFromServer()
    {
        @Override
        public void onResponse()
        {
            if(binding.recyclerView.getAdapter()==null)
            {
                initView();
                adapter.notifyDataSetChanged();
            }
            else
            {
                adapter.addItem();
                adapter.notifyDataSetChanged();

            }

//            ((AdminActivity) getActivity()).setFirstTimeRequest(true);
            oneSecBeforeDismiss();
        }

        @Override
        public void onResponse(Object object) {
            oneSecBeforeDismiss();
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



    // call back from updating status
    OnResponseFromServer callback2 = new OnResponseFromServer()
    {
        @Override
        public void onResponse()
        {
            int status = Integer.parseInt(  Singleton.getInstance().getAllOrder().getOrder().get(pos).getStatus() );
            status = status+1;
            Singleton.getInstance().getAllOrder().getOrder().get(pos).setStatus(String.valueOf( status ) );
            adapter.notifyItemChanged(pos);


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



    // call back from deleting order
    OnResponseFromServer deleteOrderCallBack = new OnResponseFromServer()
    {
        @Override
        public void onResponse()
        {
            adapter.notifyItemRemoved(pos);
            adapter.removeItem( pos);
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
