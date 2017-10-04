package com.foo.umbrella.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.foo.umbrella.R;
import com.foo.umbrella.data.ForecastConditionAdapter2;
import com.foo.umbrella.data.Model2.HourlyForecast;
import com.foo.umbrella.data.Model2.WeatherModel;
import com.foo.umbrella.data.Model2.WeatherModelLabeler;
import com.foo.umbrella.data.MyGridLayoutManager;
import com.foo.umbrella.data.SpanningGridLayoutManager;
import com.foo.umbrella.data.api.WeatherService2;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

  private final String TAG = "MainActivity";
  private final String DEFAULT_ZIP_CODE = "02861";

  /*@BindView(R.id.recyclerViewToday)
  RecyclerView recyclerViewToday;*/

  @BindView(R.id.recyclerViewTomorrow)
  RecyclerView recyclerViewTomorrow;

  @BindView(R.id.main_degree_value)
  TextView mainDegreeValue;

  @BindView(R.id.main_condition)
  TextView mainCondition;

  @BindView(R.id.city_state_location)
  TextView cityStateLocation;

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






    retrofit = new Retrofit.Builder()
            .baseUrl(WeatherService2.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    WeatherService2 weatherServiceInterface
            = retrofit.create(WeatherService2.class);

    Call<WeatherModel> weatherModelCall
            = weatherServiceInterface.forecastForZipCallable(DEFAULT_ZIP_CODE);

    weatherModelCall.enqueue(new Callback<WeatherModel>() {
      @Override
      public void onResponse(Call<WeatherModel> call, Response<WeatherModel> response) {

        Log.d(TAG, "Inside onResponse");

        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();
        SimpleDateFormat parseFormat = new SimpleDateFormat("h:mm");
        parseFormat.setTimeZone(tz);

        if (response.body().getHourlyForecast() != null)
        {
              Log.d(TAG, "Inside response body");

              List<HourlyForecast> hourlyForecastList = response.body().getHourlyForecast();

              for (int i = 0; i < hourlyForecastList.size(); i++)
              {

                Log.d(TAG, "inside for loop");

                WeatherModelLabeler weatherModelLabeler = new WeatherModelLabeler();


                try {
                  //Date date = parseFormat.parse("0:00");

                  Date date = parseFormat.parse(hourlyForecastList.get(i).getFCTTIME().getHour() + ":"
                          + hourlyForecastList.get(i).getFCTTIME().getMin());

                  weatherModelLabeler.setTimeOfDay(parseFormat.format(date)
                          + " " + hourlyForecastList.get(i).getFCTTIME().getAmpm());

                  //Log.d(TAG, "converted 0:00 to :" + parseFormat.format(date));
                } catch (ParseException e) {
                  e.printStackTrace();
                }


                weatherModelLabeler.setCurrentDegreeValue(hourlyForecastList.get(i).getTemp().getEnglish());

                weatherModelLabeler.setIconCondition(hourlyForecastList.get(i).getIconUrl());

                weatherModelLabelerList.add(weatherModelLabeler);

                Log.d(TAG, weatherModelLabelerList.get(i).getTimeOfDay());
                Log.d(TAG, weatherModelLabelerList.get(i).getCurrentDegreeValue());
                Log.d(TAG, String.valueOf(weatherModelLabelerList.get(i).getIconCondition()));



              }



          // Show results for today first
          RecyclerView recyclerViewToday = (RecyclerView) findViewById(R.id.recyclerViewToday);

          recyclerViewToday.setLayoutManager(new GridLayoutManager(getApplicationContext(), 4, GridLayoutManager.VERTICAL, false));

          recyclerViewToday.setNestedScrollingEnabled(false);

          recyclerViewToday.setAdapter(new ForecastConditionAdapter2
                  (weatherModelLabelerList, getApplicationContext(), getLargestDegree(), getSmallestDegree()));


        }


      }

      @Override
      public void onFailure(Call<WeatherModel> call, Throwable t) {
            t.printStackTrace();
      }
    });



    Log.d(TAG, "End of onCreate()");

  }

    public int getLargestDegree()
    {

      int indexOfMax = 0;

      for (int i = 1; i < weatherModelLabelerList.size(); i++)
      {
          if (Integer.parseInt(weatherModelLabelerList.get(i).getCurrentDegreeValue())
                  > Integer.parseInt(weatherModelLabelerList.get(indexOfMax).getCurrentDegreeValue()))
          {
              indexOfMax = i;
          }
      }

      return indexOfMax;
    }

    public int getSmallestDegree()
    {

      int indexOfMin = 0;

      for (int i = 1; i < weatherModelLabelerList.size(); i++)
      {
        if (Integer.parseInt(weatherModelLabelerList.get(i).getCurrentDegreeValue())
                < Integer.parseInt(weatherModelLabelerList.get(indexOfMin).getCurrentDegreeValue()))
        {
          indexOfMin = i;
        }
      }

      return indexOfMin;
    }

}
