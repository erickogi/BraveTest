package com.kogicodes.bravetest.models.schedules;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Details {

    @SerializedName("Stops")
    @Expose
    private Stops stops;
    @SerializedName("DaysOfOperation")
    @Expose
    private Integer daysOfOperation;
    @SerializedName("DatePeriod")
    @Expose
    private DatePeriod datePeriod;

    public Stops getStops() {
        return stops;
    }

    public void setStops(Stops stops) {
        this.stops = stops;
    }

    public Integer getDaysOfOperation() {
        return daysOfOperation;
    }

    public void setDaysOfOperation(Integer daysOfOperation) {
        this.daysOfOperation = daysOfOperation;
    }

    public DatePeriod getDatePeriod() {
        return datePeriod;
    }

    public void setDatePeriod(DatePeriod datePeriod) {
        this.datePeriod = datePeriod;
    }

}