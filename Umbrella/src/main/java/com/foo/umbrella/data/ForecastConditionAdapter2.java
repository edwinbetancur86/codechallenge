package com.foo.umbrella.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.foo.umbrella.R;
import com.foo.umbrella.data.Model2.WeatherModelLabeler;

import java.util.List;

/**
 * Created by Edwin B on 9/29/2017.
 */

public class ForecastConditionAdapter2 extends RecyclerView.Adapter<ForecastConditionAdapter2.ForecastViewHolder>{

    private List<WeatherModelLabeler> weatherModelLabelerList;
    private int rowLayout;
    private LayoutInflater inflater;
    private Context context;
    private final static String TAG = "ForecastConditionAdapter2";

    public ForecastConditionAdapter2(List<WeatherModelLabeler> weatherModelLabelerList, Context context) {
        this.weatherModelLabelerList = weatherModelLabelerList;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.hourly_item, parent, false);
        ForecastViewHolder holder = new ForecastViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ForecastViewHolder holder, int position) {

        holder.timeOfDay.setText(weatherModelLabelerList.get(position).getTimeOfDay());
        holder.iconCondition.setImageResource(weatherModelLabelerList.get(position).getIconCondition());
        holder.currentDegree.setText(weatherModelLabelerList.get(position).getCurrentDegreeValue());

    }

    @Override
    public int getItemCount() {
        return weatherModelLabelerList.size();
    }

    class ForecastViewHolder extends RecyclerView.ViewHolder
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






}
