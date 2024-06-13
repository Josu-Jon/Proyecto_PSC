package com.psc06.server;

import java.util.*;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.JDOHelper;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.Transaction;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.psc06.pojo.ProyectData;
import com.psc06.pojo.ProyectSprintData;
import com.psc06.pojo.SprintData;
import com.psc06.pojo.SprintStoryData;
import com.psc06.pojo.UserStoryData;
import com.psc06.server.jdo.Proyect;
import com.psc06.server.jdo.Sprint;
import com.psc06.server.jdo.UserStory;

import com.google.gson.Gson;

/**
 * Main Class for server conection and functions.
 */
@Path("/resource")
@Produces(MediaType.APPLICATION_JSON)
public class Resource {

	protected static final Logger logger = LogManager.getLogger();

	private int cont = 0;
	private PersistenceManager pm = null;
	private Transaction tx = null;

	/**
	 * Constructor for the Resource class.
	 */
	public Resource() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
	}

	/**
	 * Method to register a new sprint.
	 * 
	 * @param sprintData Get a serialized sprint
	 * @return Response with the status of the operation.
	 */
	@POST
	@Path("/registerSprint")
	public Response registerSprint(SprintData sprintData) {
		try {
			tx.begin();
			logger.info("Checking whether the user already exits or not: '{}'", sprintData.getSprintNum());
			Sprint sprint = null;
			try {
				sprint = pm.getObjectById(Sprint.class, sprintData.getSprintNum());
			} catch (JDOObjectNotFoundException jonfe) {
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
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

		}
	}

	/**
	 * Method to delete a sprint.
	 * 
	 * @param sprintData Get a serialized sprint
	 * @return Status of the operation.
	 */
	@POST
	@Path("/deleteSprint")
	public Response deleteSprint(SprintData sprintData) {
		try {
			tx.begin();
			logger.info("Checking whether the Sprint already exits: '{}'", sprintData.getSprintNum());
			Sprint sprint = null;
			try {
				sprint = pm.getObjectById(Sprint.class, sprintData.getSprintNum());
			} catch (JDOObjectNotFoundException jonfe) {
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
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

		}
	}

	/**
	 * Method to register a new user story.
	 * 
	 * @param userStoryData Get a serialized user story
	 * @return Status of the operation.
	 */
	@POST
	@Path("/registerUserStory")
	public Response registerUserStory(UserStoryData userStoryData) {
		try {
			tx.begin();
			logger.info("Checking whether the UserStory already exits or not: '{}'", userStoryData.getId());
			UserStory story = null;
			try {
				story = pm.getObjectById(UserStory.class, userStoryData.getId());
			} catch (JDOObjectNotFoundException jonfe) {
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
				story = new UserStory(userStoryData.getId(), userStoryData.getUserStory(),
						userStoryData.getEstimation(), userStoryData.getPbPriority());
				pm.makePersistent(story);
				logger.info("User Story created: {}", story);
			}
			tx.commit();
			return Response.ok().build();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

		}
	}

	/**
	 * Method to delete a user story.
	 * 
	 * @param userStoryData Get a serialized user story
	 * @return Status of the operation.
	 */
	@POST
	@Path("/deleteUserStory")
	public Response deleteUserStory(UserStoryData userStoryData) {
		try {
			tx.begin();
			logger.info("Checking whether the userstory already exits or not: '{}'", userStoryData.getId());
			UserStory story = null;
			try {
				story = pm.getObjectById(UserStory.class, userStoryData.getId());
			} catch (JDOObjectNotFoundException jonfe) {
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
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

		}
	}

	/**
	 * Method to register a new proyect.
	 * 
	 * @param proyectData Get a serialized proyect
	 * @return Response with the status of the operation.
	 */
	@POST
	@Path("/registerProyect")
	public Response registerProyect(ProyectData proyectData)
	{
		try {
			tx.begin();
			logger.info("Checking whether the user already exits or not: '{}'", proyectData.getIdProyect());
			Proyect proyect = null;
			try {
				proyect = pm.getObjectById(Proyect.class, proyectData.getIdProyect());
			} catch (JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}

			if (proyect != null) {
				logger.info("Proyect already created: {}", proyect);
			} else {
				logger.info("Creating sprint: {}", proyect);
				proyect = new Proyect(proyectData.getIdProyect());
				pm.makePersistent(proyect);
				logger.info("Sprint created: {}", proyect);
			}
			tx.commit();
			return Response.ok().build();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

		}
	}

	/**
	 * Method to delete a proyect.
	 * 
	 * @param proyectData Get a serialized proyect
	 * @return Status of the operation.
	 */
	@POST
	@Path("/deleteProyect")
	public Response deleteProyect(ProyectData proyectData) 
	{
		try {
			tx.begin();
			logger.info("Checking whether the proyect already exits: '{}'", proyectData.getIdProyect());
			Proyect proyect = null;
			try {
				proyect = pm.getObjectById(Proyect.class, proyectData.getIdProyect());
			} catch (JDOObjectNotFoundException jonfe) {
				logger.info("Exception launched: {}", jonfe.getMessage());
			}

			if (proyect != null) {
				logger.info("Deleting proyect: {}", proyect);
				pm.deletePersistent(proyect);
			} else {
				logger.info("Proyect doesn't exist.");
			}
			tx.commit();
			return Response.ok().build();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}

		}
	}

	/**
	 * Method to assign a user story to a sprint.
	 * 
	 * @param sprintStoryData Get a serialized sprint and user story
	 * @return Status of the operation.
	 */
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
					} catch (JDOObjectNotFoundException jonfe) {
						logger.info("Exception launched: {}", jonfe.getMessage());
					}

					if (story != null) {

						sprint.getAllStories().add(story);
						pm.makePersistent(sprint);

					} else {
						logger.info("Creating userstory: {}", story);
						story = new UserStory(sprintStoryData.getUserStoryData().getId(),
								sprintStoryData.getUserStoryData().getUserStory(),
								sprintStoryData.getUserStoryData().getEstimation(),
								sprintStoryData.getUserStoryData().getPbPriority());
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

	/**
	 * Method to assign a sprint to a proyect.
	 * 
	 * @param sprintStoryData Get a serialized sprint and proyect
	 * @return Status of the operation.
	 */
	@POST
	@Path("/assignSprint")
	public Response assignSprint(ProyectSprintData proyectSprintData)
	{
		Proyect proyect = null;
		Sprint sprint = null;
		try {
			tx.begin();
			logger.info("Creating query ...");

			try (Query<?> q = pm.newQuery(Proyect.class)) {
				q.setFilter("this.id == :id");
				q.setUnique(true);
				proyect = (Proyect) q.execute(proyectSprintData.getProyectData().getIdProyect());

				logger.info("Proyect retrieved: {}", proyect.toString());
				if (proyect != null) 
				{
					try {
						sprint = pm.getObjectById(Sprint.class, proyectSprintData.getSprintData().getSprintNum());
					} catch (JDOObjectNotFoundException jonfe) {
						logger.info("Exception launched: {}", jonfe.getMessage());
					}

					if (sprint != null)
					{
						proyect.getAllSprints().add(sprint);
						pm.makePersistent(proyect);

					} 
					else
					{
						logger.info("Creating sprint: {}", sprint);
						sprint = new Sprint(proyectSprintData.getSprintData().getSprintNum()); //?
						pm.makePersistent(sprint);
						proyect.getAllSprints().add(sprint);
						pm.makePersistent(proyect);
						logger.info("Assigned sprint ! ");
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

		if (proyect != null && sprint != null) {
			return Response.ok().build();
		} else {
			return Response.status(Status.BAD_REQUEST).entity("Los datos implementados no son correctos").build();
		}
	}

	/**
	 * Method to reassign a user story to a sprint.
	 * 
	 * @param sprintStoryData Get a serialized sprint and user story
	 * @return Status of the operation.
	 */
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
					} catch (JDOObjectNotFoundException jonfe) {
						logger.info("Exception launched: {}", jonfe.getMessage());
					}

					if (story != null) {
						sprint.getAllStories().remove(story);
						pm.makePersistent(sprint);
						logger.info("Deleted story {} from sprint {}! ", story, sprint);
					} else {
						logger.info("Error. El user story introducido no existe. ");
					}
					tx.commit();
					return Response.ok().build();

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return Response.status(Status.BAD_REQUEST).entity("Los datos implementados no son correctos").build();

	}
	
	/**
	 * Method to reassign a sprint to a proyect.
	 * 
	 * @param sprintStoryData Get a serialized sprint and user story
	 * @return Status of the operation.
	 */
	@POST
	@Path("/reassignSprint")
	public Response reassignSprint(ProyectSprintData proyectSprintData)
	{
		Proyect proyect = null;
		Sprint sprint = null;
		try {
			tx.begin();
			logger.info("Creating query ...");

			try (Query<?> q = pm.newQuery(Proyect.class)) {
				q.setFilter("this.id == :id");
				q.setUnique(true);
				proyect = (Proyect) q.execute(proyectSprintData.getProyectData().getIdProyect());

				logger.info("Proyect retrieved: {}", proyect.toString());
				if (proyect != null) 
				{
					try {
						sprint = pm.getObjectById(Sprint.class, proyectSprintData.getSprintData().getSprintNum());
					} catch (JDOObjectNotFoundException jonfe) {
						logger.info("Exception launched: {}", jonfe.getMessage());
					}

					if (sprint != null) 
					{
						proyect.getAllSprints().remove(sprint);
						pm.makePersistent(proyect);
						logger.info("Deleted sprint {} from proyect {}! ", sprint, proyect);
					} 
					else
					{
						logger.info("Error. El user sprint introducido no existe. ");
					}
					tx.commit();
					return Response.ok().build();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
		return Response.status(Status.BAD_REQUEST).entity("Los datos implementados no son correctos").build();
	}

	/**
	 * Method to get all user stories.
	 * 
	 * @return List of user stories and status of the operation.
	 */
	@GET
	@Path("/getAllUserStories")
	public Response getAllUserStories() {

		List<UserStory> userStories = null;

		try {
			tx.begin();
			logger.info("Creating query ...");

			try (Query<UserStory> q = pm.newQuery(UserStory.class)) {

				userStories = q.executeList();

				logger.info("User Stories retrieved: -> {}", userStories.toString());

				Gson aux = new Gson();
				String returnedJson = aux.toJson(userStories);

				tx.commit();
				return Response.ok(returnedJson).build();

			} catch (Exception e) {
				e.printStackTrace();
			}

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}

		return Response.status(Status.EXPECTATION_FAILED).entity(null).build();
	}

	@POST
	@Path("/getAllUserStoriesFromSprint")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserStoriesFromSprint(SprintData sprintId) {

		List<UserStory> userStories = null;
		Sprint sp = null;

		try {
			tx.begin();
			logger.info("Checking whether the sprint already exits: '{}'", sprintId);

			try {
				sp = pm.getObjectById(Sprint.class, sprintId.getSprintNum());

			} catch (JDOObjectNotFoundException jonfe) {

				logger.info("Exception launched: {}", jonfe.getMessage());

			}

			if (sp != null) {

				userStories = new ArrayList<UserStory>(sp.getAllStories());

				logger.info("User Stories retrieved: -> {}", userStories.toString());

				Gson aux = new Gson();
				String returnedJson = aux.toJson(userStories);

				return Response.ok(returnedJson).build();
			} else {
				logger.info("Sprint no existe");
			}
			tx.commit();

			return Response.ok().build();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
	}

	/**
	 * Method to get all sprints.
	 * 
	 * @return List of sprints and status of the operation.
	 */
	@GET
	@Path("/getAllSprints")
	public Response getAllSprints() 
	{
		List<Sprint> sprints = null;

		try {
			tx.begin();
			logger.info("Creating query ...");

			try (Query<Sprint> q = pm.newQuery(Sprint.class)) {

				sprints = q.executeList();

				logger.info("Sprints retrieved: -> {}", sprints.toString());

				Gson aux = new Gson();
				String returnedJson = aux.toJson(sprints);

				tx.commit();
				return Response.ok(returnedJson).build();

			} catch (Exception e) {
				e.printStackTrace();
			}

		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}

		return Response.status(Status.EXPECTATION_FAILED).entity(null).build();
	}

	@POST
	@Path("/getAllSprintsFromProyect")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllSprintsFromProyect(ProyectData proyectId) 
	{
		List<Sprint> sprints = null;
		Proyect pr = null;

		try {
			tx.begin();
			logger.info("Checking whether the proyect already exits: '{}'", proyectId);

			try {
				pr = pm.getObjectById(Proyect.class, proyectId.getIdProyect());

			} catch (JDOObjectNotFoundException jonfe) {

				logger.info("Exception launched: {}", jonfe.getMessage());

			}

			if (pr != null)
			{
				sprints = new ArrayList<Sprint>(pr.getAllSprints());

				logger.info("Sprints retrieved: -> {}", sprints.toString());

				Gson aux = new Gson();
				String returnedJson = aux.toJson(sprints);

				return Response.ok(returnedJson).build();
			} else {
				logger.info("Proyecto no existe");
			}
			tx.commit();

			return Response.ok().build();
		} finally {
			if (tx.isActive()) {
				tx.rollback();
			}
		}
	}

	/**
	 * Test method on server.
	 * 
	 * @return Status of the operation.
	 */
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public Response sayTest() {
		return Response.ok("Test!").build();
	}
}
