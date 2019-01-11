package com.kogicodes.bravetest.models.airports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AirportsModel {
    @SerializedName("AirportResource")
    @Expose
    private AirportResource airportResource;

    public AirportResource getAirportResource() {
        return airportResource;
    }

    public void setAirportResource(AirportResource airportResource) {
        this.airportResource = airportResource;
    }
}
