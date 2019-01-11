package com.kogicodes.bravetest.models.schedules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleModel {

    @SerializedName("ScheduleResource")
    @Expose
    private ScheduleResource scheduleResource;

    public ScheduleResource getScheduleResource() {
        return scheduleResource;
    }

    public void setScheduleResource(ScheduleResource scheduleResource) {
        this.scheduleResource = scheduleResource;
    }

}