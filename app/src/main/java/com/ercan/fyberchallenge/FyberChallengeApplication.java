package com.ercan.fyberchallenge;

import android.app.Application;

/**
 * Created by Ercan on 2/12/2016.
 */
public class FyberChallengeApplication extends Application {

    public static String API_KEY;

    public static String getApiKey() {
        return API_KEY;
    }

    public static void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }
}
