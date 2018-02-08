package tech.overpass.rpnajava1.client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import tech.overpass.rpnajava1.controllers.ChatController;
import tech.overpass.rpnajava1.controllers.ConnectionController;
import tech.overpass.rpnajava1.model.Message;
import tech.overpass.rpnajava1.server.*;

public class ClientThread extends Thread {

	private Client client;
	private Thread readMessageThread;
	private ChatController chatController = null;

	public ClientThread(ConnectionController connectionController) {
		this.client = connectionController.getClient();
	}

	@Override
	public void run() {
		synchronized (client) {
			try {
				client.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			System.out.println(client.getServerIP() + ":" + client.getServerPort());
			Socket socket = new Socket(client.getServerIP(), client.getServerPort());

			try {
				System.out.println("Socket: " + socket);
				BufferedReader in =
						new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = 
						new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

				out.println(client.getUserName());

				readMessageThread = new Thread(new ReadMessage(in)); // поток чтения входящих сообщений
				readMessageThread.start();

//				while (true) { // поток ввода сообщения
//					String msg = txtfldInput.getText();
//					if (msg.equals("/exit")) {
//						readMessageThread.interrupt();
//						break;
//					}
//					Message message = new Message(client.getUserName(), msg, new Date());
//					oos.writeObject(message);
//				}
				
				while (true) {
					synchronized (client) {
						try {
							client.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						String userInput = chatController.getInputBeforeLastWipe();
						System.out.println("input = " + userInput);
						String msg = userInput;
						Message message = new Message(client.getUserName(), msg, new Date());
						oos.writeObject(message);
					}
				}
			}
			finally {
				System.out.println("Closing: " + socket);
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Cannot connect to server");
		}
	}
	
	public void setChatController(ChatController chatController) {
		this.chatController = chatController;
	}
	
}