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

public class SuggestionTest extends APIResourceTestBase {
    String json;

    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() throws IOException {
        json = resource("suggestion.json");
    }

    @Test
    public void constructFromJson() throws Exception {
        Suggestion s = APIResource.constructFromJson(json, Suggestion.class);

        assertEquals("1", s.getId());
        assertEquals(1.0, s.getAssignments().get("a"));
        assertEquals("c", s.getAssignments().get("b"));
        assertEquals("open", s.getState());
        assertEquals("1", s.getExperiment());
        assertEquals(123, (Object) s.getCreated());
        assertEquals("def", s.getMetadata().get("abc"));
        assertEquals(123.0, s.getMetadata().get("ghi"));
        assertEquals(1.0, s.getTask().getCost(), 1e-9);
    }
}
