package propertylist.model;

public class PropertyAddress implements java.io.Serializable {

    private String city;
    private PropertyAddressGeoLocation geoLocation;
    private String neighborhood;

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
}
