package com.aree.restaurant.app.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;

import com.aree.restaurant.app.R;
import com.aree.restaurant.app.databinding.ActivityCropImageBinding;
import com.isseiaoki.simplecropview.CropImageView;
import com.isseiaoki.simplecropview.callback.CropCallback;
import com.isseiaoki.simplecropview.callback.LoadCallback;
import com.isseiaoki.simplecropview.callback.SaveCallback;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class CropImageActivity extends BaseActivity {

    ActivityCropImageBinding binding;
    private final static String TAG = "dam";
    private Uri sourceUri, croppedUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_crop_image);
        initView();
    }
    private void initView(){
        sourceUri = Uri.parse(getIntent().getExtras().getString("imageUri"));
        binding.cropImageView.setCompressFormat(Bitmap.CompressFormat.JPEG);
        binding.cropImageView.setCompressQuality(80);
        binding.cropImageView.setOutputMaxSize(150,150);
        binding.cropImageView.setCropMode(CropImageView.CropMode.CIRCLE);
        binding.cropImageView.setOverlayColor(0xAA1C1C1C);
        binding.cropImageView.startLoad(sourceUri, new LoadCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        });

        binding.imgCrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCrop();
            }
        });
    }

    private void startCrop(){
        binding.cropImageView.startCrop(sourceUri, new CropCallback() {
            @Override
            public void onSuccess(Bitmap cropped) {
                Log.i(TAG, "onSuccess: cropped");
                try {
                    croppedUri = getImageUri(cropped);
                    if (croppedUri != null){
                        Intent intent = new Intent();
                        intent.setData(croppedUri);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError() {
                Log.i(TAG, "onError: ");
            }
        }, new SaveCallback() {
            @Override
            public void onSuccess(Uri outputUri) {

            }

            @Override
            public void onError() {
                Log.i(TAG, "onError: ");
            }
        });
    }

    private Uri getImageUri(Bitmap bitmap) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "Profile_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File imageFile = File.createTempFile(
                imageFileName,  /* prefix */
                ".png",         /* suffix */
                storageDir      /* directory */
        );
        OutputStream os = new BufferedOutputStream(new FileOutputStream(imageFile));
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
        os.close();
        // Save a file: path for use with ACTION_VIEW intents
        return Uri.fromFile(imageFile);
    }
}
