package propertylist.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PropertyAddressLocation implements Parcelable {

    private float lon;
    private float lat;

    public static final Creator<PropertyAddressLocation> CREATOR = new Creator<PropertyAddressLocation>() {
        @Override
        public PropertyAddressLocation createFromParcel(Parcel source) {
            PropertyAddressLocation var = new PropertyAddressLocation();
            var.lon = source.readFloat();
            var.lat = source.readFloat();
            return var;
        }

        @Override
        public PropertyAddressLocation[] newArray(int size) {
            return new PropertyAddressLocation[size];
        }
    };


    public float getLon() {
        return this.lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return this.lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.lon);
        dest.writeFloat(this.lat);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
