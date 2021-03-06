package com.ercan.fyberchallenge;

import android.app.Application;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;


import com.fyber.fybersdk.data.model.RequestParam;
import com.fyber.fybersdk.data.rest.RestClient;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ercan on 2/10/2016.
 */

@RunWith(AndroidJUnit4.class)
public class BuildUrlStringTest extends ApplicationTestCase<Application>  {

    public BuildUrlStringTest() {
        super(Application.class);

    }

    @Test
    public void checkUrl(){
        List<RequestParam> params = new ArrayList();
        //Sha1 generated by http://www.sha1-online.com/
        String expectedResult = "http://api.fyber.com/feed/v1/offers.json?appid=157&device_id=2b6f0cc904d137be2e1730235f5664094b831186&ip=212.45.111.17&locale=de&page=2&ps_time=1312211903&pub0=campaign2&timestamp=1312471066&uid=player1&hashkey=58eeddf43cfbf81bc307d44d7f9a805a31d94228";
        String realResult = "";
        String apiKey = "e95a21621a1865bcbae3bee89c4d4f84";
        params.add(new RequestParam("appid","157"));
        params.add(new RequestParam("uid","player1"));
        params.add(new RequestParam("ip","212.45.111.17"));
        params.add(new RequestParam("locale","de"));
        params.add(new RequestParam("device_id","2b6f0cc904d137be2e1730235f5664094b831186"));
        params.add(new RequestParam("ps_time","1312211903"));
        params.add(new RequestParam("pub0","campaign2"));
        params.add(new RequestParam("page","2"));
        params.add(new RequestParam("timestamp","1312553361"));

        realResult = RestClient.buildRequestUrl(params, apiKey);
        assertEquals("Invalid url", expectedResult, realResult);
    }
}
