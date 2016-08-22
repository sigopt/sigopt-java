package com.sigopt.net;

import com.sigopt.Sigopt;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HeadersBuilderTest {
    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() {
    }

    @Test
    public void buildWithExistingHeaders() throws Exception {
        Map<String, String> existing = new HashMap<String, String>();
        existing.put("dog", "dog-value");
        Map<String, String> actual = HeadersBuilder.build(existing, "actual-api-key");

        assertTrue(actual.containsKey("Authorization"));
        assertTrue(actual.containsKey("User-Agent"));
        assertEquals("dog-value", actual.get("dog"));
    }

    @Test
    public void buildWithNullHeaders() throws Exception {
        Map<String, String> actual = HeadersBuilder.build(null, "actual-api-key");

        assertTrue(actual.containsKey("Authorization"));
        assertTrue(actual.containsKey("User-Agent"));
    }

    @Test
    public void buildWithApiKey() throws Exception {
        Map<String, String> actual = HeadersBuilder.build(new HashMap<String, String>(), "actual-api-key");
        assertTrue(actual.containsKey("Authorization"));
    }

    @Test
    public void buildWithKeys() throws Exception {
        Map<String, String> actual = HeadersBuilder.build(new HashMap<String, String>(), "actual-api-key", "AuthToken");

        assertEquals(actual.get("AuthToken"), "actual-api-key");
    }

    @Test
    public void defaultHeaders() throws Exception {
        Map<String, String> actual = HeadersBuilder.defaultHeaders();
        assertTrue("Default headers should contain the API Version.", actual.get("User-Agent").contains("Sigopt/" + Sigopt.apiVersion));
        assertTrue("Default headers should contain the Library Version.", actual.get("User-Agent").contains("JavaBindings/" + Sigopt.VERSION));
    }

    @Test
    public void customAuthHeader() throws Exception {
        Map<String, String> actual = HeadersBuilder.customAuthHeader("AuthToken", "actual-auth-value");
        assertEquals("actual-auth-value", actual.get("AuthToken"));
    }

    @Test
    public void basicAuthHeaderWithKey() throws Exception {
        Map<String, String> headers = HeadersBuilder.basicAuthHeader("api-key");
        String actual = headers.get("Authorization");
        assertEquals("Basic ", actual.substring(0, 6));
        String decoded = new String(Base64.decodeBase64(actual.substring(6)));

        assertEquals("api-key:", decoded);
    }




}
