package propertylist.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PropertyAddress implements Parcelable {

    private String city;
    private PropertyAddressGeoLocation geoLocation;
    private String neighborhood;

    public static final Creator<PropertyAddress> CREATOR = new Creator<PropertyAddress>() {
        @Override
        public PropertyAddress createFromParcel(Parcel source) {
            PropertyAddress var = new PropertyAddress();
            var.city = source.readString();
            var.geoLocation = source.readParcelable(PropertyAddressGeoLocation.class.getClassLoader());
            var.neighborhood = source.readString();
            return var;
        }

        @Override
        public PropertyAddress[] newArray(int size) {
            return new PropertyAddress[size];
        }
    };


    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public PropertyAddressGeoLocation getGeoLocation() {
        return this.geoLocation;
    }

    public void setGeoLocation(PropertyAddressGeoLocation geoLocation) {
        this.geoLocation = geoLocation;
    }

    public String getNeighborhood() {
        return this.neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.city);
        dest.writeParcelable(this.geoLocation, flags);
        dest.writeString(this.neighborhood);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
