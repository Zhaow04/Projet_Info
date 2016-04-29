package game.view;

import java.io.IOException;

import game.Main;
import game.controller.GameController;
import game.controller.HUDController;
import game.controller.InventoryViewController;
import game.controller.StartSceneController;
import game.model.GameModel;
import game.model.Player;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameView extends AnchorPane {

	//****************************** Attributes ******************************

	private GameModel model;
	private GameController gameController;
	public MapView mapView;
	private PlayerView playerView;
	private InventoryViewController inventoryViewController;

	private Stage mainStage;
	private Scene mainScene;

	//****************************** Constructor ******************************
	
	/**
	 * Creates the view of the game. Shows the start menu.
	 * 
	 * @param stage
	 */
	public GameView(Stage stage) {
		super();
		this.setPrefSize(100, 100);
		setMainStage(stage);
		stage.setTitle("RPG");
		startMenu(this);
	}
	
	/**
	 * Creates the view of the game.
	 * 
	 * @param model
	 * @param stage
	 */
	public GameView(GameModel model, Stage stage) {
		super();
		this.setPrefSize(100, 100);
		setModel(model);
		setMainStage(stage);
		
		GameController gameController = new GameController(model);
		setGameController(gameController);
		init();
	}

	//************************** Getters and Setters **************************
	
	public Stage getMainStage() {
		return mainStage;
	}

	private void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

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
	
	/**
	 * Shows the start menu.
	 * 
	 * @param gameView
	 */
	private void startMenu(GameView gameView) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/StartScene.fxml"));
			AnchorPane startRoot = (AnchorPane) loader.load();
			StartSceneController startSceneController = (StartSceneController) loader.getController();
			
			startSceneController.gameView = this;
			Scene primaryScene = new Scene(startRoot);
			getMainStage().setScene(primaryScene);
			getMainStage().show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void newGame(int mapSize) {
		setModel(new GameModel(mapSize));
		GameController gameController = new GameController(getModel());
		setGameController(gameController);
		initView();
		getModel().startThreads();
	}
	
	private void initView() {
		mainScene = mainStage.getScene();
		
		MapView mapView = new MapView(model.getMap(), this, getGameController());
		mapView.setOnKeyPressed(gameController);
		setMapView(mapView);
		test();
		initPlayerViewAndHUD(model.getPlayer(), mapView, gameController);
		
		mainScene.setRoot(this);
		mapView.requestFocus();
		
		//getMainStage().setWidth(940);
		//getMainStage().setHeight(640);
		mainStage.minHeightProperty().bind(this.widthProperty());
		mainStage.minWidthProperty().bind(this.heightProperty());
		mainStage.setOnCloseRequest((value) -> {
			Platform.exit();
			System.exit(0);
		});
	}
	
	private void init() {
		mainScene = mainStage.getScene();
		MapView mapView = new MapView(model.getMap(), this, getGameController());
		mapView.setOnKeyPressed(gameController);
		setMapView(mapView);
		test();
		initPlayerViewAndHUD(model.getPlayer(), mapView, gameController);
		mainScene.setRoot(this);

		mainStage.minHeightProperty().bind(this.widthProperty());
		mainStage.minWidthProperty().bind(this.heightProperty());
		mainStage.setOnCloseRequest((value) -> {
			Platform.exit();
			System.exit(0);
		});
		mapView.requestFocus();
	}
	
	private void test() {
		BorderPane b = new BorderPane();
		//mapView.setClip(new Rectangle(605,500));
		//mapView.getClip().translateXProperty().bind(mapView.translateXProperty().multiply(-1));
		//mapView.getClip().translateYProperty().bind(mapView.translateYProperty().multiply(-1));
		b.setCenter(mapView);
		/*try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/GameView.fxml"));
			BorderPane gameView = (BorderPane) loader.load();
			
			b.setBottom(gameView);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		this.getChildren().add(b);
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
