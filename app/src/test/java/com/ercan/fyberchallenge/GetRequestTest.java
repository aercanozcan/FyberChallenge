package com.ercan.fyberchallenge;
import com.ercan.fyberchallenge.data.model.RequestParam;
import com.ercan.fyberchallenge.data.rest.RestClient;
import com.ercan.fyberchallenge.util.CommonUtils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ercan on 2/10/2016.
 */


public class GetRequestTest {

    @Test
    public void testReuest(){
        List<RequestParam> params = new ArrayList();
        String apiKey = "1c915e3b5d42d05136185030892fbb846c278927";
        params.add(new RequestParam("appid","2070"));
        params.add(new RequestParam("uid","spiderman"));
        params.add(new RequestParam("ip","109.235.143.113"));
        params.add(new RequestParam("locale","de"));
        params.add(new RequestParam("device_id","2b6f0cc904d137be2e1730235f5664094b831186"));

        params.add(new RequestParam("timestamp", ""+CommonUtils.getUnixTimeStamp()));
        RestClient.getInstance().getOffers(params, apiKey, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

                assertTrue("Request failed cause :" + e.getMessage(), false);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                assertTrue(response.body().toString(), response.code() == 200);
            }
        });

    }
}
