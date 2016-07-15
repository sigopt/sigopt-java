package com.sigopt.model;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AssignmentsTest extends APIResourceTestBase {
    String json;
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUpMockData() throws IOException {
        json = resource("assignments.json");
    }

    @Test
    public void getValues() throws Exception {
        Assignments assignments = APIResource.constructFromJson(json, Assignments.class);

        assertEquals(0.2, assignments.getDouble("a"), 0.00001);
        assertEquals(1, assignments.getInteger("b"));
        assertEquals("foo", assignments.getString("c"));
    }

    @Test
    public void throwsNullPointerExceptionDouble() throws Exception {
        Assignments assignments = APIResource.constructFromJson(json, Assignments.class);
        thrown.expect(NullPointerException.class);
        assignments.getDouble("d");
    }

    @Test
    public void throwsNullPointerExceptionInteger() throws Exception {
        Assignments assignments = APIResource.constructFromJson(json, Assignments.class);
        thrown.expect(NullPointerException.class);
        assignments.getInteger("d");
    }

    @Test
    public void noNullPointerExceptionString() throws Exception {
        Assignments assignments = APIResource.constructFromJson(json, Assignments.class);
        thrown.expect(NullPointerException.class);
        assignments.getString("d");
    }

    @Test
    public void throwsClassCastExceptionDouble() throws Exception {
        Assignments assignments = APIResource.constructFromJson(json, Assignments.class);
        thrown.expect(ClassCastException.class);
        assignments.getDouble("c");
    }

    @Test
    public void throwsClassCastExceptionInteger() throws Exception {
        Assignments assignments = APIResource.constructFromJson(json, Assignments.class);
        thrown.expect(ClassCastException.class);
        assignments.getInteger("c");
    }

    @Test
    public void throwsClassCastExceptionString() throws Exception {
        Assignments assignments = APIResource.constructFromJson(json, Assignments.class);
        thrown.expect(ClassCastException.class);
        assignments.getString("a");
    }
}
