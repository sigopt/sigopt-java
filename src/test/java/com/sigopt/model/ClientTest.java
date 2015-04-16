package com.sigopt.model;

import com.sigopt.net.APIResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClientTest extends APIResourceTestBase {
    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() {
    }

    @Test
    public void constructFromJson() throws Exception {
        String json = resource("client.json");
        Client client = APIResource.constructFromJson(json, Client.class);

        assertEquals("1", client.getId());
        assertEquals("SigOpt", client.getName());
    }

}
