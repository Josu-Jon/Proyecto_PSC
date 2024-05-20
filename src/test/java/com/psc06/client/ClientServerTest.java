package com.psc06.client;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.util.List;
import com.psc06.pojo.UserStoryData;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;

public class ClientServerTest {

    private ClientServer clientServer;

    @Mock
    private Client client;

    @Mock
    private WebTarget webTarget;

    @Mock
    private Response response;

    @Before
    public void setUp() {
        client = ClientBuilder.newClient();
        webTarget = client.target(String.format("http://%s:%s/rest/resource", "0.0.0.0", "8080"));
    }

    @Test
    public void testRegisterUserStory() {
        assertTrue(true);
	}
    
    @Test
	public void testRegisterSprint() {
		assertTrue(true);
	}
    
    @Test
	public void testAssignUserStory() {
		assertTrue(true);
	}

    @Test
	public void testDeleteSprint() {
		assertTrue(true);
	}

    @Test
	public void testDeleteUserStory() {
		assertTrue(true);
	}

    @Test
	public void testReassignUserStory() {
		assertTrue(true);
	}

    @Test
	public void testGetAllUserStories() {
        assertTrue(true);
	}

    @Test
	public List<UserStoryData> getUserStoriesFromSprint() {
        assertTrue(true);
	}
}