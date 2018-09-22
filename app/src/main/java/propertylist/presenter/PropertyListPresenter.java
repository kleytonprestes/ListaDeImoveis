package propertylist.presenter;

import java.util.ArrayList;

import api.SyncInterface;
import propertylist.contract.PropertyListContract;
import propertylist.model.Property;
import propertylist.model.PropertyRequest;

public class PropertyListPresenter implements PropertyListContract.Presenter, SyncInterface {

    PropertyListContract.View view;
    PropertyRequest request;
    private ArrayList<Property> propertyList = new ArrayList<>();

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
    public void onSuccessSync() {
        view.setAdapter(propertyList);
    }

    @Override
    public void onFailureSync() {

    }
}
