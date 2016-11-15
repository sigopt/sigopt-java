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

public class BestAssignmentsTest extends APIResourceTestBase {
    String json;

    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() throws IOException {
        json = resource("bestAssignments.json");
    }

    @Test
    public void constructFromJson() throws Exception {
        BestAssignments b = APIResource.constructFromJson(json, BestAssignments.class);

        assertEquals(5.0, (Object) b.getValue());
        assertEquals(0.5, (Object) b.getValueStddev());
        assertEquals(1, b.getAssignments().getInteger("a"));
        assertEquals("c", b.getAssignments().get("b"));
    }
}
