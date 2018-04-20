package com.aree.restaurant.app.databinding;
import com.aree.restaurant.app.R;
import com.aree.restaurant.app.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ViewOrderItemBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.imgCancel, 1);
        sViewsWithIds.put(R.id.tvFoodName, 2);
        sViewsWithIds.put(R.id.tvPrice, 3);
        sViewsWithIds.put(R.id.imgRemove, 4);
        sViewsWithIds.put(R.id.tvQuantity, 5);
        sViewsWithIds.put(R.id.imgAdd, 6);
    }
    // views
    @NonNull
    public final android.widget.ImageView imgAdd;
    @NonNull
    public final android.widget.ImageView imgCancel;
    @NonNull
    public final android.widget.ImageView imgRemove;
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    @NonNull
    public final android.widget.TextView tvFoodName;
    @NonNull
    public final android.widget.TextView tvPrice;
    @NonNull
    public final android.widget.TextView tvQuantity;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ViewOrderItemBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 7, sIncludes, sViewsWithIds);
        this.imgAdd = (android.widget.ImageView) bindings[6];
        this.imgCancel = (android.widget.ImageView) bindings[1];
        this.imgRemove = (android.widget.ImageView) bindings[4];
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.tvFoodName = (android.widget.TextView) bindings[2];
        this.tvPrice = (android.widget.TextView) bindings[3];
        this.tvQuantity = (android.widget.TextView) bindings[5];
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
    public static ViewOrderItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ViewOrderItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ViewOrderItemBinding>inflate(inflater, com.aree.restaurant.app.R.layout.view_order_item, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ViewOrderItemBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ViewOrderItemBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.aree.restaurant.app.R.layout.view_order_item, null, false), bindingComponent);
    }
    @NonNull
    public static ViewOrderItemBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ViewOrderItemBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/view_order_item_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ViewOrderItemBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}