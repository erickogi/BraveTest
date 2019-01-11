package com.kogicodes.bravetest.data.dao;


import com.kogicodes.bravetest.models.room.AirportModel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface AirportDao {


    @Insert(onConflict = REPLACE)
    void insertMultiple(AirportModel... airportModels);

    @Insert(onConflict = REPLACE)
    void insertMultiple(List<AirportModel> airportModels);

    @Insert(onConflict = REPLACE)
    void insert(AirportModel airportModel);


    @Query("SELECT * FROM AirportModel ")
    LiveData<List<AirportModel>> fetchLive();

    @Query("SELECT * FROM AirportModel ")
    List<AirportModel> fetch();


    @Query("SELECT * FROM AirportModel WHERE airportCode = :airportCode")
    LiveData<AirportModel> fetchLive(String airportCode);

    @Query("SELECT * FROM AirportModel WHERE airportCode = :airportCode")
    AirportModel fetch(String airportCode);


    @Query("SELECT * FROM AirportModel WHERE airportCode LIKE '%' || :searchTerm || '%' OR airportNames LIKE '%' || :searchTerm || '%'")
    LiveData<List<AirportModel>> search(String searchTerm);


    @Query("SELECT * FROM AirportModel WHERE airportCode LIKE '%' || :searchTerm || '%' OR airportNames LIKE '%' || :searchTerm || '%'")
    List<AirportModel> searcha(String searchTerm);


    @Update
    void updateRecord(AirportModel airportModel);

    @Delete
    void deleteRecord(AirportModel airportModel);


    @Query("SELECT COUNT(airportCode) FROM AirportModel ")
    LiveData<Integer> getCountLive();

    @Query("SELECT COUNT(airportCode) FROM AirportModel ")
    Integer getCount();


}
