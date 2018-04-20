package com.aree.restaurant.app.databinding;
import com.aree.restaurant.app.R;
import com.aree.restaurant.app.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityHistoryForCustomerBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(5);
        sIncludes.setIncludes(1, 
            new String[] {"appbar_layout"},
            new int[] {2},
            new int[] {R.layout.appbar_layout});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.etSearchID, 3);
        sViewsWithIds.put(R.id.recyclerView, 4);
    }
    // views
    @NonNull
    public final android.support.v4.widget.SwipeRefreshLayout SwipeRefresh;
    @NonNull
    public final android.widget.EditText etSearchID;
    @Nullable
    public final com.aree.restaurant.app.databinding.AppbarLayoutBinding included;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @NonNull
    public final android.support.v7.widget.RecyclerView recyclerView;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityHistoryForCustomerBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds);
        this.SwipeRefresh = (android.support.v4.widget.SwipeRefreshLayout) bindings[0];
        this.SwipeRefresh.setTag(null);
        this.etSearchID = (android.widget.EditText) bindings[3];
        this.included = (com.aree.restaurant.app.databinding.AppbarLayoutBinding) bindings[2];
        setContainedBinding(this.included);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.recyclerView = (android.support.v7.widget.RecyclerView) bindings[4];
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
    public static ActivityHistoryForCustomerBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityHistoryForCustomerBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityHistoryForCustomerBinding>inflate(inflater, com.aree.restaurant.app.R.layout.activity_history_for_customer, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityHistoryForCustomerBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityHistoryForCustomerBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.aree.restaurant.app.R.layout.activity_history_for_customer, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityHistoryForCustomerBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityHistoryForCustomerBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_history_for_customer_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityHistoryForCustomerBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): included
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}