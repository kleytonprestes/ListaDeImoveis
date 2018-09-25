package propertylist.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PropertyPricingInfos implements Parcelable {

    private String yearlyIptu;
    private String price;
    private String businessType;
    private String monthlyCondoFee;

    public static final Creator<PropertyPricingInfos> CREATOR = new Creator<PropertyPricingInfos>() {
        @Override
        public PropertyPricingInfos createFromParcel(Parcel source) {
            PropertyPricingInfos var = new PropertyPricingInfos();
            var.yearlyIptu = source.readString();
            var.price = source.readString();
            var.businessType = source.readString();
            var.monthlyCondoFee = source.readString();
            return var;
        }

        @Override
        public PropertyPricingInfos[] newArray(int size) {
            return new PropertyPricingInfos[size];
        }
    };


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

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.yearlyIptu);
        dest.writeString(this.price);
        dest.writeString(this.businessType);
        dest.writeString(this.monthlyCondoFee);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}
