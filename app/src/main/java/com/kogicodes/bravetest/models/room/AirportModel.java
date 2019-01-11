package com.kogicodes.bravetest.models.room;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AirportModel implements Serializable {
    @PrimaryKey
    @NotNull
    private String airportCode;
    private String airportNames;
    private Double airportLatitude;
    private Double airportLongitude;
    private String airportCityCode;
    private String airportCountryCode;
    private String airportTimeZone;

    @NotNull
    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(@NotNull String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportNames() {
        return airportNames;
    }

    public void setAirportNames(String airportNames) {
        this.airportNames = airportNames;
    }

    public Double getAirportLatitude() {
        return airportLatitude;
    }

    public void setAirportLatitude(Double airportLatitude) {
        this.airportLatitude = airportLatitude;
    }

    public Double getAirportLongitude() {
        return airportLongitude;
    }

    public void setAirportLongitude(Double airportLongitude) {
        this.airportLongitude = airportLongitude;
    }

    public String getAirportCityCode() {
        return airportCityCode;
    }

    public void setAirportCityCode(String airportCityCode) {
        this.airportCityCode = airportCityCode;
    }

    public String getAirportCountryCode() {
        return airportCountryCode;
    }

    public void setAirportCountryCode(String airportCountryCode) {
        this.airportCountryCode = airportCountryCode;
    }

    public String getAirportTimeZone() {
        return airportTimeZone;
    }

    public void setAirportTimeZone(String airportTimeZone) {
        this.airportTimeZone = airportTimeZone;
    }
}
