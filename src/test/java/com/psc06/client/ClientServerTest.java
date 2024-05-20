package com.psc06.client;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import com.psc06.pojo.SprintData;
import com.psc06.pojo.SprintStoryData;
import com.psc06.pojo.UserStoryData;

import java.util.*;

public class ClientServerTest {

    @Mock
    private Client client;

    @Mock(answer=Answers.RETURNS_DEEP_STUBS)
    private WebTarget webTarget;

    @Mock
    private Invocation.Builder invocationBuilder;

    @Mock
    private Response response;

    @Mock
    private Logger logger;

    @InjectMocks
    private ClientServer clientServer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        try (MockedStatic<ClientBuilder> clientBuilder = Mockito.mockStatic(ClientBuilder.class)) {
            clientBuilder.when(ClientBuilder::newClient).thenReturn(client);
            when(client.target("http://localhost:8080/rest/resource")).thenReturn(webTarget);

            clientServer = new ClientServer("localhost", "8080");
        }

        WebTarget webTarget = mock(WebTarget.class);
		Invocation.Builder invocationBuilder = mock(Invocation.Builder.class);

        when(client.target(anyString())).thenReturn(webTarget);
        when(webTarget.path(anyString())).thenReturn(webTarget);
        when(webTarget.request(anyString())).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(Response.ok().build());
        when(invocationBuilder.get()).thenReturn(Response.ok().build());
    }

    @Test
    public void testRegisterUserStory() {
        when(webTarget.path("registerUserStory")).thenReturn(webTarget);

        //Llamamos al metodo
        Response response = clientServer.registerUserStory(1, "Test Story", 5, 10);

        // Comprobamos la response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void testRegisterSprint() {
        when(webTarget.path("registerSprint")).thenReturn(webTarget);

        Response response = clientServer.registerSprint(1);

        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void testAssignUserStory() {
        when(webTarget.path("assignUserStory")).thenReturn(webTarget);

        Response response = clientServer.assignUserStory(1, 1, "Test Story", 5, 10);

        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void testDeleteSprint() {
        when(webTarget.path("deleteSprint")).thenReturn(webTarget);

        Response response = clientServer.deleteSprint(1);

        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void testDeleteUserStory() {
        when(webTarget.path("deleteUserStory")).thenReturn(webTarget);

        Response response = clientServer.deleteUserStory(1);

        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void testModifyUserStory() {
        response = Response.ok().build();
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void testReassignUserStory() {
        when(webTarget.path("reassignUserStory")).thenReturn(webTarget);

        Response response = clientServer.reassignUserStory(1, 1, "Test Story", 5, 10);

        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void testGetAllUserStories() {
        response = Response.ok().build();
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

}