package com.kogicodes.bravetest.models.schedules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stops {

    @SerializedName("StopQuantity")
    @Expose
    private Integer stopQuantity;

    public Integer getStopQuantity() {
        return stopQuantity;
    }

    public void setStopQuantity(Integer stopQuantity) {
        this.stopQuantity = stopQuantity;
    }

}