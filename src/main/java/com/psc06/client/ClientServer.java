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
	public void registerUserStory(int id, String userStory, int estimation, int pbPriority) {
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
	}

	/**
	 * Register a new sprint
	 * @param id Sprint number
	 */
	public void registerSprint(int id) {
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
	}

	/**
	 * Assign a user story to a sprint
	 * @param sprintId Sprint number
	 * @param id User story id
	 * @param userStory User story description
	 * @param estimation User story estimation
	 * @param pbPriority User story priority
	 */
	public void assignUserStory(int sprintId, int id, String userStory, int estimation, int pbPriority) {
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
	}


	/**
	 * Delete a sprint
	 */
	public void deleteSprint(int sprintId) {
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
	}

	/**
	 * Delete a user story
	 */
	public void deleteUserStory(int id) {
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
	}

	/**
	 * Assign a user story to a sprint
	 * @param sprintId Sprint number
	 * @param id User story id
	 * @param userStory User story description
	 * @param estimation User story estimation
	 * @param pbPriority User story priority
	 */
	public void reassignUserStory(int sprintId, int id, String userStory, int estimation, int pbPriority) {
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
		
		for (UserStoryData us : stories) {
			logger.info(us.toString());
		}

		return stories;
	}
/*
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

*/
}
