package com.sigopt.model;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class APIResourceTest {
    String mockResourceJson;


    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() {
        mockResourceJson = "{\n" +
            "      \"id\": \"id-value\",\n" +
            "      \"super_awesome_name\": \"name-value\"\n" +
            "}";
    }

    public String createJsonArray(String ...jsonObjects) {
        String ret = "[";
        for(String obj : jsonObjects) {
            ret = ret + obj + ",";
        }
        ret = ret.substring(0, ret.length() - 1);
        ret = ret + "]";
        return ret;
    }

    @Test
    public void constructResourceFromJson() throws Exception {
        MockResource actual = APIResource.constructFromJson(mockResourceJson, MockResource.class);
        assertEquals("id-value", actual.getId());
        assertEquals("name-value", actual.getSuperAwesomeName());
        assertTrue(actual instanceof MockResource);
    }
}
