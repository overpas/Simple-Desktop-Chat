package tech.overpass.rpnajava1.server;

import java.io.BufferedReader;
import java.io.IOException;

import tech.overpass.rpnajava1.controllers.ChatController;

public class ReadMessage implements Runnable {

	private BufferedReader in;
	private ChatController chatController;

	public ReadMessage(BufferedReader in, ChatController chatController) {
		this.in = in;
		this.chatController = chatController;
	}

	@Override
	public void run() {
		try {
			while (true) {
				String string = in.readLine();
				System.out.println(string);
				chatController.sendMessage(string);
			}
		} catch (IOException e) {
			System.err.println("Socket closed.");
		}
	}
}
