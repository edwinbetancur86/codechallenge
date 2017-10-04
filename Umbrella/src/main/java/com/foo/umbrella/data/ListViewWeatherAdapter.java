package com.foo.umbrella.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.foo.umbrella.R;
import com.foo.umbrella.data.Model2.WeatherModel;
import com.foo.umbrella.data.Model2.WeatherModelLabeler;

import java.util.List;

/**
 * Created by Edwin B on 10/3/2017.
 */

public class ListViewWeatherAdapter extends BaseAdapter{

    private List<WeatherModelLabeler> weatherModelLabelerList;
    private LayoutInflater inflater;
    private Context context;
    private int indexMaxDegree;
    private int indexMinDegree;
    private Boolean firstCoolOccurrence = true;
    private Boolean firstWarmOccurrence = true;
    private final static String TAG = "ForecastAdapter2";


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

    public ListViewWeatherAdapter(List<WeatherModelLabeler> weatherModelLabelerList,
                                  Context context, int indexMaxDegree, int indexMinDegree) {

        this.weatherModelLabelerList = weatherModelLabelerList;
        this.context = context;
        this.indexMaxDegree = indexMaxDegree;
        this.indexMinDegree = indexMinDegree;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public int getCount() {
        return weatherModelLabelerList.size();
    }

    @Override
    public Object getItem(int position) {
        return weatherModelLabelerList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.hourly_item, parent, false);

       /* timeOfDay = (TextView) itemView.findViewById(R.id.time_of_day);
        iconCondition = (ImageView) itemView.findViewById(R.id.imgCondition);
        currentDegree = (TextView) itemView.findViewById(R.id.degree_value_of_hour);*/

        return view;
    }
}
