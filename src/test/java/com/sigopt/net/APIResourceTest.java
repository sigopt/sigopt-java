package com.sigopt.net;

import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class APIResourceTest {
    String mockResourceJson;

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
        mockResourceJson = "{\n" +
            "      \"id\": \"id-value\",\n" +
            "      \"super_awesome_name\": \"name-value\",\n" +
            "      \"pub_field\": 111\n" +
            "}";
    }

    public String createMockResourceJson(String id, String superAwesomeName, Integer pubField) {
        return "{\n" +
            "  \"id\": \"" + id + "\",\n" +
            "  \"super_awesome_name\": \"" + superAwesomeName + "\",\n" +
            "  \"pub_field\": " + pubField + "\n" +
            "}";
    }

    public String createJsonArray(String ...jsonObjects) {
        String ret = "[";
        for(String obj : jsonObjects) {
            ret = ret + obj + ",";
        }
        ret = ret.substring(0, ret.length() - 1);
        ret = ret + "]";
        return ret;
    }

    @Test
    public void constructResourceFromJson() throws Exception {
        MockResource actual = APIResource.constructFromJson(mockResourceJson, MockResource.class);
        assertEquals("id-value", actual.getId());
        assertEquals("name-value", actual.getSuperAwesomeName());
        assertEquals((Integer)111, actual.pubField);
        assertTrue(actual instanceof MockResource);
    }

    @Test
    public void preProcessJson() throws Exception {
        String expected = createMockResourceJson("id-1", "name-1", 1).replaceAll("[ \n]+", "");

        String actual = APIResource.preProcessJson(createMockResourceJson("id-1", "name-1", 1));
        assertEquals(expected, actual.replaceAll("[ \n]+", ""));
    }

}
