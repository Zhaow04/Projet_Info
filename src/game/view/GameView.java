package game.view;

import java.io.IOException;

import game.Main;
import game.controller.GameController;
import game.model.GameModel;
import game.model.Inventory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameView {

	//****************************** Attributes ******************************

	private GameModel model;
	private GameController gameController;
	private MapView mapView;

	private final Stage mainStage;
	private Scene mainScene;

	//****************************** Constructor ******************************

	public GameView(GameModel model, Stage mainStage) {
		this.model = model;
		this.mainStage = mainStage;
		mainScene = mainStage.getScene();
		Inventory inventory = model.getPlayer().getInventory();
		
		gameController = new GameController(model, this);
		mapView = new MapView(model.getMap(), gameController);
		
		
		createHUD(mapView, inventory, gameController);
		mainScene.setRoot(mapView);
		mapView.requestFocus();
		
		//System.out.println(currentScene.getHeight());
		//System.out.println(stage.getHeight());

		mainStage.minHeightProperty().bind(mapView.widthProperty());
		mainStage.minWidthProperty().bind(mapView.heightProperty());
	}
	
	/*
	public GameView(GameModel model, Controller controller){

		setModel(model);
		setController(controller);
		GameController mapController = controller.getMapController();
		MapView mapView = new MapView(model, mapController);
		setMainScene(new Scene(mapView.getRoot()));

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/StartScene.fxml"));
			AnchorPane startRoot = (AnchorPane) loader.load();
			StartSceneController startSceneController = (StartSceneController) loader.getController();

			startSceneController.setMainScene(mainScene);
			Scene startScene = new Scene(startRoot);
			setStartScene(startScene);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/HUD.fxml"));
			StackPane hud = (StackPane) loader.load();
			mapView.getRoot().getChildren().add(hud);
			AnchorPane.setBottomAnchor(hud, 0.0);
			AnchorPane.setRightAnchor(hud, 0.0);

			hudController = (HUDController) loader.getController();
			Player player = (Player) model.getLivingList().get(0);
			//System.out.println(hudController.getInventoryViewController());
			//hudController.getInventoryViewController().setInventory(player.getInventory());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		BeingController beingController = controller.getBeingController();

		BeingView beingView = new BeingView(model,beingController, mapView);
		ObstacleView obstacleView = new ObstacleView(model, mapView);
		SafeHouseView safehouseView = new SafeHouseView(model, mapView);

		ItemController itemController = new ItemController();
		//System.out.println(hudController);
		//itemController.setInventoryController(hudController.getInventoryViewController());
		ItemView itemView = new ItemView(model,itemController, mapView);

		beingView.initAllObjectView();
		obstacleView.initAllObjectView();
		safehouseView.initAllObjectView();
		itemView.initAllItemView();

		initEventHandler();
	}*/

	//************************** Getters and Setters **************************
	
	private void createHUD(MapView mapView, Inventory inventory, GameController gameController) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/HUD.fxml"));
			StackPane hud = (StackPane) loader.load();
			mapView.getChildren().add(hud);
			AnchorPane.setBottomAnchor(hud, 0.0);
			AnchorPane.setRightAnchor(hud, 0.0);
			
			HUDController hudController = (HUDController) loader.getController();
			gameController.setHudController(hudController);
			InventoryViewController inventoryViewController = hudController.getInventoryViewController();
			inventoryViewController.setInventory(inventory);
			mapView.getObservableView().setInventoryViewController(inventoryViewController);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isViewUpToDate() {
		return mapView.isViewUpToDate();
	}

}
