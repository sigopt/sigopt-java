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
import static org.junit.Assert.assertTrue;

public class ParamsBuilderTest {
    HashMap<String, Object> params;

    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() {
        params = new HashMap<String, Object>();
        params.put("age", 100);
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
