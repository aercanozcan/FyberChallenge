package com.ercan.fyberchallenge;

import com.ercan.fyberchallenge.util.Sha1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ercan on 2/10/2016.
 */
public class HashKeyTest {


    @Test
    public void checkHashKeyRules(){
        String hashSeed="appid=157&device_id=2b6f0cc904d137be2e1730235f5664094b831186&ip=212.45.111.17&locale=de&page=2&ps_time=1312211903&pub0=campaign2&timestamp=1312471066&uid=player1&e95a21621a1865bcbae3bee89c4d4f84";
        String expectedResult ="58eeddf43cfbf81bc307d44d7f9a805a31d94228";
        String realResult = Sha1.getHash(hashSeed);
        assertEquals("Invalid HashKey",expectedResult,realResult);
    }
}
