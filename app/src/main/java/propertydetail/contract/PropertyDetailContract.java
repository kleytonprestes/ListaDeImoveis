package propertydetail.contract;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;

import java.util.ArrayList;

import api.AppDataBase;
import basecontract.BaseContract;

public class PropertyDetailContract {

    public interface View extends BaseContract.View {

        void setViewPagerImages(ArrayList<String> images);

        void setInfos(String textInfos);

        void setAddress(String textAddress);

        void setPrice(String textPrice);

        void setImageButton(Drawable imageResource);
    }

    public interface Presenter extends BaseContract.Presenter<View> {

        void getInfos(Intent intent);

        AppDataBase initDataBase();

        void favoriteProperty(AppDataBase appDataBase);

        void setButtonImage(FloatingActionButton fab);
    }

}
