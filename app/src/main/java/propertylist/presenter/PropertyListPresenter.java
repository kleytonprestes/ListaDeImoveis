package propertylist.presenter;

import android.arch.persistence.room.Room;
import android.content.Intent;

import java.util.ArrayList;

import api.SyncInterface;
import persistence.AppDataBase;
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
    private ArrayList<Property> propertyListSale = new ArrayList<>();
    private ArrayList<Property> propertyListRent = new ArrayList<>();

    private float valueMinSaleToZap = 600000;
    private float valueMinRentalToZap = 3500;
    private float valueMaxSaleVivaReal = 700000;
    private float valueMaxRentalVivaReal = 4000;
    private boolean isFromZap;
    boolean isRent;
    boolean isSale;

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

    @Override
    public void setPropertyList(ArrayList<Property> propertyListFavorites) {
        view.setAdapter(propertyListFavorites);
    }

    @Override
    public AppDataBase initDataBase() {
          return Room.databaseBuilder(view.getContext(),
                AppDataBase.class, "property_db").build();
    }

    @Override
    public void setFavoriteProperty(AppDataBase appDataBase, Property property) {
        if (property.isFavorite()) {
            property.setFavorite(false);
            appDataBase.propertyDao().deleteProperty(property);
        } else {
            property.setFavorite(true);
            appDataBase.propertyDao().insertProperty(property);
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

        propertyList.clear();
        propertyList.addAll(propertyListZap);

        view.setAdapter(getFirstTwenty(propertyList));

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

        propertyList.clear();
        propertyList.addAll(propertyListVivaReal);
        view.setAdapter(getFirstTwenty(propertyList));
    }

    @Override
    public void listRentAndSale(boolean isRent, boolean isSale) {
        ArrayList<Property> propertyListRentAndSale = new ArrayList<>();
        getAllVivaRealToRentAndToSale();
        this.isRent = isRent;
        this.isSale = isSale;

        if (isRent && !isSale) {
            propertyListRentAndSale.addAll(propertyListRent);
        } else if (isSale && !isRent) {
            propertyListRentAndSale.addAll(propertyListSale);
        } else {
            propertyListRentAndSale.addAll(propertyList);
        }

        view.setAdapter(getFirstTwenty(propertyListRentAndSale));
    }

    private ArrayList<Property> getFirstTwenty(ArrayList<Property> propertyList) {
        ArrayList<Property> propertyArrayList = new ArrayList<>();
        if (propertyList.size() > 19){
            for (int i = 0; i < 19; i++) {
                propertyArrayList.add(propertyList.get(i));
            }
        }

        return propertyArrayList;
    }

    @Override
    public void addItensOnList(ArrayList<Property> propertyListItensPage) {
        int pageSize = propertyListItensPage.size();
        ArrayList<Property> propertyArrayList = new ArrayList<>();

        if (isRent && !isSale) {
            propertyArrayList.addAll(propertyListRent);
        } else if (isSale && !isRent) {
            propertyArrayList.addAll(propertyListSale);
        } else {
            propertyArrayList.addAll(propertyList);
        }

        for (int i = pageSize; i < pageSize + 20; i++) {
            if (i < propertyArrayList.size()) {
                propertyListItensPage.add(propertyArrayList.get(i));
            }
        }
    }

    private void getAllVivaRealToRentAndToSale() {


        for (int i = 0; i < propertyList.size(); i++) {
            if ("sale".equalsIgnoreCase(propertyList.get(i).getPricingInfos().getBusinessType())) {

                propertyListSale.add(propertyList.get(i));

            } else {
                propertyListRent.add(propertyList.get(i));

            }
        }
    }

    @Override
    public void onSuccessSync() {
        organizeList(isFromZap);
    }

    @Override
    public void onFailureSync() {

    }
}
