package com.fyber.fybersdk.data.model.rest;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Ercan on 2/11/2016.
 */
@Parcel
public class OfferType {

    @SerializedName("offer_type_id")
    private String id;

    @SerializedName("readable")
    private String readable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
    }
}
