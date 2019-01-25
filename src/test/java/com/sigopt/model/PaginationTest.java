package com.sigopt.model;

import com.sigopt.model.APIResource;
import com.sigopt.net.APIMethodCaller;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class PaginationTest extends APIResourceTestBase {
    String json;
    String jsonEmpty;
    String jsonLast;

    private static <T> List<T> iterableToList(Iterable<T> it) {
        ArrayList<T> x = new ArrayList();
        for (T t: it) {
            x.add(t);
        }
        return x;
    }

    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() throws IOException {
        json = resource("pagination.json");
        jsonEmpty = resource("pagination_empty.json");
        jsonLast = resource("pagination_last.json");
    }

    @Test
    public void constructFromJson() throws Exception {
        Pagination<Client> pagination = APIResource.constructPaginationFromJson(json, Client.class);
        Client c = APIResource.constructFromJson(resource("client.json"), Client.class);
        assertEquals(2, (Object) pagination.getCount());
        assertEquals("1", pagination.getPaging().getBefore());
        assertEquals("2", pagination.getPaging().getAfter());
        assertEquals(1, pagination.getData().size());

        assertEquals("SigOpt", pagination.getData().get(0).getName());
        assertEquals(c, pagination.getData().get(0));

        pagination = APIResource.constructPaginationFromJson(jsonLast, Client.class);
        assertEquals(2, (Object) pagination.getCount());
        assertEquals(null, pagination.getPaging().getBefore());
        assertEquals(null, pagination.getPaging().getAfter());
    }

    @Test
    public void iteratePages() throws Exception {
        Pagination<Client> page1 = APIResource.constructPaginationFromJson(json, Client.class);
        Pagination<Client> page2 = APIResource.constructPaginationFromJson(jsonLast, Client.class);

        APIMethodCaller<Pagination<Client>> apiMethodCaller = Mockito.mock(APIMethodCaller.class);
        page1.bind(apiMethodCaller);

        Mockito.when(apiMethodCaller.getParams()).thenReturn(new HashMap<String, Object>());
        Mockito.when(apiMethodCaller.call()).thenReturn(page2);

        List<Client> clients = iterableToList(page1.iteratePages());

        assertEquals(2, clients.size());
        assertEquals("SigOpt", clients.get(0).getName());
        assertEquals("Other SigOpt", clients.get(1).getName());

        Mockito.verify(apiMethodCaller, Mockito.atLeastOnce()).getParams();
        Mockito.verify(apiMethodCaller).addParam("before", page1.getPaging().getBefore());
        Mockito.verify(apiMethodCaller).removeParam("after");
        Mockito.verify(apiMethodCaller).call();
        Mockito.verifyNoMoreInteractions(apiMethodCaller);
    }

    @Test
    public void iteratePagesEmpty() throws Exception {
        Pagination<Client> emptyPage = APIResource.constructPaginationFromJson(jsonEmpty, Client.class);

        APIMethodCaller<Pagination<Client>> apiMethodCaller = Mockito.mock(APIMethodCaller.class);
        emptyPage.bind(apiMethodCaller);

        Mockito.when(apiMethodCaller.getParams()).thenReturn(new HashMap<String, Object>());

        List<Client> clients = iterableToList(emptyPage.iteratePages());

        assertEquals(0, clients.size());

        Mockito.verify(apiMethodCaller, Mockito.atLeastOnce()).getParams();
        Mockito.verify(apiMethodCaller, Mockito.never()).addParam(Mockito.anyString(), Mockito.any());
        Mockito.verify(apiMethodCaller, Mockito.never()).removeParam(Mockito.anyString());
        Mockito.verify(apiMethodCaller, Mockito.never()).call();
        Mockito.verifyNoMoreInteractions(apiMethodCaller);
    }

    @Test
    public void iteratePagesSingle() throws Exception {
        Pagination<Client> page1 = APIResource.constructPaginationFromJson(json, Client.class);
        Pagination<Client> page2 = APIResource.constructPaginationFromJson(jsonEmpty, Client.class);

        APIMethodCaller<Pagination<Client>> apiMethodCaller = Mockito.mock(APIMethodCaller.class);
        page1.bind(apiMethodCaller);

        Mockito.when(apiMethodCaller.getParams()).thenReturn(new HashMap<String, Object>());
        Mockito.when(apiMethodCaller.call()).thenReturn(page2);

        List<Client> clients = iterableToList(page1.iteratePages());

        assertEquals(1, clients.size());
        assertEquals("SigOpt", clients.get(0).getName());

        Mockito.verify(apiMethodCaller, Mockito.atLeastOnce()).getParams();
        Mockito.verify(apiMethodCaller).addParam("before", page1.getPaging().getBefore());
        Mockito.verify(apiMethodCaller).removeParam("after");
        Mockito.verify(apiMethodCaller).call();
        Mockito.verifyNoMoreInteractions(apiMethodCaller);
    }

    @Test
    public void iteratePagesAfter() throws Exception {
        Pagination<Client> page1 = APIResource.constructPaginationFromJson(json, Client.class);
        Pagination<Client> page2 = APIResource.constructPaginationFromJson(jsonLast, Client.class);

        APIMethodCaller<Pagination<Client>> apiMethodCaller = Mockito.mock(APIMethodCaller.class);
        page1.bind(apiMethodCaller);

        Mockito.when(apiMethodCaller.getParams()).thenReturn(new HashMap<String, Object>() {{
            put("after", "2");
        }});
        Mockito.when(apiMethodCaller.call()).thenReturn(page2);

        List<Client> clients = iterableToList(page1.iteratePages());

        assertEquals(2, clients.size());
        assertEquals("SigOpt", clients.get(0).getName());
        assertEquals("Other SigOpt", clients.get(1).getName());

        Mockito.verify(apiMethodCaller, Mockito.atLeastOnce()).getParams();
        Mockito.verify(apiMethodCaller).addParam("after", page1.getPaging().getAfter());
        Mockito.verify(apiMethodCaller).removeParam("before");
        Mockito.verify(apiMethodCaller).call();
        Mockito.verifyNoMoreInteractions(apiMethodCaller);
    }

    @Test
    public void iterateMultiplePages() throws Exception {
        Pagination<Client> page1 = APIResource.constructPaginationFromJson(json, Client.class);
        Pagination<Client> page2 = APIResource.constructPaginationFromJson(json, Client.class);
        Pagination<Client> page3 = APIResource.constructPaginationFromJson(json, Client.class);
        Pagination<Client> page4 = APIResource.constructPaginationFromJson(jsonLast, Client.class);

        APIMethodCaller<Pagination<Client>> apiMethodCaller = Mockito.mock(APIMethodCaller.class);
        page1.bind(apiMethodCaller);
        Mockito.when(apiMethodCaller.call()).thenReturn(page2).thenReturn(page3).thenReturn(page4);

        List<Client> clients = iterableToList(page1.iteratePages());
        assertEquals(4, clients.size());
        assertEquals("SigOpt", clients.get(0).getName());
        assertEquals("SigOpt", clients.get(1).getName());
        assertEquals("SigOpt", clients.get(2).getName());
        assertEquals("Other SigOpt", clients.get(3).getName());

        Mockito.verify(apiMethodCaller, Mockito.atLeastOnce()).getParams();
        Mockito.verify(apiMethodCaller, Mockito.times(3)).addParam("before", page1.getPaging().getBefore());
        Mockito.verify(apiMethodCaller, Mockito.times(3)).removeParam("after");
        Mockito.verify(apiMethodCaller, Mockito.times(3)).call();
        Mockito.verifyNoMoreInteractions(apiMethodCaller);
    }
}
