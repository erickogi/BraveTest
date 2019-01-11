package com.kogicodes.bravetest.ui.main;

import android.app.Application;

import com.kogicodes.bravetest.data.repo.AirportsRepository;
import com.kogicodes.bravetest.data.repo.ScheduleRepository;
import com.kogicodes.bravetest.models.basic.Resource;
import com.kogicodes.bravetest.models.room.AirportModel;
import com.kogicodes.bravetest.models.schedules.Schedule;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;


public class MainViewModel extends AndroidViewModel {


    AirportsRepository mRepository;
    ScheduleRepository scheduleRepository;
    private MediatorLiveData<Resource<List<AirportModel>>> airportListObservable = new MediatorLiveData<>();
    private MediatorLiveData<Resource<List<Schedule>>> scheduleListObservable = new MediatorLiveData<>();


    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepository = new AirportsRepository(application);
        scheduleRepository = new ScheduleRepository(application);
        airportListObservable.addSource(mRepository.getairportListObservable(), airports -> airportListObservable.setValue(airports));
        scheduleListObservable.addSource(scheduleRepository.getscheduleListObservable(), schedules -> scheduleListObservable.setValue(schedules));

    }

    public void getAirports(String query) {
        mRepository.fetchData(query);
    }

    public void getAirports() {
        mRepository.getAirportsOnline(0);
    }

    public void getSchedules(String origin, String destination, String dateTime) {
        scheduleRepository.fetchData(origin, destination, dateTime);
    }

    public LiveData<Resource<List<AirportModel>>> getairportListObservable() {
        return airportListObservable;
    }

    public LiveData<Resource<List<Schedule>>> getScheduleListObservable() {
        return scheduleListObservable;
    }


}
