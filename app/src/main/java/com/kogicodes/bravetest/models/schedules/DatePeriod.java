package com.kogicodes.bravetest.models.schedules;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatePeriod {

    @SerializedName("Effective")
    @Expose
    private String effective;
    @SerializedName("Expiration")
    @Expose
    private String expiration;

    public String getEffective() {
        return effective;
    }

    public void setEffective(String effective) {
        this.effective = effective;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

}
