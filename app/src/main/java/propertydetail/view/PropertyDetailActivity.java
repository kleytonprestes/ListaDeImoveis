package propertydetail.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

import kleyton.com.br.testegrupozap.R;
import propertydetail.contract.PropertyDetailContract;
import propertydetail.presenter.PropertyDetailPresenter;


public class PropertyDetailActivity extends AppCompatActivity implements PropertyDetailContract.View {

    CustomPagerAdapter customPagerAdapter;
    ViewPager viewPager;
    PropertyDetailContract.Presenter presenter = new PropertyDetailPresenter();

    TextView priceTV;
    TextView infosTV;
    TextView addressTV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_details_activity);

        presenter.attachView(PropertyDetailActivity.this);

        initViews();
        getInfos();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    void initViews() {

        priceTV = findViewById(R.id.property_price);
        infosTV = findViewById(R.id.property_infos);
        addressTV = findViewById(R.id.property_address);
    }

    @Override
    public void setViewPagerImages(ArrayList<String> images) {
        customPagerAdapter = new CustomPagerAdapter(PropertyDetailActivity.this, images);

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(customPagerAdapter);
    }

    @Override
    public void setInfos(String textInfos) {
    infosTV.setText(textInfos);
    }

    @Override
    public void setAddress(String textAddress) {
        addressTV.setText(textAddress);
    }

    @Override
    public void setPrice(String textPrice) {
        priceTV.setText(textPrice);
    }

    void getInfos() {
        presenter.getInfos(getIntent());
    }

    @Override
    public Context getContext() {
        return PropertyDetailActivity.this;
    }
}
