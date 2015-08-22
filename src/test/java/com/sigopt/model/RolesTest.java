package com.sigopt.model;

import com.google.gson.reflect.TypeToken;
import com.sigopt.net.APIResource;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RolesTest extends APIResourceTestBase {
    @BeforeClass
    public static void setUp() {
    }

    @Before
    public void setUpMockData() {
    }

    @Test
    public void constructFromJson() throws Exception {
        String json = resource("roles.json");
        Type type = new TypeToken<List<Role>>() {}.getType();
        List<Role> roles = APIResource.constructTypedFromJson(json, type);

        assertEquals(1, roles.size());

        Role role = roles.get(0);
        User user = role.getUser();
        Client client = role.getClient();
        assertEquals("admin", role.getRole());
        assertEquals("1", user.getId());
        assertEquals("Patrick", user.getName());
        assertEquals("patrick@sigopt.com", user.getEmail());
        assertEquals("1", client.getId());
        assertEquals("SigOpt", client.getName());
    }

}
