package com.kogicodes.bravetest.data.repo;

import android.app.Application;

import com.kogicodes.bravetest.data.PrefrenceManager;
import com.kogicodes.bravetest.models.basic.Resource;
import com.kogicodes.bravetest.models.basic.Status;
import com.kogicodes.bravetest.models.oauth.Token;
import com.kogicodes.bravetest.models.schedules.Schedule;
import com.kogicodes.bravetest.models.schedules.ScheduleModel;
import com.kogicodes.bravetest.networking.RequestService;
import com.kogicodes.bravetest.utils.DateTimeUtils;
import com.kogicodes.bravetest.utils.DefaultExecutorSupplier;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleRepository {

    private PrefrenceManager prefrenceManager;
    private MutableLiveData<Resource<List<Schedule>>> scheduleListObservable = new MutableLiveData<Resource<List<Schedule>>>();


    public ScheduleRepository(Application application) {
        prefrenceManager = new PrefrenceManager(application);
    }


    private void getSchedulesOnline(int lastPage, String origin, String destination, String date) {
        Token token = prefrenceManager.getToken();
        if (token.getAccessToken() != null && new DateTimeUtils().isTokenValid(token.getExpiryDate())) {
            scheduleCallPageOne(lastPage, origin, destination, date);

        } else {
            oauthCall(lastPage, origin, destination, date);
        }


    }

    private void oauthCall(int lastPage, String origin, String destination, String date) {
        Call<Token> call = RequestService.getService("").getOauth("6g8vjdydrqtw8ced72dw4hnv", "MpM79TfUgF", "client_credentials");

        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(() -> {
            try {
                Response<Token> response = call.execute();

                if (response.isSuccessful()) {

                    prefrenceManager.saveExpiryDate(response.body());
                    scheduleCallPageOne(lastPage, origin, destination, date);
                } else {

                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        });

    }

    private void scheduleCallPageOne(int lastPage, String origin, String destination, String date) {


        setListObservableStatus(Status.LOADING, String.valueOf(".....Fetching Schedules....."));

        Call<ScheduleModel> call = RequestService.getService(prefrenceManager.getToken().getAccessToken()).getSchedules(origin, destination, date);

        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(() -> {

            call.enqueue(new Callback<ScheduleModel>() {
                @Override
                public void onResponse(Call<ScheduleModel> call, Response<ScheduleModel> response) {

                    if (response.isSuccessful()) {
                        saveSchedules(response.body());


                    } else {
                        setListObservableStatus(Status.ERROR, String.valueOf(response.toString()));
                    }

                }

                @Override
                public void onFailure(Call<ScheduleModel> call, Throwable t) {
                    setListObservableStatus(Status.ERROR, String.valueOf(t.getMessage()));


                }
            });


        });

    }

    private void setListObservableStatus(Status status, String message) {
        List<Schedule> loadingList = null;
        if (scheduleListObservable.getValue() != null) {
            loadingList = scheduleListObservable.getValue().data;
        }
        switch (status) {
            case ERROR:
                scheduleListObservable.postValue(Resource.error(message, loadingList));
                break;
            case LOADING:
                scheduleListObservable.postValue(Resource.loading(loadingList));
                break;
            case SUCCESS:
                if (loadingList != null) {
                    scheduleListObservable.postValue(Resource.success(loadingList));
                }
                break;
        }

    }


    private Map<String, String> createCallBody(int lastPage) {

        HashMap<String, String> hashMap = new HashMap<>();
        // hashMap.put("limit","100");
        // hashMap.put("offset",""+lastPage);
        hashMap.put("directFlights", "0");
        return hashMap;
    }

    private void saveSchedules(ScheduleModel body) {
        List<Schedule> schedules = body.getScheduleResource().getSchedule();
        setListObservableStatus(Status.SUCCESS, String.valueOf(".....Fetching Schedules....."));

        Status loadingStatus = Status.LOADING;
        if (scheduleListObservable.getValue() != null) {
            loadingStatus = scheduleListObservable.getValue().status;
        }


        switch (loadingStatus) {
            case LOADING:
                scheduleListObservable.postValue(Resource.loading(body.getScheduleResource().getSchedule()));
                break;
            case ERROR:
                scheduleListObservable.postValue(Resource.error(null, body.getScheduleResource().getSchedule()));
                break;
            case SUCCESS:
                scheduleListObservable.postValue(Resource.success(body.getScheduleResource().getSchedule()));
                break;
            default:
        }


    }


    public void fetchData(String origin, String destination, String date) {
        List<Schedule> loadingList = null;
        if (scheduleListObservable.getValue() != null) {
            loadingList = scheduleListObservable.getValue().data;
        }
        scheduleListObservable.setValue(Resource.loading(loadingList));
        getSchedulesOnline(0, origin, destination, date);

    }

    public MutableLiveData<Resource<List<Schedule>>> getscheduleListObservable() {
        return scheduleListObservable;
    }


}
