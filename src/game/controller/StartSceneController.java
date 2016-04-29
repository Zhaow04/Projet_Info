package game.controller;

import game.model.GameModel;
import game.view.GameView;
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
	public GameView gameView;
	
	@FXML
	private BorderPane startMenu;
	
	@FXML
	private AnchorPane configMenu;
	
	@FXML
	private Button startButton;
	
	@FXML
	private TextField mapSizeField;
	
	/**
	 * Void constructor.
	 */
	public StartSceneController() {

	}

	/**
	 * Gets the stage.
	 * @return stage
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * Sets the stage.
	 * @param stage
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Gets the main scene.
	 * @return main scene
	 */
	public Scene getMainScene() {
		return mainScene;
	}

	/**
	 * Sets the main scene.
	 * @param mainScene
	 */
	public void setMainScene(Scene mainScene) {
		this.mainScene = mainScene;
	}
	
	/**
	 * Displays the configuration menu.
	 */
	@FXML
	private void newGame() {
		startMenu.setVisible(false);
		configMenu.setVisible(true);
		//Stage stage = (Stage) startButton.getScene().getWindow();
		//stage.setScene(mainScene);
	}
	
	/**
	 * Handles the map size input. Creates a model with the map size input and the associated view.
	 */
	@FXML
	private void handleMapSizeInput() {
		if(isMapSizeInputValid()) {
			int mapSize = Integer.parseInt(mapSizeField.getText());
			//GameModel model = new GameModel(mapSize);
			gameView.newGame(mapSize);
			//new GameView(model, stage);
			//model.startThreads();
		}
	}

	/**
	 * Returns whether or not the size input is valid, i.e. positive and less than 100.
	 * @return size input valid or not
	 */
	private boolean isMapSizeInputValid() {
		boolean isValid = false;
		int mapSize;
		try {
			mapSize = Integer.parseInt(mapSizeField.getText());
			if(mapSize > 0 && mapSize < 100)
				isValid = true;
		} catch (NumberFormatException e) {
			isValid = false;
		}
		return isValid;
	}

}
