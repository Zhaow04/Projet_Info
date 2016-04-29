package game.view;

import java.io.IOException;

import game.Main;
import game.model.Observable;
import game.model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Controller class for the HUD. Displays the health, the experience and the level of the player.
 * 
 * @author ZhaoWen
 *
 */
public class HUDController implements Observer {
	
	private Player player;
	private InventoryViewController inventoryViewController;
	private Stage inventoryWindow;
	
	@FXML
	private ProgressBar healthBar;
	@FXML
	private ProgressBar xpBar;
	@FXML
	private Text lvl;
	@FXML
	private Button characterButton;
	@FXML
	private Button inventoryButton;
	@FXML
	private Button configButton;
	@FXML
	private Button saveButton;
	
	/**
	 * Void constructor.
	 */
	public HUDController() {
		
	}
	
	/**
	 * Initialize the HUD (health bar, experience bar, level, inventory window).
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
		double d = ((double) player.getStats().getHp())/player.getStats().getMaxHp();
		healthBar.setProgress(d);
		double d1 = ((double) player.getStats().getXp())/player.getStats().getXpToLevelUp();
		xpBar.setProgress(d1);
		lvl.setText("Nv. " + player.getStats().getLevel());
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
	 * Gets the inventoryViewController.
	 * @return
	 */
	public InventoryViewController getInventoryViewController() {
		return inventoryViewController;
	}

	@Override
	public void update(Observable o, Object arg) {
		double d = ((double) player.getStats().getHp())/player.getStats().getMaxHp();
		healthBar.setProgress(d);
		double d1 = ((double) player.getStats().getXp())/player.getStats().getXpToLevelUp();
		xpBar.setProgress(d1);
		lvl.setText("Nv. " + player.getStats().getLevel());
	}
	
}
