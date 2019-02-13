package com.sigopt.model;

import com.sigopt.model.APIResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ProjectTest extends APIResourceTestBase {
    private String json;

    @Before
    public void setUpMockData() throws IOException {
        this.json = resource("project.json");
    }

    @Test
    public void constructFromJson() throws Exception {
        Project project = APIResource.constructFromJson(this.json, Project.class);

        assertEquals("test-project", project.getId());
        assertEquals("Test Project", project.getName());
        assertEquals("1", project.getClient());
        assertEquals("2092", project.getUser());
        assertEquals(123, project.getCreated());
        assertEquals(456, project.getUpdated());
        assertEquals("def", project.getMetadata().get("abc"));
        assertEquals(123.0, project.getMetadata().get("ghi"));
    }
}
