package propertylist.model;

public class PropertyPricingInfos implements java.io.Serializable {

    private String yearlyIptu;
    private String price;
    private String businessType;
    private String monthlyCondoFee;

    public String getYearlyIptu() {
        return this.yearlyIptu;
    }

    public void setYearlyIptu(String yearlyIptu) {
        this.yearlyIptu = yearlyIptu;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBusinessType() {
        return this.businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getMonthlyCondoFee() {
        return this.monthlyCondoFee;
    }

    public void setMonthlyCondoFee(String monthlyCondoFee) {
        this.monthlyCondoFee = monthlyCondoFee;
    }
}
