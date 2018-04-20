
package com.aree.restaurant.app.model.Singleton;

import android.os.Parcelable;
import android.os.Parcelable.Creator;
import org.parceler.Generated;
import org.parceler.IdentityCollection;
import org.parceler.ParcelWrapper;
import org.parceler.ParcelerRuntimeException;

@Generated(value = "org.parceler.ParcelAnnotationProcessor", date = "2018-04-15T22:42+0700")
@SuppressWarnings({
    "unchecked",
    "deprecation"
})
public class AllOrderForAdminModel$$Parcelable
    implements Parcelable, ParcelWrapper<com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel>
{

    private com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel allOrderForAdminModel$$0;
    @SuppressWarnings("UnusedDeclaration")
    public final static Creator<AllOrderForAdminModel$$Parcelable>CREATOR = new Creator<AllOrderForAdminModel$$Parcelable>() {


        @Override
        public AllOrderForAdminModel$$Parcelable createFromParcel(android.os.Parcel parcel$$2) {
            return new AllOrderForAdminModel$$Parcelable(read(parcel$$2, new IdentityCollection()));
        }

        @Override
        public AllOrderForAdminModel$$Parcelable[] newArray(int size) {
            return new AllOrderForAdminModel$$Parcelable[size] ;
        }

    }
    ;

    public AllOrderForAdminModel$$Parcelable(com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel allOrderForAdminModel$$2) {
        allOrderForAdminModel$$0 = allOrderForAdminModel$$2;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel$$0, int flags) {
        write(allOrderForAdminModel$$0, parcel$$0, flags, new IdentityCollection());
    }

    public static void write(com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel allOrderForAdminModel$$1, android.os.Parcel parcel$$1, int flags$$0, IdentityCollection identityMap$$0) {
        int identity$$0 = identityMap$$0 .getKey(allOrderForAdminModel$$1);
        if (identity$$0 != -1) {
            parcel$$1 .writeInt(identity$$0);
        } else {
            parcel$$1 .writeInt(identityMap$$0 .put(allOrderForAdminModel$$1));
            parcel$$1 .writeString(allOrderForAdminModel$$1 .pointExchangeFoodId);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .date);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .foodType);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .foodId);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .companyName);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .photo);
            parcel$$1 .writeInt(allOrderForAdminModel$$1 .oid);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .pointExchangeFoodAmount);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .lastname);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .foodPrice);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .foodName);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .total);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .phoneNumber);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .foodAmount);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .name);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .floorNumber);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .pointExchangeFoodPoint);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .time);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .companyPhoneNumber);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .order_id);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .pointExchangeFoodName);
            parcel$$1 .writeString(allOrderForAdminModel$$1 .status);
        }
    }

    @Override
    public int describeContents() {
        return  0;
    }

    @Override
    public com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel getParcel() {
        return allOrderForAdminModel$$0;
    }

    public static com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel read(android.os.Parcel parcel$$3, IdentityCollection identityMap$$1) {
        int identity$$1 = parcel$$3 .readInt();
        if (identityMap$$1 .containsKey(identity$$1)) {
            if (identityMap$$1 .isReserved(identity$$1)) {
                throw new ParcelerRuntimeException("An instance loop was detected whild building Parcelable and deseralization cannot continue.  This error is most likely due to using @ParcelConstructor or @ParcelFactory.");
            }
            return identityMap$$1 .get(identity$$1);
        } else {
            com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel allOrderForAdminModel$$4;
            int reservation$$0 = identityMap$$1 .reserve();
            allOrderForAdminModel$$4 = new com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel();
            identityMap$$1 .put(reservation$$0, allOrderForAdminModel$$4);
            allOrderForAdminModel$$4 .pointExchangeFoodId = parcel$$3 .readString();
            allOrderForAdminModel$$4 .date = parcel$$3 .readString();
            allOrderForAdminModel$$4 .foodType = parcel$$3 .readString();
            allOrderForAdminModel$$4 .foodId = parcel$$3 .readString();
            allOrderForAdminModel$$4 .companyName = parcel$$3 .readString();
            allOrderForAdminModel$$4 .photo = parcel$$3 .readString();
            allOrderForAdminModel$$4 .oid = parcel$$3 .readInt();
            allOrderForAdminModel$$4 .pointExchangeFoodAmount = parcel$$3 .readString();
            allOrderForAdminModel$$4 .lastname = parcel$$3 .readString();
            allOrderForAdminModel$$4 .foodPrice = parcel$$3 .readString();
            allOrderForAdminModel$$4 .foodName = parcel$$3 .readString();
            allOrderForAdminModel$$4 .total = parcel$$3 .readString();
            allOrderForAdminModel$$4 .phoneNumber = parcel$$3 .readString();
            allOrderForAdminModel$$4 .foodAmount = parcel$$3 .readString();
            allOrderForAdminModel$$4 .name = parcel$$3 .readString();
            allOrderForAdminModel$$4 .floorNumber = parcel$$3 .readString();
            allOrderForAdminModel$$4 .pointExchangeFoodPoint = parcel$$3 .readString();
            allOrderForAdminModel$$4 .time = parcel$$3 .readString();
            allOrderForAdminModel$$4 .companyPhoneNumber = parcel$$3 .readString();
            allOrderForAdminModel$$4 .order_id = parcel$$3 .readString();
            allOrderForAdminModel$$4 .pointExchangeFoodName = parcel$$3 .readString();
            allOrderForAdminModel$$4 .status = parcel$$3 .readString();
            com.aree.restaurant.app.model.Singleton.AllOrderForAdminModel allOrderForAdminModel$$3 = allOrderForAdminModel$$4;
            identityMap$$1 .put(identity$$1, allOrderForAdminModel$$3);
            return allOrderForAdminModel$$3;
        }
    }

}
