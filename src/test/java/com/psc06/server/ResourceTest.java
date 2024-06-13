package com.psc06.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.jdo.JDOHelper;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.psc06.server.jdo.Sprint;
import com.psc06.server.jdo.UserStory;
import com.psc06.server.jdo.Proyect;
import com.psc06.pojo.SprintData;
import com.psc06.pojo.UserStoryData;
import com.psc06.pojo.SprintStoryData;
import com.psc06.pojo.ProyectSprintData;
import com.psc06.pojo.ProyectData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.*;
import java.lang.reflect.Type;

public class ResourceTest {
    
    private Resource resource;

    @Mock
    private PersistenceManager persistenceManager;

    @Mock
    private Transaction transaction;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // initializing static mock object PersistenceManagerFactory
        try (MockedStatic<JDOHelper> jdoHelper = Mockito.mockStatic(JDOHelper.class)) {
            PersistenceManagerFactory pmf = mock(PersistenceManagerFactory.class);
            jdoHelper.when(() -> JDOHelper.getPersistenceManagerFactory("datanucleus.properties")).thenReturn(pmf);
            
            when(pmf.getPersistenceManager()).thenReturn(persistenceManager);
            when(persistenceManager.currentTransaction()).thenReturn(transaction);

            // instantiate tested object with mock dependencies
            resource = new Resource();
        }
    }

    @Test
    public void testSayTest() {
        Response response = resource.sayTest();
        assertEquals(Response.Status.OK, response.getStatusInfo());
        assertEquals("Test!", response.getEntity().toString());
    }

    @Test
    public void testRegisterSprint(){

        // Preparamos el persistence manager para devolver el mock
        SprintData sprint = new SprintData();
        sprint.setSprintNum(5);

        // Simulamos que el objeto no esta en la BBDD
        when(persistenceManager.getObjectById(any())).thenThrow(new JDOObjectNotFoundException());
        
        // Preparamos el mock
        when(transaction.isActive()).thenReturn(true);

        //Llamamos al metodo a testear
        Response response = resource.registerSprint(sprint);

        // Comprobamos que se ha guardado correctamente
        ArgumentCaptor<Sprint> sprintCaptor = ArgumentCaptor.forClass(Sprint.class);
        verify(persistenceManager).makePersistent(sprintCaptor.capture());
        assertEquals(5, sprintCaptor.getValue().getSprintNum());

        // Comprobamos la response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void testDeleteSprint(){

        // Preparamos el persistence manager para devolver el mock
        SprintData sprintData = new SprintData();
        sprintData.setSprintNum(5);

        Response response = resource.registerSprint(sprintData);

        Sprint sprint = spy(Sprint.class);
        // Simulamos que el objeto no esta en la BBDD
        when(persistenceManager.getObjectById(Sprint.class, sprintData.getSprintNum())).thenReturn(sprint);

        //Llamamos al metodo a testear
        response = resource.deleteSprint(sprintData);

        // Comprobamos la response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void testRegisterUserStory(){

        // Preparamos el persistence manager para devolver el mock
        UserStoryData userStoryData = new UserStoryData();
        userStoryData.setEstimation(3);
        userStoryData.setPbPriority(2);
        userStoryData.setUserStory("Test");
        userStoryData.setId(1);

        // Simulamos que el objeto no esta en la BBDD
        when(persistenceManager.getObjectById(UserStory.class)).thenThrow(new JDOObjectNotFoundException());
        
        // Preparamos el mock
        when(transaction.isActive()).thenReturn(true);

        //Llamamos al metodo a testear
        Response response = resource.registerUserStory(userStoryData);

        // Comprobamos que se ha guardado correctamente
        ArgumentCaptor<UserStory> usCaptor = ArgumentCaptor.forClass(UserStory.class);
        verify(persistenceManager).makePersistent(usCaptor.capture());
        assertEquals(1, usCaptor.getValue().getId());
        assertEquals("Test", usCaptor.getValue().getUserStory());
        assertEquals(3, usCaptor.getValue().getEstimation());
        assertEquals(2, usCaptor.getValue().getPbPriority());


        // Comprobamos la response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void testDeleteUserStory(){

        // Preparamos el persistence manager para devolver el mock
        UserStoryData userStoryData = new UserStoryData();
        userStoryData.setEstimation(3);
        userStoryData.setPbPriority(2);
        userStoryData.setUserStory("Test");
        userStoryData.setId(1);

        Response response = resource.registerUserStory(userStoryData);

        //Llamamos al metodo a testear
        response = resource.deleteUserStory(userStoryData);

        // Comprobamos la response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void testRegisterProyect()
    {
        // Preparamos el persistence manager para devolver el mock
        ProyectData proyect = new ProyectData();
        proyect.setIdProyect(5);

        // Simulamos que el objeto no esta en la BBDD
        when(persistenceManager.getObjectById(any())).thenThrow(new JDOObjectNotFoundException());
        
        // Preparamos el mock
        when(transaction.isActive()).thenReturn(true);

        //Llamamos al metodo a testear
        Response response = resource.registerProyect(proyect);

        // Comprobamos que se ha guardado correctamente
        ArgumentCaptor<Proyect> proyectCaptor = ArgumentCaptor.forClass(Proyect.class);
        verify(persistenceManager).makePersistent(proyectCaptor.capture());
        assertEquals(5, proyectCaptor.getValue().getId());

        // Comprobamos la response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void testDeleteProyect()
    {
        // Preparamos el persistence manager para devolver el mock
        ProyectData proyectData = new ProyectData();
        proyectData.setIdProyect(5);

        Response response = resource.registerProyect(proyectData);

        Proyect proyect = spy(Proyect.class);
        // Simulamos que el objeto no esta en la BBDD
        when(persistenceManager.getObjectById(Proyect.class, proyectData.getIdProyect())).thenReturn(proyect);

        //Llamamos al metodo a testear
        response = resource.deleteProyect(proyectData);

        // Comprobamos la response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }


    @Test
    public void testAssignUserStory(){

        // Simulamos que el objeto no esta en la BBDD
        @SuppressWarnings("unchecked") 
        Query<Sprint> query = mock(Query.class);
        when(persistenceManager.newQuery(Sprint.class)).thenReturn(query);

        Sprint sprint = new Sprint(1);
        when(query.execute(sprint.getSprintNum())).thenReturn(sprint);

        // Preparamos el persistence manager para devolver el mock
        SprintStoryData sprintStoryData = new SprintStoryData();
        UserStoryData userStoryData = new UserStoryData();
        userStoryData.setEstimation(3);
        userStoryData.setPbPriority(2);
        userStoryData.setUserStory("Test");
        userStoryData.setId(1);
        SprintData sp = new SprintData();
        sp.setSprintNum(1);
        sprintStoryData.setSprintData(sp);
        sprintStoryData.setUserStoryData(userStoryData);
        
        // Preparamos el comportamiento del mock
        when(transaction.isActive()).thenReturn(false);

        //Llamamos al metodo a testear
        Response response = resource.assignUserStory(sprintStoryData);

        // Comprobamos que se ha guardado correctamente
        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        verify(query).setFilter(stringCaptor.capture());
        assertEquals("this.num == :num", stringCaptor.getValue());

        ArgumentCaptor<Boolean> uniqueCaptor = ArgumentCaptor.forClass(Boolean.class);
        verify(query).setUnique(uniqueCaptor.capture());
        assertTrue(uniqueCaptor.getValue());

        // Comprobamos la response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }
    
    @Test
    public void testAssignSprint()
    {
        // Simulamos que el objeto no esta en la BBDD
        @SuppressWarnings("unchecked") 
        Query<Proyect> query = mock(Query.class);
        when(persistenceManager.newQuery(Proyect.class)).thenReturn(query);

        Proyect proyect = new Proyect(1);
        when(query.execute(proyect.getId())).thenReturn(proyect);

        // Preparamos el persistence manager para devolver el mock
        ProyectSprintData proyectSprintData = new ProyectSprintData();
        SprintData sprintData = new SprintData();
        sprintData.setSprintNum(1);
        ProyectData proyectData = new ProyectData();
        proyectData.setIdProyect(1);
        proyectSprintData.setSprintData(sprintData);
        proyectSprintData.setProyectData(proyectData);
        
        // Preparamos el comportamiento del mock
        when(transaction.isActive()).thenReturn(false);

        //Llamamos al metodo a testear
        Response response = resource.assignSprint(proyectSprintData);

        // Comprobamos que se ha guardado correctamente
        ArgumentCaptor<String> proyectCaptor = ArgumentCaptor.forClass(String.class);
        verify(query).setFilter(proyectCaptor.capture());
        assertEquals("this.id == :id", proyectCaptor.getValue());

        ArgumentCaptor<Boolean> uniqueCaptor = ArgumentCaptor.forClass(Boolean.class);
        verify(query).setUnique(uniqueCaptor.capture());
        assertTrue(uniqueCaptor.getValue());

        // Comprobamos la response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }


    @Test
    public void testReassignUserStory(){

        // Simulamos que el objeto no esta en la BBDD
        @SuppressWarnings("unchecked") 
        Query<Sprint> query = mock(Query.class);
        when(persistenceManager.newQuery(Sprint.class)).thenReturn(query);

        Sprint sprint = new Sprint(1);
        when(query.execute(sprint.getSprintNum())).thenReturn(sprint);

        // Preparamos el persistence manager para devolver el mock
        SprintStoryData sprintStoryData = new SprintStoryData();
        UserStoryData userStoryData = new UserStoryData();
        userStoryData.setEstimation(3);
        userStoryData.setPbPriority(2);
        userStoryData.setUserStory("Test");
        userStoryData.setId(1);
        SprintData sp = new SprintData();
        sp.setSprintNum(1);
        sprintStoryData.setSprintData(sp);
        sprintStoryData.setUserStoryData(userStoryData);
        
        // Preparamos el comportamiento del mock
        when(transaction.isActive()).thenReturn(false);

        //Llamamos al metodo a testear
        Response response = resource.reassignUserStory(sprintStoryData);

        // Comprobamos que se ha guardado correctamente
        ArgumentCaptor<String> stringCaptor = ArgumentCaptor.forClass(String.class);
        verify(query).setFilter(stringCaptor.capture());
        assertEquals("this.num == :num", stringCaptor.getValue());

        ArgumentCaptor<Boolean> uniqueCaptor = ArgumentCaptor.forClass(Boolean.class);
        verify(query).setUnique(uniqueCaptor.capture());
        assertTrue(uniqueCaptor.getValue());

        // Comprobamos la response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }

    @Test
    public void testReassignSprint()
    {
        // Simulamos que el objeto no esta en la BBDD
        @SuppressWarnings("unchecked") 
        Query<Proyect> query = mock(Query.class);
        when(persistenceManager.newQuery(Proyect.class)).thenReturn(query);

        Proyect proyect = new Proyect(1);
        when(query.execute(proyect.getId())).thenReturn(proyect);

        // Preparamos el persistence manager para devolver el mock
        ProyectSprintData proyectSprintData = new ProyectSprintData();
        SprintData sprintData = new SprintData();
        sprintData.setSprintNum(1);
        ProyectData proyectData = new ProyectData();
        proyectData.setIdProyect(1);
        proyectSprintData.setSprintData(sprintData);
        proyectSprintData.setProyectData(proyectData);
        
        // Preparamos el comportamiento del mock
        when(transaction.isActive()).thenReturn(false);

        //Llamamos al metodo a testear
        Response response = resource.reassignSprint(proyectSprintData);

        // Comprobamos que se ha guardado correctamente
        ArgumentCaptor<String> proyectCaptor = ArgumentCaptor.forClass(String.class);
        verify(query).setFilter(proyectCaptor.capture());
        assertEquals("this.id == :id", proyectCaptor.getValue());

        ArgumentCaptor<Boolean> uniqueCaptor = ArgumentCaptor.forClass(Boolean.class);
        verify(query).setUnique(uniqueCaptor.capture());
        assertTrue(uniqueCaptor.getValue());

        // Comprobamos la response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }


    @Test
    public void testGetAllUserStory(){

        // Preparamos el persistence manager para devolver el mock
        List<UserStory> us2 = new ArrayList<UserStory>();

        UserStoryData userStoryData = new UserStoryData();
        userStoryData.setEstimation(3);
        userStoryData.setPbPriority(2);
        userStoryData.setUserStory("Test");
        userStoryData.setId(1);

        @SuppressWarnings("unchecked")
        Query<UserStory> query = mock(Query.class);
        when(persistenceManager.newQuery(UserStory.class)).thenReturn(query);

        UserStory user = new UserStory(1, "Test", 3, 2);
        us2.add(user);
        resource.registerUserStory(userStoryData);

        when(query.executeList()).thenReturn(us2);

        //Llamamos al metodo a testear
        Response response = resource.getAllUserStories();

        // Comprobamos que se ha guardado correctamente
        String listString = response.getEntity().toString();

		Gson gson = new Gson();
        Type userStoryListType = new TypeToken<Collection<UserStory>>() {}.getType();
		List<UserStory> stories = gson.fromJson(listString, userStoryListType);
		// create the type for the collection. In this case define that the collection is of type Dataset
        assertEquals(stories.toString(), us2.toString());

        // Comprobamos la response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }


    /**
     * Test para comprobar que se devuelven todas las historias de usuario de un sprint
     */
    @Test
    public void testGetAllUserStoryFromSprint(){

        // Preparamos el persistence manager para devolver el mock
        List<UserStory> us2 = new ArrayList<UserStory>();

        SprintData sp = new SprintData();
        sp.setSprintNum(1);

        UserStoryData userStoryData = new UserStoryData();
        userStoryData.setEstimation(3);
        userStoryData.setPbPriority(2);
        userStoryData.setUserStory("Test");
        userStoryData.setId(1);
        sp.addUserStory(userStoryData);

        @SuppressWarnings("unchecked")
        Query<Sprint> query = mock(Query.class);
        when(persistenceManager.newQuery(Sprint.class)).thenReturn(query);

        us2.add(new UserStory(1, "Test", 3, 2));


        SprintStoryData sprintStoryData = new SprintStoryData();
        sprintStoryData.setSprintData(sp);
        sprintStoryData.setUserStoryData(userStoryData);

        UserStory user = new UserStory(1, "Test", 3, 2);
        us2.add(user);

        when(query.execute()).thenReturn(sp);

        //Llamamos al metodo a testear
        Response response = resource.getUserStoriesFromSprint(sp);

		// create the type for the collection. In this case define that the collection is of type Dataset
        
        assertEquals(sp.getUserStories().toString(), "["+user.toString()+"]");

        // Comprobamos la response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }
    
    @Test
    public void testGetAllSprints()
    {
        // Preparamos el persistence manager para devolver el mock
        List<Sprint> sprints = new ArrayList<Sprint>();

        SprintData sprintData = new SprintData();
        sprintData.setSprintNum(1);

        @SuppressWarnings("unchecked")
        Query<Sprint> query = mock(Query.class);
        when(persistenceManager.newQuery(Sprint.class)).thenReturn(query);

        Sprint sprint = new Sprint(1);
        sprints.add(sprint);
        resource.registerSprint(sprintData);

        when(query.executeList()).thenReturn(sprints);

        //Llamamos al metodo a testear
        Response response = resource.getAllSprints();

        // Comprobamos que se ha guardado correctamente
        String listString = response.getEntity().toString();

		Gson gson = new Gson();
        Type sprintListType = new TypeToken<Collection<UserStory>>() {}.getType();
		List<Sprint> sprintes = gson.fromJson(listString, sprintListType);
		// create the type for the collection. In this case define that the collection is of type Dataset
        assertEquals(sprintes.toString(), sprints.toString()); //expected:<[[User Story 0 (null): [Estimacion: 0. Prioridad: 0.]]]> but was:<[[Sprint 1: []]]>

        // Comprobamos la response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }


    /**
     * Test para comprobar que se devuelven todas las historias de usuario de un sprint
     */
    @Test
    public void testGetAllSprintsFromProyect()
    {
        // Preparamos el persistence manager para devolver el mock
        List<Sprint> sprints = new ArrayList<Sprint>();

        ProyectData proyectData = new ProyectData();
        proyectData.setIdProyect(1);

        SprintData sprintData = new SprintData();
        sprintData.setSprintNum(1);

        proyectData.addSprint(sprintData);

        @SuppressWarnings("unchecked")
        Query<Sprint> query = mock(Query.class);
        when(persistenceManager.newQuery(Sprint.class)).thenReturn(query);

        sprints.add(new Sprint(1));

        ProyectSprintData proyectSprintData = new ProyectSprintData();
        proyectSprintData.setProyectData(proyectData);
        proyectSprintData.setSprintData(sprintData);

        Sprint sprint = new Sprint(1);
        sprints.add(sprint);

        when(query.executeList()).thenReturn(sprints);

        //Llamamos al metodo a testear
        Response response = resource.getAllSprintsFromProyect(proyectData);

		// create the type for the collection. In this case define that the collection is of type Dataset
        assertEquals(proyectData.getSprints().toString(), "["+sprints.toString()+"]"); //expected:<[[Sprint 1. Start date: null, End date: null, Stories: []]]> but was:<[[[Sprint 1: [], Sprint 1: []]]]>

        // Comprobamos la response
        assertEquals(Response.Status.OK, response.getStatusInfo());
    }
    
}
