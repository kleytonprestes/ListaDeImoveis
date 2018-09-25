package propertylist.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PropertyAddressGeoLocation implements Parcelable {

    private String precision;
    private PropertyAddressLocation location;

    public static final Creator<PropertyAddressGeoLocation> CREATOR = new Creator<PropertyAddressGeoLocation>() {
        @Override
        public PropertyAddressGeoLocation createFromParcel(Parcel source) {
            PropertyAddressGeoLocation var = new PropertyAddressGeoLocation();
            var.precision = source.readString();
            var.location = source.readParcelable(PropertyAddressLocation.class.getClassLoader());
            return var;
        }

        @Override
        public PropertyAddressGeoLocation[] newArray(int size) {
            return new PropertyAddressGeoLocation[size];
        }
    };


    public String getPrecision() {
        return this.precision;
    }

    public void setPrecision(String precision) {
        this.precision = precision;
    }

    public PropertyAddressLocation getLocation() {
        return this.location;
    }

    public void setLocation(PropertyAddressLocation location) {
        this.location = location;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.precision);
        dest.writeParcelable(this.location, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
