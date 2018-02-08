package tech.overpass.rpnajava1.server;

import java.net.Socket;
import java.util.ArrayList;

import tech.overpass.rpnajava1.controllers.ChatController;

class Clients {

	private ArrayList<ClientServiceThread> clients = new ArrayList<>();
	private int count = 0;
	
	public void addUser(ClientServiceThread client) {
		clients.add(client);
		count++;
	}

	public void distribute(Socket s, String message) {
		for (ClientServiceThread cst : clients) {
			cst.sendMsg(message);
		}
	}

}