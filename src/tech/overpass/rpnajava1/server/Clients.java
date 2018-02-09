package tech.overpass.rpnajava1.server;

import java.net.Socket;
import java.util.ArrayList;

class Clients {

	private ArrayList<ClientServiceThread> clients = new ArrayList<>();
	private int count = 0;
	
	public void addUser(ClientServiceThread client) {
		clients.add(client);
		count++;
	}

	public void distribute(Socket s, String message, String exceptUserName) {
		for (ClientServiceThread cst : clients) {
			if (!cst.getUserName().equals(exceptUserName)) {
				cst.sendMsg(message);
			}
		}
	}

}