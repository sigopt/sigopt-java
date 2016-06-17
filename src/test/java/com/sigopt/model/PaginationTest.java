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

public class PaginationTest extends APIResourceTestBase {
    String json;

    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() throws IOException {
        json = resource("pagination.json");
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
    }
}
