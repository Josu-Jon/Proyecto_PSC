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
import com.psc06.pojo.SprintData;
import com.psc06.pojo.UserStoryData;
import com.psc06.pojo.SprintStoryData;

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

}
