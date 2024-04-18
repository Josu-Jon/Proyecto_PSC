package com.psc06.client;

import javax.jdo.Query;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.psc06.pojo.SprintData;
import com.psc06.pojo.SprintStoryData;
import com.psc06.pojo.UserStoryData;
import com.psc06.server.jdo.UserStory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExampleClient {

	protected static final Logger logger = LogManager.getLogger();

	// Userstory 1
	private static final int id1 = 1;
	private static final String userstory1 = "Crear cliente y servidor";
	private static final int est1 = 5;
	private static final int pb1 = 2;

	// Userstory 2
	private static final int id2 = 2;
	private static final String userstory2 = "Crear pom";
	private static final int est2 = 2;
	private static final int pb2 = 7;

	// Sprint 1
	private static final int sprintId = 1;

	private Client client;
	private WebTarget webTarget;

	public ExampleClient(String hostname, String port) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/resource", hostname, port));
	}

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


	public static void main(String[] args) {
		if (args.length != 2) {
			logger.info("Use: java Client.Client [host] [port]");
			System.exit(0);
		}

		String hostname = args[0];
		String port = args[1];

		ExampleClient conSer = new ExampleClient(hostname, port);
		conSer.registerSprint(sprintId);
		conSer.registerUserStory(id1, userstory1, est1, pb1);
		conSer.registerUserStory(id2, userstory2, est2, pb2);
		conSer.assignUserStory(sprintId, id1, userstory1, est1, pb1);
		conSer.reassignUserStory(sprintId, id1, userstory1, est1, pb1);
		
	}
}
