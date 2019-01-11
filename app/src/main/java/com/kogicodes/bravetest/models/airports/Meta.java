package com.kogicodes.bravetest.models.airports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Meta {


    @SerializedName("@Version")
    @Expose
    private String version;
    @SerializedName("Link")
    @Expose
    private List<Link> link = null;
    @SerializedName("TotalCount")
    @Expose
    private Integer totalCount;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Link> getLink() {
        return link;
    }

    public void setLink(List<Link> link) {
        this.link = link;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

}
