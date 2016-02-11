package com.ercan.fyberchallenge.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.ercan.fyberchallenge.R;
import com.ercan.fyberchallenge.adapter.OfferListAdapter;
import com.ercan.fyberchallenge.data.model.Const;
import com.ercan.fyberchallenge.data.model.rest.Offer;
import com.ercan.fyberchallenge.data.model.rest.ResponseEnvelope;

import org.parceler.Parcels;

/**
 * Created by Ercan on 2/11/2016.
 */
public class OfferListFragment extends ListFragment {


    public static final String TAG = "OfferListFragment";
    public static final String EXTRA_OFFERS = "offers";
    ResponseEnvelope response;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            response = Parcels.unwrap(getArguments().getParcelable(EXTRA_OFFERS));

        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setEmptyText(getContext().getText(R.string.warn_no_offers_to_display));
        if (response != null) {
            loadOffers(response);
        }

    }

    public void loadOffers(ResponseEnvelope responseEnvelope) {
        if (responseEnvelope.getCode().equals(Const.CODE_OK) || responseEnvelope.getCode().equals(Const.CODE_NO_CONTENT)) {
            setListAdapter(new OfferListAdapter(getActivity(), responseEnvelope.getOffers()));
        } else {
            //Already handled in MainActivity#onSubmit
        }

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Offer offer = ((OfferListAdapter) l.getAdapter()).getItem(position);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(offer.getLink()));
        startActivity(intent);
    }
}
