package com.foo.umbrella.data.Model2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Edwin B on 9/29/2017.
 */

public class Features {

    @SerializedName("hourly")
    @Expose
    private Integer hourly;

    public Integer getHourly() {
        return hourly;
    }

    public void setHourly(Integer hourly) {
        this.hourly = hourly;
    }

}
