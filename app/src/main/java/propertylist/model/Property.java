package propertylist.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Property implements Parcelable {


    private boolean owner;
    private ArrayList<String> images;
    private PropertyAddress address;
    private int usableAreas;
    private String listingType;
    private int bathrooms;
    private int bedrooms;
    private String createdAt;
    private PropertyPricingInfos pricingInfos;
    private String listingStatus;
    private String id;
    private int parkingSpaces;
    private String updatedAt;
    private boolean isFavorite;

    public static final Creator<Property> CREATOR = new Creator<Property>() {
        @Override
        public Property createFromParcel(Parcel source) {
            Property var = new Property();
            var.owner = source.readByte() != 0;
            var.images = source.readArrayList(null);
            var.address = source.readParcelable(PropertyAddress.class.getClassLoader());
            var.usableAreas = source.readInt();
            var.listingType = source.readString();
            var.bathrooms = source.readInt();
            var.bedrooms = source.readInt();
            var.createdAt = source.readString();
            var.pricingInfos = source.readParcelable(PropertyPricingInfos.class.getClassLoader());
            var.listingStatus = source.readString();
            var.id = source.readString();
            var.parkingSpaces = source.readInt();
            var.updatedAt = source.readString();
            var.isFavorite = source.readByte() != 0;
            return var;
        }

        @Override
        public Property[] newArray(int size) {
            return new Property[size];
        }
    };


    public boolean getOwner() {
        return this.owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public ArrayList<String> getImages() {
        return this.images;
    }

    public void setImages(ArrayList images) {
        this.images = images;
    }

    public PropertyAddress getAddress() {
        return this.address;
    }

    public void setAddress(PropertyAddress address) {
        this.address = address;
    }

    public int getUsableAreas() {
        return this.usableAreas;
    }

    public void setUsableAreas(int usableAreas) {
        this.usableAreas = usableAreas;
    }

    public String getListingType() {
        return this.listingType;
    }

    public void setListingType(String listingType) {
        this.listingType = listingType;
    }

    public int getBathrooms() {
        return this.bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getBedrooms() {
        return this.bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public PropertyPricingInfos getPricingInfos() {
        return this.pricingInfos;
    }

    public void setPricingInfos(PropertyPricingInfos pricingInfos) {
        this.pricingInfos = pricingInfos;
    }

    public String getListingStatus() {
        return this.listingStatus;
    }

    public void setListingStatus(String listingStatus) {
        this.listingStatus = listingStatus;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getParkingSpaces() {
        return this.parkingSpaces;
    }

    public void setParkingSpaces(int parkingSpaces) {
        this.parkingSpaces = parkingSpaces;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte(owner ? (byte) 1 : (byte) 0);
        dest.writeList(this.images);
        dest.writeParcelable(this.address, flags);
        dest.writeInt(this.usableAreas);
        dest.writeString(this.listingType);
        dest.writeInt(this.bathrooms);
        dest.writeInt(this.bedrooms);
        dest.writeString(this.createdAt);
        dest.writeParcelable(this.pricingInfos, flags);
        dest.writeString(this.listingStatus);
        dest.writeString(this.id);
        dest.writeInt(this.parkingSpaces);
        dest.writeString(this.updatedAt);
        dest.writeByte(isFavorite ? (byte) 1 : 0);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
