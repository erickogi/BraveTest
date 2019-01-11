package com.kogicodes.bravetest.models.airports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AirportResource {
    @SerializedName("Airports")
    @Expose
    private Airports airports;
    @SerializedName("Meta")
    @Expose
    private Meta meta;

    public Airports getAirports() {
        return airports;
    }

    public void setAirports(Airports airports) {
        this.airports = airports;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
