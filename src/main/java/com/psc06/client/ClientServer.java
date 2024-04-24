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


import java.util.Scanner; //

public class ClientServer
{

	protected static final Logger logger = LogManager.getLogger();

	// Userstory 1
	private static final int id = 0;
	private static final String userstory = "";
	private static final int est = 0;
	private static final int pb = 0;

	// Userstory 2
    /*
	private static final int id2 = 2;
	private static final String userstory2 = "Crear pom";
	private static final int est2 = 2;
	private static final int pb2 = 7; */

	// Sprint 1
	private static final int sprintId = 1;

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

		//Scanner para leer los datos
		Scanner scanner = new Scanner(System.in);

        //
		System.out.println("Introduzca el nombre de la historia de usuario: ");
        String strUserStory = scanner.nextLine();
		
		System.out.println("Introduzca tiempo estimado: (en números)");
        String strEst = scanner.nextLine();
		int intEst = 0;  // Variable para almacenar el valor entero de strEst

        System.out.println("Introduzca la prioridad: (en números)");
        String strPb = scanner.nextLine();
        int intPb = 0;  // Variable para almacenar el valor entero de strPb

		//transformar de string a int
        try {
            intEst = Integer.parseInt(strEst);  // Convertir strEst a int
            intPb = Integer.parseInt(strPb);    // Convertir strPb a int
        } catch (NumberFormatException e) {
            System.out.println("Error: Uno de los valores introducidos no es un número válido.");
            // Manejo adicional de errores o salida del método, si es necesario
            scanner.close();
            return;  // Salir del método si hay un error
        }

		conSer.registerSprint(sprintId);
		conSer.registerUserStory(id, strUserStory, intEst, intPb);
		conSer.assignUserStory(sprintId, id, strUserStory, intEst, intPb);
		conSer.reassignUserStory(sprintId, id, strUserStory, intEst, intPb);

		System.out.println("User story creada");
	}
}
