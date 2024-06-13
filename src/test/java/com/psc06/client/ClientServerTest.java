package com.psc06.client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import com.psc06.pojo.SprintData;
import com.psc06.pojo.SprintStoryData;
import com.psc06.pojo.UserStoryData;
import com.psc06.pojo.ProyectSprintData;
import com.psc06.pojo.ProyectData;

import java.lang.reflect.Type;
import java.util.Collection;
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

    @Captor
    private ArgumentCaptor<Entity<UserStoryData>> usdCaptor;

    @Captor
    private ArgumentCaptor<Entity<SprintStoryData>> ssdCaptor;

    @Captor
    private ArgumentCaptor<Entity<SprintData>> sdCaptor;

    @Captor
    private ArgumentCaptor<Entity<ProyectSprintData>> psdCaptor;

    @Captor
    private ArgumentCaptor<Entity<ProyectData>> prCaptor;

    @Mock
    private Gson gson;

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

        when(client.target(anyString())).thenReturn(webTarget);
        when(webTarget.path(anyString())).thenReturn(webTarget);
        when(webTarget.request(anyString())).thenReturn(invocationBuilder);
    }

    @Test
    public void testRegisterUserStory() {

        when(webTarget.path("registerUserStory")).thenReturn(webTarget);

        Response response = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

        assertEquals(Response.Status.OK, clientServer.registerUserStory(1, "Test Story", 5, 10).getStatusInfo());
        
        verify(invocationBuilder).post(Entity.entity(usdCaptor.capture(), MediaType.APPLICATION_JSON));
        assertEquals(1, usdCaptor.getValue().getEntity().getId());
        assertEquals("Test Story", usdCaptor.getValue().getEntity().getUserStory());
        assertEquals(5, usdCaptor.getValue().getEntity().getEstimation());
        assertEquals(10, usdCaptor.getValue().getEntity().getPbPriority());
    }

    @Test
    public void testRegisterUserStoryFalse() {

        when(webTarget.path("registerUserStory")).thenReturn(webTarget);

        Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

        assertEquals(Response.Status.OK, clientServer.registerUserStory(1, "Test Story", 5, 10).getStatusInfo());
        
        verify(invocationBuilder).post(Entity.entity(usdCaptor.capture(), MediaType.APPLICATION_JSON));
        assertEquals(1, usdCaptor.getValue().getEntity().getId());
        assertEquals("Test Story", usdCaptor.getValue().getEntity().getUserStory());
        assertEquals(5, usdCaptor.getValue().getEntity().getEstimation());
        assertEquals(10, usdCaptor.getValue().getEntity().getPbPriority());
    }

    @Test
    public void testRegisterSprint() {
        when(webTarget.path("registerSprint")).thenReturn(webTarget);

        Response response = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

        assertEquals(Response.Status.OK, clientServer.registerSprint(1).getStatusInfo());
        
        verify(invocationBuilder).post(Entity.entity(sdCaptor.capture(), MediaType.APPLICATION_JSON));
        assertEquals(1, sdCaptor.getValue().getEntity().getSprintNum());
    }

    @Test
    public void testRegisterSprintFalse() {
        when(webTarget.path("registerSprint")).thenReturn(webTarget);

        Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

        assertEquals(Response.Status.OK, clientServer.registerSprint(1).getStatusInfo());
        
        verify(invocationBuilder).post(Entity.entity(sdCaptor.capture(), MediaType.APPLICATION_JSON));
        assertEquals(1, sdCaptor.getValue().getEntity().getSprintNum());
    }

    @Test
    public void testRegisterProyect() 
    {
        when(webTarget.path("registerProyect")).thenReturn(webTarget);

        Response response = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

        assertEquals(Response.Status.OK, clientServer.registerProyect(1).getStatusInfo());
        
        verify(invocationBuilder).post(Entity.entity(prCaptor.capture(), MediaType.APPLICATION_JSON));
        assertEquals(1, prCaptor.getValue().getEntity().getIdProyect());
    }

    @Test
    public void testRegisterProyectFalse() 
    {
        when(webTarget.path("registerProyect")).thenReturn(webTarget);

        Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

        assertEquals(Response.Status.OK, clientServer.registerProyect(1).getStatusInfo());
        
        verify(invocationBuilder).post(Entity.entity(prCaptor.capture(), MediaType.APPLICATION_JSON));
        assertEquals(1, prCaptor.getValue().getEntity().getIdProyect());
    }

    @Test
    public void testAssignUserStory() {
        when(webTarget.path("assignUserStory")).thenReturn(webTarget);

        Response response = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

        assertEquals(Response.Status.OK, clientServer.assignUserStory(1, 1, "Test Story", 5, 10).getStatusInfo());
        
        verify(invocationBuilder).post(Entity.entity(ssdCaptor.capture(), MediaType.APPLICATION_JSON));
        assertEquals(1, ssdCaptor.getValue().getEntity().getUserStoryData().getId());
        assertEquals("Test Story", ssdCaptor.getValue().getEntity().getUserStoryData().getUserStory());
        assertEquals(5, ssdCaptor.getValue().getEntity().getUserStoryData().getEstimation());
        assertEquals(10, ssdCaptor.getValue().getEntity().getUserStoryData().getPbPriority());
    }

    @Test
    public void testAssignUserStoryFalse() {
        when(webTarget.path("assignUserStory")).thenReturn(webTarget);

        Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

        assertEquals(Response.Status.OK, clientServer.assignUserStory(1, 1, "Test Story", 5, 10).getStatusInfo());
        
        verify(invocationBuilder).post(Entity.entity(ssdCaptor.capture(), MediaType.APPLICATION_JSON));
        assertEquals(1, ssdCaptor.getValue().getEntity().getUserStoryData().getId());
        assertEquals("Test Story", ssdCaptor.getValue().getEntity().getUserStoryData().getUserStory());
        assertEquals(5, ssdCaptor.getValue().getEntity().getUserStoryData().getEstimation());
        assertEquals(10, ssdCaptor.getValue().getEntity().getUserStoryData().getPbPriority());
    }
    
