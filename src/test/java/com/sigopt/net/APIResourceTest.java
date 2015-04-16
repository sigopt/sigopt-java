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
    String mockResourceListJson;

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
            "  \"response\": {\n" +
            "    \"mock_resource\": {\n" +
            "      \"id\": \"id-value\",\n" +
            "      \"super_awesome_name\": \"name-value\",\n" +
            "      \"pub_field\": 111\n" +
            "    }\n" +
            "  }\n" +
            "}";
        mockResourceListJson = "{\n" +
            "  \"response\": {\n" +
            "    \"mock_resources\": [\n" +
            "      {\n" +
            "        \"id\": \"id-value\",\n" +
            "        \"super_awesome_name\": \"name-value\",\n" +
            "        \"pub_field\": 111\n" +
            "      },{\n" +
            "        \"id\": \"id-value-2\",\n" +
            "        \"super_awesome_name\": \"name-value-2\",\n" +
            "        \"pub_field\": 222\n" +
            "      }\n" +
            "\n" +
            "    ]\n" +
            "  }\n" +
            "}";
    }

    public String sigoptWrapJson(String key, String json) {
        String ret = "{\n" +
            "  \"response\": {\n" +
            "    \"" + key + "\": " + json + "\n" +
            "  }\n" +
            "}\n";
        return ret;
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
    public void constructResourceListFromJson() throws Exception {
        Type type = new TypeToken<List<MockResource>>() {}.getType();
        List<MockResource> actual = APIResource.constructListFromJson(mockResourceListJson, type);

        MockResource mr = actual.get(0);
        assertEquals("id-value", mr.getId());
        assertEquals("name-value", mr.getSuperAwesomeName());
        assertEquals((Integer)111, mr.pubField);
        assertTrue(mr instanceof MockResource);

        mr = actual.get(1);
        assertEquals("id-value-2", mr.getId());
        assertEquals("name-value-2", mr.getSuperAwesomeName());
        assertEquals((Integer)222, mr.pubField);
        assertTrue(mr instanceof MockResource);
    }

    @Test
    public void preProcessJson() throws Exception {
        String expected = createMockResourceJson("id-1", "name-1", 1).replaceAll("[ \n]+", "");

        String actual = APIResource.preProcessJson(sigoptWrapJson("mock_resource", createMockResourceJson("id-1", "name-1", 1)));
        assertEquals(expected, actual.replaceAll("[ \n]+", ""));
    }

}
