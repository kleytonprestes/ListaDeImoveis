package propertydetail.contract;

import android.content.Intent;

import java.util.ArrayList;

import basecontract.BaseContract;

public class PropertyDetailContract {

    public interface View extends BaseContract.View {

        void setViewPagerImages(ArrayList<String> images);

        void setInfos(String textInfos);

        void setAddress(String textAddress);

        void setPrice(String textPrice);
    }

    public interface Presenter extends BaseContract.Presenter<View> {

        void getInfos(Intent intent);

    }

}
