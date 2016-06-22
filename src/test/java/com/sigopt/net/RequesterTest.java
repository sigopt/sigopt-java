package com.sigopt.net;

import com.squareup.okhttp.Request;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class RequesterTest {
    @Test
    public void createRequestGet() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("dog", "dog-value");
        Request actual = Requester.createRequest("get", "https://www.test.com", params, new HashMap<String, String>(), "");

        // Make sure we url encode params.
        String split[] = actual.url().toString().split("[?&]");
        String expected[] = new String[]{"dog=dog-value"};
        for(String exp : expected) {
            boolean found = false;
            for (String act : split) {
                if (act.equals(exp)) {
                    found = true;
                    break;
                }
            }
            assertTrue("Didn't find " + exp, found);
        }
    }

    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMock() {
    }
}
