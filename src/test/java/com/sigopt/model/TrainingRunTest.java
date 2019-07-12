package com.sigopt.model;

import com.sigopt.model.APIResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;


public class TrainingRunTest extends APIResourceTestBase {
    String json;

    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() throws IOException {
        json = resource("trainingRun.json");
    }

    @Test
    public void constructFromJson() throws Exception {
        TrainingRun tr = APIResource.constructFromJson(json, TrainingRun.class);

        assertEquals("1247", tr.getId());
        assertEquals(1562883201, (Object) tr.getCreated());
        assertEquals(1562883201, (Object) tr.getUpdated());
        assertNull(tr.getMetadata());
        assertFalse(tr.getFinished());
        assertEquals(0, (Object) tr.getCheckpointCount());
        assertNull(tr.getObservation());
        assertEquals("24801162", tr.getSuggestion());
    }
}
