package com.ercan.fyberchallenge.data.model.rest;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by Ercan on 2/11/2016.
 */
@Parcel
public class ResponseEnvelope {

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    @SerializedName("count")
    private int count;

    @SerializedName("pages")
    private int pages;

    @SerializedName("information")
    private Information information;

    @SerializedName("offers")
    private List<Offer> offers;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
