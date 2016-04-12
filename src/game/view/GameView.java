package game.view;

import java.io.IOException;

import game.Main;
import game.controller.GameController;
import game.model.GameModel;
import game.model.Player;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class GameView extends AnchorPane {

	//****************************** Attributes ******************************

	private GameModel model;
	private GameController gameController;
	public MapView mapView;
	private PlayerView playerView;
	private InventoryViewController inventoryViewController;

	private final Stage mainStage;
	private Scene mainScene;

	//****************************** Constructor ******************************

	public GameView(GameModel model, Stage stage) {
		super();
		this.setPrefSize(100, 100);
		setModel(model);
		this.mainStage = stage;
		mainScene = stage.getScene();
		
		GameController gameController = new GameController(model, this);
		setGameController(gameController);
		MapView mapView = new MapView(model.getMap(), this, getGameController());
		mapView.setOnKeyPressed(gameController);
		setMapView(mapView);
		this.getChildren().add(mapView);
		initPlayerViewAndHUD(model.getPlayer(), mapView, gameController);
		//mapView.toBack();
		
		mainScene.setRoot(this);
		mapView.requestFocus();

		mainStage.minHeightProperty().bind(this.widthProperty());
		mainStage.minWidthProperty().bind(this.heightProperty());
		mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent event) {
				Platform.exit();
				System.exit(0);
			}
		});
	}

	//************************** Getters and Setters **************************
	
	public GameModel getModel() {
		return model;
	}

	private void setModel(GameModel model) {
		this.model = model;
	}

	public GameController getGameController() {
		return gameController;
	}

	private void setGameController(GameController gameController) {
		this.gameController = gameController;
	}

	public MapView getMapView() {
		return mapView;
	}

	private void setMapView(MapView mapView) {
		this.mapView = mapView;
	}

	public PlayerView getPlayerView() {
		return playerView;
	}

	private void setPlayerView(PlayerView playerView) {
		this.playerView = playerView;
	}

	public InventoryViewController getInventoryViewController() {
		return inventoryViewController;
	}

	private void setInventoryViewController(InventoryViewController inventoryViewController) {
		this.inventoryViewController = inventoryViewController;
	}
	
	private void initPlayerViewAndHUD(Player player, MapView mapView, GameController gameController) {
		PlayerView playerView = new PlayerView(getModel().getPlayer(), mapView);
		setPlayerView(playerView);
		createHUD(getModel().getPlayer(), gameController);
	}

	private void createHUD(Player player, GameController gameController) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/HUD.fxml"));
			StackPane hud = (StackPane) loader.load();
			this.getChildren().add(hud);
			AnchorPane.setBottomAnchor(hud, 0.0);
			AnchorPane.setRightAnchor(hud, 0.0);
			
			HUDController hudController = (HUDController) loader.getController();
			gameController.setHudController(hudController);
			InventoryViewController inventoryViewController = hudController.getInventoryViewController();
			setInventoryViewController(inventoryViewController);
			inventoryViewController.setPlayer(player);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isViewUpToDate() {
		return mapView.isViewUpToDate();
	}

}
