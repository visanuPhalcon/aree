package com.aree.restaurant.app.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.databinding.CellMenuBinding;
import com.aree.restaurant.app.model.MenuModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


public class CellMenuViewHolder extends RecyclerView.ViewHolder implements GlideModule {

    public CellMenuBinding binding;
    private GlideDrawableImageViewTarget imageViewTarget;

    public CellMenuViewHolder(View itemView) {
        super(itemView);

        binding = DataBindingUtil.bind(itemView);


    }

    public void bind(final MenuModel model, Context context,
                     final MenuAdapter.OnItemClickListener listener,
                     final int position )
    {
        binding.tvPrice.setText(model.getPrice()+".");
        binding.tvNameMenu.setText(model.getMenuName());

//        if (imageViewTarget==null)
//            imageViewTarget = new GlideDrawableImageViewTarget(binding.imgFoodMenu);
//        Glide.with(context).load("https://thomas.vanhoutte.be/miniblog/wp-content/uploads/spinningwheel.gif")
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imageViewTarget);


        Glide.with(context).load(model.getPhoto()).diskCacheStrategy(DiskCacheStrategy.RESULT).crossFade().into(binding.imgFoodMenu);
        binding.imgFoodMenu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                listener.onItemClick(model,position);

            }
        });

    }

    @Override
    public void applyOptions(Context context, GlideBuilder glideBuilder) {
        glideBuilder.setDecodeFormat(DecodeFormat.ALWAYS_ARGB_8888);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

//        OkHttpClient.Builder httpClient;
//        httpClient = new OkHttpClient.Builder();
//        httpClient.connectTimeout(120, TimeUnit.SECONDS);
//        httpClient.readTimeout(60, TimeUnit.SECONDS);
//        httpClient.writeTimeout(60, TimeUnit.SECONDS);
//
//
//
//        OkHttpClient client = new OkHttpClient();
//        client.setConnectTimeout(15, TimeUnit.SECONDS);
//        client.setReadTimeout(15, TimeUnit.SECONDS);
//        glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(httpClient));

    }
}