@Test
public void testAssignSprint() 
{
    when(webTarget.path("assignSprint")).thenReturn(webTarget);

    Response response = Response.ok().build();
    when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
    when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

    assertEquals(Response.Status.OK, clientServer.assignSprint(1, 1).getStatusInfo());
    
    verify(invocationBuilder).post(Entity.entity(psdCaptor.capture(), MediaType.APPLICATION_JSON));
    assertEquals(1, psdCaptor.getValue().getEntity().getSprintData().getSprintNum());
    assertEquals(1, psdCaptor.getValue().getEntity().getProyectData().getIdProyect());
}

@Test
public void testAssignSprintFalse()
{
    when(webTarget.path("assignSprint")).thenReturn(webTarget);

    Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
    when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

    assertEquals(Response.Status.OK, clientServer.assignSprint(1, 1).getStatusInfo());
    
    verify(invocationBuilder).post(Entity.entity(psdCaptor.capture(), MediaType.APPLICATION_JSON));
    assertEquals(1, psdCaptor.getValue().getEntity().getSprintData().getSprintNum());
    assertEquals(1, psdCaptor.getValue().getEntity().getProyectData().getIdProyect());
}


    @Test
    public void testDeleteSprint() {
        when(webTarget.path("deleteSprint")).thenReturn(webTarget);

        Response response = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

        assertEquals(Response.Status.OK, clientServer.deleteSprint(1).getStatusInfo());
        
        verify(invocationBuilder).post(Entity.entity(sdCaptor.capture(), MediaType.APPLICATION_JSON));
        assertEquals(1, sdCaptor.getValue().getEntity().getSprintNum());
    }

    @Test
    public void testDeleteSprintFalse() {
        when(webTarget.path("deleteSprint")).thenReturn(webTarget);

        Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

        assertEquals(Response.Status.OK, clientServer.deleteSprint(1).getStatusInfo());
        
        verify(invocationBuilder).post(Entity.entity(sdCaptor.capture(), MediaType.APPLICATION_JSON));
        assertEquals(1, sdCaptor.getValue().getEntity().getSprintNum());
    }

    @Test
    public void testDeleteUserStory() {
        when(webTarget.path("deleteUserStory")).thenReturn(webTarget);
        
        Response response = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

        assertEquals(Response.Status.OK, clientServer.deleteUserStory(1).getStatusInfo());
        
        verify(invocationBuilder).post(Entity.entity(usdCaptor.capture(), MediaType.APPLICATION_JSON));
        assertEquals(1, usdCaptor.getValue().getEntity().getId());
    }

    @Test
    public void testDeleteUserStoryFalse() {
        when(webTarget.path("deleteUserStory")).thenReturn(webTarget);
        
        Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

        assertEquals(Response.Status.OK, clientServer.deleteUserStory(1).getStatusInfo());
        
        verify(invocationBuilder).post(Entity.entity(usdCaptor.capture(), MediaType.APPLICATION_JSON));
        assertEquals(1, usdCaptor.getValue().getEntity().getId());
    }

    @Test
    public void testDeleteProyect() 
    {
        when(webTarget.path("deleteProyect")).thenReturn(webTarget);

        Response response = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

        assertEquals(Response.Status.OK, clientServer.deleteProyect(1).getStatusInfo());
        
        verify(invocationBuilder).post(Entity.entity(prCaptor.capture(), MediaType.APPLICATION_JSON));
        assertEquals(1, prCaptor.getValue().getEntity().getIdProyect());
    }

    @Test
    public void testDeleteProyectFalse()
    {
        when(webTarget.path("deleteProyect")).thenReturn(webTarget);

        Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

        assertEquals(Response.Status.OK, clientServer.deleteProyect(1).getStatusInfo());
        
        verify(invocationBuilder).post(Entity.entity(prCaptor.capture(), MediaType.APPLICATION_JSON));
        assertEquals(1, prCaptor.getValue().getEntity().getIdProyect());
    }

