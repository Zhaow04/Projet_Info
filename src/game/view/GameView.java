package game.view;

import java.io.IOException;

import game.Main;
import game.controller.Controller;
import game.model.GameModel;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameView {
	
	//****************************** Attributes ******************************
	
	private GameModel model;
	
	static Stage stage;
	private Scene currentScene;
	
	private Scene startScene, mainScene;
	
	private Controller controller;
	private HUDController hudController;
	
	//****************************** Constructor ******************************
	
	public GameView(GameModel model, Stage stage) {
		this.model = model;
		GameView.stage = stage;
		currentScene = stage.getScene();
		
		AnchorPane root = MapView.getRootList().get(0);
		stage.getScene().setRoot(root);
		
		root.requestFocus();
		//System.out.println(currentScene.getHeight());
		//System.out.println(stage.getHeight());
		
		stage.minHeightProperty().bind(root.widthProperty());
		stage.minWidthProperty().bind(root.heightProperty());
		initEventHandler();
	}
	/*
	public GameView(GameModel model, Controller controller){

		setModel(model);
		setController(controller);
		MapController mapController = controller.getMapController();
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

		public Scene getStartScene() {
			return startScene;
		}

		private void setStartScene(Scene startScene) {
			this.startScene = startScene;
		}
		
		public Scene getMainScene() {
			return mainScene;
		}
		
		private void setMainScene(Scene mainScene) {
			this.mainScene = mainScene;
		}
		
		public GameModel getModel() {
			return model;
		}

		private void setModel(GameModel model) {
			this.model = model;
		}

		public Controller getController() {
			return controller;
		}

		private void setController(Controller controller) {
			this.controller = controller;
		}

		private void initEventHandler() {
			currentScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
				@Override
				public void handle(KeyEvent ke){
					//System.out.println(ke.getCode());
					BeingView.beingController.addKey(ke.getCode().toString().toUpperCase());
					//System.out.println(ke.getCode());
					//System.out.println(ke.getCode());
				}
			});
		}
	
}
