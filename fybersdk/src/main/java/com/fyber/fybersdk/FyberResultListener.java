package com.fyber.fybersdk;

import android.content.Intent;

import com.fyber.fybersdk.data.model.rest.Offer;

import org.parceler.Parcels;

/**
 * Created by trial on 29/02/16.
 * A helper class that listens activity result and calls callback methods
 */
public abstract class FyberResultListener {


    final public void onActivityResult(int code, Intent resultIntent) {

        if (code == OfferWallActivity.DISMISSED_BY_BUTTON) {
            dismissWithButton();
        } else {//the only other case is an item is clicked
            onOfferClicked((Offer) Parcels.unwrap(resultIntent.getParcelableExtra(OfferWallActivity.RESULT_OFFER)));
        }
    }


    public abstract void dismissWithButton();


    public abstract void onOfferClicked(Offer offer);
}
