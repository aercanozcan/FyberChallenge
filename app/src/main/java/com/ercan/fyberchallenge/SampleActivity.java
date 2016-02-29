package com.ercan.fyberchallenge;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.fyber.fybersdk.FyberResultListener;
import com.fyber.fybersdk.OfferWall;
import com.fyber.fybersdk.data.model.rest.Offer;

/**
 * Created by trial on 29/02/16.
 */
public class SampleActivity extends Activity {
    FyberResultListener fyberResultListener;
    Button btnOfferWall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        btnOfferWall = (Button) findViewById(R.id.btnOfferWall);

        fyberResultListener = new FyberResultListener() {
            @Override
            public void dismissWithButton() {
                Toast.makeText(SampleActivity.this, "Dismissed with button", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onOfferClicked(Offer offer) {
                Toast.makeText(SampleActivity.this, "Offer clicked = " + offer.getTitle(), Toast.LENGTH_SHORT).show();
            }
        };

        btnOfferWall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //example call with with apikey,appid and uid
                OfferWall.showOfferWall(SampleActivity.this, "1c915e3b5d42d05136185030892fbb846c278927", "2070", "player3", null, R.layout.btn_custom_layout);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fyberResultListener.onActivityResult(resultCode, data);
    }
}
