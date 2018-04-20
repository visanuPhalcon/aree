package com.aree.restaurant.app.databinding;
import com.aree.restaurant.app.R;
import com.aree.restaurant.app.BR;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityRegisterBinding extends android.databinding.ViewDataBinding  {

    @Nullable
    private static final android.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = new android.databinding.ViewDataBinding.IncludedLayouts(28);
        sIncludes.setIncludes(1, 
            new String[] {"appbar_layout"},
            new int[] {2},
            new int[] {R.layout.appbar_layout});
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.RegisterSection, 3);
        sViewsWithIds.put(R.id.tvFirstName, 4);
        sViewsWithIds.put(R.id.tvLastName, 5);
        sViewsWithIds.put(R.id.etFirstName, 6);
        sViewsWithIds.put(R.id.etLastName, 7);
        sViewsWithIds.put(R.id.tvCompanyName, 8);
        sViewsWithIds.put(R.id.tvFloorNo, 9);
        sViewsWithIds.put(R.id.etCompanyName, 10);
        sViewsWithIds.put(R.id.etFloorNo, 11);
        sViewsWithIds.put(R.id.tvPhoneNo, 12);
        sViewsWithIds.put(R.id.tvPhoneNoCompany, 13);
        sViewsWithIds.put(R.id.etPhoneNo, 14);
        sViewsWithIds.put(R.id.etPhoneNoCompany, 15);
        sViewsWithIds.put(R.id.tvBirthDay, 16);
        sViewsWithIds.put(R.id.etBirthDay, 17);
        sViewsWithIds.put(R.id.tvEmail, 18);
        sViewsWithIds.put(R.id.etEmail, 19);
        sViewsWithIds.put(R.id.tvUsername, 20);
        sViewsWithIds.put(R.id.etUsername, 21);
        sViewsWithIds.put(R.id.tvPassword, 22);
        sViewsWithIds.put(R.id.etPassword, 23);
        sViewsWithIds.put(R.id.tvPasswordRepeat, 24);
        sViewsWithIds.put(R.id.etPasswordRepeat, 25);
        sViewsWithIds.put(R.id.tvInformation, 26);
        sViewsWithIds.put(R.id.btnRegister, 27);
    }
    // views
    @NonNull
    public final android.widget.ScrollView RegisterSection;
    @NonNull
    public final android.widget.Button btnRegister;
    @NonNull
    public final android.widget.EditText etBirthDay;
    @NonNull
    public final android.widget.EditText etCompanyName;
    @NonNull
    public final android.widget.EditText etEmail;
    @NonNull
    public final android.widget.EditText etFirstName;
    @NonNull
    public final android.widget.EditText etFloorNo;
    @NonNull
    public final android.widget.EditText etLastName;
    @NonNull
    public final android.widget.EditText etPassword;
    @NonNull
    public final android.widget.EditText etPasswordRepeat;
    @NonNull
    public final android.widget.EditText etPhoneNo;
    @NonNull
    public final android.widget.EditText etPhoneNoCompany;
    @NonNull
    public final android.widget.EditText etUsername;
    @Nullable
    public final com.aree.restaurant.app.databinding.AppbarLayoutBinding included;
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    private final android.widget.LinearLayout mboundView1;
    @NonNull
    public final android.widget.TextView tvBirthDay;
    @NonNull
    public final android.widget.TextView tvCompanyName;
    @NonNull
    public final android.widget.TextView tvEmail;
    @NonNull
    public final android.widget.TextView tvFirstName;
    @NonNull
    public final android.widget.TextView tvFloorNo;
    @NonNull
    public final android.widget.TextView tvInformation;
    @NonNull
    public final android.widget.TextView tvLastName;
    @NonNull
    public final android.widget.TextView tvPassword;
    @NonNull
    public final android.widget.TextView tvPasswordRepeat;
    @NonNull
    public final android.widget.TextView tvPhoneNo;
    @NonNull
    public final android.widget.TextView tvPhoneNoCompany;
    @NonNull
    public final android.widget.TextView tvUsername;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityRegisterBinding(@NonNull android.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        super(bindingComponent, root, 1);
        final Object[] bindings = mapBindings(bindingComponent, root, 28, sIncludes, sViewsWithIds);
        this.RegisterSection = (android.widget.ScrollView) bindings[3];
        this.btnRegister = (android.widget.Button) bindings[27];
        this.etBirthDay = (android.widget.EditText) bindings[17];
        this.etCompanyName = (android.widget.EditText) bindings[10];
        this.etEmail = (android.widget.EditText) bindings[19];
        this.etFirstName = (android.widget.EditText) bindings[6];
        this.etFloorNo = (android.widget.EditText) bindings[11];
        this.etLastName = (android.widget.EditText) bindings[7];
        this.etPassword = (android.widget.EditText) bindings[23];
        this.etPasswordRepeat = (android.widget.EditText) bindings[25];
        this.etPhoneNo = (android.widget.EditText) bindings[14];
        this.etPhoneNoCompany = (android.widget.EditText) bindings[15];
        this.etUsername = (android.widget.EditText) bindings[21];
        this.included = (com.aree.restaurant.app.databinding.AppbarLayoutBinding) bindings[2];
        setContainedBinding(this.included);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView1 = (android.widget.LinearLayout) bindings[1];
        this.mboundView1.setTag(null);
        this.tvBirthDay = (android.widget.TextView) bindings[16];
        this.tvCompanyName = (android.widget.TextView) bindings[8];
        this.tvEmail = (android.widget.TextView) bindings[18];
        this.tvFirstName = (android.widget.TextView) bindings[4];
        this.tvFloorNo = (android.widget.TextView) bindings[9];
        this.tvInformation = (android.widget.TextView) bindings[26];
        this.tvLastName = (android.widget.TextView) bindings[5];
        this.tvPassword = (android.widget.TextView) bindings[22];
        this.tvPasswordRepeat = (android.widget.TextView) bindings[24];
        this.tvPhoneNo = (android.widget.TextView) bindings[12];
        this.tvPhoneNoCompany = (android.widget.TextView) bindings[13];
        this.tvUsername = (android.widget.TextView) bindings[20];
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
    public static ActivityRegisterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot) {
        return inflate(inflater, root, attachToRoot, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityRegisterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.view.ViewGroup root, boolean attachToRoot, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return android.databinding.DataBindingUtil.<ActivityRegisterBinding>inflate(inflater, com.aree.restaurant.app.R.layout.activity_register, root, attachToRoot, bindingComponent);
    }
    @NonNull
    public static ActivityRegisterBinding inflate(@NonNull android.view.LayoutInflater inflater) {
        return inflate(inflater, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityRegisterBinding inflate(@NonNull android.view.LayoutInflater inflater, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        return bind(inflater.inflate(com.aree.restaurant.app.R.layout.activity_register, null, false), bindingComponent);
    }
    @NonNull
    public static ActivityRegisterBinding bind(@NonNull android.view.View view) {
        return bind(view, android.databinding.DataBindingUtil.getDefaultComponent());
    }
    @NonNull
    public static ActivityRegisterBinding bind(@NonNull android.view.View view, @Nullable android.databinding.DataBindingComponent bindingComponent) {
        if (!"layout/activity_register_0".equals(view.getTag())) {
            throw new RuntimeException("view tag isn't correct on view:" + view.getTag());
        }
        return new ActivityRegisterBinding(bindingComponent, view);
    }
    /* flag mapping
        flag 0 (0x1L): included
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}