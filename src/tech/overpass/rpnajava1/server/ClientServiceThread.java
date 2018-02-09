package tech.overpass.rpnajava1.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import tech.overpass.rpnajava1.model.Message;

class ClientServiceThread extends Thread {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private ObjectInputStream ois;
	private String username;

	public ClientServiceThread(Socket s) throws IOException {
		socket = s;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		ois = new ObjectInputStream(socket.getInputStream());
		start();
	}

	public void run() {
		try {
			username = in.readLine();
			while (!socket.isClosed()) {
				Message message = (Message) ois.readObject();
				DateFormat dateFormat = new SimpleDateFormat("hh:mm");
				String completeMessage = message.getName() + " [" 
						+ dateFormat.format(message.gerDate()) + "]> "
						+ message.getMessage();
				Server.clients.distribute(socket, completeMessage);
				Server.logMessage(completeMessage);
			}
		} catch (IOException e) {
			DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
			System.out.println("[" + dateFormat.format(new Date()) + "]> User " 
					+ username + " disconnected.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Class not found");
		} finally {
			System.out.println("Closing: " + socket);
			Server.clients.distribute(socket, "Left: " + username);
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Socket not closed");
			}
		}
	}

	public void sendMsg(String message) {
		out.println(message);
	}

	public Socket getSocket() {
		return socket;
	}
	
	public String getUserName() {
		return username;
	}
}
