package com.foo.umbrella.data.api;

import com.foo.umbrella.BuildConfig;
import com.foo.umbrella.data.Model2.WeatherModel;
import com.foo.umbrella.data.model.WeatherData;

import retrofit2.Call;
import retrofit2.adapter.rxjava.Result;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Edwin B on 9/29/2017.
 */

public interface WeatherService2 {

    String BASE_URL = "http://api.wunderground.com";

    /**
     * Get the forecast for a given zip code using {@link Call}
     */
    @GET("/api/" + BuildConfig.API_KEY + "/conditions/hourly/q/{zip}.json")
    Call<WeatherModel> forecastForZipCallable(@Path("zip") String zipCode);

    /**
     * Get the forecast for a given zip code using {@link Observable}
     */
    @GET("/api/" + BuildConfig.API_KEY + "/conditions/hourly/q/{zip}.json")
    Observable<Result<WeatherModel>> forecastForZipObservable(@Path("zip") String zipCode);

}
