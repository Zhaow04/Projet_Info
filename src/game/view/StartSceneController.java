package game.view;

import game.model.GameModel;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class StartSceneController {
	
	private Stage stage;
	private Scene mainScene;
	
	@FXML
	private BorderPane startMenu;
	
	@FXML
	private AnchorPane configMenu;
	
	@FXML
	private Button startButton;
	
	@FXML
	private TextField mapSizeField;
	
	public StartSceneController() {

	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public Scene getMainScene() {
		return mainScene;
	}

	public void setMainScene(Scene mainScene) {
		this.mainScene = mainScene;
	}
	
	@FXML
	private void newGame() {
		startMenu.setVisible(false);
		configMenu.setVisible(true);
		//Stage stage = (Stage) startButton.getScene().getWindow();
		//stage.setScene(mainScene);
	}
	
	@FXML
	private void handleMapSizeInput() {
		if(isMapSizeInputValid()) {
			int mapSize = Integer.parseInt(mapSizeField.getText());
			GameModel model = new GameModel(mapSize);
			new GameView(model, stage);
		}
	}
	
	private boolean isMapSizeInputValid() {
		int mapSize = Integer.parseInt(mapSizeField.getText());
		if(mapSize > 0 && mapSize < 100) {
			return true;
		}
		else
			return false;
	}

}
