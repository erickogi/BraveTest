package com.kogicodes.bravetest.models.schedules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ScheduleResource {

    @SerializedName("Schedule")
    @Expose
    private List<Schedule> schedule = null;
    @SerializedName("Meta")
    @Expose
    private Meta meta;

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

}
