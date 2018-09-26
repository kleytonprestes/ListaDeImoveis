package propertylist.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Room;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import api.AppDataBase;
import persistence.PropertyDaoAsync;

public class PropertyAsyncTask extends AsyncTask<Void, Void, ArrayList<Property>> {

    AppDataBase appDataBase;
    PropertyDaoAsync propertyDaoAsync;

    public PropertyAsyncTask(AppDataBase appDataBase, PropertyDaoAsync propertyDaoAsync) {
        this.appDataBase = appDataBase;
        this.propertyDaoAsync = propertyDaoAsync;
    }

    @Override
    protected ArrayList<Property> doInBackground(Void... voids) {

        return (ArrayList<Property>) appDataBase.propertyDao().getAllProperty();
    }

    @Override
    protected void onPostExecute(ArrayList<Property> propertyArrayList) {
        super.onPostExecute(propertyArrayList);
        propertyDaoAsync.getList(propertyArrayList);

    }
}
