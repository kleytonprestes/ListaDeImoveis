package propertylist.contract;

import android.content.Intent;

import java.util.ArrayList;

import basecontract.BaseContract;
import propertylist.model.Property;

public class PropertyListContract {
    
    public interface View extends BaseContract.View {
        void setAdapter(ArrayList<Property> propertyList);
    }
    
    public interface Presenter extends BaseContract.Presenter<View> {

        void requestPropertyList();

        void getExtras(Intent intent);
    }
}
