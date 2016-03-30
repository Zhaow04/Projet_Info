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
	
	@FXML
	private Button inventoryButton;
	
	public HUDController() {
		
	}
	
	@FXML
	private void openInventory() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/InventoryView.fxml"));
			AnchorPane inventoryView = (AnchorPane) loader.load();
			inventoryViewController = (InventoryViewController) loader.getController();
			
			Scene inventoryScene = new Scene(inventoryView);
			Stage inventoryWindow = new Stage();
			inventoryWindow.setScene(inventoryScene);
			inventoryWindow.initOwner(inventoryButton.getScene().getWindow());
			inventoryWindow.show();
			/*
			mapView.getRoot().getChildren().add(hud);
			AnchorPane.setBottomAnchor(hud, 0.0);
			AnchorPane.setRightAnchor(hud, 0.0);*/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public InventoryViewController getInventoryViewController() {
		return inventoryViewController;
	}

	public void setInventoryViewController(InventoryViewController inventoryViewController) {
		this.inventoryViewController = inventoryViewController;
	}
	
}
