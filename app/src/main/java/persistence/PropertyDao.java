package persistence;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import propertylist.model.Property;

@Dao
public interface PropertyDao {

    @Insert
    void insertProperty(Property property);

    @Query("SELECT * FROM property")
    List<Property> getAllProperty();

    @Delete()
    void deleteProperty(Property property);

}
