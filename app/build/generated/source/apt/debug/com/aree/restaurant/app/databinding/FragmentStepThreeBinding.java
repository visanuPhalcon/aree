package com.aree.restaurant.app.databinding;
import com.aree.restaurant.app.R;
import com.aree.restaurant.app.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentStepThreeBinding extends android.databinding.ViewDataBinding  {

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
        sViewsWithIds.put(R.id.tvOrderID, 5);
        sViewsWithIds.put(R.id.ButtonSection, 6);
        sViewsWithIds.put(R.id.btnGoToMain, 7);
    }
    // views
    @NonNull
    public final android.widget.LinearLayout ButtonSection;
    @NonNull
    public final android.widget.RelativeLayout StepOneOval;
    @NonNull
    public final android.widget.RelativeLayout StepThreeOval;
    @NonNull
    public final android.widget.RelativeLayout StepTwoOval;
    @NonNull
    public final android.widget.Button btnGoToMain;
    @NonNull
    public final android.widget.FrameLayout contentContainer;
    @NonNull
    public final android.support.percent.PercentRelativeLayout stepSection;
    @NonNull
    public final android.widget.TextView tvOrderID;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentStepThreeBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds);
        this.ButtonSection = (android.widget.LinearLayout) bindings[6];
        this.StepOneOval = (android.widget.RelativeLayout) bindings[2];
        this.StepThreeOval = (android.widget.RelativeLayout) bindings[4];
        this.StepTwoOval = (android.widget.RelativeLayout) bindings[3];
        this.btnGoToMain = (android.widget.Button) bindings[7];
        this.contentContainer = (android.widget.FrameLayout) bindings[0];
        this.contentContainer.setTag(null);
        this.stepSection = (android.support.percent.PercentRelativeLayout) bindings[1];
        this.tvOrderID = (android.widget.TextView) bindings[5];
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
    public static FragmentStepThreeBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentStepThreeBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentStepThreeBinding>inflate(inflater, com.aree.restaurant.app.R.layout.fragment_step_three, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentStepThreeBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentStepThreeBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.aree.restaurant.app.R.layout.fragment_step_three, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentStepThreeBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentStepThreeBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_step_three_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentStepThreeBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}