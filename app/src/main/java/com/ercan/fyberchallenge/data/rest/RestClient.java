package com.ercan.fyberchallenge.data.rest;

import com.ercan.fyberchallenge.data.model.RequestParam;
import com.ercan.fyberchallenge.util.CommonUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;

import java.util.List;

/**
 * Created by Ercan on 2/10/2016.
 */
public class RestClient {

    public static final String FIELD_HASHKEY = "hashkey";
    public static final String FORMAT_TYPE_JSON = ".json";
    public static final String OFFERS = "offers";
    public static final String API_ROOT = "http://api.fyber.com/feed/v1/" + OFFERS + FORMAT_TYPE_JSON;
    public static final String REQUEST_TAG = "offersTag";
    public static RestClient instance;

    private final OkHttpClient client = new OkHttpClient();

    private RestClient(){
    }

    public static RestClient getInstance(){
        if(instance == null)
            instance = new RestClient();
        return instance;
    }

    public static String buildRequestUrl(List<RequestParam> params, String apiKey){

        String parameterString = CommonUtils.stringfyRequestParams(params);
        return API_ROOT + "?" + parameterString + FIELD_HASHKEY+ "=" + CommonUtils.generateHashKey(parameterString,apiKey);

    }

    public void getOffers(List<RequestParam> params, String apiKey,Callback callback){
        Request request = new Request.Builder()
                .url(RestClient.buildRequestUrl(params, apiKey)).tag(REQUEST_TAG)
                .build();
        client.newCall(request).enqueue(callback);

    }

    public void cancelRequest(String tag) {
        client.cancel(tag);
    }




}
