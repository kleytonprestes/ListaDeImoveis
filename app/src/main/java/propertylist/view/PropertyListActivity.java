package propertylist.view;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import api.AppDataBase;
import kleyton.com.br.testegrupozap.R;
import propertydetail.view.PropertyDetailActivity;
import propertylist.contract.PropertyListContract;
import propertylist.model.Property;
import propertylist.model.PropertyListAdapter;
import propertylist.model.PropertyListClick;
import propertylist.presenter.PropertyListPresenter;

public class PropertyListActivity extends AppCompatActivity implements PropertyListContract.View, PropertyListClick {

    public final static String PROPERTY_INTENT_KEY = "propertyIntent";

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

        presenter.attachView(PropertyListActivity.this);

        appDataBase = presenter.initDataBase();

        initViews();

        getIntentExtras();

        getPropertyList();

    }

    private void initViews() {
        recycler = findViewById(R.id.property_list);

        progressBar = findViewById(R.id.progress_property);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public Context getContext() {
        return PropertyListActivity.this;
    }

    private void getPropertyList() {
        progressBar.setVisibility(View.VISIBLE);
        presenter.requestPropertyList();
    }

    private void getIntentExtras() {
        presenter.getExtras(getIntent());
    }


    @Override
    public void setAdapter(ArrayList<Property> propertyList) {
        manager = new LinearLayoutManager(PropertyListActivity.this);
        adapter = new PropertyListAdapter(PropertyListActivity.this, propertyList,
                PropertyListActivity.this);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapter);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemClickListener(Property property) {
        Intent intent = new Intent(PropertyListActivity.this, PropertyDetailActivity.class);
        intent.putExtra(PROPERTY_INTENT_KEY, property);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_favorite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(PropertyListActivity.this, PropertyListFavorites.class);
        startActivity(intent);

        return true;
    }

    @Override
    public void onFavoriteClickListener(final Property property) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                presenter.setFavoriteProperty(appDataBase, property);

            }
        }).start();

        adapter.notifyDataSetChanged();
    }
}
