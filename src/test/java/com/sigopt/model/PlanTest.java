package com.sigopt.model;

import com.sigopt.model.APIResource;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlanTest extends APIResourceTestBase {
    @Test
    public void constructFromJson() throws Exception {
        String json = resource("plan.json");
        Plan plan = APIResource.constructFromJson(json, Plan.class);

        assertEquals("premium", plan.getId());
        assertEquals("SigOpt Premium", plan.getName());
        assertEquals(1, (Object) plan.getPlanRules().getMaxDimension());
        assertEquals(2, (Object) plan.getPlanRules().getMaxExperiments());
        assertEquals(3, (Object) plan.getPlanRules().getMaxObservations());
        assertEquals(4, (Object) plan.getPlanRules().getMaxParallelism());
        assertEquals(5, (Object) plan.getPlanRules().getMaxUsers());
        assertEquals(0, (Object) plan.getCurrentPeriod().getStart());
        assertEquals(1000, (Object) plan.getCurrentPeriod().getEnd());
        assertEquals(1000, (Object) plan.getCurrentPeriod().getEnd());
        assertEquals(2, (Object) plan.getCurrentPeriod().getExperiments().size());
        assertEquals("1", plan.getCurrentPeriod().getExperiments().get(0));
        assertEquals("2", plan.getCurrentPeriod().getExperiments().get(1));
    }
}
