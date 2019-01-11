package com.kogicodes.bravetest.models.schedules;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Link {

    @SerializedName("@Href")
    @Expose
    private String href;
    @SerializedName("@Rel")
    @Expose
    private String rel;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

}
