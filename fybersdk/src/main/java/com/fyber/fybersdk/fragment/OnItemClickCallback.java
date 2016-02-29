package com.fyber.fybersdk.fragment;

import com.fyber.fybersdk.data.model.rest.Offer;

/**
 * Created by trial on 29/02/16.
 * to notify system when an offer Item clicked from fragment
 */
public interface OnItemClickCallback {
    void onItemclick(Offer offer);
}
