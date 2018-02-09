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

	public void distribute(Socket socket, String message, String exceptUserName) {
		for (ClientServiceThread cst : clients) {
			if (!cst.getUserName().equals(exceptUserName)) {
				cst.sendMsg(message);
			}
		}
	}
	
	public void distribute(Socket socket, String message) {
		for (ClientServiceThread cst : clients) {
			if (cst.getSocket() != socket) {
				cst.sendMsg(message);
			}
		}
	}

}