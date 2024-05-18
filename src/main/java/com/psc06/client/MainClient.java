package com.psc06.client;

import com.psc06.pojo.UserStoryData;
import com.psc06.client.ClientServer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainClient {

	private static final int sprintId = 1;

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

    protected static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
		
		if (args.length != 2) {
			logger.info("Use: java Client.Client [host] [port]");
			System.exit(0);
		}

		String hostname = args[0];
		String port = args[1];

		ClientServer conSer = new ClientServer(hostname, port);
		conSer.registerSprint(sprintId);
		conSer.registerUserStory(id1, userstory1, est1, pb1);
		conSer.registerUserStory(id2, userstory2, est2, pb2);
		conSer.assignUserStory(sprintId, id1, userstory1, est1, pb1);
		//conSer.getUserStoriesFromSprint(sprintId);
		//conSer.reassignUserStory(sprintId, id1, userstory1, est1, pb1);

		//conSer.getAllUserStories();
		
		 Interface interfaz = new Interface(conSer);

    // Mostrar la ventana
    interfaz.mostrarVentana();
	}
}