/*
    @Test
    public void testModifyUserStory() {
        Response response = Response.ok().build();

        when(clientServer.deleteUserStory(1)).thenReturn(Response.ok().build());
        when(clientServer.registerUserStory(1, "Test Story", 5, 10)).thenReturn(response);
        assertEquals(Response.Status.OK, clientServer.modifyUserStory(1, "Test Story", 5, 10).getStatusInfo());
    }
*/
/*
    @Test
    public void testModifySprint() {
        Response response = Response.ok().build();

        when(clientServer.deleteSprint(1)).thenReturn(response);
        when(clientServer.registerSprint(1)).thenReturn(response);
        assertEquals(Response.Status.OK, clientServer.modifySprint(1).getStatusInfo());
    }
*/
    @Test
    public void testReassignUserStory() {
        when(webTarget.path("reassignUserStory")).thenReturn(webTarget);

        Response response = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

        assertEquals(Response.Status.OK, clientServer.reassignUserStory(1, 1, "Test Story", 5, 10).getStatusInfo());
        
        verify(invocationBuilder).post(Entity.entity(ssdCaptor.capture(), MediaType.APPLICATION_JSON));
        assertEquals(1, ssdCaptor.getValue().getEntity().getSprintData().getSprintNum());
        assertEquals(1, ssdCaptor.getValue().getEntity().getUserStoryData().getId());
        assertEquals("Test Story", ssdCaptor.getValue().getEntity().getUserStoryData().getUserStory());
        assertEquals(5, ssdCaptor.getValue().getEntity().getUserStoryData().getEstimation());
        assertEquals(10, ssdCaptor.getValue().getEntity().getUserStoryData().getPbPriority());
    }

    @Test
    public void testReassignUserStoryFalse() {
        when(webTarget.path("reassignUserStory")).thenReturn(webTarget);

        Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

        assertEquals(Response.Status.OK, clientServer.reassignUserStory(1, 1, "Test Story", 5, 10).getStatusInfo());
        
        verify(invocationBuilder).post(Entity.entity(ssdCaptor.capture(), MediaType.APPLICATION_JSON));
        assertEquals(1, ssdCaptor.getValue().getEntity().getSprintData().getSprintNum());
        assertEquals(1, ssdCaptor.getValue().getEntity().getUserStoryData().getId());
        assertEquals("Test Story", ssdCaptor.getValue().getEntity().getUserStoryData().getUserStory());
        assertEquals(5, ssdCaptor.getValue().getEntity().getUserStoryData().getEstimation());
        assertEquals(10, ssdCaptor.getValue().getEntity().getUserStoryData().getPbPriority());
    }

    /*@Test
    public void testReassignSprints() 
    {
        when(webTarget.path("reassignSprints")).thenReturn(webTarget);

        Response response = Response.ok().build();
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

        assertEquals(Response.Status.OK, clientServer.reassignSprints(1, 1)); //
        
        verify(invocationBuilder).post(Entity.entity(psdCaptor.capture(), MediaType.APPLICATION_JSON));
        assertEquals(1, psdCaptor.getValue().getEntity().getSprintData().getSprintNum());
        assertEquals(1, psdCaptor.getValue().getEntity().getProyectData().getIdProyect());
    }

    @Test
    public void testReassignSprintsFalse() 
    {
        when(webTarget.path("reassignSprints")).thenReturn(webTarget);

        Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        when(webTarget.request(MediaType.APPLICATION_JSON)).thenReturn(invocationBuilder);
        when(invocationBuilder.post(any(Entity.class))).thenReturn(response);

        assertEquals(Response.Status.OK, clientServer.reassignSprints(1, 1)); //
        
        verify(invocationBuilder).post(Entity.entity(psdCaptor.capture(), MediaType.APPLICATION_JSON));
        assertEquals(1, psdCaptor.getValue().getEntity().getSprintData().getSprintNum());
        assertEquals(1, psdCaptor.getValue().getEntity().getProyectData().getIdProyect());
    }*/
    
