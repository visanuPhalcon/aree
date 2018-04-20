package com.aree.restaurant.app.databinding;
import com.aree.restaurant.app.R;
import com.aree.restaurant.app.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ListMenuDialog2Binding extends android.databinding.ViewDataBinding  {

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
        sViewsWithIds.put(R.id.ArrowForFood, 8);
        sViewsWithIds.put(R.id.line1, 9);
        sViewsWithIds.put(R.id.generalFoodSection, 10);
        sViewsWithIds.put(R.id.tvGeneralMenuList, 11);
        sViewsWithIds.put(R.id.radio_group_GeneralFood, 12);
        sViewsWithIds.put(R.id.imgGeneralFoodPhoto, 13);
        sViewsWithIds.put(R.id.ArrowForGeneralFood, 14);
        sViewsWithIds.put(R.id.line3, 15);
        sViewsWithIds.put(R.id.snackSection, 16);
        sViewsWithIds.put(R.id.tvSnackMenuList, 17);
        sViewsWithIds.put(R.id.radio_group_Snack, 18);
        sViewsWithIds.put(R.id.imgSnackPhoto, 19);
        sViewsWithIds.put(R.id.ArrowForSnack, 20);
        sViewsWithIds.put(R.id.line4, 21);
        sViewsWithIds.put(R.id.BeveragesSection, 22);
        sViewsWithIds.put(R.id.tvBeverageList, 23);
        sViewsWithIds.put(R.id.radio_group2, 24);
        sViewsWithIds.put(R.id.imgBeveragePhoto, 25);
        sViewsWithIds.put(R.id.line2, 26);
        sViewsWithIds.put(R.id.btnDecrease, 27);
        sViewsWithIds.put(R.id.tvAmount, 28);
        sViewsWithIds.put(R.id.btnAdd, 29);
        sViewsWithIds.put(R.id.tvTotal, 30);
        sViewsWithIds.put(R.id.btnOrder, 31);
    }
    // views
    @NonNull
    public final android.widget.ImageView ArrowForFood;
    @NonNull
    public final android.widget.ImageView ArrowForGeneralFood;
    @NonNull
    public final android.widget.ImageView ArrowForSnack;
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
    public final android.support.percent.PercentRelativeLayout generalFoodSection;
    @NonNull
    public final android.widget.ImageView imgBeveragePhoto;
    @NonNull
    public final android.widget.ImageView imgClose;
    @NonNull
    public final android.widget.ImageView imgFoodPhoto;
    @NonNull
    public final android.widget.ImageView imgGeneralFoodPhoto;
    @NonNull
    public final android.widget.ImageView imgSnackPhoto;
    @NonNull
    public final android.view.View line1;
    @NonNull
    public final android.view.View line2;
    @NonNull
    public final android.view.View line3;
    @NonNull
    public final android.view.View line4;
    @NonNull
    private final android.widget.ScrollView mboundView0;
    @NonNull
    public final android.widget.RadioGroup radioGroup;
    @NonNull
    public final android.widget.RadioGroup radioGroup2;
    @NonNull
    public final android.widget.RadioGroup radioGroupGeneralFood;
    @NonNull
    public final android.widget.RadioGroup radioGroupSnack;
    @NonNull
    public final android.support.percent.PercentRelativeLayout seaFoodSection;
    @NonNull
    public final android.support.percent.PercentRelativeLayout snackSection;
    @NonNull
    public final android.widget.TextView tvAmount;
    @NonNull
    public final android.widget.TextView tvBeverageList;
    @NonNull
    public final android.widget.TextView tvGeneralMenuList;
    @NonNull
    public final android.widget.TextView tvMenuList;
    @NonNull
    public final android.widget.TextView tvSnackMenuList;
    @NonNull
    public final android.widget.TextView tvTitle;
    @NonNull
    public final android.widget.TextView tvTotal;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ListMenuDialog2Binding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 0);
        final Object[] bindings = mapBindings(bindingComponent, root, 32, sIncludes, sViewsWithIds);
        this.ArrowForFood = (android.widget.ImageView) bindings[8];
        this.ArrowForGeneralFood = (android.widget.ImageView) bindings[14];
        this.ArrowForSnack = (android.widget.ImageView) bindings[20];
        this.BeveragesSection = (android.support.percent.PercentRelativeLayout) bindings[22];
        this.HeaderSection = (android.support.percent.PercentRelativeLayout) bindings[1];
        this.btnAdd = (android.widget.ImageView) bindings[29];
        this.btnDecrease = (android.widget.ImageView) bindings[27];
        this.btnOrder = (android.widget.Button) bindings[31];
        this.generalFoodSection = (android.support.percent.PercentRelativeLayout) bindings[10];
        this.imgBeveragePhoto = (android.widget.ImageView) bindings[25];
        this.imgClose = (android.widget.ImageView) bindings[3];
        this.imgFoodPhoto = (android.widget.ImageView) bindings[7];
        this.imgGeneralFoodPhoto = (android.widget.ImageView) bindings[13];
        this.imgSnackPhoto = (android.widget.ImageView) bindings[19];
        this.line1 = (android.view.View) bindings[9];
        this.line2 = (android.view.View) bindings[26];
        this.line3 = (android.view.View) bindings[15];
        this.line4 = (android.view.View) bindings[21];
        this.mboundView0 = (android.widget.ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        this.radioGroup = (android.widget.RadioGroup) bindings[6];
        this.radioGroup2 = (android.widget.RadioGroup) bindings[24];
        this.radioGroupGeneralFood = (android.widget.RadioGroup) bindings[12];
        this.radioGroupSnack = (android.widget.RadioGroup) bindings[18];
        this.seaFoodSection = (android.support.percent.PercentRelativeLayout) bindings[4];
        this.snackSection = (android.support.percent.PercentRelativeLayout) bindings[16];
        this.tvAmount = (android.widget.TextView) bindings[28];
        this.tvBeverageList = (android.widget.TextView) bindings[23];
        this.tvGeneralMenuList = (android.widget.TextView) bindings[11];
        this.tvMenuList = (android.widget.TextView) bindings[5];
        this.tvSnackMenuList = (android.widget.TextView) bindings[17];
        this.tvTitle = (android.widget.TextView) bindings[2];
        this.tvTotal = (android.widget.TextView) bindings[30];
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
    public static ListMenuDialog2Binding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ListMenuDialog2Binding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ListMenuDialog2Binding>inflate(inflater, com.aree.restaurant.app.R.layout.list_menu_dialog2, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ListMenuDialog2Binding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ListMenuDialog2Binding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.aree.restaurant.app.R.layout.list_menu_dialog2, null, false), bindingComponent);
    }
    @NonNull
    public static ListMenuDialog2Binding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ListMenuDialog2Binding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/list_menu_dialog2_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ListMenuDialog2Binding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}