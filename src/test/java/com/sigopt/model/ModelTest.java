package com.sigopt.model;

import com.sigopt.model.APIResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ModelTest {
    @Test
    public void testEquality() throws Exception {
        Experiment e = new Experiment();
        Experiment e2 = new Experiment();
        Bounds b = new Bounds();

        assertEquals(e, e);
        assertEquals(e, e2);
        assertNotEquals(e, new HashMap<String, Object>());
        assertNotEquals(new HashMap<String, Object>(), e);
        assertNotEquals(e, b);

        assertEquals(new Experiment.Builder().name("a").build(), new Experiment.Builder().name("a").build());
        assertNotEquals(e, new Experiment.Builder().name("a").build());
        assertNotEquals(e, new Experiment.Builder().name(null).build());
        assertNotEquals(new Experiment.Builder().name("a").build(), new Experiment.Builder().name("b").build());
        assertNotEquals(
            new Experiment.Builder().name("a").build(),
            new Experiment.Builder().name("a").id("3").build()
        );
    }

    @Test
    public void testNulls() throws Exception {
        Experiment e = new Experiment();
        assertEquals(e.getId(), null);
        assertEquals(e.getProgress(), null);
        assertEquals(e.getMetadata(), null);
        Experiment e2 = new Experiment.Builder().build();
        assertEquals(e2.getId(), null);
        assertEquals(e2.getProgress(), null);
        assertEquals(e2.getMetadata(), null);
        Experiment e3 = new Experiment.Builder().id(null).progress(null).metadata(null).build();
        assertEquals(e3.getId(), null);
        assertEquals(e3.getProgress(), null);
        assertEquals(e3.getMetadata(), null);
    }

    @Test
    public void testJson() throws Exception {
        assertEquals(
            "{\"bounds\":{\"min\":3.0}}",
            new Parameter.Builder().bounds(new Bounds.Builder().min(3.0).build()).build().toJson()
        );
        assertEquals(new Experiment().toJson(), "{}");
        assertEquals(new Experiment.Builder().build().toJson(), "{}");
        assertEquals(new Experiment.Builder().name(null).build().toJson(), "{\"name\":null}");
        Map<String, Object> assignments = new HashMap<String, Object>();
        assignments.put("a", 1);
        assignments.put("b", "c");
        assertEquals(
            new Observation.Builder().assignments(assignments).build().toJson(),
            "{\"assignments\":{\"a\":1,\"b\":\"c\"}}"
        );
    }
}
