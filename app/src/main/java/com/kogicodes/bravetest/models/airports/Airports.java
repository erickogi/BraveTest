package com.kogicodes.bravetest.models.airports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Airports {


    @SerializedName("Airport")
    @Expose
    private List<Airport> airport = null;

    public List<Airport> getAirport() {
        return airport;
    }

    public void setAirport(List<Airport> airport) {
        this.airport = airport;
    }

}
