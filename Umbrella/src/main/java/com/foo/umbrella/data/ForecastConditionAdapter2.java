package com.foo.umbrella.data;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.foo.umbrella.R;
import com.foo.umbrella.data.Model2.WeatherModelLabeler;

import java.util.List;

/**
 * Created by Edwin B on 9/29/2017.
 */

public class ForecastConditionAdapter2 extends RecyclerView.Adapter<ForecastConditionAdapter2.ForecastViewHolder>{

    private List<WeatherModelLabeler> weatherModelLabelerList;
    LayoutInflater inflater;
    private Context context;
    private int maxDegree;
    private int minDegree;
    private Boolean firstCoolOccurrence = true;
    private Boolean firstWarmOccurrence = true;
    private final static String TAG = "ForecastConditionAdapter2";


    public static class ForecastViewHolder extends RecyclerView.ViewHolder
    {

        TextView timeOfDay;
        ImageView iconCondition;
        TextView currentDegree;


        public ForecastViewHolder(View itemView)
        {
            super(itemView);
            timeOfDay = (TextView) itemView.findViewById(R.id.time_of_day);
            iconCondition = (ImageView) itemView.findViewById(R.id.imgCondition);
            currentDegree = (TextView) itemView.findViewById(R.id.degree_value_of_hour);
        }
    }


    public ForecastConditionAdapter2(List<WeatherModelLabeler> weatherModelLabelerList,
                                     Context context, int maxDegree, int minDegree) {
        this.weatherModelLabelerList = weatherModelLabelerList;
        this.context = context;
        this.maxDegree = maxDegree;
        this.minDegree = minDegree;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public ForecastConditionAdapter2.ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.hourly_item, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {



        holder.timeOfDay.setText(weatherModelLabelerList.get(position).getTimeOfDay());

        if ((Integer.parseInt(weatherModelLabelerList.get(position).getCurrentDegreeValue()) == minDegree) && (firstCoolOccurrence))
        {
            holder.iconCondition.setColorFilter(ContextCompat.getColor(context, R.color.weather_cool));
            firstCoolOccurrence = false;
        }

        if ((Integer.parseInt(weatherModelLabelerList.get(position).getCurrentDegreeValue()) == maxDegree) && (firstWarmOccurrence))
        {
            holder.iconCondition.setColorFilter(ContextCompat.getColor(context, R.color.weather_warm));
            firstWarmOccurrence = false;
        }

        Glide.with(context).load(weatherModelLabelerList.get(position).getIconCondition()).into(holder.iconCondition);
        holder.currentDegree.setText(weatherModelLabelerList.get(position).getCurrentDegreeValue());

    }

    @Override
    public int getItemCount() {
        return weatherModelLabelerList.size();
    }








}
