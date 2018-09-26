package api;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import persistence.PropertyDao;
import propertylist.model.Property;

@Database(entities = {Property.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public abstract PropertyDao propertyDao();
    private static AppDataBase INSTANCE;
    public static AppDataBase getDataBaseInstance(Context context) {
        if (INSTANCE == null) {
            return  Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDataBase.class,
                    "database-name").build();
        }
        else {
            return INSTANCE;
        }
    }
}
