package tech.overpass.rpnajava1.server;

import java.io.PrintWriter;
import java.util.LinkedList;

class LastMessage {

	private LinkedList<String> messages = new LinkedList<>();

	public void add(String msg) {
		if (messages.size() < 10) {
			messages.add(msg);
		} else {
			messages.addLast(msg);
			messages.removeFirst();
		}
	}

	public void send(PrintWriter out) {
		for (String str : messages) {
			out.println(str);
		}
	}
	
}