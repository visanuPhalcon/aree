package com.aree.restaurant.app.databinding;
import com.aree.restaurant.app.R;
import com.aree.restaurant.app.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentStepTwoBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.stepSection, 1);
        sViewsWithIds.put(R.id.StepOneOval, 2);
        sViewsWithIds.put(R.id.StepTwoOval, 3);
        sViewsWithIds.put(R.id.StepThreeOval, 4);
        sViewsWithIds.put(R.id.ButtonSection, 5);
        sViewsWithIds.put(R.id.btnConfirmOrder, 6);
        sViewsWithIds.put(R.id.sectionTotal, 7);
        sViewsWithIds.put(R.id.tvTotal, 8);
        sViewsWithIds.put(R.id.orderList, 9);
        sViewsWithIds.put(R.id.ExchangePointForFoodList, 10);
        sViewsWithIds.put(R.id.tvShippingCost, 11);
        sViewsWithIds.put(R.id.tvAddress, 12);
        sViewsWithIds.put(R.id.tvDate, 13);
    }
    // views
    @NonNull
    public final android.widget.LinearLayout ButtonSection;
    @NonNull
    public final android.widget.LinearLayout ExchangePointForFoodList;
    @NonNull
    public final android.widget.RelativeLayout StepOneOval;
    @NonNull
    public final android.widget.RelativeLayout StepThreeOval;
    @NonNull
    public final android.widget.RelativeLayout StepTwoOval;
    @NonNull
    public final android.widget.Button btnConfirmOrder;
    @NonNull
    public final android.widget.FrameLayout contentContainer;
    @NonNull
    public final android.widget.LinearLayout orderList;
    @NonNull
    public final android.widget.LinearLayout sectionTotal;
    @NonNull
    public final android.support.percent.PercentRelativeLayout stepSection;
    @NonNull
    public final android.widget.TextView tvAddress;
    @NonNull
    public final android.widget.TextView tvDate;
    @NonNull
    public final android.widget.TextView tvShippingCost;
    @NonNull
    public final android.widget.TextView tvTotal;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentStepTwoBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds);
        this.ButtonSection = (android.widget.LinearLayout) bindings[5];
        this.ExchangePointForFoodList = (android.widget.LinearLayout) bindings[10];
        this.StepOneOval = (android.widget.RelativeLayout) bindings[2];
        this.StepThreeOval = (android.widget.RelativeLayout) bindings[4];
        this.StepTwoOval = (android.widget.RelativeLayout) bindings[3];
        this.btnConfirmOrder = (android.widget.Button) bindings[6];
        this.contentContainer = (android.widget.FrameLayout) bindings[0];
        this.contentContainer.setTag(null);
        this.orderList = (android.widget.LinearLayout) bindings[9];
        this.sectionTotal = (android.widget.LinearLayout) bindings[7];
        this.stepSection = (android.support.percent.PercentRelativeLayout) bindings[1];
        this.tvAddress = (android.widget.TextView) bindings[12];
        this.tvDate = (android.widget.TextView) bindings[13];
        this.tvShippingCost = (android.widget.TextView) bindings[11];
        this.tvTotal = (android.widget.TextView) bindings[8];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static FragmentStepTwoBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentStepTwoBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentStepTwoBinding>inflate(inflater, com.aree.restaurant.app.R.layout.fragment_step_two, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentStepTwoBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentStepTwoBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.aree.restaurant.app.R.layout.fragment_step_two, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentStepTwoBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentStepTwoBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_step_two_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentStepTwoBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}