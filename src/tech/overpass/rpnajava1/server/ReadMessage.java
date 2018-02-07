package tech.overpass.rpnajava1.server;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadMessage implements Runnable {

	BufferedReader in;

	public ReadMessage(BufferedReader in) {
		this.in = in;
	}

	@Override
	public void run() {
		try {
			while (true) {
				String s = in.readLine();
				System.out.println(s);
			}
		} catch (IOException e) {
			//e.printStackTrace();
			//System.err.println("Error I/O");
			System.err.println("Socket closed.");
		}
	}
}
