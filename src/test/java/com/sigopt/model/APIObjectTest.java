package com.sigopt.model;

import com.sigopt.model.APIResource;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class APIObjectTest extends APIResourceTestBase {
    String json;

    @Before
    public void setUpMockData() throws IOException {
        json = resource("experiment.json");
    }

    @Test
    public void testGet() throws Exception {
        Experiment exp = APIResource.constructFromJson(json, Experiment.class);

        // Simple case
        assertEquals(exp.getId(), exp.get("id"));

        // nulls
        assertNull(exp.getMetric());
        assertNull(exp.get("metric"));

        // snake_case -> camelCase
        assertEquals((double) exp.getObservationBudget(), exp.get("observation_budget"));
    }

    @Test
    public void testSet() throws Exception {
        assertEquals(
            (new Experiment.Builder()).id("1").build(),
            (new Experiment.Builder()).set("id", "1").build()
        );
        assertEquals(
            (new Experiment.Builder()).observationBudget(23).build(),
            (new Experiment.Builder()).set("observation_budget", 23).build()
        );
    }
}
