package game.view;

import java.io.IOException;

import game.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HUDController {
	
	
	private InventoryViewController inventoryViewController;
	private Stage inventoryWindow;
	
	@FXML
	private Button inventoryButton;
	
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
	
	@FXML
	private void openInventory() {
		inventoryWindow.initOwner(inventoryButton.getScene().getWindow());
		inventoryWindow.show();
	}

	public InventoryViewController getInventoryViewController() {
		return inventoryViewController;
	}

	public void setInventoryViewController(InventoryViewController inventoryViewController) {
		this.inventoryViewController = inventoryViewController;
	}
	
}
