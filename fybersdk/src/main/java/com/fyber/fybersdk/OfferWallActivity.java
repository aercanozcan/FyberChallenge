package com.fyber.fybersdk;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fybersdk.R;
import com.fyber.fybersdk.data.model.rest.Offer;
import com.fyber.fybersdk.fragment.OfferListFragment;
import com.fyber.fybersdk.fragment.OnItemClickCallback;

import org.parceler.Parcels;

/**
 * An activity that contains OfferListFragment with some activity result callbacks.
 */
public class OfferWallActivity extends AppCompatActivity implements OnItemClickCallback {

    public static final String EXTRA_OFFERS = "offers";
    public static final String EXTRA_CUSTOM_VIEW_ID = "customViewId";
    public static final String RESULT_OFFER = "offer";
    public static int DISMISSED_BY_BUTTON = 0;
    public static int DISMISSED_BY_ITEM_CLICK = 1;

    private View btnExit;

    private ViewGroup btnContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_wall_activiy);
        btnExit = findViewById(R.id.btnExit);
        btnContainer = (ViewGroup) findViewById(R.id.btnContainer);
        int customViewId = getIntent().getIntExtra(EXTRA_CUSTOM_VIEW_ID, -1);

        if (customViewId == -1) {
            btnExit.setOnClickListener(new DismissClickListener());
        } else {
            btnContainer.removeAllViews();
            btnExit = LayoutInflater.from(this).inflate(customViewId, btnContainer, true);
            btnContainer.getChildAt(0).setOnClickListener(new DismissClickListener());

        }


        OfferListFragment offerListFragment = new OfferListFragment();

        //passing the same parameters to fragment.
        Bundle args = new Bundle();


        //Loading the fragment
        args.putParcelable(OfferListFragment.EXTRA_OFFERS, getIntent().getParcelableExtra(EXTRA_OFFERS));
        offerListFragment.setArguments(args);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, offerListFragment, OfferListFragment.TAG)
                .commit();


        btnExit.setOnClickListener(new DismissClickListener());
    }

    @Override
    public void onItemclick(Offer offer) {
        //Set the result and dismiss the activity
        Intent intent = new Intent();
        intent.putExtra(RESULT_OFFER, Parcels.wrap(offer));
        setResult(DISMISSED_BY_ITEM_CLICK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        //only dismiss by X button or clicking an offer
    }

    class DismissClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            setResult(DISMISSED_BY_BUTTON);//notify activity that activity dismissed by the exit button
            finish();
        }
    }
}
