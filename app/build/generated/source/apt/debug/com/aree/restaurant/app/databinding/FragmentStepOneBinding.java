package com.aree.restaurant.app.databinding;
import com.aree.restaurant.app.R;
import com.aree.restaurant.app.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentStepOneBinding extends android.databinding.ViewDataBinding  {

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
        sViewsWithIds.put(R.id.tvTotal, 6);
        sViewsWithIds.put(R.id.btnPickMore, 7);
        sViewsWithIds.put(R.id.btnConfirmOrder, 8);
        sViewsWithIds.put(R.id.imgCancel, 9);
        sViewsWithIds.put(R.id.tvMenuName, 10);
        sViewsWithIds.put(R.id.imgRemove, 11);
        sViewsWithIds.put(R.id.imgAdd, 12);
        sViewsWithIds.put(R.id.imgCancel1, 13);
        sViewsWithIds.put(R.id.tvMenuName1, 14);
        sViewsWithIds.put(R.id.imgRemove2, 15);
        sViewsWithIds.put(R.id.imgAdd2, 16);
        sViewsWithIds.put(R.id.imgCancel3, 17);
        sViewsWithIds.put(R.id.tvMenuName3, 18);
        sViewsWithIds.put(R.id.imgRemove4, 19);
        sViewsWithIds.put(R.id.imgAdd4, 20);
        sViewsWithIds.put(R.id.imgCancel5, 21);
        sViewsWithIds.put(R.id.tvMenuName5, 22);
        sViewsWithIds.put(R.id.imgRemove5, 23);
        sViewsWithIds.put(R.id.textView, 24);
        sViewsWithIds.put(R.id.imgAdd5, 25);
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
    public final android.widget.Button btnConfirmOrder;
    @NonNull
    public final android.widget.Button btnPickMore;
    @NonNull
    public final android.widget.FrameLayout contentContainer;
    @NonNull
    public final android.widget.ImageView imgAdd;
    @NonNull
    public final android.widget.ImageView imgAdd2;
    @NonNull
    public final android.widget.ImageView imgAdd4;
    @NonNull
    public final android.widget.ImageView imgAdd5;
    @NonNull
    public final android.widget.ImageView imgCancel;
    @NonNull
    public final android.widget.ImageView imgCancel1;
    @NonNull
    public final android.widget.ImageView imgCancel3;
    @NonNull
    public final android.widget.ImageView imgCancel5;
    @NonNull
    public final android.widget.ImageView imgRemove;
    @NonNull
    public final android.widget.ImageView imgRemove2;
    @NonNull
    public final android.widget.ImageView imgRemove4;
    @NonNull
    public final android.widget.ImageView imgRemove5;
    @NonNull
    public final android.support.percent.PercentRelativeLayout stepSection;
    @NonNull
    public final android.widget.TextView textView;
    @NonNull
    public final android.widget.TextView tvMenuName;
    @NonNull
    public final android.widget.TextView tvMenuName1;
    @NonNull
    public final android.widget.TextView tvMenuName3;
    @NonNull
    public final android.widget.TextView tvMenuName5;
    @NonNull
    public final android.widget.TextView tvTotal;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentStepOneBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 26, sIncludes, sViewsWithIds);
        this.ButtonSection = (android.widget.LinearLayout) bindings[5];
        this.StepOneOval = (android.widget.RelativeLayout) bindings[2];
        this.StepThreeOval = (android.widget.RelativeLayout) bindings[4];
        this.StepTwoOval = (android.widget.RelativeLayout) bindings[3];
        this.btnConfirmOrder = (android.widget.Button) bindings[8];
        this.btnPickMore = (android.widget.Button) bindings[7];
        this.contentContainer = (android.widget.FrameLayout) bindings[0];
        this.contentContainer.setTag(null);
        this.imgAdd = (android.widget.ImageView) bindings[12];
        this.imgAdd2 = (android.widget.ImageView) bindings[16];
        this.imgAdd4 = (android.widget.ImageView) bindings[20];
        this.imgAdd5 = (android.widget.ImageView) bindings[25];
        this.imgCancel = (android.widget.ImageView) bindings[9];
        this.imgCancel1 = (android.widget.ImageView) bindings[13];
        this.imgCancel3 = (android.widget.ImageView) bindings[17];
        this.imgCancel5 = (android.widget.ImageView) bindings[21];
        this.imgRemove = (android.widget.ImageView) bindings[11];
        this.imgRemove2 = (android.widget.ImageView) bindings[15];
        this.imgRemove4 = (android.widget.ImageView) bindings[19];
        this.imgRemove5 = (android.widget.ImageView) bindings[23];
        this.stepSection = (android.support.percent.PercentRelativeLayout) bindings[1];
        this.textView = (android.widget.TextView) bindings[24];
        this.tvMenuName = (android.widget.TextView) bindings[10];
        this.tvMenuName1 = (android.widget.TextView) bindings[14];
        this.tvMenuName3 = (android.widget.TextView) bindings[18];
        this.tvMenuName5 = (android.widget.TextView) bindings[22];
        this.tvTotal = (android.widget.TextView) bindings[6];
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
    public static FragmentStepOneBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentStepOneBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<FragmentStepOneBinding>inflate(inflater, com.aree.restaurant.app.R.layout.fragment_step_one, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static FragmentStepOneBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentStepOneBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.aree.restaurant.app.R.layout.fragment_step_one, null, false), bindingComponent);
    }
    @NonNull
    public static FragmentStepOneBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static FragmentStepOneBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/fragment_step_one_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new FragmentStepOneBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}