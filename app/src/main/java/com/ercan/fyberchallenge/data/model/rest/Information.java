package com.ercan.fyberchallenge.data.model.rest;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Ercan on 2/11/2016.
 */
@Parcel
public class Information {

    @SerializedName("ap_name")
    private String appName;

    @SerializedName("appid")
    private String appId;

    @SerializedName("virtual_currency")
    private String virtualCurrency;

    @SerializedName("country")
    private String country;

    @SerializedName("language")
    private String language;

    @SerializedName("support_url")
    private String supportUrl;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getVirtualCurrency() {
        return virtualCurrency;
    }

    public void setVirtualCurrency(String virtualCurrency) {
        this.virtualCurrency = virtualCurrency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSupportUrl() {
        return supportUrl;
    }

    public void setSupportUrl(String supportUrl) {
        this.supportUrl = supportUrl;
    }
}
