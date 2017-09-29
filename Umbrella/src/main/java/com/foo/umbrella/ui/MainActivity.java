package com.foo.umbrella.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.foo.umbrella.R;
import com.foo.umbrella.data.ForecastConditionAdapter2;
import com.foo.umbrella.data.Model2.HourlyForecast;
import com.foo.umbrella.data.Model2.WeatherModel;
import com.foo.umbrella.data.Model2.WeatherModelLabeler;
import com.foo.umbrella.data.api.WeatherService;
import com.foo.umbrella.data.api.WeatherService2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

  private LinearLayout layoutManager;

  @BindView(R.id.recyclerViewToday)
  RecyclerView recyclerViewToday;

  @BindView(R.id.recyclerViewTomorrow)
  RecyclerView recyclerViewTomorrow;

  @BindView(R.id.main_degree_value)
  TextView mainDegreeValue;

  @BindView(R.id.main_condition)
  TextView mainCondition;

  @BindView(R.id.city_state_location)
  TextView cityStateLoction;

  @BindView(R.id.settings_button)
  Button settingsButton;

  private Retrofit retrofit;
  private List<WeatherModelLabeler> weatherModelLabelerList = new ArrayList<>();


  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (getSupportActionBar() != null)
    {
      getSupportActionBar().hide();
    }

    ButterKnife.bind(this);

    LinearLayoutManager layoutManager
            = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

    recyclerViewToday.setLayoutManager(layoutManager);
    //recyclerViewTomorrow.setLayoutManager(layoutManager);


    retrofit = new Retrofit.Builder()
            .baseUrl(WeatherService2.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    WeatherService2 weatherServiceInterface
            = retrofit.create(WeatherService2.class);

    Call<WeatherModel> weatherModelCall
            = weatherServiceInterface.forecastForZipCallable("02861");

    weatherModelCall.enqueue(new Callback<WeatherModel>() {
      @Override
      public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {


        if (response.body().getHourlyForecast() != null)
        {
              List<HourlyForecast> hourlyForecastList = response.body().getHourlyForecast();

              for (int i = 0; i < hourlyForecastList.size(); i++)
              {

                WeatherModelLabeler weatherModelLabeler = new WeatherModelLabeler();

                weatherModelLabeler.setTimeOfDay(hourlyForecastList.get(i).getFCTTIME().getHour() + " : "
                        + hourlyForecastList.get(i).getFCTTIME().getMin());

                weatherModelLabelerList.add(weatherModelLabeler);

              }

        }


      }

      @Override
      public void onFailure(Call<WeatherModel> call, Throwable t) {
            t.printStackTrace();
      }
    });


    // Show results for today first
    recyclerViewToday.setAdapter(new ForecastConditionAdapter2(weatherModelLabelerList, getApplicationContext()));


  }
}
