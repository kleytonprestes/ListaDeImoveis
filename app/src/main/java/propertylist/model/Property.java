package propertylist.model;

public class Property implements java.io.Serializable {

    private boolean owner;
    private String[] images;
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

    public boolean getOwner() {
        return this.owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public String[] getImages() {
        return this.images;
    }

    public void setImages(String[] images) {
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
}
