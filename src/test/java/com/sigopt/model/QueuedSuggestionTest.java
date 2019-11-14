package com.sigopt.model;

import com.sigopt.model.APIResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class QueuedSuggestionTest extends APIResourceTestBase {
    String json;

    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() throws IOException {
        json = resource("queued_suggestion.json");
    }

    @Test
    public void constructFromJson() throws Exception {
        QueuedSuggestion s = APIResource.constructFromJson(json, QueuedSuggestion.class);

        assertEquals("1", s.getId());
        assertEquals(1.0, s.getAssignments().get("a"));
        assertEquals("c", s.getAssignments().get("b"));
        assertEquals("1", s.getExperiment());
        assertEquals(123, (Object) s.getCreated());
        assertEquals(1.0, s.getTask().getCost(), 1e-9);
    }
}
