import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;


public class MainTest {
    
    public static final String BASE_URI = "http://localhost:8080/rest/";
    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() throws Exception {

        ResourceConfig rc = new ResourceConfig().packages("com.psc06");
        server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);

        server.start();
        Client c = ClientBuilder.newClient();
        target = c.target(BASE_URI);
    }

    @Test
    public void testGetIt() {
        String responseMsg = target.path("resource/test").request().get(String.class); 
        assertEquals("Test!", responseMsg);
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }
}
