package com.kogicodes.bravetest.models.schedules;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ScheduledTimeLocal_ {

    @SerializedName("DateTime")
    @Expose
    private String dateTime;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public DateTime getDate() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
        DateTime dt = formatter.parseDateTime(dateTime);
        return dt;

    }

}
