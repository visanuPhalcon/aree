package com.aree.restaurant.app.config;

import android.app.Application;

import com.aree.restaurant.app.R;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Nanthakorn on 2/23/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/SukhumvitSet-Thin.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
