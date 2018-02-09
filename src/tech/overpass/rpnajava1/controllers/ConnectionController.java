package tech.overpass.rpnajava1.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import tech.overpass.rpnajava1.client.Client;
import tech.overpass.rpnajava1.client.ClientThread;
import tech.overpass.rpnajava1.util.InputValidator;

public class ConnectionController implements Initializable {

	@FXML
	private Button btnConnect;
	@FXML
	private TextField txtfldIPAddress;
	@FXML
	private TextField txtfldPort;
	@FXML
	private TextField txtfldNickname;
	private Client client;
	private Stage stage;
	private ChatController chatController;
	private ClientThread clientThread;
	
	private void switchToChatWindow() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
			Parent root = fxmlLoader.load(getClass().getResource("../ui/chat_window.fxml")
					.openStream());
			Scene scene = new Scene(root);
			chatController = (ChatController) fxmlLoader.getController();
			chatController.setClient(this.client);
			synchronized (client) {
				clientThread.setChatController(chatController);
				client.notifyAll();
			}
			stage.setScene(scene);
			stage.centerOnScreen();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtfldIPAddress.setText("localhost");
		txtfldNickname.setText("username");
		txtfldPort.textProperty().addListener((observable, oldValue, newValue) -> {
			while (newValue.startsWith("0")) {
				newValue = newValue.replaceFirst("0", "");
				txtfldPort.setText(newValue);
			}
			if (!newValue.matches("\\d*")) {
				txtfldPort.setText(newValue.replaceAll("[^\\d]", ""));
			}
		});
		txtfldPort.setText("1234");
	}

	@FXML
	public void connect() {
		String serverIP = InputValidator.isIPAddressValid(txtfldIPAddress.getText()) ? 
				txtfldIPAddress.getText() : 
				"localhost";
		int serverPort = InputValidator.isPortNumberValid(txtfldPort.getText()) ? 
				Integer.parseInt(txtfldPort.getText()) : 
				1234;
		String userName = InputValidator.isUserNameValid(txtfldNickname.getText()) ?
				txtfldNickname.getText() :
				"username";
		client = new Client(serverIP, serverPort, userName);
		clientThread = new ClientThread(this);
		clientThread.start();
		switchToChatWindow();
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent windowEvent) {
				clientThread.interrupt();
				Platform.exit();
			}
		});
	}

	public Client getClient() {
		return client;
	}

}
