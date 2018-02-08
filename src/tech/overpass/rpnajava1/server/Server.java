package tech.overpass.rpnajava1.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import tech.overpass.rpnajava1.controllers.ChatController;
import tech.overpass.rpnajava1.util.LogConfigurator;

public class Server {

	public static final int PORT = 1234;
	public static Clients clients;
	public static LastMessage lastMessage;
	private static Logger messageLogger = Logger.getLogger(Server.class.getName());

	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		clients = new Clients();
		lastMessage = new LastMessage();
		System.out.println("Started: " + s);
		try {
			LogConfigurator.configure(messageLogger);
			while (true) {
				Socket socket = s.accept();
				try {
					System.out.println("Connected: " + socket);///
					clients.addUser(new ClientServiceThread(socket));
				} catch (IOException e) {
					System.out.println("Closing: " + socket);
					socket.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Logging failed");
		} finally {
			s.close();
		}
	}
	
	public static void logMessage(String completeMessage) {
		messageLogger.info(completeMessage);
	}
	
//	public static void addChatClient(ChatController chatController) {
//		clients.addChatClient(chatController);
//	}
}