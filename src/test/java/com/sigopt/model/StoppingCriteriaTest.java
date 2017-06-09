package com.sigopt.model;

import com.sigopt.model.APIResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class StoppingCriteriaTest extends APIResourceTestBase {
    String json;

    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() throws IOException {
        json = resource("stoppingCriteria.json");
    }

    @Test
    public void constructFromJson() throws Exception {
        StoppingCriteria b = APIResource.constructFromJson(json, StoppingCriteria.class);

        assertEquals(true, b.getShouldStop());
        assertNotNull(b.getReasons());
        assertTrue(b.getReasons().contains("observation_budget_reached"));
        assertTrue(b.getReasons().contains("possible_stagnation"));
    }
}
