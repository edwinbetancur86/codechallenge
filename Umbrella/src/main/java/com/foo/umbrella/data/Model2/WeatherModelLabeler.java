package com.foo.umbrella.data.Model2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edwin B on 9/29/2017.
 */

public class WeatherModelLabeler {

    private String timeOfDay;
    private int iconCondition;
    private String currentDegreeValue;

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public int getIconCondition() {
        return iconCondition;
    }

    public void setIconCondition(int iconCondition) {
        this.iconCondition = iconCondition;
    }

    public String getCurrentDegreeValue() {
        return currentDegreeValue;
    }

    public void setCurrentDegreeValue(String currentDegreeValue) {
        this.currentDegreeValue = currentDegreeValue;
    }



}
