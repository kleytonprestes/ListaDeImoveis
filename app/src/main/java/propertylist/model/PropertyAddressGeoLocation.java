package propertylist.model;

public class PropertyAddressGeoLocation implements java.io.Serializable {

    private String precision;
    private PropertyAddressLocation location;

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
}