/*
    @Test
    public void testGetAllUserStories() {
          List<UserStoryData> stories = new ArrayList<UserStoryData>();
        List<UserStoryData> mockStories = new ArrayList<UserStoryData>();
        UserStoryData usd1 = new UserStoryData();
        usd1.setId(1);
        usd1.setUserStory("Story 1");
        usd1.setEstimation(5);
        usd1.setPbPriority(1);

        UserStoryData usd2 = new UserStoryData();
        usd2.setId(2);
        usd2.setUserStory("Story 2");
        usd2.setEstimation(3);
        usd2.setPbPriority(2);

        mockStories.add(usd1);
        mockStories.add(usd2);

        String jsonResponse = "[{\"id\":1,\"userStory\":\"Story 1\",\"estimation\":5,\"pbPriority\":1},{\"id\":2,\"userStory\":\"Story 2\",\"estimation\":3,\"pbPriority\":2}]";

        //Response response = Response.ok(jsonResponse).build();

        when(webTarget.path("getAllUserStories")).thenReturn(webTarget);
        when(invocationBuilder.get()).thenReturn(response);

        // Simular la respuesta del servidor
        
        when(response.readEntity(String.class)).thenReturn(jsonResponse);
        when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());        

        // Definir el tipo de la lista de UserStoryData
        Type userStoryDataListType = new TypeToken<List<UserStoryData>>() {}.getType();
        
        // Configurar el mock de Gson para devolver la lista simulada
        when(gson.fromJson(jsonResponse, userStoryDataListType)).thenReturn(mockStories);
        //when(gson.fromJson(anyString(), any(Type.class))).thenReturn(mockStories);

        // Llamamos al método a testear
        stories = clientServer.getAllUserStories();

        verify(webTarget).path("getAllUserStories");
        
        // Comprobamos que la lista de historias de usuario se obtuvo correctamente
        assertEquals(stories.getClass(), mockStories.getClass());


        assertNotNull(stories);
        assertEquals(2, stories.size());
        assertEquals("Story 1", stories.get(0).getUserStory());
        assertEquals(5, stories.get(0).getEstimation());
        assertEquals(1, stories.get(0).getPbPriority());
        assertEquals("Story 2", stories.get(1).getUserStory());
        assertEquals(3, stories.get(1).getEstimation());
        assertEquals(2, stories.get(1).getPbPriority());

    }
*/
}