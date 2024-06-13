package com.psc06.client;

import java.util.*;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import javax.jdo.Query;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.GenericType;

import com.psc06.pojo.ProyectData;
import com.psc06.pojo.ProyectSprintData;
import com.psc06.pojo.SprintData;
import com.psc06.pojo.SprintStoryData;
import com.psc06.pojo.UserStoryData;
import com.psc06.server.jdo.UserStory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * ClientServer class. Defines the conection between client and server.
 */
public class ClientServer {

	protected static final Logger logger = LogManager.getLogger();

	private Client client;
	private WebTarget webTarget;

	/**
	 * Constructor
	 * @param hostname Hostname
	 * @param port Port
	 * @return ClientServer conection
	 
	 */
	public ClientServer(String hostname, String port) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
	}

	/**
	 * Register a new user story
	 * @param id User story id
	 * @param userStory User story description
	 * @param estimation User story estimation
	 * @param pbPriority User story priority
	 */
	public Response registerUserStory(int id, String userStory, int estimation, int pbPriority) {
		WebTarget registerUserWebTarget = webTarget.path("registerUserStory");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		
		UserStoryData newStory = new UserStoryData();
		newStory.setId(id);
		newStory.setUserStory(userStory);
		newStory.setEstimation(estimation);
		newStory.setPbPriority(pbPriority);
		Response response = invocationBuilder.post(Entity.entity(newStory, MediaType.APPLICATION_JSON));

		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		} else {
			logger.info("User Story correctly created. ");
		}
		return Response.ok().build();
	}

	/**
	 * Register a new sprint
	 * @param id Sprint number
	 * @return Response
	 */
	public Response registerSprint(int id) {
		WebTarget registerUserWebTarget = webTarget.path("registerSprint");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		
		SprintData sp = new SprintData();
		sp.setSprintNum(id);

		Response response = invocationBuilder.post(Entity.entity(sp, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		} else {
			logger.info("Sprint correctly created. ");
		}
		return Response.ok().build();
	}

	/**
	 * Register a new proyect
	 * @param idProyect Proyect id
	 * @return Response
	 */
	public Response registerProyect(int idProyect)
	{
		WebTarget registerProyecTarget=webTarget.path("registerProyect");
		Invocation.Builder invocationBuilder=registerProyecTarget.request(MediaType.APPLICATION_JSON);

		ProyectData newProyect=new ProyectData();
		newProyect.setIdProyect(idProyect);

		Response response = invocationBuilder.post(Entity.entity(newProyect, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		} else {
			logger.info("Proyect correctly created. ");
		}
		return Response.ok().build();
	}


	/**
	 * Assign a user story to a sprint
	 * @param sprintId Sprint number
	 * @param id User story id
	 * @param userStory User story description
	 * @param estimation User story estimation
	 * @param pbPriority User story priority
	 * @return Response
	 */
	public Response assignUserStory(int sprintId, int id, String userStory, int estimation, int pbPriority) {
		WebTarget registerUserWebTarget = webTarget.path("assignUserStory");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		
		SprintData sp = new SprintData();
		sp.setSprintNum(sprintId);
		UserStoryData newStory = new UserStoryData();
		newStory.setId(id);
		newStory.setUserStory(userStory);
		newStory.setEstimation(estimation);
		newStory.setPbPriority(pbPriority);
		SprintStoryData sprintStory = new SprintStoryData();
		sprintStory.setSprintData(sp);
		sprintStory.setUserStoryData(newStory);

		Response response = invocationBuilder.post(Entity.entity(sprintStory, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		} else {
			logger.info("User Story correctly assigned. ");
		}
		return Response.ok().build();
	}

	/**
	 * Assign sprints to a proyect
	 * @param sprintId Sprint number
	 * @param proyectId Proyect id
	 * @return Response
	 */
	public Response assignSprint(int sprintId, int proyectId) 
	{
		WebTarget registerUserWebTarget = webTarget.path("assignSprint");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		
		SprintData sp = new SprintData();
		sp.setSprintNum(sprintId);
		ProyectData newProyect = new ProyectData();
		newProyect.setIdProyect(proyectId);
		ProyectSprintData proyectSprint = new ProyectSprintData();
		proyectSprint.setSprintData(sp);
		proyectSprint.setProyectData(newProyect);

		Response response = invocationBuilder.post(Entity.entity(proyectSprint, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		} else {
			logger.info("User Story correctly assigned. ");
		}
		return Response.ok().build();
	}

	/**
	 * Delete a sprint
	 * @param sprintId Sprint number
	 * @return Response
	 */
	public Response deleteSprint(int sprintId) {
		WebTarget registerUserWebTarget = webTarget.path("deleteSprint");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);

		SprintData sp = new SprintData();
		sp.setSprintNum(sprintId);

		Response response = invocationBuilder.post(Entity.entity(sp, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		} else {
			logger.info("Sprint " + sp.getSprintNum() + " deleted. ");
		}
		return Response.ok().build();
	}

	/**
	 * Delete a user story
	 * @param id User story id
	 * @return Response
	 */
	public Response deleteUserStory(int id) {
		WebTarget registerUserWebTarget = webTarget.path("deleteUserStory");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);

		UserStoryData story = new UserStoryData();
		story.setId(id);

		Response response = invocationBuilder.post(Entity.entity(story, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		} else {
			logger.info("User Story " + story.getId() + " deleted. ");
		}
		return Response.ok().build();
	}
	
	/**
	 * Delete a proyect
	 * @param proyectId Proyect id
	 * @return Response
	 */
	public Response deleteProyect(int proyectId) {
		WebTarget registerUserWebTarget = webTarget.path("deleteSprint");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);

		ProyectData proyect = new ProyectData();
		proyect.setIdProyect(proyectId);

		Response response = invocationBuilder.post(Entity.entity(proyect, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		} else {
			logger.info("Proyect " + proyect.getIdProyect() + " deleted. ");
		}
		return Response.ok().build();
	}

	/**
	 * Modify a user story
	 * @param id User story id
	 * @param userStory User story description
	 * @param estimation User story estimation
	 * @param pbPriority User story priority
	 * @return Response
	 */
	public Response modifyUserStory(int id, String userStory, int estimation, int pbPriority){
		deleteUserStory(id);
		return registerUserStory(id, userStory, estimation, pbPriority);
	}

	/**
	 * Modify a sprint
	 * @param id Sprint number
	 * @return Response
	 */
	public Response modifySprint(int id){
		deleteSprint(id);
		return registerSprint(id);
	}
	
	/**
	 * Modify a proyect
	 * @param id proyect id
	 * @return Response
	 */
	public Response modifyProyect(int id){
		deleteProyect(id);
		return registerProyect(id);
	}


	/**
	 * Reassign a user story to a sprint
	 * @param sprintId Sprint number
	 * @param id User story id
	 * @param userStory User story description
	 * @param estimation User story estimation
	 * @param pbPriority User story priority
	 * @return Response
	 */
	public Response reassignUserStory(int sprintId, int id, String userStory, int estimation, int pbPriority) {
		WebTarget registerUserWebTarget = webTarget.path("reassignUserStory");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		
		SprintData sp = new SprintData();
		sp.setSprintNum(sprintId);
		UserStoryData newStory = new UserStoryData();
		newStory.setId(id);
		newStory.setUserStory(userStory);
		newStory.setEstimation(estimation);
		newStory.setPbPriority(pbPriority);
		SprintStoryData sprintStory = new SprintStoryData();
		sprintStory.setSprintData(sp);
		sprintStory.setUserStoryData(newStory);

		Response response = invocationBuilder.post(Entity.entity(sprintStory, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		} else {
			logger.info("User Story " + id + " correctly deleted from sprint " + sprintId + ". ");
		}
		return Response.ok().build();
	}
	
	/**
	 * Reassign a sprint to a proyect
	 * @param sprintId Sprint number
	 * @param proyectId proyect id
	 * @return Response
	 */
	public Response reassignSprints(int sprintId, int proyectId)
	{
		WebTarget registerUserWebTarget = webTarget.path("reassignUserStory");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		
		SprintData sp = new SprintData();
		sp.setSprintNum(sprintId);
		ProyectData newProyect = new ProyectData();
		newProyect.setIdProyect(proyectId);
		ProyectSprintData proyectSprint = new ProyectSprintData();
		proyectSprint.setSprintData(sp);
		proyectSprint.setProyectData(newProyect);

		Response response = invocationBuilder.post(Entity.entity(proyectSprint, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		} else {
			logger.info("Sprint " + sprintId + " correctly deleted from proyect " + proyectId + ". ");
		}
		return Response.ok().build();
	}

	/**
	 * Get all user stories
	 * @return List of user stories
	 */
	public List<UserStoryData> getAllUserStories() {

		WebTarget registerUserWebTarget = webTarget.path("getAllUserStories");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.get();
		String listString= response.readEntity(String.class);

		Gson gson = new Gson();
		// create the type for the collection. In this case define that the collection is of type Dataset
		Type userStoryDataListType = new TypeToken<Collection<UserStoryData>>() {}.getType();
		List<UserStoryData> stories = gson.fromJson(listString, userStoryDataListType);

		if(stories != null){
			for (UserStoryData us : stories) {
				logger.info(us.toString());
			}
		}else{
			logger.error("Not found any User Story");
		}
		

		return stories;
	}
	

		public List<UserStoryData> getUserStoriesFromSprint(int sprintId) {

			WebTarget registerUserWebTarget = webTarget.path("registerSprint");
			Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
			
			SprintData sp = new SprintData();
			sp.setSprintNum(sprintId);

			Response response = invocationBuilder.post(Entity.entity(sp, MediaType.APPLICATION_JSON));

			String listString = response.readEntity(String.class);

			logger.info(listString);

			Gson gson = new Gson();
			// create the type for the collection. In this case define that the collection is of type Dataset
			Type userStoryDataListType = new TypeToken<Collection<UserStoryData>>() {}.getType();
			List<UserStoryData> stories = gson.fromJson(listString, userStoryDataListType);
			
			return stories;
		}

	}
