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

public class ExperimentTest extends APIResourceTestBase {
    String json;

    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() throws IOException {
        json = resource("experiment.json");
    }

    @Test
    public void constructFromJson() throws Exception {
        Experiment exp = APIResource.constructFromJson(json, Experiment.class);

        assertEquals("123", exp.getId());
        assertTrue(exp.getDevelopment());
        assertEquals("cross_validated", exp.getType());
        assertEquals(5, (Object) exp.getFolds());
        assertEquals("Test Experiment", exp.getName());
        assertEquals(321, (Object) exp.getCreated());
        assertEquals("active", exp.getState());
        assertEquals("678", exp.getClient());
        assertEquals(51, (Object) exp.getObservationBudget());

        assertNotNull(exp.getMetrics());
        assertEquals(2, exp.getMetrics().size());
        assertEquals("Revenue", exp.getMetrics().get(0).getName());
        assertEquals("Sales", exp.getMetrics().get(1).getName());

        assertNotNull(exp.getProgress());
        assertEquals(3, (Object) exp.getProgress().getObservationCount());
        assertNotNull(exp.getProgress().getFirstObservation());
        assertEquals("1", exp.getProgress().getFirstObservation().getId());
        assertEquals(1.0, exp.getProgress().getFirstObservation().getAssignments().get("a"));
        assertEquals("c", exp.getProgress().getFirstObservation().getAssignments().get("b"));
        assertEquals(3.1, exp.getProgress().getFirstObservation().getValue(), 1e-9);
        assertNull(exp.getProgress().getFirstObservation().getValueStddev());
        assertFalse(exp.getProgress().getLastObservation().isFailed());
        assertEquals(451, (Object) exp.getProgress().getFirstObservation().getCreated());
        assertEquals("11", exp.getProgress().getFirstObservation().getSuggestion());
        assertEquals("123", exp.getProgress().getFirstObservation().getExperiment());
        assertNotNull(exp.getProgress().getLastObservation());
        assertEquals("2", exp.getProgress().getLastObservation().getId());
        assertEquals(2.0, exp.getProgress().getLastObservation().getAssignments().get("a"));
        assertEquals("d", exp.getProgress().getLastObservation().getAssignments().get("b"));
        assertEquals(3.1, exp.getProgress().getLastObservation().getValue(), 1e-9);
        assertEquals(0.5, exp.getProgress().getLastObservation().getValueStddev(), 1e-9);
        assertFalse(exp.getProgress().getLastObservation().isFailed());
        assertEquals(452, (Object) exp.getProgress().getLastObservation().getCreated());
        assertEquals("12", exp.getProgress().getLastObservation().getSuggestion());
        assertEquals("123", exp.getProgress().getLastObservation().getExperiment());
        assertNotNull(exp.getProgress().getBestObservation());
        assertEquals("3", exp.getProgress().getBestObservation().getId());
        assertEquals(3.0, exp.getProgress().getBestObservation().getAssignments().get("a"));
        assertEquals("d", exp.getProgress().getBestObservation().getAssignments().get("b"));
        assertNull(exp.getProgress().getBestObservation().getValue());
        assertNull(exp.getProgress().getBestObservation().getValueStddev());
        assertTrue(exp.getProgress().getBestObservation().isFailed());
        assertEquals(453, (Object) exp.getProgress().getBestObservation().getCreated());
        assertEquals("13", exp.getProgress().getBestObservation().getSuggestion());
        assertEquals("123", exp.getProgress().getBestObservation().getExperiment());
        assertEquals("def", exp.getProgress().getBestObservation().getMetadata().get("abc"));
        assertEquals(123.0, exp.getProgress().getBestObservation().getMetadata().get("ghi"));

        assertNotNull(exp.getParameters());
        assertEquals(2, exp.getParameters().size());
        assertEquals("a", exp.getParameters().get(0).getName());
        assertEquals("double", exp.getParameters().get(0).getType());
        assertEquals(1, exp.getParameters().get(0).getBounds().getMin(), 1e-9);
        assertEquals(2, exp.getParameters().get(0).getBounds().getMax(), 1e-9);
        assertNull(exp.getParameters().get(0).getCategoricalValues());
        assertEquals("b", exp.getParameters().get(1).getName());
        assertEquals("categorical", exp.getParameters().get(1).getType());
        assertNull(exp.getParameters().get(1).getBounds());
        assertEquals(2, exp.getParameters().get(1).getCategoricalValues().size());
        assertEquals("c", exp.getParameters().get(1).getCategoricalValues().get(0).getName());
        assertEquals("d", exp.getParameters().get(1).getCategoricalValues().get(1).getName());

        assertEquals("def", exp.getMetadata().get("abc"));
        assertEquals(123.0, exp.getMetadata().get("ghi"));
    }
}
