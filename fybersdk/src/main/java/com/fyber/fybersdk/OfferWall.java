package com.fyber.fybersdk;

import android.app.Activity;
import android.content.Intent;
import android.provider.Settings;
import android.widget.Toast;

import com.example.fybersdk.R;
import com.fyber.fybersdk.data.model.RequestParam;
import com.fyber.fybersdk.data.model.rest.ResponseEnvelope;
import com.fyber.fybersdk.data.rest.RestClient;
import com.fyber.fybersdk.util.CommonUtils;
import com.fyber.fybersdk.util.Sha1;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.parceler.Parcels;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by trial on 29/02/16.
 */
public class OfferWall {


    private OfferWall() {
    }//static access only

    /**
     * This method will launch another activity and show offerwallFragment in it.
     * The caller Activity should call {@link FyberResultListener#onActivityResult(int, Intent)} on  {@link Activity#onActivityResult(int, int, Intent)} method
     *
     * @param context
     * @param apiKey
     * @param appId
     * @param uId
     * @param pub0         can be null
     * @param customViewId can be -1 for showing default button
     */
    public static void showOfferWall(final Activity context, final String apiKey, String appId, String uId, String pub0, final int customViewId) {
        final List<RequestParam> params = new ArrayList();
        params.add(new RequestParam("appid", appId));
        if (pub0 != null) {
            if (!pub0.isEmpty()) {
                params.add(new RequestParam("pub0", pub0));
            }
        }

        params.add(new RequestParam("uid", uId));


        String android_id = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        params.add(new RequestParam("ip", CommonUtils.getIPAddress(true)));
        params.add(new RequestParam("locale", Locale.getDefault().getDisplayLanguage()));
        params.add(new RequestParam("timestamp", CommonUtils.getUnixTimeStamp()));
        params.add(new RequestParam("device_id", android_id));
        new Thread(new Runnable() {
            public void run() {
                try {
                    AdvertisingIdClient.Info adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                    params.add(new RequestParam("google_ad_id", adInfo.getId()));
                    params.add(new RequestParam("google_ad_id_limited_tracking_enabled", String.valueOf(adInfo.isLimitAdTrackingEnabled())));
                    RestClient.getInstance().getOffers(params, apiKey, new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            if (context == null)
                                return;
                            context.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(context, R.string.warn_get_offers_failed, Toast.LENGTH_SHORT).show();

                                }
                            });

                        }

                        @Override
                        public void onResponse(final Response response) throws IOException {

                            if (response.code() == HttpURLConnection.HTTP_OK) {

                                String reponseBody = new String(response.body().string());
                                String hashkey = new String(response.header(RestClient.HASHKEY_HEADER));

                                if (!Sha1.getHash(reponseBody + apiKey).equalsIgnoreCase(hashkey)) {// check hashkey
                                    onFailure(response.request(), new IOException("INVALID HASHKEY"));
                                    return;
                                } else {
                                    final ResponseEnvelope responseEnvelope = new Gson().fromJson(reponseBody, ResponseEnvelope.class);
                                    //Starts the offerwall activity on success
                                    Intent intent = new Intent(context, OfferWallActivity.class);
                                    intent.putExtra(OfferWallActivity.EXTRA_OFFERS, Parcels.wrap(responseEnvelope));
                                    intent.putExtra(OfferWallActivity.EXTRA_CUSTOM_VIEW_ID, customViewId);
                                    context.startActivityForResult(intent, 0);//since it has ony one purpose no need to handle the "code" parameter

                                }

                            } else {
                                //Something went wrong... blame the backend quickly!
                            }

                        }


                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

    /**
     * @param context
     * @param apiKey
     * @param appId
     * @param uId
     * @param pub0
     */
    public static void showOfferWall(final Activity context, final String apiKey, String appId, String uId, String pub0) {
        OfferWall.showOfferWall(context, apiKey, appId, uId, pub0, -1);

    }

    /**
     * @param context
     * @param apiKey
     * @param appId
     * @param uId
     */
    public static void showOfferWall(final Activity context, final String apiKey, String appId, String uId) {
        OfferWall.showOfferWall(context, apiKey, appId, uId, null, -1);

    }
}
