package com.aree.restaurant.app.databinding;
import com.aree.restaurant.app.R;
import com.aree.restaurant.app.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ListMenuDialogBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.HeaderSection, 1);
        sViewsWithIds.put(R.id.tvTitle, 2);
        sViewsWithIds.put(R.id.imgClose, 3);
        sViewsWithIds.put(R.id.seaFoodSection, 4);
        sViewsWithIds.put(R.id.tvMenuList, 5);
        sViewsWithIds.put(R.id.radio_group, 6);
        sViewsWithIds.put(R.id.imgFoodPhoto, 7);
        sViewsWithIds.put(R.id.line1, 8);
        sViewsWithIds.put(R.id.BeveragesSection, 9);
        sViewsWithIds.put(R.id.tvBeverageList, 10);
        sViewsWithIds.put(R.id.radio_group2, 11);
        sViewsWithIds.put(R.id.imgBeveragePhoto, 12);
        sViewsWithIds.put(R.id.line2, 13);
        sViewsWithIds.put(R.id.btnDecrease, 14);
        sViewsWithIds.put(R.id.tvAmount, 15);
        sViewsWithIds.put(R.id.btnAdd, 16);
        sViewsWithIds.put(R.id.tvTotal, 17);
        sViewsWithIds.put(R.id.btnOrder, 18);
    }
    // views
    @NonNull
    public final android.support.percent.PercentRelativeLayout BeveragesSection;
    @NonNull
    public final android.support.percent.PercentRelativeLayout HeaderSection;
    @NonNull
    public final android.widget.ImageView btnAdd;
    @NonNull
    public final android.widget.ImageView btnDecrease;
    @NonNull
    public final android.widget.Button btnOrder;
    @NonNull
    public final android.widget.ImageView imgBeveragePhoto;
    @NonNull
    public final android.widget.ImageView imgClose;
    @NonNull
    public final android.widget.ImageView imgFoodPhoto;
    @NonNull
    public final android.view.View line1;
    @NonNull
    public final android.view.View line2;
    @NonNull
    private final android.widget.ScrollView mboundView0;
    @NonNull
    public final android.widget.RadioGroup radioGroup;
    @NonNull
    public final android.widget.RadioGroup radioGroup2;
    @NonNull
    public final android.support.percent.PercentRelativeLayout seaFoodSection;
    @NonNull
    public final android.widget.TextView tvAmount;
    @NonNull
    public final android.widget.TextView tvBeverageList;
    @NonNull
    public final android.widget.TextView tvMenuList;
    @NonNull
    public final android.widget.TextView tvTitle;
    @NonNull
    public final android.widget.TextView tvTotal;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ListMenuDialogBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds);
        this.BeveragesSection = (android.support.percent.PercentRelativeLayout) bindings[9];
        this.HeaderSection = (android.support.percent.PercentRelativeLayout) bindings[1];
        this.btnAdd = (android.widget.ImageView) bindings[16];
        this.btnDecrease = (android.widget.ImageView) bindings[14];
        this.btnOrder = (android.widget.Button) bindings[18];
        this.imgBeveragePhoto = (android.widget.ImageView) bindings[12];
        this.imgClose = (android.widget.ImageView) bindings[3];
        this.imgFoodPhoto = (android.widget.ImageView) bindings[7];
        this.line1 = (android.view.View) bindings[8];
        this.line2 = (android.view.View) bindings[13];
        this.mboundView0 = (android.widget.ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.radioGroup = (android.widget.RadioGroup) bindings[6];
        this.radioGroup2 = (android.widget.RadioGroup) bindings[11];
        this.seaFoodSection = (android.support.percent.PercentRelativeLayout) bindings[4];
        this.tvAmount = (android.widget.TextView) bindings[15];
        this.tvBeverageList = (android.widget.TextView) bindings[10];
        this.tvMenuList = (android.widget.TextView) bindings[5];
        this.tvTitle = (android.widget.TextView) bindings[2];
        this.tvTotal = (android.widget.TextView) bindings[17];
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
    public static ListMenuDialogBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ListMenuDialogBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ListMenuDialogBinding>inflate(inflater, com.aree.restaurant.app.R.layout.list_menu_dialog, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ListMenuDialogBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ListMenuDialogBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.aree.restaurant.app.R.layout.list_menu_dialog, null, false), bindingComponent);
    }
    @NonNull
    public static ListMenuDialogBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ListMenuDialogBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/list_menu_dialog_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ListMenuDialogBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}