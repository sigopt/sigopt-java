package com.sigopt.net;

import com.google.gson.reflect.TypeToken;
import com.sigopt.Sigopt;
import com.sigopt.model.Experiment;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
    APIResource.class
})
public class APIMethodCallerTest {
    APIMethodCaller<MockResource> caller;
    APIMethodCaller<List<MockResource>> listCaller;

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
        Sigopt.clientToken = "client-token";
        Sigopt.userToken = "user-token";
    }

    @Before
    public void setUpMockData() {
        caller = new APIMethodCaller("get", "/path", null, MockResource.class);
        Type type = new TypeToken<List<MockResource>>() {}.getType();
        listCaller = new APIMethodCaller("post", "/path", null, type);
    }

    @Test
    public void callWithAPIResource() throws Exception {
        APIMethod.Builder builder = Mockito.mock(APIMethod.Builder.class);
        APIMethod method = Mockito.mock(APIMethod.class);
        caller.apiMethodBuilder = builder;
        Mockito.when(builder.build()).thenReturn(method);
        Mockito.stub(method.execute()).toReturn(null);
        method.response = new Requester.Response("{}", 200);

        MockResource expected = new MockResource("exp-id", "exp-name");
        PowerMockito.mockStatic(APIResource.class);
        PowerMockito.when(APIResource.constructFromJson(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(expected);

        assertEquals(expected, caller.call());
    }

    @Test
    public void callWithList() throws Exception {
        APIMethod.Builder builder = Mockito.mock(APIMethod.Builder.class);
        APIMethod method = Mockito.mock(APIMethod.class);
        listCaller.apiMethodBuilder = builder;
        Mockito.when(builder.build()).thenReturn(method);
        Mockito.stub(method.execute()).toReturn(null);
        method.response = new Requester.Response("{}", 200);

        List<MockResource> expected = new ArrayList<MockResource>();
        expected.add(new MockResource("exp-id", "exp-name"));

        PowerMockito.mockStatic(APIResource.class);
        PowerMockito.when(APIResource.constructTypedFromJson(Mockito.anyString(), Mockito.any(Class.class))).thenReturn(expected);

        assertEquals(expected, listCaller.call());
    }


}
