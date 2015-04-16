package com.sigopt.model;

import com.sigopt.net.APIResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ObservationTest extends APIResourceTestBase {
    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() {
    }

    @Test
    public void constructFromJson() throws Exception {
        String json = resource("observation.json");
        Observation obs = APIResource.constructFromJson(json, Observation.class);

        assertEquals(false, obs.isFailed());
        assertEquals(1.0, obs.getValue(), 1e-9);
        assertEquals(0.01, obs.getValueStddev(), 1e-9);
        assertTrue("Assignments is parsed when nested", obs.getAssignments() instanceof Map);
        assertEquals(2.0, (Double) obs.getAssignments().get("param1"), 1e-9);
        assertEquals("true", obs.getAssignments().get("param2"));
    }

}
