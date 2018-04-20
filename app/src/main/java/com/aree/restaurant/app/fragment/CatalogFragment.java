package com.aree.restaurant.app.fragment;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.activity.OrderActivity;
import com.aree.restaurant.app.databinding.FragmentCatalogBinding;
import com.aree.restaurant.app.utils.Utils;

public class CatalogFragment extends Fragment  {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FragmentCatalogBinding binding;


    public CatalogFragment() {
        // Required empty public constructor
    }

    public static CatalogFragment newInstance(String param1, String param2) {
        CatalogFragment fragment = new CatalogFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_catalog, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView()
    {
        binding.menuRice.setOnClickListener(menuOnClick);
        binding.menuNoodles.setOnClickListener(menuOnClick);
        binding.menuDessert.setOnClickListener(menuOnClick);
        binding.menuBeverages.setOnClickListener(menuOnClick);
        binding.menuPromotion.setOnClickListener(menuOnClick);
    }

    public View.OnClickListener menuOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {

            Intent intent = new Intent(getActivity(), OrderActivity.class);
            if( Utils.checkConnection(getActivity())==true )
            {

                switch (view.getId())
                {
                    case R.id.menuPromotion:
                        intent.putExtra("TabPosition", 0);
                        break;
                    case R.id.menuRice:
                        intent.putExtra("TabPosition", 1);
                        break;
                    case R.id.menuNoodles:
                        intent.putExtra("TabPosition", 2);
                        break;
                    case R.id.menuDessert:
                        intent.putExtra("TabPosition", 3);
                        break;
                    case R.id.menuBeverages:
                        intent.putExtra("TabPosition", 4);
                        break;
                }

                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
            else
                Utils.internetIsNotAvailableDialog(getActivity());



        }
    };
}
