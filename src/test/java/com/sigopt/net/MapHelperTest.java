package com.sigopt.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class MapHelperTest {
    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() {
    }


    @Test
    public void ensureWithNull() throws Exception {
        Map<String, Object> actual = MapHelper.ensure(null);
        assertEquals(new HashMap<String, Object>(), actual);
    }

    @Test
    public void ensureWithMap() throws Exception {
        Map<String, Object> expected = new HashMap<String, Object>();
        expected.put("test", "fake-data");
        Map<String, Object> actual = MapHelper.ensure(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void mergeWithNulls() throws Exception {
        Map<String, Object> actual = MapHelper.merge(null, null);
        assertEquals(new HashMap<String, Object>(), actual);
    }

    @Test
    public void mergeWithOneNull() throws Exception {
        Map<String, Object> expected = new HashMap<String, Object>();
        expected.put("test", "value");

        Map<String, Object> actual = MapHelper.merge(expected, null);
        assertEquals(expected, actual);

        actual = MapHelper.merge(null, expected);
        assertEquals(expected, actual);
    }

    @Test
    public void mergeWithTwoMaps() throws Exception {
        Map<String, Object> one = new HashMap<String, Object>();
        one.put("test", "test-value");
        one.put("overlap", "old-value");
        Map<String, Object> two = new HashMap<String, Object>();
        two.put("other", "other-value");
        two.put("overlap", "new-value");

        Map<String, Object> actual = MapHelper.merge(one, two);
        assertEquals("test-value", actual.get("test"));
        assertEquals("other-value", actual.get("other"));
        assertEquals("new-value", actual.get("overlap"));
    }

}
