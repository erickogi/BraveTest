package com.kogicodes.bravetest.models.schedules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Arrival {

    @SerializedName("AirportCode")
    @Expose
    private String airportCode;
    @SerializedName("ScheduledTimeLocal")
    @Expose
    private ScheduledTimeLocal_ scheduledTimeLocal;
    @SerializedName("Terminal")
    @Expose
    private Terminal terminal;

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public ScheduledTimeLocal_ getScheduledTimeLocal() {
        return scheduledTimeLocal;
    }

    public void setScheduledTimeLocal(ScheduledTimeLocal_ scheduledTimeLocal) {
        this.scheduledTimeLocal = scheduledTimeLocal;
    }

    public Terminal getTerminal() {
        if (terminal == null) {
            terminal = new Terminal();
        }
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

}
