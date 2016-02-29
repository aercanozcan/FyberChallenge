package com.fyber.fybersdk.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.example.fybersdk.R;
import com.fyber.fybersdk.adapter.OfferListAdapter;
import com.fyber.fybersdk.data.model.Const;
import com.fyber.fybersdk.data.model.rest.Offer;
import com.fyber.fybersdk.data.model.rest.ResponseEnvelope;

import org.parceler.Parcels;

/**
 * Created by Ercan on 2/11/2016.
 */
public class OfferListFragment extends ListFragment {


    public static final String TAG = "OfferListFragment";
    public static final String EXTRA_OFFERS = "offers";
    ResponseEnvelope response;
    OnItemClickCallback onItemClickCallback;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnItemClickCallback) {
            onItemClickCallback = (OnItemClickCallback) context;
        }
    }

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
        if (onItemClickCallback != null) {
            onItemClickCallback.onItemclick(offer);
        }
    }
}
