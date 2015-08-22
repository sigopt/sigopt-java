package com.sigopt.model;

import com.google.gson.reflect.TypeToken;
import com.sigopt.net.APIResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HistoryTest extends APIResourceTestBase {
    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() {
    }

    @Test
    public void constructFromJson() throws Exception {
        String json = resource("history.json");
        Type type = new TypeToken<Pagination<Observation>>() {}.getType();
        Pagination<Observation> observations = APIResource.constructListFromJson(json, type);

        assertEquals(1, observations.getCount());
        assertEquals("X", observations.getPaging().getBefore());
        assertEquals("Y", observations.getPaging().getAfter());
        assertEquals(1, observations.getData().size());
        assertEquals(false, observations.getData().get(0).isFailed());
    }

}
