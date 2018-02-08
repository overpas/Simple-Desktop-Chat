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

//	private void setupInfo() {
//		setupServerIp();
//		setupServerPort();
//		setupUserName();
//	}

//	private void setupServerIp() {
//		System.out.println("Enter server ip address name (type anything for localhost):");
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		String ipString = "";
//		try {
//			ipString = reader.readLine();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		InetAddressValidator ipValidator = InetAddressValidator.getInstance();
//		if (ipValidator.isValid(ipString)) {
//			serverIP = ipString;
//			try {
//				ipAddress = InetAddress.getByName(serverIP);
//				System.out.println(ipAddress);
//			} catch (UnknownHostException e) {
//				e.printStackTrace();
//			}
//		} else {
//			serverIP = "localhost";
//			try {
//				ipAddress = InetAddress.getByName(serverIP);
//				System.out.println(ipAddress);
//			} catch (UnknownHostException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	private void setupServerPort() {
//		System.out.println("Enter server port (type anything for 1234):");
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		String portString = "";
//		try {
//			portString = reader.readLine();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		int portNumber = 1234;
//		try {
//			portNumber = Integer.parseInt(portString);
//		} catch (NumberFormatException e) {
//			serverPort = 1234;
//			return;
//		}
//		serverPort = portNumber;
//	}
//
//	private void setupUserName() {
//		System.out.println("Enter nickname:");
//		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//		String input = "";
//		try {
//			do {
//				input = reader.readLine();
//				if (isUserNameValid(input)) {
//					userName = input;
//					break;
//				} else {
//					System.out.println("Enter a proper nickname:");
//				}
//			} while (!isUserNameValid(input));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//	private boolean isUserNameValid(String userName) {
//		Pattern pattern = Pattern.compile("^[a-zA-Z0-9_-]{4,20}$");
//		return pattern.matcher(userName).matches();
//	}
	
//	public void connect() {
//		ClientThread clientThread = new ClientThread(this);
//		Thread thread = new Thread(clientThread);
//		thread.start();
//		try {
//			thread.join();
//		} catch (InterruptedException e) {
//			clientThread.stop();
//			thread.interrupt();
//			System.err.println("The chat window has been closed.");
//			e.printStackTrace();
//		}
//	}
	
	public String getServerIP() {
		return serverIP;
	}
	
	public int getServerPort() {
		return serverPort;
	}
	
	public String getUserName() {
		return userName;
	}

//	public static void main(String[] args) {
//		Client client = new Client();
//		client.setupInfo();
//		Thread clientThread = new Thread(new ClientThread(client));
//		clientThread.start();
//		try {
//			clientThread.join();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
}
