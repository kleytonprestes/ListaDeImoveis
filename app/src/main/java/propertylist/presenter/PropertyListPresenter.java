package propertylist.presenter;

import android.content.Intent;

import java.util.ArrayList;

import api.SyncInterface;
import propertylist.contract.PropertyListContract;
import propertylist.model.Property;
import propertylist.model.PropertyRequest;

import static mainactivity.MainActivity.IS_FROM_ZAP;

public class PropertyListPresenter implements PropertyListContract.Presenter, SyncInterface {

    PropertyListContract.View view;
    PropertyRequest request;
    private ArrayList<Property> propertyList = new ArrayList<>();
    private ArrayList<Property> propertyListZap = new ArrayList<>();
    private ArrayList<Property> propertyListVivaReal = new ArrayList<>();
    private float valueMinSaleToZap = 600000;
    private float valueMinRentalToZap = 3500;
    private float valueMaxSaleVivaReal = 700000;
    private float valueMaxRentalVivaReal = 4000;
    private boolean isFromZap;

    public PropertyListPresenter() {
    }

    @Override
    public void attachView(PropertyListContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void requestPropertyList() {
        request = new PropertyRequest(this, propertyList);
        request.startSync();
    }

    @Override
    public void getExtras(Intent intent) {

        if (intent.getExtras() != null) {
            isFromZap = intent.getExtras().getBoolean(IS_FROM_ZAP);
        }
    }

    private void organizeList(boolean isFromZap) {
        if (isFromZap) {
            organizeListZap();
        } else {
            organizeListVivaReal();
        }
    }

    private void organizeListZap() {
        for (int i = 0; i < propertyList.size(); i++) {
            Property property = propertyList.get(i);
            if (property.getPricingInfos().getBusinessType().equalsIgnoreCase("sale")
                    && Float.parseFloat(property.getPricingInfos().getPrice()) >= valueMinSaleToZap) {
                propertyListZap.add(property);
            }

            if (property.getPricingInfos().getBusinessType().equalsIgnoreCase("rental")
                    && Float.parseFloat(property.getPricingInfos().getPrice()) >= valueMinRentalToZap)
                propertyListZap.add(property);
        }
        view.setAdapter(propertyListZap);

    }

    private void organizeListVivaReal() {

        for (int i = 0; i < propertyList.size(); i++) {
            Property property = propertyList.get(i);
            if (property.getPricingInfos().getBusinessType().equalsIgnoreCase("sale")
                    && Float.parseFloat(property.getPricingInfos().getPrice()) <= valueMaxSaleVivaReal) {
                propertyListVivaReal.add(property);
            }

            if (property.getPricingInfos().getBusinessType().equalsIgnoreCase("rental")
                    && Float.parseFloat(property.getPricingInfos().getPrice()) <= valueMaxRentalVivaReal)
                propertyListVivaReal.add(property);
        }
        view.setAdapter(propertyListVivaReal);
    }

    @Override
    public void onSuccessSync() {
        organizeList(isFromZap);
    }

    @Override
    public void onFailureSync() {

    }
}
