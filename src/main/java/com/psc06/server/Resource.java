package com.psc06.server;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.JDOHelper;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.Transaction;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.psc06.pojo.SprintData;
import com.psc06.pojo.SprintStoryData;
import com.psc06.pojo.UserStoryData;
import com.psc06.server.jdo.Sprint;
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
	@Path("/registerSprint")
	public Response registerSprint(SprintData sprintData) {
		try
        {	
            tx.begin();
            logger.info("Checking whether the user already exits or not: '{}'", sprintData.getSprintNum());
			Sprint sprint = null;
			try {
				sprint = pm.getObjectById(Sprint.class, sprintData.getSprintNum());
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}
			
			if (sprint != null) {
				logger.info("Sprint already created: {}", sprint);
			} else {
				logger.info("Creating sprint: {}", sprint);
				sprint = new Sprint(sprintData.getSprintNum());
				pm.makePersistent(sprint);					 
				logger.info("Sprint created: {}", sprint);
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

	@POST
	@Path("/deleteSprint")
	public Response deleteSprint(SprintData sprintData) {
		try
        {	
            tx.begin();
            logger.info("Checking whether the user already exits or not: '{}'", sprintData.getSprintNum());
			Sprint sprint = null;
			try {
				sprint = pm.getObjectById(Sprint.class, sprintData.getSprintNum());
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}

			if (sprint != null) {
				logger.info("Deleting Sprint: {}", sprint);
				pm.deletePersistent(sprint);
			} else {
				logger.info("Sprint doesn't exist.");
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

			if (story != null) {
				logger.info("User Story: {}", story);
			}

			if (story != null) {
				logger.info("Story already created, updating values: {}", story);
				story.setUserStory(userStoryData.getUserStory());
				story.setPbPriority(userStoryData.getPbPriority());
				story.setEstimation(userStoryData.getEstimation());
				logger.info("Updated values: {}", story);
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

	@POST
	@Path("/deleteUserStory")
	public Response deleteUserStory(UserStoryData userStoryData) {
		try
        {	
            tx.begin();
            logger.info("Checking whether the user story already exits or not: '{}'", userStoryData.getId());
			UserStory story = null;
			try {
				story = pm.getObjectById(UserStory.class, userStoryData.getId());
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}

			if (story != null) {
				logger.info("Deleting User Story: {}", story.toString());
				pm.deletePersistent(story);
			} else {
				logger.info("User Story doesn't exist.");
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

	@POST
	@Path("/assignUserStory")
	public Response assignUserStory(SprintStoryData sprintStoryData) {
		Sprint sprint = null;
		UserStory story = null;
		try {
			tx.begin();
			logger.info("Creating query ...");

			try (Query<?> q = pm.newQuery(Sprint.class)) {
				q.setFilter("this.num == :num");
				q.setUnique(true);
				sprint = (Sprint) q.execute(sprintStoryData.getSprintData().getSprintNum());

				logger.info("Sprint retrieved: {}", sprint.toString());
				if (sprint != null) {

					
					try {
						story = pm.getObjectById(UserStory.class, sprintStoryData.getUserStoryData().getId());
					} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
						logger.info("Exception launched: {}", jonfe.getMessage());
					}

					if (story != null) {
						
						sprint.getAllStories().add(story);
						pm.makePersistent(sprint);

					} else {
						logger.info("Creating userstory: {}", story);
						story = new UserStory(sprintStoryData.getUserStoryData().getId(), sprintStoryData.getUserStoryData().getUserStory(),
											sprintStoryData.getUserStoryData().getEstimation(), sprintStoryData.getUserStoryData().getPbPriority());
						pm.makePersistent(story);
						sprint.getAllStories().add(story);
						pm.makePersistent(sprint);
						logger.info("Assigned story ! ");
					}


				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}

		if (sprint != null && story != null) {
			return Response.ok().build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity("Los datos implementados no son correctos").build();
		}
	}

	@POST
	@Path("/reassignUserStory")
	public Response reassignUserStory(SprintStoryData sprintStoryData) {
		Sprint sprint = null;
		UserStory story = null;
		try {
			tx.begin();
			logger.info("Creating query ...");

			try (Query<?> q = pm.newQuery(Sprint.class)) {
				q.setFilter("this.num == :num");
				q.setUnique(true);
				sprint = (Sprint) q.execute(sprintStoryData.getSprintData().getSprintNum());

				logger.info("Sprint retrieved: {}", sprint.toString());
				if (sprint != null) {

					
					try {
						story = pm.getObjectById(UserStory.class, sprintStoryData.getUserStoryData().getId());
					} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
						logger.info("Exception launched: {}", jonfe.getMessage());
					}

					if (story != null) {
						sprint.getAllStories().remove(story);
						pm.makePersistent(sprint);
						logger.info("Deleted story {} from sprint {}! ", story, sprint);
					} else {
						logger.info("Error. El user story introducido no existe. ");
					}
					return Response.ok().build();

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return Response.status(Status.BAD_REQUEST).entity("Los datos implementados no son correctos").build();

	}


	@SuppressWarnings("unchecked")
	@GET
	@Path("/getAllUserStories")
	public Query<UserStory> getAllUserStories() {

		Query<UserStory> queryUserStory = null;

		try {
			tx.begin();
			logger.info("Creating query ...");

			try (Query<?> q = pm.newQuery(UserStory.class)) {
				
				if (q != null) {
					queryUserStory = (Query<UserStory>) q.execute();
					logger.info("User Stories retrieved.");
				}
				
				if (queryUserStory != null) {
					return queryUserStory;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			tx.commit();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}

		return null;
	}

	/*
	@GET
	@Path("/getUserStory")
	public Response getUserStory(SprintData sprint) {
		
	}

	@POST
	@Path("/setUserStoryParams")
	public Response setUserStoryParams(UserStoryData userStoryData) {
		
	}
	*/
}

