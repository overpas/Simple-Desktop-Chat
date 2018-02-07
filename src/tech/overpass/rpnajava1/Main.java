package tech.overpass.rpnajava1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tech.overpass.rpnajava1.controllers.ConnectionController;

public class Main extends Application {

	private Stage stage;
	private FXMLLoader fxmlLoader;
	private ConnectionController connectionController;

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			fxmlLoader = new FXMLLoader();
			Parent root = fxmlLoader.load(getClass().getResource("ui/connection_window.fxml").openStream());
			Scene scene = new Scene(root);
			connectionController = (ConnectionController) fxmlLoader.getController();
			connectionController.setStage(stage);
			stage.setScene(scene);
			stage.getIcons().add(new Image("file:resources/images/icon.png"));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void switchToChatWindow() {
		try {
			Parent root = fxmlLoader.load(getClass().getResource("ui/chat_window.fxml").openStream());
			stage.getScene().setRoot(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
