package persistence;

import java.util.ArrayList;

import propertylist.model.Property;

public interface PropertyDaoAsync {

    void getList(ArrayList<Property> propertyArrayList);

}
