package com.aree.restaurant.app.databinding;
import com.aree.restaurant.app.R;
import com.aree.restaurant.app.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityOrderBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(17);
        sIncludes.setIncludes(1, 
            new String[] {"appbar_layout"},
            new int[] {2},
            new int[] {R.layout.appbar_layout});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.viewPagerTab, 3);
        sViewsWithIds.put(R.id.basketView, 4);
        sViewsWithIds.put(R.id.sliding_layout, 5);
        sViewsWithIds.put(R.id.viewPager, 6);
        sViewsWithIds.put(R.id.tvMenu, 7);
        sViewsWithIds.put(R.id.tvTotalMenu, 8);
        sViewsWithIds.put(R.id.imgArrow, 9);
        sViewsWithIds.put(R.id.orderList, 10);
        sViewsWithIds.put(R.id.recyclerView, 11);
        sViewsWithIds.put(R.id.PointSection, 12);
        sViewsWithIds.put(R.id.tvTotalPoint, 13);
        sViewsWithIds.put(R.id.totalSection, 14);
        sViewsWithIds.put(R.id.tvTotalCost, 15);
        sViewsWithIds.put(R.id.btnConfirmOrder, 16);
    }
    // views
    @NonNull
    public final android.widget.RelativeLayout PointSection;
    @NonNull
    public final android.widget.RelativeLayout activityOrder;
    @NonNull
    public final com.aree.restaurant.app.views.BasketView basketView;
    @NonNull
    public final android.widget.Button btnConfirmOrder;
    @NonNull
    public final android.support.v7.widget.AppCompatImageView imgArrow;
    @Nullable
    public final com.aree.restaurant.app.databinding.AppbarLayoutBinding included;
    @NonNull
    public final android.widget.LinearLayout orderList;
    @NonNull
    public final android.support.v7.widget.RecyclerView recyclerView;
    @NonNull
    public final com.sothree.slidinguppanel.SlidingUpPanelLayout slidingLayout;
    @NonNull
    public final android.widget.LinearLayout tab;
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
    @NonNull
    public final android.support.v4.view.ViewPager viewPager;
    @NonNull
    public final com.ogaclejapan.smarttablayout.SmartTabLayout viewPagerTab;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityOrderBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 17, sIncludes, sViewsWithIds);
        this.PointSection = (android.widget.RelativeLayout) bindings[12];
        this.activityOrder = (android.widget.RelativeLayout) bindings[0];
        this.activityOrder.setTag(null);
        this.basketView = (com.aree.restaurant.app.views.BasketView) bindings[4];
        this.btnConfirmOrder = (android.widget.Button) bindings[16];
        this.imgArrow = (android.support.v7.widget.AppCompatImageView) bindings[9];
        this.included = (com.aree.restaurant.app.databinding.AppbarLayoutBinding) bindings[2];
        setContainedBinding(this.included);
        this.orderList = (android.widget.LinearLayout) bindings[10];
        this.recyclerView = (android.support.v7.widget.RecyclerView) bindings[11];
        this.slidingLayout = (com.sothree.slidinguppanel.SlidingUpPanelLayout) bindings[5];
        this.tab = (android.widget.LinearLayout) bindings[1];
        this.tab.setTag(null);
        this.totalSection = (android.widget.RelativeLayout) bindings[14];
        this.tvMenu = (android.widget.TextView) bindings[7];
        this.tvTotalCost = (android.widget.TextView) bindings[15];
        this.tvTotalMenu = (android.widget.TextView) bindings[8];
        this.tvTotalPoint = (android.widget.TextView) bindings[13];
        this.viewPager = (android.support.v4.view.ViewPager) bindings[6];
        this.viewPagerTab = (com.ogaclejapan.smarttablayout.SmartTabLayout) bindings[3];
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
    public static ActivityOrderBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityOrderBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityOrderBinding>inflate(inflater, com.aree.restaurant.app.R.layout.activity_order, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityOrderBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityOrderBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.aree.restaurant.app.R.layout.activity_order, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityOrderBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityOrderBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_order_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityOrderBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): included
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}