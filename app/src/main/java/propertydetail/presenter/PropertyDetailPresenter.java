package propertydetail.presenter;

import android.content.Intent;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import kleyton.com.br.testegrupozap.R;
import propertydetail.contract.PropertyDetailContract;
import propertylist.model.Property;

import static propertylist.view.PropertListActivity.PROPERTY_INTENT_KEY;

public class PropertyDetailPresenter implements PropertyDetailContract.Presenter {

    PropertyDetailContract.View view;
    Property property;


    @Override
    public void attachView(PropertyDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void getInfos(Intent intent) {
        if (intent.getExtras() != null) {
            property = intent.getExtras().getParcelable(PROPERTY_INTENT_KEY);
        }



        if (property != null && property.getPricingInfos() != null) {
            String price = view.getContext().getResources().getString(R.string.price);
            view.setPrice(String.format(price,formatPrice()));
            view.setAddress(formatAddress());
            view.setInfos(formatInfos());

            view.setViewPagerImages(property.getImages());
        }

    }


    private String formatPrice() {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        String price = property.getPricingInfos().getPrice();
        float formatPrice = Float.parseFloat(price);

        return nf.format(formatPrice);
    }

    private String formatInfos() {

        Resources resources = view.getContext().getResources();
        String bathRooms = resources.getString(R.string.bathRooms);
        String badRooms = resources.getString(R.string.badRooms);
        String usableArea = resources.getString(R.string.usableArea);

        StringBuilder infos = new StringBuilder();
        infos.append(String.format(bathRooms, property.getBathrooms())).append(", ");
        infos.append(String.format(badRooms, property.getBedrooms())).append(", ");
        infos.append(String.format(usableArea, property.getUsableAreas()));


        return infos.toString();
    }

    private String formatAddress() {
        return getStreetName();
    }

    private String getStreetName(){
        Geocoder geocoder = new Geocoder(view.getContext(), Locale.getDefault());

        float latitude = property.getAddress().getGeoLocation().getLocation().getLat();
        float longitude = property.getAddress().getGeoLocation().getLocation().getLon();


        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

            if (addresses != null && addresses.size() > 0) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder();
                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("");
                }
                return strReturnedAddress.toString();
            }
            else {
                return "";
            }
        } catch (IOException e) {

            e.printStackTrace();
            return "";
        }
    }



}
