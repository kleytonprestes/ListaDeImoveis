package propertydetail.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import persistence.AppDataBase;
import kleyton.com.br.testegrupozap.R;
import propertydetail.contract.PropertyDetailContract;
import propertydetail.presenter.PropertyDetailPresenter;


public class PropertyDetailActivity extends AppCompatActivity implements PropertyDetailContract.View,
        View.OnClickListener {

    PropertyDetailContract.Presenter presenter = new PropertyDetailPresenter();

    AppDataBase appDataBase;

    CustomPagerAdapter customPagerAdapter;
    ViewPager viewPager;
    TextView priceTV;
    TextView infosTV;
    TextView addressTV;
    FloatingActionButton fab;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_details_activity);

        presenter.attachView(PropertyDetailActivity.this);

        appDataBase = presenter.initDataBase();

        initViews();
        configToolbar();

        getInfos();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    void initViews() {

        toolbar = findViewById(R.id.toolbar);
        priceTV = findViewById(R.id.property_price);
        infosTV = findViewById(R.id.property_infos);
        addressTV = findViewById(R.id.property_address);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    private void configToolbar() {

        toolbar.setTitle("Detalhes");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void setViewPagerImages(ArrayList<String> images) {
        customPagerAdapter = new CustomPagerAdapter(PropertyDetailActivity.this, images);

        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(customPagerAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
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

    @Override
    public void setImageButton(Drawable imageResource) {
        fab.setImageDrawable(imageResource);
    }

    void getInfos() {
        presenter.getInfos(getIntent());
    }

    @Override
    public Context getContext() {
        return PropertyDetailActivity.this;
    }

    @Override
    public void onClick(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                presenter.favoriteProperty(appDataBase);
            }
        }).start();

        presenter.setButtonImage(fab);

    }
}
