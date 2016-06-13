package com.sigopt.net;

import com.google.gson.FieldNamingPolicy;
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
import static org.junit.Assert.assertTrue;

public class ParamsBuilderTest {
    Gson gson = new GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .create();
    HashMap<String, Object> params;

    static class MockResource extends APIResource {
        String id;
        String superAwesomeName;

        public Integer pubField = 555;

        public MockResource(String id, String superAwesomeName) {
            this.id = id;
            this.superAwesomeName = superAwesomeName;
        }

        public String getId() {
            return this.id;
        }

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
    public void buildWithData() throws Exception {
        MockResource mockResource = new MockResource("id-10", "some-name-here");
        params.put("data", mockResource);
        Map<String, Object> actual = ParamsBuilder.build(params);
        assertEquals(gson.toJson(mockResource), actual.get("data"));
    }

    @Test
    public void buildWithMultiData() throws Exception {
        List<MockResource> multi = new ArrayList<MockResource>();
        multi.add(new MockResource("id-10", "some-name-here"));
        multi.add(new MockResource("id-11", "some-other-name-here"));

        params.put("multi_data", multi);
        Map<String, Object> actual = ParamsBuilder.build(params);
        assertEquals(gson.toJson(multi), actual.get("multi_data"));
    }

    @Test
    public void buildWithJson() throws Exception {
        MockResource mockResource = new MockResource("id-10", "some-name-here");
        params.put("data", mockResource);
        Map<String, Object> actual = ParamsBuilder.build(params);
        String dataString = ((String) actual.get("data"));
        assertTrue("pubField serialized as underscore case", dataString.indexOf("pub_field") >= 0);
        assertTrue("superAwesomeName serialized as underscore case", dataString.indexOf("super_awesome_name") >= 0);
    }
}
