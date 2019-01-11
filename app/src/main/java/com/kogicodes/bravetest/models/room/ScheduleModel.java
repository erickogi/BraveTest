package com.kogicodes.bravetest.models.room;


public class ScheduleModel {
    private int id;
    private String airportCode;
    private String totalJourneyDuration;
    private String depatureAirportCode;
    private String depatureDateTime;
    private String arrivalAirportCode;
    private String arrivalDateTime;
    private String arrivlTerminalName;
    private String marketingCarrierAirlineId;
    private String marketingCarrierFlightNumber;
    private String operatingCarrierAilineId;
    private String aircraftCode;
    private int stopsQuantity;
    private String daysOfOperations;
    private String effectiveFrom;
    private String expiresOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getTotalJourneyDuration() {
        return totalJourneyDuration;
    }

    public void setTotalJourneyDuration(String totalJourneyDuration) {
        this.totalJourneyDuration = totalJourneyDuration;
    }

    public String getDepatureAirportCode() {
        return depatureAirportCode;
    }

    public void setDepatureAirportCode(String depatureAirportCode) {
        this.depatureAirportCode = depatureAirportCode;
    }

    public String getDepatureDateTime() {
        return depatureDateTime;
    }

    public void setDepatureDateTime(String depatureDateTime) {
        this.depatureDateTime = depatureDateTime;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public String getArrivalDateTime() {
        return arrivalDateTime;
    }

    public void setArrivalDateTime(String arrivalDateTime) {
        this.arrivalDateTime = arrivalDateTime;
    }

    public String getArrivlTerminalName() {
        return arrivlTerminalName;
    }

    public void setArrivlTerminalName(String arrivlTerminalName) {
        this.arrivlTerminalName = arrivlTerminalName;
    }

    public String getMarketingCarrierAirlineId() {
        return marketingCarrierAirlineId;
    }

    public void setMarketingCarrierAirlineId(String marketingCarrierAirlineId) {
        this.marketingCarrierAirlineId = marketingCarrierAirlineId;
    }

    public String getMarketingCarrierFlightNumber() {
        return marketingCarrierFlightNumber;
    }

    public void setMarketingCarrierFlightNumber(String marketingCarrierFlightNumber) {
        this.marketingCarrierFlightNumber = marketingCarrierFlightNumber;
    }

    public String getOperatingCarrierAilineId() {
        return operatingCarrierAilineId;
    }

    public void setOperatingCarrierAilineId(String operatingCarrierAilineId) {
        this.operatingCarrierAilineId = operatingCarrierAilineId;
    }

    public String getAircraftCode() {
        return aircraftCode;
    }

    public void setAircraftCode(String aircraftCode) {
        this.aircraftCode = aircraftCode;
    }

    public int getStopsQuantity() {
        return stopsQuantity;
    }

    public void setStopsQuantity(int stopsQuantity) {
        this.stopsQuantity = stopsQuantity;
    }

    public String getDaysOfOperations() {
        return daysOfOperations;
    }

    public void setDaysOfOperations(String daysOfOperations) {
        this.daysOfOperations = daysOfOperations;
    }

    public String getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(String effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public String getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(String expiresOn) {
        this.expiresOn = expiresOn;
    }
}
