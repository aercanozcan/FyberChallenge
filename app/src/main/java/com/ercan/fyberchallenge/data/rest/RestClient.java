package com.ercan.fyberchallenge.data.rest;

import android.util.Log;

import com.ercan.fyberchallenge.data.model.RequestParam;
import com.ercan.fyberchallenge.util.CommonUtils;
import com.squareup.okhttp.OkHttpClient;

import java.util.List;

/**
 * Created by Ercan on 2/10/2016.
 */
public class RestClient {

    public static final String FIELD_HASHKEY = "hashkey";
    public static final String FORMAT_TYPE_JSON = ".json";
    public static final String OFFERS = "offers";
    public static final String API_ROOT = "http://api.fyber.com/feed/v1/" + OFFERS + FORMAT_TYPE_JSON;

    private final OkHttpClient client = new OkHttpClient();


    public static String buildRequestUrl(List<RequestParam> params, String apiKey){

        String parameterString = CommonUtils.stringfyRequestParams(params);
        Log.e("TEST", CommonUtils.generateHashKey(parameterString,apiKey));
        return API_ROOT + "?" + parameterString + FIELD_HASHKEY+ "=" + CommonUtils.generateHashKey(parameterString,apiKey);

    }




}
