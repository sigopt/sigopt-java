package com.sigopt.model;

import com.sigopt.model.APIResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CheckpointTest extends APIResourceTestBase {
    String json;

    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() throws IOException {
        json = resource("checkpoint.json");
    }

    @Test
    public void constructFromJson() throws Exception {
        Checkpoint chk = APIResource.constructFromJson(json, Checkpoint.class);

        assertEquals("12053", chk.getId());
        assertEquals(1562883203, (Object) chk.getCreated());
        assertNull(chk.getMetadata());
        assertFalse(chk.getShouldStop());
        assertNotNull(chk.getStoppingReasons());
        assertEquals("1247", chk.getTrainingRun());
        assertEquals(1.0, (Object) chk.getValues().get(0).getValue());
        assertEquals("Validation Accuracy", chk.getValues().get(0).getName());
        assertEquals(false, chk.getStoppingReasons().get("test"));
    }
}
