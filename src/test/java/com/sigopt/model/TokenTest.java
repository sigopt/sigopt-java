package com.sigopt.model;

import com.sigopt.model.APIResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TokenTest extends APIResourceTestBase {
    String json;

    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() throws IOException {
        json = resource("token.json");
    }

    @Test
    public void constructFromJson() throws Exception {
        Token t = APIResource.constructFromJson(json, Token.class);

        assertFalse(t.getAllExperiments());
        assertTrue(t.getDevelopment());
        assertEquals(2, (Object) t.getPermissions());
        assertEquals("123", t.getToken());
        assertEquals("456", t.getClientId());
        assertEquals("1", t.getExperiment());
        assertEquals("789", t.getUser());
    }
}
