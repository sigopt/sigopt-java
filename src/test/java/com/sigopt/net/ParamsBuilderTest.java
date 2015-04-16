package com.sigopt.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ParamsBuilderTest {
    Gson gson = new GsonBuilder().create();
    HashMap<String, Object> params;

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
        params = new HashMap<String, Object>();
        params.put("age", 100);
    }


    @Test
    public void buildWithClientToken() throws Exception {
        Map<String, Object> actual = ParamsBuilder.build(params, "fake-c-token", null);
        assertEquals("fake-c-token", actual.get("client_token"));
    }

    @Test
    public void buildWithUserToken() throws Exception {
        Map<String, Object> actual = ParamsBuilder.build(params, null, "fake-u-token");
        assertEquals("fake-u-token", actual.get("user_token"));
    }

    @Test
    public void buildWithBothTokens() throws Exception {
        Map<String, Object> actual = ParamsBuilder.build(params, "fake-c-token", "fake-u-token");
        assertEquals("fake-c-token", actual.get("client_token"));
        assertEquals("fake-u-token", actual.get("user_token"));
    }

    @Test
    public void buildWithData() throws Exception {
        MockResource mockResource = new MockResource("id-10", "some-name-here");
        params.put("data", mockResource);
        Map<String, Object> actual = ParamsBuilder.build(params, null, null);
        assertEquals(gson.toJson(mockResource), actual.get("data"));
    }

    @Test
    public void buildWithMultiData() throws Exception {
        List<MockResource> multi = new ArrayList<MockResource>();
        multi.add(new MockResource("id-10", "some-name-here"));
        multi.add(new MockResource("id-11", "some-other-name-here"));

        params.put("multi_data", multi);
        Map<String, Object> actual = ParamsBuilder.build(params, null, null);
        assertEquals(gson.toJson(multi), actual.get("multi_data"));
    }

}
