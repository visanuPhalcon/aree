package com.aree.restaurant.app.views;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * Created by Admin on 24/7/2560.
 */

public class LinearLayoutWrapContent extends LinearLayoutManager {
    public LinearLayoutWrapContent(Context context) {
        super(context);
    }

    public LinearLayoutWrapContent(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public LinearLayoutWrapContent(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean supportsPredictiveItemAnimations() {
        return true;
    }
}
