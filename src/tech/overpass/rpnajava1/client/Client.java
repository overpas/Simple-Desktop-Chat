package tech.overpass.rpnajava1.client;

import java.net.InetAddress;

public class Client {

	private String serverIP;
	private int serverPort;
	private InetAddress ipAddress;
	private String userName;

	public Client(String serverIP, int serverPort, String userName) {
		this.serverIP = serverIP;
		this.serverPort = serverPort;
		this.userName = userName;
	}

	public Client() {
		this("localhost", 1234, "username");
	}

	public String getServerIP() {
		return serverIP;
	}
	
	public int getServerPort() {
		return serverPort;
	}
	
	public String getUserName() {
		return userName;
	}

}
