package api;

import java.util.List;

import propertylist.model.Property;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceApi {
    @GET("sources/source-1.json")
    Call<List<Property>> getProPertyList();
}
