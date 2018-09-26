package propertylist.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;


import java.util.ArrayList;

import kleyton.com.br.testegrupozap.R;
import propertydetail.view.PropertyDetailActivity;
import propertylist.contract.PropertyListContract;
import propertylist.model.Property;
import propertylist.model.PropertyListAdapter;
import propertylist.model.PropertyListClick;
import propertylist.presenter.PropertyListPresenter;
import utils.Utils;

public class PropertListActivity extends AppCompatActivity implements PropertyListContract.View, PropertyListClick {

    public final static String PROPERTY_INTENT_KEY = "propertyIntent";

    private PropertyListContract.Presenter presenter = new PropertyListPresenter();

    RecyclerView.LayoutManager manager;
    RecyclerView recycler;
    PropertyListAdapter adapter;
    ProgressBar progressBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_list_activity);

        presenter.attachView(PropertListActivity.this);

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
        return PropertListActivity.this;
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
        manager = new LinearLayoutManager(PropertListActivity.this);
        adapter = new PropertyListAdapter(PropertListActivity.this, propertyList,
                PropertListActivity.this);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapter);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemClickListener(Property property) {
        Intent intent = new Intent(PropertListActivity.this, PropertyDetailActivity.class);
        intent.putExtra(PROPERTY_INTENT_KEY, property);
        startActivity(intent);

    }
}
