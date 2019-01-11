package com.kogicodes.bravetest.data;


import android.content.Context;

import com.kogicodes.bravetest.data.dao.AirportDao;
import com.kogicodes.bravetest.models.room.AirportModel;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @author kogi
 */
@Database(entities = {AirportModel.class}, version = 1)

public abstract class BraveTestDatabase extends RoomDatabase {

    private static BraveTestDatabase INSTANCE;

    public static BraveTestDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (BraveTestDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BraveTestDatabase.class, "brave_test_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();

                }
            }
        }
        return INSTANCE;


    }


    public abstract AirportDao airportDao();


}
