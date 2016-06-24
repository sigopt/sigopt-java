package com.sigopt.model;

import com.sigopt.model.APIResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class ModelTest {
    @Test
    public void testStructEquality() throws Exception {
        Experiment e = new Experiment();
        Experiment e2 = new Experiment();
        Bounds b = new Bounds();

        assertEquals(e, e);
        assertEquals(e, e2);
        assertNotEquals(e, new HashMap<String, Object>());
        assertNotEquals(new HashMap<String, Object>(), e);
        assertNotEquals(e, b);
        assertNotEquals(b, e);

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
    public void testMapEquality() throws Exception {
        Assignments a = new Assignments();
        Assignments a2 = new Assignments();
        Bounds b = new Bounds();

        // Spec indicates that Map implementations should compare equal
        // https://docs.oracle.com/javase/7/docs/api/java/util/Map.html
        assertEquals(a, a);
        assertEquals(a, a2);
        assertEquals(a, new HashMap<String, Object>());
        assertEquals(new HashMap<String, Object>(), a);
        assertNotEquals(a, b);
        assertNotEquals(b, a);

        assertEquals(new Assignments.Builder().set("a", "1").build(), new Assignments.Builder().set("a", "1").build());
        assertNotEquals(a, new Assignments.Builder().set("a", "1").build());
        assertNotEquals(a, new Assignments.Builder().set("a", (String) null).build());
        assertNotEquals(new Assignments.Builder().set("a", "1").build(), new Assignments.Builder().set("a", "2").build());
        assertNotEquals(
            new Assignments.Builder().set("a", "1").build(),
            new Assignments.Builder().set("a", "1").set("b", "3").build()
        );
    }

    @Test
    public void testStructNulls() throws Exception {
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
    public void testMapNulls() throws Exception {
        Assignments a = new Assignments();
        assertEquals(a.get("a"), null);
        Assignments a2 = new Assignments.Builder().build();
        assertEquals(a2.get("a"), null);
        Assignments a3 = new Assignments.Builder().set("a", (String) null).build();
        assertEquals(a3.get("a"), null);
    }

    @Test
    public void testMapLike() throws Exception {
        Assignments a = new Assignments.Builder().set("a", "1").set("b", 5).build();
        Map<String, Object> b = new HashMap<String, Object>();
        b.put("a", "1");
        b.put("b", 5);

        assertEquals(a.containsKey("a"), b.containsKey("a"));
        assertEquals(a.containsKey("fake"), b.containsKey("fake"));
        assertEquals(a.containsValue("1"), b.containsValue("1"));
        assertEquals(a.containsValue("fake"), b.containsValue("fake"));
        assertEquals(a.entrySet(), b.entrySet());
        assertEquals(a, b);
        assertEquals(b, a);
        assertEquals(a.get("a"), b.get("a"));
        assertEquals(a.get("fake"), b.get("fake"));
        assertEquals(a.hashCode(), b.hashCode());
        assertEquals(a.isEmpty(), b.isEmpty());
        assertEquals(a.keySet(), b.keySet());
        assertEquals(a.size(), b.size());
        assertEquals(new HashSet<Object>(a.values()), new HashSet<Object>(b.values()));
    }

    @Test
    public void testStructJson() throws Exception {
        assertEquals(
            "{\"bounds\":{\"min\":3.0}}",
            new Parameter.Builder().bounds(new Bounds.Builder().min(3.0).build()).build().toJson()
        );
        assertEquals(new Experiment().toJson(), "{}");
        assertEquals(new Experiment.Builder().build().toJson(), "{}");
        assertEquals(new Experiment.Builder().name(null).build().toJson(), "{\"name\":null}");
    }

    @Test
    public void testMapJson() throws Exception {
        assertEquals(
            "{\"a\":\"1\"}",
            new Assignments.Builder().set("a", "1").build().toJson()
        );
        assertEquals(new Assignments().toJson(), "{}");
        assertEquals(new Assignments.Builder().build().toJson(), "{}");
        assertEquals(new Assignments.Builder().set("a", (String) null).build().toJson(), "{\"a\":null}");
        Map<String, Object> assignments = new HashMap<String, Object>();
        assignments.put("a", 1);
        assignments.put("b", "c");
        String observationJson = new Observation.Builder().assignments(assignments).build().toJson();
        assertTrue(
            "{\"assignments\":{\"a\":1,\"b\":\"c\"}}".equals(observationJson) ||
            "{\"assignments\":{\"b\":\"c\",\"a\":1}}".equals(observationJson)
        );

        String observation2Json = new Observation.Builder()
            .assignments(new Assignments.Builder()
                .set("a", "1")
                .build())
            .build()
            .toJson();
        assertEquals("{\"assignments\":{\"a\":\"1\"}}", observation2Json);
    }
}
