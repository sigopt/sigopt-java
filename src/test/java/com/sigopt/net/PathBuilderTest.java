package com.sigopt.net;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class PathBuilderTest {
    HashMap<String, String> mockParams;

    @Before
    public void setUpMockData() {
        mockParams = new HashMap<String, String>();
        mockParams.put("age", "100");
    }

    @Test
    public void buildWithNoValues() throws Exception {
        String expected = "/some/plain/path";
        String actual = PathBuilder.build(expected, mockParams);
        assertEquals(expected, actual);
    }

    @Test
    public void buildWithParamValues() throws Exception {
        String path = "/some/:age/path";
        String actual = PathBuilder.build(path, mockParams);
        assertEquals("/some/" + mockParams.get("age") + "/path", actual);
    }
}
