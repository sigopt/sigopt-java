package com.sigopt.net;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class PathBuilderTest {
    HashMap<String, Object> mockParams;
    MockResource mockResource;
    static class MockResource extends APIResource {
        String id;
        String superAwesomeName;

        @APIPathKey(key="pub_field")
        public Integer pubField = 555;

        public MockResource(String id, String superAwesomeName) {
            this.id = id;
            this.superAwesomeName = superAwesomeName;
        }

        @APIPathKey(key="id")
        public String getId() {
            return this.id;
        }

        @APIPathKey(key="super_awesome_name")
        public String getSuperAwesomeName() {
            return this.superAwesomeName;
        }
    }

    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() {
        mockResource = new MockResource("id-10", "some-name-here");
        mockParams = new HashMap<String, Object>();
        mockParams.put("age", 100);
    }


    @Test
    public void buildWithNoValues() throws Exception {
        String expected = "/some/plain/path";
        String actual = PathBuilder.build(expected, mockResource, mockParams);
        assertEquals(expected, actual);
    }

    @Test
    public void buildWithInstanceMethodValues() throws Exception {
        String path = "/some/:id/path";
        String actual = PathBuilder.build(path, mockResource, mockParams);
        assertEquals("/some/" + mockResource.getId() + "/path", actual);
    }

    @Test
    public void buildWithInstanceFieldValues() throws Exception {
        String path = "/some/:pub_field/path";
        String actual = PathBuilder.build(path, mockResource, mockParams);
        assertEquals("/some/" + mockResource.pubField + "/path", actual);
    }

    @Test
    public void buildWithParamValues() throws Exception {
        String path = "/some/:age/path";
        String actual = PathBuilder.build(path, mockResource, mockParams);
        assertEquals("/some/" + mockParams.get("age") + "/path", actual);
    }

    @Test
    public void buildWithInstanceAndParamValues() throws Exception {
        String path = "/some/:age/path/:id/:super_awesome_name";
        String actual = PathBuilder.build(path, mockResource, mockParams);
        assertEquals("/some/" + mockParams.get("age") + "/path/" + mockResource.getId() + "/" + mockResource.getSuperAwesomeName(), actual);
    }


}
