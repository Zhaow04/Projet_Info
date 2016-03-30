package game.view;

import java.io.IOException;

import game.Main;
import game.controller.BeingController;
import game.controller.Controller;
import game.controller.ItemController;
import game.controller.MapController;
import game.model.GameModel;
import game.model.Player;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class GameView {
	
	//****************************** Attributes ******************************
	
	private Scene startScene, mainScene;
	private GameModel model;
	private Controller controller;
	private HUDController hudController;
	
	//****************************** Constructor ******************************
	
	public GameView(GameModel model, Controller controller){

		setModel(model);
		setController(controller);
		MapController mapController = new MapController();
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
			// TODO Auto-generated catch block
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
	}
	
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
			mainScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
				@Override
				public void handle(KeyEvent ke){
					controller.getBeingController().addKey(ke.getCode().toString().toUpperCase());
					//System.out.println(ke.getCode());
					//System.out.println(ke.getCode());
				}
			});
		}
	
}
