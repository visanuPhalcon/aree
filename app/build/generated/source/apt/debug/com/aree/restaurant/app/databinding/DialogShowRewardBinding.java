package com.aree.restaurant.app.databinding;
import com.aree.restaurant.app.R;
import com.aree.restaurant.app.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class DialogShowRewardBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.tvMenu, 1);
        sViewsWithIds.put(R.id.tvTotalMenu, 2);
        sViewsWithIds.put(R.id.imgArrow, 3);
        sViewsWithIds.put(R.id.orderList, 4);
        sViewsWithIds.put(R.id.recyclerView, 5);
        sViewsWithIds.put(R.id.PointSection, 6);
        sViewsWithIds.put(R.id.tvTotalPoint, 7);
        sViewsWithIds.put(R.id.totalSection, 8);
        sViewsWithIds.put(R.id.tvTotalCost, 9);
        sViewsWithIds.put(R.id.btnConfirmOrder, 10);
    }
    // views
    @NonNull
    public final android.widget.RelativeLayout PointSection;
    @NonNull
    public final android.widget.Button btnConfirmOrder;
    @NonNull
    public final android.support.v7.widget.AppCompatImageView imgArrow;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    public final android.widget.LinearLayout orderList;
    @NonNull
    public final android.support.v7.widget.RecyclerView recyclerView;
    @NonNull
    public final android.widget.RelativeLayout totalSection;
    @NonNull
    public final android.widget.TextView tvMenu;
    @NonNull
    public final android.widget.TextView tvTotalCost;
    @NonNull
    public final android.widget.TextView tvTotalMenu;
    @NonNull
    public final android.widget.TextView tvTotalPoint;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public DialogShowRewardBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 11, sIncludes, sViewsWithIds);
        this.PointSection = (android.widget.RelativeLayout) bindings[6];
        this.btnConfirmOrder = (android.widget.Button) bindings[10];
        this.imgArrow = (android.support.v7.widget.AppCompatImageView) bindings[3];
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.orderList = (android.widget.LinearLayout) bindings[4];
        this.recyclerView = (android.support.v7.widget.RecyclerView) bindings[5];
        this.totalSection = (android.widget.RelativeLayout) bindings[8];
        this.tvMenu = (android.widget.TextView) bindings[1];
        this.tvTotalCost = (android.widget.TextView) bindings[9];
        this.tvTotalMenu = (android.widget.TextView) bindings[2];
        this.tvTotalPoint = (android.widget.TextView) bindings[7];
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
    public static DialogShowRewardBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DialogShowRewardBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<DialogShowRewardBinding>inflate(inflater, com.aree.restaurant.app.R.layout.dialog_show_reward, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static DialogShowRewardBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DialogShowRewardBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.aree.restaurant.app.R.layout.dialog_show_reward, null, false), bindingComponent);
    }
    @NonNull
    public static DialogShowRewardBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static DialogShowRewardBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/dialog_show_reward_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new DialogShowRewardBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}