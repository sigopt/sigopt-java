package com.sigopt.model;

import com.sigopt.net.APIResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ExperimentTest extends APIResourceTestBase {
    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() {
    }

    @Test
    public void constructFromJson() throws Exception {
        String json = resource("experiment.json");
        Experiment exp = APIResource.constructFromJson(json, Experiment.class);

        assertEquals("1", exp.getId());
        assertEquals("offline", exp.getType());
        assertEquals("Ads Clickthrough Rate", exp.getName());

        assertTrue("Parameters is parsed when nested", exp.getParameters() instanceof List);
        assertNotNull(exp.getParameters());
        assertTrue(exp.getParameters().size() > 0);

        assertTrue("Metric is parsed when nested", exp.getMetric() instanceof Metric);
        assertNotNull(exp.getMetric());

        assertTrue("Cohorts is parsed when nested", exp.getCohorts() instanceof List);
        assertNotNull(exp.getCohorts());
        assertTrue(exp.getCohorts().size() > 0);
    }

}
