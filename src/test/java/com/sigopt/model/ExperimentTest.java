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
        assertEquals("Test Experiment", exp.getName());
        assertEquals(321, (Object) exp.getCreated());
        assertEquals(453, (Object) exp.getUpdated());
        assertEquals("active", exp.getState());
        assertEquals("678", exp.getClient());
        assertEquals("789", exp.getUser());
        assertEquals(51, (Object) exp.getObservationBudget());
        assertEquals(3, (Object) exp.getNumSolutions());
        assertEquals(2, (Object) exp.getParallelBandwidth());

        assertNotNull(exp.getLinearConstraints());
        assertEquals(1, exp.getLinearConstraints().size());
        assertEquals("greater_than", exp.getLinearConstraints().get(0).getType());
        assertEquals(5.0, (Object) exp.getLinearConstraints().get(0).getThreshold());
        assertNotNull(exp.getLinearConstraints().get(0).getTerms());
        assertEquals(1, exp.getLinearConstraints().get(0).getTerms().size());
        assertEquals("a", exp.getLinearConstraints().get(0).getTerms().get(0).getName());
        assertEquals(2.0, (Object) exp.getLinearConstraints().get(0).getTerms().get(0).getWeight());

        assertNotNull(exp.getConditionals());
        assertEquals(1, exp.getConditionals().size());
        assertEquals("num_hidden_layers", exp.getConditionals().get(0).getName());
        assertEquals(2, exp.getConditionals().get(0).getValues().size());
        assertEquals("1", exp.getConditionals().get(0).getValues().get(0));
        assertEquals("3", exp.getConditionals().get(0).getValues().get(1));

        assertNotNull(exp.getTasks());
        assertEquals(2, exp.getTasks().size());
        assertEquals("test_task_1", exp.getTasks().get(0).getName());
        assertEquals("test_task_2", exp.getTasks().get(1).getName());
        assertEquals(0.1, exp.getTasks().get(0).getCost(), 1e-9);
        assertEquals(1.0, exp.getTasks().get(1).getCost(), 1e-9);

        assertNotNull(exp.getMetrics());
        assertEquals(2, exp.getMetrics().size());
        assertEquals("Revenue", exp.getMetrics().get(0).getName());
        assertEquals(200.0, exp.getMetrics().get(0).getValueBaseline(), 1e-9);
        assertEquals("Sales", exp.getMetrics().get(1).getName());
        assertNull(exp.getMetrics().get(1).getValueBaseline());

        assertNotNull(exp.getProgress());
        assertEquals(3, (Object) exp.getProgress().getObservationCount());

        assertNotNull(exp.getProgress().getFirstObservation());
        assertEquals("1", exp.getProgress().getFirstObservation().getId());
        assertEquals(1.0, exp.getProgress().getFirstObservation().getAssignments().get("a"));
        assertEquals("c", exp.getProgress().getFirstObservation().getAssignments().get("b"));
        assertNotNull(exp.getProgress().getFirstObservation().getValues());
        assertEquals(2, exp.getProgress().getFirstObservation().getValues().size());
        assertEquals("Revenue", exp.getProgress().getFirstObservation().getValues().get(0).getName());
        assertEquals(3.1, exp.getProgress().getFirstObservation().getValues().get(0).getValue(), 1e-9);
        assertNull(exp.getProgress().getFirstObservation().getValues().get(0).getValueStddev());
        assertEquals("Sales", exp.getProgress().getFirstObservation().getValues().get(1).getName());
        assertEquals(2.1, exp.getProgress().getFirstObservation().getValues().get(1).getValue(), 1e-9);
        assertNull(exp.getProgress().getFirstObservation().getValues().get(1).getValueStddev());
        assertFalse(exp.getProgress().getFirstObservation().isFailed());
        assertEquals(451, (Object) exp.getProgress().getFirstObservation().getCreated());
        assertEquals("11", exp.getProgress().getFirstObservation().getSuggestion());
        assertEquals("123", exp.getProgress().getFirstObservation().getExperiment());

        assertNotNull(exp.getProgress().getLastObservation());
        assertEquals("2", exp.getProgress().getLastObservation().getId());
        assertEquals(2.0, exp.getProgress().getLastObservation().getAssignments().get("a"));
        assertEquals("d", exp.getProgress().getLastObservation().getAssignments().get("b"));
        assertEquals("Revenue", exp.getProgress().getLastObservation().getValues().get(0).getName());
        assertEquals(3.1, exp.getProgress().getLastObservation().getValues().get(0).getValue(), 1e-9);
        assertEquals(0.5, exp.getProgress().getLastObservation().getValues().get(0).getValueStddev(), 1e-9);
        assertEquals("Sales", exp.getProgress().getLastObservation().getValues().get(1).getName());
        assertEquals(2.1, exp.getProgress().getFirstObservation().getValues().get(1).getValue(), 1e-9);
        assertNull(exp.getProgress().getLastObservation().getValues().get(1).getValueStddev());
        assertFalse(exp.getProgress().getLastObservation().isFailed());
        assertEquals(452, (Object) exp.getProgress().getLastObservation().getCreated());
        assertEquals("12", exp.getProgress().getLastObservation().getSuggestion());
        assertEquals("123", exp.getProgress().getLastObservation().getExperiment());
        assertEquals("task_name", exp.getProgress().getLastObservation().getTask().getName());
        assertEquals(1.0, exp.getProgress().getLastObservation().getTask().getCost(), 1e-9);

        assertNotNull(exp.getProgress().getBestObservation());
        assertEquals("3", exp.getProgress().getBestObservation().getId());
        assertEquals(3.0, exp.getProgress().getBestObservation().getAssignments().get("a"));
        assertEquals("d", exp.getProgress().getBestObservation().getAssignments().get("b"));
        assertEquals("Revenue", exp.getProgress().getBestObservation().getValues().get(0).getName());
        assertNull(exp.getProgress().getBestObservation().getValues().get(0).getValue());
        assertNull(exp.getProgress().getBestObservation().getValues().get(0).getValueStddev());
        assertEquals("Sales", exp.getProgress().getBestObservation().getValues().get(1).getName());
        assertNull(exp.getProgress().getBestObservation().getValues().get(1).getValue());
        assertNull(exp.getProgress().getBestObservation().getValues().get(1).getValueStddev());
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
        assertNotNull(exp.getParameters().get(0).getConditions());
        assertEquals(0, exp.getParameters().get(0).getConditions().get("num_hidden_layers").size());

        assertEquals("b", exp.getParameters().get(1).getName());
        assertEquals("categorical", exp.getParameters().get(1).getType());
        assertNull(exp.getParameters().get(1).getBounds());
        assertEquals(2, exp.getParameters().get(1).getCategoricalValues().size());
        assertEquals("c", exp.getParameters().get(1).getCategoricalValues().get(0).getName());
        assertEquals("d", exp.getParameters().get(1).getCategoricalValues().get(1).getName());
        assertNotNull(exp.getParameters().get(1).getConditions());
        assertEquals(2, exp.getParameters().get(1).getConditions().get("num_hidden_layers").size());
        assertEquals("1", exp.getParameters().get(1).getConditions().get("num_hidden_layers").get(0));
        assertEquals("3", exp.getParameters().get(1).getConditions().get("num_hidden_layers").get(1));

        assertEquals("def", exp.getMetadata().get("abc"));
        assertEquals(123.0, exp.getMetadata().get("ghi"));
    }
}
