package propertydetail.presenter;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.support.design.widget.FloatingActionButton;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import persistence.AppDataBase;
import kleyton.com.br.testegrupozap.R;
import propertydetail.contract.PropertyDetailContract;
import propertylist.model.Property;
import utils.Utils;

import static propertylist.view.PropertyListActivity.PROPERTY_INTENT_KEY;

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

        if (property != null) {
            String price = view.getContext().getResources().getString(R.string.price);
            view.setPrice(String.format(price,Utils.formatPrice(property)));
            view.setAddress(formatAddress());
            view.setInfos(Utils.formatInfos(property, view.getContext()));
            view.setViewPagerImages(property.getImages());
            view.setImageButton(getImageResource());
        }
    }

    @Override
    public AppDataBase initDataBase() {
        return Room.databaseBuilder(view.getContext(),
                AppDataBase.class, "property_db").build();
    }

    @Override
    public void favoriteProperty(AppDataBase appDataBase) {
        if (property.isFavorite()) {
            property.setFavorite(false);
            appDataBase.propertyDao().deleteProperty(property);
        } else {
            property.setFavorite(true);
            appDataBase.propertyDao().insertProperty(property);
        }
    }

    @Override
    public void setButtonImage(FloatingActionButton fab) {
        if (property.isFavorite()) {
            fab.setImageDrawable(view.getContext().getResources().getDrawable(R.drawable.star_blank));
        } else {
            fab.setImageDrawable(view.getContext().getResources().getDrawable(R.drawable.star_red));
        }
    }




    private Drawable getImageResource() {
        if (property.isFavorite()) {
            return view.getContext().getResources().getDrawable(R.drawable.star_red);
        } else {
            return view.getContext().getResources().getDrawable(R.drawable.star_blank);
        }
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
