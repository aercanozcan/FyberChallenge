package com.fyber.fybersdk.data.model.rest;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Ercan on 2/11/2016.
 */
@Parcel
public class TimeToPayout {

    @SerializedName("amount")
    private int amount;

    @SerializedName("readable")
    private String readable;

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
