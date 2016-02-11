package com.ercan.fyberchallenge.data.model.rest;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by Ercan on 2/11/2016.
 */
@Parcel
public class Offer {

    @SerializedName("title")
    private String title;

    @SerializedName("offer_id")
    private String id;

    @SerializedName("required_actions")
    private String requiredActions;

    @SerializedName("link")
    private String link;

    @SerializedName("payout")
    private int payout;

    @SerializedName("offer_types")
    private List<OfferType> offerTypes;

    @SerializedName("time_to_payout")
    private TimeToPayout timeToPayout;

    @SerializedName("thumbnail")
    private Thumbnail thumbnail;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRequiredActions() {
        return requiredActions;
    }

    public void setRequiredActions(String requiredActions) {
        this.requiredActions = requiredActions;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getPayout() {
        return payout;
    }

    public void setPayout(int payout) {
        this.payout = payout;
    }

    public List<OfferType> getOfferTypes() {
        return offerTypes;
    }

    public void setOfferTypes(List<OfferType> offerTypes) {
        this.offerTypes = offerTypes;
    }

    public TimeToPayout getTimeToPayout() {
        return timeToPayout;
    }

    public void setTimeToPayout(TimeToPayout timeToPayout) {
        this.timeToPayout = timeToPayout;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }
}
