package com.aree.restaurant.app.utils;

import android.Manifest;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;


import com.aree.restaurant.app.R;
import com.aree.restaurant.app.views.DialogMessageOneButton;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class Utils {

    private final static boolean IS_ONLINE = true;
    public final static int WHITE = 0xFFFFFFFF;
    public final static int TRANSPARENT = 0x00000000;
    public final static int BLACK = 0xFF000000;
    private static final String TAG = "Kondee";
    private static AlertDialog dialog;
    private static ProgressDialog progressDialog;
    private static final int READ_PHONE_STATE_REQUEST_CODE = 11110;
    static Handler handler = new Handler();



    public static String encodeBitmapToBase64(Bitmap bitmap, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, quality, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
    }

    public static Bitmap decodeBase64ToBitmap(String input) {
        byte[] decodeByte = Base64.decode(input, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.length);
    }

    public static int dp2px(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (dp * displayMetrics.scaledDensity);
    }

    public static int dp2px(Context context, float dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (dp * displayMetrics.scaledDensity);
    }


    public static void showProgressDialog(Context context, String message)
    {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(progressDialog.STYLE_SPINNER);
        progressDialog.show();
    }


    public static void dismissDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    public static boolean isProgressDialogShowing ()
    {
        if (progressDialog != null)
        {
            if (progressDialog.isShowing())
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }



    public static Boolean isOnline() {
        return IS_ONLINE;
    }

    public static String getBalanceFormat(Object o) {
        DecimalFormatSymbols symbol = new DecimalFormatSymbols(Locale.getDefault());
        symbol.setGroupingSeparator(',');
        symbol.setDecimalSeparator('.');
        DecimalFormat format = new DecimalFormat("###,###,###,###,###", symbol);
        format.setDecimalSeparatorAlwaysShown(false);

        if (o instanceof String) {
            try {
                Number parse = format.parse(o.toString());
                o = parse;
            } catch (ParseException e) {
                return o.toString();
            }
        }

        return format.format(o);
    }

    public static String toTitleCase(String string) {
        boolean nextTitleCase = true;
        StringBuilder titleCase = new StringBuilder();

        for (char c : string.toCharArray()) {
            c = Character.toLowerCase(c);
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }

            titleCase.append(c);
        }
        return titleCase.toString();
    }

    public static String encode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static String decode(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static String getDateFormatFromServer(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }

    public static String getDateFormatFromServer(String date) {
        SimpleDateFormat serverDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        Date formattedDate;
        try {
            formattedDate = serverDateFormat.parse(date);
        } catch (ParseException e) {
            formattedDate = new Date();
        }

        return simpleDateFormat.format(formattedDate);
    }

    public static String setDateFormatForServer(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat serverDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date formattedDate;
        try {
            formattedDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            formattedDate = new Date();
        }

        return serverDateFormat.format(formattedDate);
    }


    public static String getRealPathFromURI(Uri contentURI ,Context context)
    {

        String path = contentURI.getPath();
        return path;
    }


    public static void showFailConnectionDialog(final Context context)
    {
        DialogMessageOneButton dialog = new DialogMessageOneButton( context  );
        dialog.showDialog(context.getString(R.string.label_fail_to_connect),context.getString(R.string.label_error_fail_to_connect));
        dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
            @Override
            public void onClickConfirm() {

            }
        });
    }

    public static void internetIsNotAvailableDialog(final Context context)
    {
        DialogMessageOneButton dialog = new DialogMessageOneButton( context  );
        dialog.showDialog(context.getString(R.string.label_internet_isnot_available),context.getString(R.string.label_error_internet_isnot_available));
        dialog.setOnConfirmListener(new DialogMessageOneButton.OnDialogClickListener() {
            @Override
            public void onClickConfirm() {

            }
        });
    }


    public static boolean checkConnection(Activity activity)
    {
        NetworkInfo networkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting())
        {
            return methodRequestPermission(activity);
        }
        else
        {
            return false;
        }

    }


    public static boolean checkConnection(Context context)
    {
        Activity activity = (Activity) context;
        NetworkInfo networkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnectedOrConnecting())
        {
            return methodRequestPermission(activity);
        }
        else
        {
            return false;
        }

    }


    private static boolean methodRequestPermission(Activity activity)
    {

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.READ_PHONE_STATE))
            {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, READ_PHONE_STATE_REQUEST_CODE);

                return true;
            }
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_PHONE_STATE}, READ_PHONE_STATE_REQUEST_CODE);

            return false;
        }
        else return true;


    }

    public static void dismissKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != activity.getCurrentFocus())
            imm.hideSoftInputFromWindow(activity.getCurrentFocus()
                    .getApplicationWindowToken(), 0);
    }





}
