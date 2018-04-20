package com.aree.restaurant.app.databinding;
import com.aree.restaurant.app.R;
import com.aree.restaurant.app.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class RewardDialogBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.titleSection, 1);
        sViewsWithIds.put(R.id.tvNameMenu, 2);
        sViewsWithIds.put(R.id.imgClose, 3);
        sViewsWithIds.put(R.id.imgRewardPhoto, 4);
        sViewsWithIds.put(R.id.amountSection, 5);
        sViewsWithIds.put(R.id.imgAdd, 6);
        sViewsWithIds.put(R.id.etAmount, 7);
        sViewsWithIds.put(R.id.imgRemove, 8);
        sViewsWithIds.put(R.id.btnOrder, 9);
    }
    // views
    @NonNull
    public final android.widget.RelativeLayout amountSection;
    @NonNull
    public final android.widget.Button btnOrder;
    @NonNull
    public final android.widget.TextView etAmount;
    @NonNull
    public final android.widget.ImageView imgAdd;
    @NonNull
    public final android.support.v7.widget.AppCompatImageView imgClose;
    @NonNull
    public final android.widget.ImageView imgRemove;
    @NonNull
    public final android.widget.ImageView imgRewardPhoto;
    @NonNull
    private final android.support.percent.PercentRelativeLayout mboundView0;
    @NonNull
    public final android.widget.RelativeLayout titleSection;
    @NonNull
    public final android.widget.TextView tvNameMenu;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public RewardDialogBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds);
        this.amountSection = (android.widget.RelativeLayout) bindings[5];
        this.btnOrder = (android.widget.Button) bindings[9];
        this.etAmount = (android.widget.TextView) bindings[7];
        this.imgAdd = (android.widget.ImageView) bindings[6];
        this.imgClose = (android.support.v7.widget.AppCompatImageView) bindings[3];
        this.imgRemove = (android.widget.ImageView) bindings[8];
        this.imgRewardPhoto = (android.widget.ImageView) bindings[4];
        this.mboundView0 = (android.support.percent.PercentRelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.titleSection = (android.widget.RelativeLayout) bindings[1];
        this.tvNameMenu = (android.widget.TextView) bindings[2];
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
    public static RewardDialogBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static RewardDialogBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<RewardDialogBinding>inflate(inflater, com.aree.restaurant.app.R.layout.reward_dialog, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static RewardDialogBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static RewardDialogBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.aree.restaurant.app.R.layout.reward_dialog, null, false), bindingComponent);
    }
    @NonNull
    public static RewardDialogBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static RewardDialogBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/reward_dialog_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new RewardDialogBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}