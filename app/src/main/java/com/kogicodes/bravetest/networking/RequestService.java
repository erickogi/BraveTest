package com.kogicodes.bravetest.networking;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author kogi
 */
public class RequestService {


    private static final String BASE_URL = "https://api.lufthansa.com/v1/";

    private static Retrofit getRetrofit(String token) {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)

                .addConverterFactory(GsonConverterFactory.create())

                .client(getClient(token))
                .build();
    }

    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)

                .addConverterFactory(GsonConverterFactory.create())

                //.client(getClient(token))
                .build();
    }

    private static OkHttpClient getClient(final String token) {

        return new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + token)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();
    }


    public static EndPoints getService(String token) {
        return getRetrofit(token).create(EndPoints.class);

    }

    public static EndPoints getService() {
        return getRetrofit().create(EndPoints.class);

    }
}
