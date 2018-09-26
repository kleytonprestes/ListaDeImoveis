package propertylist.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import persistence.AppDataBase;
import kleyton.com.br.testegrupozap.R;
import persistence.PropertyDaoAsync;
import propertydetail.view.PropertyDetailActivity;
import propertylist.contract.PropertyListContract;
import propertylist.model.Property;
import propertylist.model.PropertyAsyncTask;
import propertylist.model.PropertyListAdapter;
import propertylist.model.PropertyListClick;
import propertylist.presenter.PropertyListPresenter;

import static propertylist.view.PropertyListActivity.PROPERTY_INTENT_KEY;

public class PropertyListFavorites extends AppCompatActivity implements PropertyListContract.View, PropertyListClick,
        PropertyDaoAsync {

    private PropertyListContract.Presenter presenter = new PropertyListPresenter();

    RecyclerView.LayoutManager manager;
    RecyclerView recycler;
    PropertyListAdapter adapter;
    ProgressBar progressBar;
    AppDataBase appDataBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_list_activity);

        presenter.attachView(PropertyListFavorites.this);

        appDataBase = presenter.initDataBase();

        initViews();
        configToolbar();

    }

    @Override
    protected void onResume() {
        super.onResume();
        new PropertyAsyncTask(appDataBase, this).execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void initViews() {
        recycler = findViewById(R.id.property_list);

        progressBar = findViewById(R.id.progress_property);
    }

    @Override
    public void setAdapter(ArrayList<Property> propertyList) {
        manager = new LinearLayoutManager(PropertyListFavorites.this);
        adapter = new PropertyListAdapter(PropertyListFavorites.this, propertyList,
                PropertyListFavorites.this);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapter);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onItemClickListener(Property property) {
        Intent intent = new Intent(PropertyListFavorites.this, PropertyDetailActivity.class);
        intent.putExtra(PROPERTY_INTENT_KEY, property);
        startActivity(intent);
    }

    @Override
    public void onFavoriteClickListener(final Property property) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                if (property.isFavorite()) {
                    property.setFavorite(false);
                    appDataBase.propertyDao().deleteProperty(property);
                } else {
                    property.setFavorite(true);
                    appDataBase.propertyDao().insertProperty(property);
                }

                new PropertyAsyncTask(appDataBase, PropertyListFavorites.this).execute();
            }

        }).start();
    }


    @Override
    public void getList(ArrayList<Property> propertyArrayList) {
        presenter.setPropertyList(propertyArrayList);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private void configToolbar() {

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
