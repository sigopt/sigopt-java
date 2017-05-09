package com.sigopt.net;

import com.sigopt.model.APIResource;
import com.sigopt.model.MockResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ParamsBuilderTest {
    HashMap<String, Object> params;

    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() {
        params = new HashMap<String, Object>();
    }

    @Test
    public void buildSimple() throws Exception {
        params.put("age", 100);
        params.put("name", "sigopt");
        params.put("is_cool", true);
        params.put("null_key", null);
        Map<String, Object> actual = ParamsBuilder.build(params);
        assertEquals("100", actual.get("age"));
        assertEquals("sigopt", actual.get("name"));
        assertEquals("true", actual.get("is_cool"));
        assertFalse(actual.containsKey("null_key"));
    }

    @Test
    public void buildWithData() throws Exception {
        MockResource mockResource = new MockResource("id-10", "some-name-here");
        params.put("data", mockResource);
        Map<String, Object> actual = ParamsBuilder.build(params);
        assertEquals(mockResource.toJson(), actual.get("data"));
    }

    @Test
    public void buildWithJson() throws Exception {
        MockResource mockResource = new MockResource("id-10", "some-name-here");
        params.put("data", mockResource);
        Map<String, Object> actual = ParamsBuilder.build(params);
        String dataString = ((String) actual.get("data"));
        assertTrue("superAwesomeName serialized as underscore case", dataString.indexOf("super_awesome_name") >= 0);
    }
}
