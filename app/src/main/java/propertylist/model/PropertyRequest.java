package propertylist.model;

import java.util.List;

import api.BaseSync;
import api.RetrofitConfig;
import api.ServiceApi;
import api.SyncInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyRequest extends BaseSync {

    private SyncInterface syncInterface;
    private List<Property> propertyList;

    public PropertyRequest(SyncInterface syncInterface, List<Property> propertyList) {
        this.syncInterface = syncInterface;
        this.propertyList = propertyList;
    }

    @Override
    public void onSuccessSync() {

        syncInterface.onSuccessSync();
    }

    @Override
    public void onFailureSync() {
        syncInterface.onFailureSync();
    }

    @Override
    public void startSync() {

        ServiceApi serviceApi = RetrofitConfig.getService();

        Call<List<Property>> propertyCall = serviceApi.getProPertyList();

        propertyCall.enqueue(new Callback<List<Property>>() {
            @Override
            public void onResponse(Call<List<Property>> call, Response<List<Property>> response) {

                if (response.body() != null) {

                    List<Property> propertyResponse = response.body();
                    if (propertyResponse != null) {
                        propertyList.addAll(propertyResponse);
                    }

                }

                onSuccessSync();
            }

            @Override
            public void onFailure(Call<List<Property>> call, Throwable t) {
                onFailureSync();
            }
        });

    }
}
