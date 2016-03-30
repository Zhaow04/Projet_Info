package game.view;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartSceneController {
	
	public StartSceneController() {
		
	}
	
	private Scene mainScene;
	
	@FXML
	private Button startButton;
	
	@FXML
	private void start() {
		Stage stage = (Stage) startButton.getScene().getWindow();
		stage.setScene(mainScene);
	}

	public Scene getMainScene() {
		return mainScene;
	}

	public void setMainScene(Scene mainScene) {
		this.mainScene = mainScene;
	}

}
