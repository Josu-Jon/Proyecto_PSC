package com.psc06.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.psc06.pojo.UserStoryData;
import com.psc06.server.jdo.UserStory;

@Path("/resource")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {

	protected static final Logger logger = LogManager.getLogger();

	private int cont = 0;
	private PersistenceManager pm=null;
	private Transaction tx=null;

	public Resource() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
	}
	
	@POST
	@Path("/registerUserStory")
	public Response registerUserStory(UserStoryData userStoryData) {
		try
        {	
            tx.begin();
            logger.info("Checking whether the user already exits or not: '{}'", userStoryData.getId());
			UserStory story = null;
			try {
				story = pm.getObjectById(UserStory.class, userStoryData.getId());
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}

			logger.info("User Story: {}", story);

			if (story != null) {
				logger.info("Story already created: {}", story);
			} else {
				logger.info("Creating story: {}", story);
				story = new UserStory(userStoryData.getId(), userStoryData.getUserStory(), userStoryData.getEstimation(), userStoryData.getPbPriority());
				pm.makePersistent(story);					 
				logger.info("User Story created: {}", story);
			}
			tx.commit();
			return Response.ok().build();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
      
		}
	}

}

