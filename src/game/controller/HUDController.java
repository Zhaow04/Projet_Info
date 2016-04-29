package game.controller;

import java.io.IOException;

import game.Main;
import game.utilities.ResourceManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HUDController {
	
	private GameController gameController;
	private InventoryViewController inventoryViewController;
	private Stage inventoryWindow;
	
	@FXML
	private Button inventoryButton;
	
	@FXML
	private Button saveButton;
	
	
	public HUDController() {
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
	}
	
	public void setGameController(GameController gameController){
		this.gameController=gameController;
	}
	
	@FXML
	private void openInventory() {
		if(inventoryWindow.getOwner() == null) {
			inventoryWindow.initOwner(inventoryButton.getScene().getWindow());
		}
		inventoryWindow.show();
	}

	@FXML
	private void save(){
		ResourceManager.save(gameController.getGameModel(), "sauvegarde");
	}
	
	public InventoryViewController getInventoryViewController() {
		return inventoryViewController;
	}

	public void setInventoryViewController(InventoryViewController inventoryViewController) {
		this.inventoryViewController = inventoryViewController;
	}
	
}
