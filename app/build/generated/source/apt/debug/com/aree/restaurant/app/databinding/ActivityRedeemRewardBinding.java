package com.aree.restaurant.app.databinding;
import com.aree.restaurant.app.R;
import com.aree.restaurant.app.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityRedeemRewardBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(9);
        sIncludes.setIncludes(1, 
            new String[] {"appbar_layout"},
            new int[] {2},
            new int[] {R.layout.appbar_layout});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.basketView, 3);
        sViewsWithIds.put(R.id.myPoint, 4);
        sViewsWithIds.put(R.id.tvPoints, 5);
        sViewsWithIds.put(R.id.DescriptionSection, 6);
        sViewsWithIds.put(R.id.recyclerView, 7);
        sViewsWithIds.put(R.id.viewStubRewardList, 8);
    }
    // views
    @NonNull
    public final android.widget.TextView DescriptionSection;
    @NonNull
    public final com.aree.restaurant.app.views.BasketView basketView;
    @NonNull
    public final android.widget.RelativeLayout headerSection;
    @Nullable
    public final com.aree.restaurant.app.databinding.AppbarLayoutBinding included;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    public final android.widget.LinearLayout myPoint;
    @NonNull
    public final android.support.v7.widget.RecyclerView recyclerView;
    @NonNull
    public final android.widget.TextView tvPoints;
    @NonNull
    public final android.databinding.ViewStubProxy viewStubRewardList;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityRedeemRewardBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 9, sIncludes, sViewsWithIds);
        this.DescriptionSection = (android.widget.TextView) bindings[6];
        this.basketView = (com.aree.restaurant.app.views.BasketView) bindings[3];
        this.headerSection = (android.widget.RelativeLayout) bindings[1];
        this.headerSection.setTag(null);
        this.included = (com.aree.restaurant.app.databinding.AppbarLayoutBinding) bindings[2];
        setContainedBinding(this.included);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.myPoint = (android.widget.LinearLayout) bindings[4];
        this.recyclerView = (android.support.v7.widget.RecyclerView) bindings[7];
        this.tvPoints = (android.widget.TextView) bindings[5];
        this.viewStubRewardList = new android.databinding.ViewStubProxy((android.view.ViewStub) bindings[8]);
        this.viewStubRewardList.setContainingBinding(this);
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
        if (viewStubRewardList.getBinding() != null) {
            executeBindingsOn(viewStubRewardList.getBinding());
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;

    @NonNull
    public static ActivityRedeemRewardBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityRedeemRewardBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityRedeemRewardBinding>inflate(inflater, com.aree.restaurant.app.R.layout.activity_redeem_reward, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityRedeemRewardBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityRedeemRewardBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.aree.restaurant.app.R.layout.activity_redeem_reward, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityRedeemRewardBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityRedeemRewardBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_redeem_reward_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityRedeemRewardBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): included
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}