package com.kogicodes.bravetest.models.airports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Name {
    @SerializedName("@LanguageCode")
    @Expose
    private String languageCode;
    @SerializedName("$")
    @Expose
    private String $;

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String get$() {
        return $;
    }

    public void set$(String $) {
        this.$ = $;
    }

}
