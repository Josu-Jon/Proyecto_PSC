package com.psc06.server;

import static org.junit.Assert.assertEquals;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.experimental.categories.*;

import com.github.noconnor.junitperf.JUnitPerfRule;
import com.github.noconnor.junitperf.reporting.providers.HtmlReportGenerator;
import com.psc06.pojo.ProyectData;
import com.psc06.pojo.SprintData;
import com.psc06.pojo.UserStoryData;
import com.psc06.server.jdo.Sprint;
import com.psc06.server.jdo.UserStory;

import categories.IntegrationTest;

@Category(IntegrationTest.class)
public class ServerIT {
    
    private static final PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
    
    private static HttpServer server;
    private WebTarget target;

    @Rule
    public JUnitPerfRule perfTestRule = new JUnitPerfRule(new HtmlReportGenerator("target/junitperf/report.html"));

    @BeforeClass
    public static void prepareTests() throws Exception {
        // start the server
        server = Main.startServer();

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            pm.makePersistent(new Sprint(1));
            pm.makePersistent(new UserStory(1, "Test", 5, 5));
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    @Before
    public void setUp() {
        // create the client
        Client c = ClientBuilder.newClient();
        target = c.target(Main.BASE_URI).path("resource");
    }

    @AfterClass
    public static void tearDownServer() throws Exception {
        server.shutdown();

        PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx = pm.currentTransaction();
        try {
            tx.begin();
            pm.newQuery(Sprint.class).deletePersistentAll();
            pm.newQuery(UserStory.class).deletePersistentAll();
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
            pm.close();
        }
    }

    @Test
    public void testGetTest() {
        Response response = target.path("test").request().get();
        assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
        assertEquals("Test!", response.readEntity(String.class));
    }

    @Test
    public void testRegisterSprint() {
        SprintData sprint = new SprintData();
        sprint.setSprintNum(2);

        Response response = target.path("registerSprint")
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(sprint, MediaType.APPLICATION_JSON));

        assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
    }

    @Test
    public void testRegisterUserStory() {
        UserStoryData story = new UserStoryData();
        story.setId(2);
        story.setUserStory("Test de integracion");
        story.setEstimation(4);
        story.setPbPriority(2);

        Response response = target.path("registerUserStory")
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(story, MediaType.APPLICATION_JSON));

        assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
    }
    
    @Test
    public void testRegisterProyect() 
    {
        ProyectData proyect = new ProyectData();
        proyect.setIdProyect(2);

        Response response = target.path("registerProyect")
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(proyect, MediaType.APPLICATION_JSON));

        assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
    }

    @Test
    public void testDeleteUserStory() {
        UserStoryData us = new UserStoryData();
        us.setId(2);
        us.setUserStory("Test");
        us.setEstimation(4);
        us.setPbPriority(2);

        Response response = target.path("deleteUserStory")
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(us, MediaType.APPLICATION_JSON));

        assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
    }

    @Test
    public void testDeleteSprint() {
        SprintData sprint = new SprintData();
        sprint.setSprintNum(2);

        Response response = target.path("deleteSprint")
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(sprint, MediaType.APPLICATION_JSON));

        assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
    }

    @Test
    public void testDeleteProyect() 
    {
        ProyectData proyect = new ProyectData();
        proyect.setIdProyect(2);
    
        Response response = target.path("deleteProyect")
            .request(MediaType.APPLICATION_JSON)
            .post(Entity.entity(proyect, MediaType.APPLICATION_JSON));
    
        assertEquals(Family.SUCCESSFUL, response.getStatusInfo().getFamily());
    }
}
