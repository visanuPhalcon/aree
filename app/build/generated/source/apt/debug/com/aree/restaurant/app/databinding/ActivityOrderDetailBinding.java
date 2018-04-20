package com.aree.restaurant.app.databinding;
import com.aree.restaurant.app.R;
import com.aree.restaurant.app.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityOrderDetailBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(13);
        sIncludes.setIncludes(1, 
            new String[] {"appbar_layout"},
            new int[] {2},
            new int[] {R.layout.appbar_layout});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.imgProfile, 3);
        sViewsWithIds.put(R.id.profileSection, 4);
        sViewsWithIds.put(R.id.tvName, 5);
        sViewsWithIds.put(R.id.tvAddress, 6);
        sViewsWithIds.put(R.id.tvPhoneNo, 7);
        sViewsWithIds.put(R.id.orderList, 8);
        sViewsWithIds.put(R.id.ExchangePointForFoodList, 9);
        sViewsWithIds.put(R.id.tvShippingCost, 10);
        sViewsWithIds.put(R.id.tvTotalCost, 11);
        sViewsWithIds.put(R.id.btnOrder, 12);
    }
    // views
    @NonNull
    public final android.widget.LinearLayout ExchangePointForFoodList;
    @NonNull
    public final android.widget.Button btnOrder;
    @NonNull
    public final de.hdodenhof.circleimageview.CircleImageView imgProfile;
    @Nullable
    public final com.aree.restaurant.app.databinding.AppbarLayoutBinding included;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @NonNull
    public final android.widget.LinearLayout orderList;
    @NonNull
    public final android.widget.LinearLayout profileSection;
    @NonNull
    public final android.widget.TextView tvAddress;
    @NonNull
    public final android.widget.TextView tvName;
    @NonNull
    public final android.widget.TextView tvPhoneNo;
    @NonNull
    public final android.widget.TextView tvShippingCost;
    @NonNull
    public final android.widget.TextView tvTotalCost;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityOrderDetailBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 13, sIncludes, sViewsWithIds);
        this.ExchangePointForFoodList = (android.widget.LinearLayout) bindings[9];
        this.btnOrder = (android.widget.Button) bindings[12];
        this.imgProfile = (de.hdodenhof.circleimageview.CircleImageView) bindings[3];
        this.included = (com.aree.restaurant.app.databinding.AppbarLayoutBinding) bindings[2];
        setContainedBinding(this.included);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.orderList = (android.widget.LinearLayout) bindings[8];
        this.profileSection = (android.widget.LinearLayout) bindings[4];
        this.tvAddress = (android.widget.TextView) bindings[6];
        this.tvName = (android.widget.TextView) bindings[5];
        this.tvPhoneNo = (android.widget.TextView) bindings[7];
        this.tvShippingCost = (android.widget.TextView) bindings[10];
        this.tvTotalCost = (android.widget.TextView) bindings[11];
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        included.invalidateAll();
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        if (included.hasPendingBindings()) {
            return true;
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    public void setLifecycleOwner(@Nullable android.arch.lifecycle.LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        included.setLifecycleOwner(lifecycleOwner);
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeIncluded((com.aree.restaurant.app.databinding.AppbarLayoutBinding) object, fieldId);
        }
        return false;
    }
    private boolean onChangeIncluded(com.aree.restaurant.app.databinding.AppbarLayoutBinding Included, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
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
        executeBindingsOn(included);
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ActivityOrderDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityOrderDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityOrderDetailBinding>inflate(inflater, com.aree.restaurant.app.R.layout.activity_order_detail, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityOrderDetailBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityOrderDetailBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.aree.restaurant.app.R.layout.activity_order_detail, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityOrderDetailBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityOrderDetailBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_order_detail_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityOrderDetailBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): included
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}