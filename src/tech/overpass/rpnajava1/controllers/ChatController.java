package tech.overpass.rpnajava1.controllers;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import tech.overpass.rpnajava1.client.Client;

public class ChatController implements Initializable {

	@FXML
	private TextField txtfldMessageInput;
	@FXML
	private TextArea txtAreaMessages;
	@FXML
	private TextArea txtAreaNicknameAndTime;
	private Client client;
	private String inputBeforeLastWipe = "";

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		txtfldMessageInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.ENTER)) {
					String message = txtfldMessageInput.getText();
					txtAreaMessages.appendText(message + System.lineSeparator());
					DateFormat formatter = new SimpleDateFormat("hh:mm");
					txtAreaNicknameAndTime.appendText(client.getUserName() + " [" 
							+ formatter.format(new Date()) + "]" + System.lineSeparator());
					inputBeforeLastWipe = txtfldMessageInput.getText();
					txtfldMessageInput.setText("");
					synchronized (client) {
						if (client != null) {
							client.notifyAll();
						}
					}
				}
			}
		});
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public String getInputBeforeLastWipe() {
		return inputBeforeLastWipe;
	}
	
	public void sendMessage(String message) {
		if (message.contains(">")) {
			String[] messageParts = message.split(">", 2);
			txtAreaNicknameAndTime.appendText(messageParts[0].trim() + System.lineSeparator());
			txtAreaMessages.appendText(messageParts[1].trim() + System.lineSeparator());
		} else {
			txtAreaNicknameAndTime.appendText(message + System.lineSeparator());
			txtAreaMessages.appendText(System.lineSeparator());
		}
	}

}
