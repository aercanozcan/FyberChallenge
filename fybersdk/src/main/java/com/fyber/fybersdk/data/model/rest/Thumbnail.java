package com.fyber.fybersdk.data.model.rest;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Ercan on 2/11/2016.
 */
@Parcel
public class Thumbnail {

    @SerializedName("lowres")
    private String lowRes;

    @SerializedName("hires")
    private String hiRes;

    public String getLowRes() {
        return lowRes;
    }

    public void setLowRes(String lowRes) {
        this.lowRes = lowRes;
    }

    public String getHiRes() {
        return hiRes;
    }

    public void setHiRes(String hiRes) {
        this.hiRes = hiRes;
    }
}
