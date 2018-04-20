package com.aree.restaurant.app.databinding;
import com.aree.restaurant.app.R;
import com.aree.restaurant.app.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityMainBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(23);
        sIncludes.setIncludes(0, 
            new String[] {"appbar_layout"},
            new int[] {1},
            new int[] {R.layout.appbar_layout});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.basketSection, 2);
        sViewsWithIds.put(R.id.imgAdmin, 3);
        sViewsWithIds.put(R.id.loginFrame, 4);
        sViewsWithIds.put(R.id.imgUsername, 5);
        sViewsWithIds.put(R.id.etUsername, 6);
        sViewsWithIds.put(R.id.imgPassword, 7);
        sViewsWithIds.put(R.id.etPassword, 8);
        sViewsWithIds.put(R.id.btnLogin, 9);
        sViewsWithIds.put(R.id.tvforgetPassword, 10);
        sViewsWithIds.put(R.id.tvRegister, 11);
        sViewsWithIds.put(R.id.profileFrame, 12);
        sViewsWithIds.put(R.id.imgProfile, 13);
        sViewsWithIds.put(R.id.imgEditProfile, 14);
        sViewsWithIds.put(R.id.tvName, 15);
        sViewsWithIds.put(R.id.tvUsername, 16);
        sViewsWithIds.put(R.id.tvPhoneNo, 17);
        sViewsWithIds.put(R.id.tvCompanyName, 18);
        sViewsWithIds.put(R.id.tvFloorNo, 19);
        sViewsWithIds.put(R.id.PointSection, 20);
        sViewsWithIds.put(R.id.tvPoints, 21);
        sViewsWithIds.put(R.id.contentContainer, 22);
    }
    // views
    @NonNull
    public final android.widget.LinearLayout PointSection;
    @NonNull
    public final android.widget.RelativeLayout activityMain;
    @NonNull
    public final android.widget.RelativeLayout basketSection;
    @NonNull
    public final android.widget.Button btnLogin;
    @NonNull
    public final android.widget.FrameLayout contentContainer;
    @NonNull
    public final android.widget.EditText etPassword;
    @NonNull
    public final android.widget.AutoCompleteTextView etUsername;
    @NonNull
    public final android.widget.ImageView imgAdmin;
    @NonNull
    public final android.widget.ImageView imgEditProfile;
    @NonNull
    public final android.support.v7.widget.AppCompatImageView imgPassword;
    @NonNull
    public final de.hdodenhof.circleimageview.CircleImageView imgProfile;
    @NonNull
    public final android.support.v7.widget.AppCompatImageView imgUsername;
    @Nullable
    public final com.aree.restaurant.app.databinding.AppbarLayoutBinding included;
    @NonNull
    public final android.widget.FrameLayout loginFrame;
    @NonNull
    public final android.widget.FrameLayout profileFrame;
    @NonNull
    public final android.widget.TextView tvCompanyName;
    @NonNull
    public final android.widget.TextView tvFloorNo;
    @NonNull
    public final android.widget.TextView tvName;
    @NonNull
    public final android.widget.TextView tvPhoneNo;
    @NonNull
    public final android.widget.TextView tvPoints;
    @NonNull
    public final android.widget.TextView tvRegister;
    @NonNull
    public final android.widget.TextView tvUsername;
    @NonNull
    public final android.widget.TextView tvforgetPassword;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityMainBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds);
        this.PointSection = (android.widget.LinearLayout) bindings[20];
        this.activityMain = (android.widget.RelativeLayout) bindings[0];
        this.activityMain.setTag(null);
        this.basketSection = (android.widget.RelativeLayout) bindings[2];
        this.btnLogin = (android.widget.Button) bindings[9];
        this.contentContainer = (android.widget.FrameLayout) bindings[22];
        this.etPassword = (android.widget.EditText) bindings[8];
        this.etUsername = (android.widget.AutoCompleteTextView) bindings[6];
        this.imgAdmin = (android.widget.ImageView) bindings[3];
        this.imgEditProfile = (android.widget.ImageView) bindings[14];
        this.imgPassword = (android.support.v7.widget.AppCompatImageView) bindings[7];
        this.imgProfile = (de.hdodenhof.circleimageview.CircleImageView) bindings[13];
        this.imgUsername = (android.support.v7.widget.AppCompatImageView) bindings[5];
        this.included = (com.aree.restaurant.app.databinding.AppbarLayoutBinding) bindings[1];
        setContainedBinding(this.included);
        this.loginFrame = (android.widget.FrameLayout) bindings[4];
        this.profileFrame = (android.widget.FrameLayout) bindings[12];
        this.tvCompanyName = (android.widget.TextView) bindings[18];
        this.tvFloorNo = (android.widget.TextView) bindings[19];
        this.tvName = (android.widget.TextView) bindings[15];
        this.tvPhoneNo = (android.widget.TextView) bindings[17];
        this.tvPoints = (android.widget.TextView) bindings[21];
        this.tvRegister = (android.widget.TextView) bindings[11];
        this.tvUsername = (android.widget.TextView) bindings[16];
        this.tvforgetPassword = (android.widget.TextView) bindings[10];
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
    public static ActivityMainBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityMainBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityMainBinding>inflate(inflater, com.aree.restaurant.app.R.layout.activity_main, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityMainBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityMainBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.aree.restaurant.app.R.layout.activity_main, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityMainBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityMainBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_main_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityMainBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): included
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}