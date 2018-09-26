package persistence;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import propertylist.model.Property;

@Database(entities = {Property.class}, version = 1)
@TypeConverters({Converters.class})
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
