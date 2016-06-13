package com.sigopt.net;

import com.sigopt.Sigopt;
import com.sigopt.exception.APIException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
    MapHelper.class,
    PathBuilder.class,
    ParamsBuilder.class,
    HeadersBuilder.class,
    Requester.class
})
public class APIMethodTest {
    Map<String, Object> params;
    Map<String, String> headers;
    Object obj;
    String clientToken;
    String apiBase;

    @BeforeClass
    public static void setUp() {
        Sigopt.clientToken = "client-token";
    }

    @Before
    public void setUpMockData() {
        params = new HashMap<String, Object>();
        headers = new HashMap<String, String>();
        obj = new Object();
        clientToken = "new-client-token";
        apiBase = "new-api-base";
    }

    @Test
    public void constructorWithoutAnyOptionals() throws Exception {
        APIMethod method = new APIMethod("get", "/path", null, null, null, null, null, null);

        assertEquals("client-token", method.clientToken);
        assertEquals(Sigopt.apiBase, method.apiBase);
        assertEquals("get", method.method);
        assertEquals("/path", method.path);
        assertTrue("Params should be a map.", method.params instanceof Map);
        assertTrue("Params should be non null.", method.params != null);
        assertTrue("Headers should be a map.", method.headers instanceof Map);
        assertTrue("Headers should be non null.", method.headers != null);
    }

    @Test
    public void constructorWithOptionals() throws Exception {
        APIMethod method = new APIMethod("get", "/path", params, headers, obj, clientToken, apiBase);

        assertEquals(clientToken, method.clientToken);
        assertEquals(apiBase, method.apiBase);
        assertEquals("get", method.method);
        assertEquals("/path", method.path);
        assertTrue("Params should be a map.", method.params instanceof Map);
        assertTrue("Params should be non null.", method.params != null);
        assertTrue("Headers should be a map.", method.headers instanceof Map);
        assertTrue("Headers should be non null.", method.headers != null);
    }

    @Test
    public void constructorCallsParamsBuilder() throws Exception {
        Map<String, Object> expected = new HashMap<String, Object>();
        expected.put("dog", "dog-value");

        PowerMockito.mockStatic(ParamsBuilder.class);
        PowerMockito.when(ParamsBuilder.build(Mockito.anyMapOf(String.class, Object.class), Mockito.anyString(), Mockito.anyString())).thenReturn(expected);

        APIMethod method = new APIMethod("get", "/path", params, headers, obj, clientToken, apiBase);

        assertTrue("Params should be a map.", method.params instanceof Map);
        assertEquals(expected, method.params);
    }

    @Test
    public void constructorCallsHeadersBuilder() throws Exception {
        Map<String, String> expected = new HashMap<String, String>();
        expected.put("dog", "dog-value");

        PowerMockito.mockStatic(HeadersBuilder.class);
        PowerMockito.when(HeadersBuilder.build(Mockito.anyMapOf(String.class, String.class))).thenReturn(expected);

        APIMethod method = new APIMethod("get", "/path", params, headers, obj, clientToken, apiBase);

        assertTrue("Headers should be a map.", method.headers instanceof Map);
        assertEquals(expected, method.headers);
    }

    @Test
    public void constructorCallsPathBuilder() throws Exception {
        String expected = "/super/fake/path";

        PowerMockito.mockStatic(PathBuilder.class);
        PowerMockito.when(PathBuilder.build(Mockito.anyString(), Mockito.any(), Mockito.anyMap())).thenReturn(expected);

        APIMethod method = new APIMethod("get", "/path", params, headers, obj, clientToken, apiBase);

        assertEquals(expected, method.path);
    }

    @Test
    public void execute() throws Exception {
        Requester.Response response = new Requester.Response("{\"fake\": \"body\"}", 200);
        PowerMockito.mockStatic(Requester.class);
        PowerMockito.when(Requester.request(Mockito.anyString(), Mockito.anyString(), Mockito.anyMap(), Mockito.anyMap())).thenReturn(response);

        APIMethod method = new APIMethod("get", "/path", params, headers, obj, clientToken, apiBase);
        method.execute();

        assertEquals(response, method.response);
    }

    @Test
    public void executeWithErrorCode() throws Exception {
        Requester.Response response = new Requester.Response("{\"fake\": \"body\"}", 400);
        PowerMockito.mockStatic(Requester.class);
        PowerMockito.when(Requester.request(Mockito.anyString(), Mockito.anyString(), Mockito.anyMap(), Mockito.anyMap())).thenReturn(response);

        APIMethod method = new APIMethod("get", "/path", params, headers, obj, clientToken, apiBase);

        try {
            method.execute();
            assertFalse("Exception was expected.", true);
        } catch(APIException e) {
            assertEquals(method.exception, e);
            assertEquals(e.getApiMethod(), method);
        }
    }

}
