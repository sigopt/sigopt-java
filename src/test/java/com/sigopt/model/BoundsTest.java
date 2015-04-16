package com.sigopt.model;

import com.sigopt.net.APIResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoundsTest extends APIResourceTestBase {
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
        Bounds bounds = exp.getParameters().get(0).getBounds();

        assertEquals(0, bounds.getMin(), 1e-9);
        assertEquals(10, bounds.getMax(), 1e-9);
    }

}
