package game.controller;

import java.io.IOException;

import game.Main;
import game.model.Observable;
import game.model.Player;
import game.view.Observer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Implements {@link Observer}. <br/>
 * Controller class for the HUD. Displays the health, the experience and the level of the player, as well as the buttons P,S,I.
 * 
 */
public class HUDController implements Observer {
	
	//****************************** Attributes ******************************

	private Player player;
	private GameController gameController;
	private InventoryViewController inventoryViewController;
	private Stage inventoryWindow;
	
	@FXML
	private ProgressBar healthBar;
	@FXML
	private ProgressBar xpBar;
	@FXML
	private Text lvl;
	@FXML
	private Button pauseButton;
	@FXML
	private Button inventoryButton;
	@FXML
	private Button saveButton;
	
	//****************************** Constructor ******************************

	/**
	 * Void constructor.
	 */
	public HUDController() {
		
	}
	
	//******************************** Methods ********************************

	/**
	 * Sets the {@link GameController}.
	 * @param gameController
	 */
	public void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	/**
	 * Initializes the HUD (health bar, experience bar, level, inventory window).
	 * @param player
	 */
	public void init(Player player) {
		this.player = player;
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/InventoryView.fxml"));
			AnchorPane inventoryView = (AnchorPane) loader.load();
			inventoryViewController = (InventoryViewController) loader.getController();
			
			Scene inventoryScene = new Scene(inventoryView);
			inventoryWindow = new Stage();
			inventoryWindow.setScene(inventoryScene);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		inventoryViewController.init(player);
		player.addObserver(this);
		double d = ((double) player.getHp())/player.getMaxHp();
		healthBar.setProgress(d);
		double d1 = ((double) player.getXp())/player.getXpToLevelUp();
		xpBar.setProgress(d1);
		lvl.setText("Nv. " + player.getLevel());
	}
	
	/**
	 * See {@link GameController#pauseAndStart()}.
	 */
	@FXML
	private void pauseAndStart() {
		gameController.pauseAndStart();
	}
	
	/**
	 * Shows the inventory window.
	 */
	@FXML
	private void openInventory() {
		if(inventoryWindow.getOwner() == null) {
			inventoryWindow.initOwner(inventoryButton.getScene().getWindow());
		}
		inventoryWindow.show();
	}
	
	/**
	 * See {@link GameController#save()}.
	 */
	@FXML
	private void save() {
		gameController.save();
	}

	/**
	 * Gets the inventoryViewController.
	 * @return
	 */
	public InventoryViewController getInventoryViewController() {
		return inventoryViewController;
	}

	/**
	 * Updates the HUD (player's health, experience and level).
	 */
	@Override
	public void update(Observable o, Object arg) {
		double d = ((double) player.getHp())/player.getMaxHp();
		healthBar.setProgress(d);
		double d1 = ((double) player.getXp())/player.getXpToLevelUp();
		xpBar.setProgress(d1);
		lvl.setText("Nv. " + player.getLevel());
	}
	
}
