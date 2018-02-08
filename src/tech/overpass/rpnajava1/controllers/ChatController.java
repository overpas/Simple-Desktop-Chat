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
import tech.overpass.rpnajava1.server.Server;

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
					// TODO
					String previousMessages = txtAreaMessages.getText();
					String previousNicknamesAndDates = txtAreaNicknameAndTime.getText();
					String message = txtfldMessageInput.getText();
					txtAreaMessages.setText(previousMessages + System.lineSeparator() + message);
					DateFormat formatter = new SimpleDateFormat("hh:mm");
					txtAreaNicknameAndTime.setText(previousNicknamesAndDates + System.lineSeparator()
							+ client.getUserName() + " [" + formatter.format(new Date()) + "]");
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

	public TextField getMessageInputTextField() {
		return txtfldMessageInput;
	}
	
	public String getInputBeforeLastWipe() {
		return inputBeforeLastWipe;
	}

}
