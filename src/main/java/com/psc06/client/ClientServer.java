package com.psc06.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import java.util.List;

import com.psc06.pojo.UserStoryData;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientServer {

	protected static final Logger logger = LogManager.getLogger();

	private Client client;
	private WebTarget webTarget;

	public ClientServer(String hostname, String port) {
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
			logger.info("User Story correctly created");
		}
	}
	public void createUserStory(int id, String userStory, int estimation, int pbPriority){
		UserStoryData usdAux = new UserStoryData();
		usdAux.setId(id);
		usdAux.setUserStory(userStory);
		usdAux.setEstimation(estimation);
		usdAux.setPbPriority(pbPriority);
		
		WebTarget createUserStoryWebTarget = webTarget.path("createUserStory");
		Invocation.Builder invocationBuilder = createUserStoryWebTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.post(Entity.entity(usdAux, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		} else {
			logger.info("User Story correctly created");
		}
	}
	public void deleteUserStory(int id){
		WebTarget createUserStoryWebTarget = webTarget.path("deleteUserStory");
		Invocation.Builder invocationBuilder = createUserStoryWebTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.post(Entity.entity(id, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		} else {
			logger.info("User Story correctly deleted");
		}
	}
	public void modifyUserStory(int id, String userStory, int estimation, int pbPriority){

	}
	public UserStoryData getUserStory(int id){
		return null;
	}
	public List<UserStoryData> getAllUserStorys(){
		return null;
	}
}
