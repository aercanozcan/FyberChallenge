package com.ercan.fyberchallenge;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ercan.fyberchallenge.data.model.rest.ResponseEnvelope;
import com.ercan.fyberchallenge.data.rest.RestClient;
import com.ercan.fyberchallenge.fragment.FyberFormFragment;
import com.ercan.fyberchallenge.fragment.OfferListFragment;
import com.ercan.fyberchallenge.fragment.OnSubmit;

import org.parceler.Parcels;


public class MainActivity extends AppCompatActivity implements OnSubmit {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.lytFyberFormFragmentContainer, new FyberFormFragment(), FyberFormFragment.TAG).commit();
        if (getResources().getBoolean(R.bool.is_tablet)) {
            getSupportFragmentManager().beginTransaction().replace(R.id.lytOfferListFragmentContainer, new OfferListFragment(), OfferListFragment.TAG).commit();
        }

    }

    @Override
    public void onSubmit(ResponseEnvelope response) {
        try {
            Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
            if (getResources().getBoolean(R.bool.is_tablet)) {
                OfferListFragment offerListFragment = (OfferListFragment) getSupportFragmentManager().findFragmentByTag(OfferListFragment.TAG);
                offerListFragment.loadOffers(response);
            } else {
                OfferListFragment offerListFragment = new OfferListFragment();
                Bundle args = new Bundle();
                args.putParcelable(OfferListFragment.EXTRA_OFFERS, Parcels.wrap(response));
                offerListFragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.lytFyberFormFragmentContainer, offerListFragment, OfferListFragment.TAG).addToBackStack(OfferListFragment.TAG).commit();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() >= 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RestClient.getInstance().cancelRequest(RestClient.REQUEST_TAG);
    }
}
