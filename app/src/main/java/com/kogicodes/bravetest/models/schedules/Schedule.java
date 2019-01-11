package com.kogicodes.bravetest.models.schedules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Schedule {

    @SerializedName("TotalJourney")
    @Expose
    private TotalJourney totalJourney;
    @SerializedName("Flight")
    @Expose
    private List<Flight> flight = null;

    public TotalJourney getTotalJourney() {
        return totalJourney;
    }

    public void setTotalJourney(TotalJourney totalJourney) {
        this.totalJourney = totalJourney;
    }

    public List<Flight> getFlight() {
        return flight;
    }

    public void setFlight(List<Flight> flight) {
        this.flight = flight;
    }
}
