package com.sigopt.model;

import com.sigopt.net.APIResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CohortTest extends APIResourceTestBase {
    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() {
    }

    @Test
    public void constructFromJson() throws Exception {
        String json = resource("cohort.json");
        Cohort exp = APIResource.constructFromJson(json, Cohort.class);

        assertEquals("1", exp.getId());
        assertEquals("control", exp.getName());
        assertEquals(0.75, exp.getAllocation(), 1e-9);

        assertTrue("Suggestion is parsed when nested", exp.getSuggestion() instanceof Suggestion);
        assertNotNull(exp.getSuggestion());

        assertEquals((Integer) 500, exp.getSuccesses());
        assertEquals((Integer) 900, exp.getAttempts());
        assertEquals("active", exp.getState());
    }



}
