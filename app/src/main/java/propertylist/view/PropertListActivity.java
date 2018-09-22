package propertylist.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;

import kleyton.com.br.testegrupozap.R;
import propertylist.contract.PropertyListContract;
import propertylist.model.Property;
import propertylist.model.PropertyListAdapter;
import propertylist.presenter.PropertyListPresenter;

public class PropertListActivity extends AppCompatActivity implements PropertyListContract.View {

    private PropertyListContract.Presenter presenter = new PropertyListPresenter();

    private RecyclerView.LayoutManager manager;
    RecyclerView recycler;
    PropertyListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.property_list_activity);

        presenter.attachView(this);

        recycler = findViewById(R.id.property_list);

        getPropertyList();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public Context getContext() {
        return this;
    }

    public void getPropertyList() {
        presenter.requestPropertyList();
    }


    @Override
    public void setAdapter(ArrayList<Property> propertyList) {
        manager = new LinearLayoutManager(this);
        adapter = new PropertyListAdapter(this, propertyList);
        recycler.setLayoutManager(manager);
        recycler.setAdapter(adapter);
    }
}
