package game.controller;

import game.view.GameView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * Controller class for the start scene. The start menu allows the user to enter the size of the map.
 * 
 * @author ZhaoWen
 *
 */
public final class StartSceneController {
	
private GameView gameView;
	
	@FXML
	private BorderPane startMenu;
	
	@FXML
	private AnchorPane configMenu;
	
	@FXML
	private AnchorPane loadMenu;
	
	@FXML
	private Button startButton;
	
	@FXML
	private Button loadButton;
	
	@FXML
	private TextField mapSizeField;
	
	/**
	 * Void constructor.
	 */
	public StartSceneController() {

	}
	
	/**
	 * Connects the {@code StartSceneController} to the specified {@code GameView} in order to call the
	 * method {@link GameView#newGame} after the user enters a size for the map.
	 * @param gameView
	 */
	public void init(GameView gameView) {
		this.gameView = gameView;
	}
	
	/**
	 * Displays the configuration menu. Allows the user to enter a map size.
	 */
	@FXML
	private void newGame() {
		startMenu.setVisible(false);
		configMenu.setVisible(true);
	}
	
	/**
	 * Resets the start menu.
	 */
	private void reset() {
		mapSizeField.clear();
		startMenu.setVisible(true);
		configMenu.setVisible(false);
	}
	
	/**
	 * Loads the previously saved game.
	 */
	@FXML
	private void loadGame() {
		gameView.loadGame();
	}
	
	/**
	 * Handles the map size input. Creates a model with the map size input and the associated view.
	 */
	@FXML
	private void handleMapSizeInput() {
		if(isMapSizeInputValid()) {
			int mapSize = Integer.parseInt(mapSizeField.getText());
			gameView.newGame(mapSize);
			reset();
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
			if(mapSize >= 16 && mapSize <= 50)
				isValid = true;
		} catch (NumberFormatException e) {
			
		}
		return isValid;
	}
	
}
