package com.sigopt.model;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class AssignmentsTest extends APIResourceTestBase {
    String json;

    @Before
    public void setUpMockData() throws IOException {
        json = resource("assignments.json");
    }

    @Test
    public void getValues() throws Exception {
        Assignments assignments = APIResource.constructFromJson(json, Assignments.class);

        assertEquals(0.2, assignments.getDouble("a"), 0.00001);
        assertEquals(1, assignments.getInteger("b"));
        assertEquals("foo", assignments.getCategorical("c"));
    }
}
