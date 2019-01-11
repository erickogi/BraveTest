package com.kogicodes.bravetest.data.repo;

import android.app.Application;
import android.util.Log;

import com.google.gson.Gson;
import com.kogicodes.bravetest.data.BraveTestDatabase;
import com.kogicodes.bravetest.data.PrefrenceManager;
import com.kogicodes.bravetest.data.dao.AirportDao;
import com.kogicodes.bravetest.models.airports.Airport;
import com.kogicodes.bravetest.models.airports.AirportsModel;
import com.kogicodes.bravetest.models.basic.Resource;
import com.kogicodes.bravetest.models.basic.Status;
import com.kogicodes.bravetest.models.oauth.Token;
import com.kogicodes.bravetest.models.room.AirportModel;
import com.kogicodes.bravetest.networking.RequestService;
import com.kogicodes.bravetest.utils.DateTimeUtils;
import com.kogicodes.bravetest.utils.DefaultExecutorSupplier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class AirportsRepository {
    private AirportDao dao;
    private BraveTestDatabase db;
    private PrefrenceManager prefrenceManager;
    private MutableLiveData<Resource<List<AirportModel>>> airportListObservable = new MutableLiveData<Resource<List<AirportModel>>>();


    public AirportsRepository(Application application) {
        db = BraveTestDatabase.getDatabase(application);
        prefrenceManager = new PrefrenceManager(application);
        dao = db.airportDao();
    }


    public void getAirportsOnline(int lastPage) {
        if (dao.getCount() < 100) {
            Token token = prefrenceManager.getToken();
            if (token.getAccessToken() != null && new DateTimeUtils().isTokenValid(token.getExpiryDate())) {
                airportCallPageOne(lastPage);

            } else {
                oauthCall(lastPage);
            }
        }


    }

    private void oauthCall(int lastPage) {
        Call<Token> call = RequestService.getService().getOauth("6g8vjdydrqtw8ced72dw4hnv", "MpM79TfUgF", "client_credentials");
        Log.d("callError", "auir");

        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(() -> {
            call.enqueue(new Callback<Token>() {
                @Override
                public void onResponse(Call<Token> call, Response<Token> response) {
                    Timber.tag("callError").d(response.toString());
                    Log.d("callError", response.toString());

                    if (response.isSuccessful()) {

                        Token token = response.body();
                        token.setExpiryDate(new DateTimeUtils().getExpiryDateInMilliseconds(token.getExpiresIn()));
                        prefrenceManager.saveExpiryDate(response.body());

                        airportCallPageOne(lastPage);
                    } else {

                    }
                }

                @Override
                public void onFailure(Call<Token> call, Throwable t) {
                    Timber.tag("callError").d(t.toString());
                    Log.d("callError", t.toString());

                }
            });


        });

    }

    private void airportCallPageOne(int lastPage) {


        Call<AirportsModel> call = RequestService.getService(prefrenceManager.getToken().getAccessToken()).getAirports(createCallBody(lastPage));

        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(() -> {

            call.enqueue(new Callback<AirportsModel>() {
                @Override
                public void onResponse(Call<AirportsModel> call, Response<AirportsModel> response) {
                    if (response.isSuccessful()) {
                        saveAirport(response.body());
                        int totalCount = response.body().getAirportResource().getMeta().getTotalCount();

                        int next = lastPage + 100;
                        if (next < totalCount) {
                            airportCallPageOne(next);
                        } else {

                        }

                    }
                }

                @Override
                public void onFailure(Call<AirportsModel> call, Throwable t) {
                    Timber.tag("callError").d(t.toString());


                }
            });


        });

    }

    private Map<String, String> createCallBody(int lastPage) {

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("limit", "100");
        hashMap.put("offset", "" + lastPage);
        hashMap.put("LHoperated", "0");
        hashMap.put("lang", "en");
        return hashMap;
    }

    private void saveAirport(AirportsModel body) {

        List<Airport> airports = body.getAirportResource().getAirports().getAirport();
        for (Airport airport : airports) {
            if (airport != null) {
                Log.d("datafggg", new Gson().toJson(airport));

                try {
                    AirportModel airportModel = new AirportModel();
                    airportModel.setAirportCode(airport.getAirportCode());
                    airportModel.setAirportCityCode(airport.getCityCode());
                    airportModel.setAirportCountryCode(airport.getCountryCode());
                    airportModel.setAirportLatitude(airport.getPosition().getCoordinate().getLatitude());
                    airportModel.setAirportLongitude(airport.getPosition().getCoordinate().getLongitude());
                    airportModel.setAirportNames(airport.getNames().getName().get$());
                    airportModel.setAirportTimeZone(airport.getTimeZoneId());
                    dao.insert(airportModel);

                } catch (Exception nm) {
                    nm.printStackTrace();
                }
            }
        }

    }


    public void fetchData(String query) {
        List<AirportModel> loadingList = null;
        if (airportListObservable.getValue() != null) {
            loadingList = airportListObservable.getValue().data;
        }
        airportListObservable.setValue(Resource.loading(loadingList));
        loadAirportsFromDB(query);

    }

    public MutableLiveData<Resource<List<AirportModel>>> getairportListObservable() {
        return airportListObservable;
    }


    private void loadAirportsFromDB(String searchString) {

        DefaultExecutorSupplier.getInstance().forBackgroundTasks().execute(() -> {
            Status loadingStatus = Status.LOADING;
            if (airportListObservable.getValue() != null) {
                loadingStatus = airportListObservable.getValue().status;
            }


            switch (loadingStatus) {
                case LOADING:
                    airportListObservable.postValue(Resource.loading(dao.searcha(searchString)));
                    break;
                case ERROR:
                    airportListObservable.postValue(Resource.error(null, dao.searcha(searchString)));
                    break;
                case SUCCESS:
                    airportListObservable.postValue(Resource.success(dao.searcha(searchString)));
                    break;
                default:
                    airportListObservable.postValue(Resource.success(dao.searcha(searchString)));

            }


        });

    }


}
