package com.kogicodes.bravetest.networking;

import com.kogicodes.bravetest.models.airports.AirportsModel;
import com.kogicodes.bravetest.models.oauth.Token;
import com.kogicodes.bravetest.models.schedules.ScheduleModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * @author kogi
 */
public interface EndPoints {
    @FormUrlEncoded
    @POST("oauth/token")
    Call<Token> getOauth(@Field("client_id") String clientId, @Field("client_secret") String clientSecret, @Field("grant_type") String grantType);


    @GET("references/airports")
    Call<AirportsModel> getAirports(@QueryMap Map<String, String> params);


    @GET("operations/schedules/{origin}/{destination}/{fromDateTime}")
    Call<ScheduleModel> getSchedules(
            @Path("origin") String origin,
            @Path("destination") String destination,
            @Path("fromDateTime") String fromDateTime);
//            @Query("limit")  int limit,
//            @Query("offset")  int offset,
//            @Query("directFlights")  String directFlights );

}
