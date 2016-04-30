package game.view;

import java.io.IOException;

import game.Main;
import game.controller.GameController;
import game.controller.HUDController;
import game.controller.InventoryViewController;
import game.controller.StartSceneController;
import game.model.GameModel;
import game.model.Observable;
import game.model.Player;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Implements {@code Observer} <br/>
 * Extends from {@code BorderPane} <br/>
 * View of the game.
 * 
 * @author ZhaoWen
 * @see {@link BorderPane}
 *
 */
public class GameView extends BorderPane implements Observer {

	//****************************** Attributes ******************************

	private GameModel model;
	private GameController gameController;
	private MapView mapView;
	public InventoryViewController inventoryViewController;

	private Stage mainStage;
	private Scene startScene;
	private Scene gameScene;

	//****************************** Constructor ******************************
	
	/**
	 * Creates a window and shows the start menu.
	 * 
	 * @param stage
	 */
	public GameView(Stage stage) {
		super();
		this.mainStage = stage;
		gameScene = new Scene(this);
		gameController = new GameController();
		showStartMenu();
	}
	
	/**
	 * Creates the view of the game.
	 * 
	 * @param model
	 * @param stage
	 */
	public GameView(Stage stage, GameModel model, GameController gameController) {
		super();
		this.setPrefSize(100, 100);
		this.model = model;
		this.mainStage = stage;
		this.gameController = gameController;
	}

	//************************** Getters and Setters **************************
	
	//******************************** Methods ********************************

	/**
	 * Shows the start menu if the player is dead.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Player && arg == "dead") {
			model = null;
			mapView = null;
			inventoryViewController = null;
			System.gc();
			Platform.runLater(() -> {
				showStartMenu();
			});
		}
	}
	
	/**
	 * Shows the start menu.
	 * 
	 * @param gameView
	 */
	private void showStartMenu() {
		if(startScene == null) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("view/StartScene.fxml"));
				AnchorPane startRoot = (AnchorPane) loader.load();
				StartSceneController startSceneController = (StartSceneController) loader.getController();

				startSceneController.init(this);
				startScene = new Scene(startRoot);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		mainStage.setScene(startScene);
		mainStage.sizeToScene();
		mainStage.centerOnScreen();
		mainStage.show();
	}
	
	/**
	 * Creates a {@code GameModel} with {@code mapSize} as the parameter for the size of the map. Creates
	 * also the controller of the game and the view. Finally, shows the view and starts the threads of the
	 * model.
	 * @param mapSize
	 */
	public void newGame(int mapSize) {
		model = new GameModel(mapSize);
		model.getPlayer().addObserver(this);
		gameController.init(model);
		
		mapView = new MapView(model.getMap(), this, gameController);
		mapView.setOnKeyPressed(gameController);
		this.setCenter(mapView);
		
		initPlayerViewAndHUD(model.getPlayer(), mapView, gameController);
		
		mainStage.setScene(gameScene);
		mapView.requestFocus();
		mainStage.sizeToScene();
		mainStage.centerOnScreen();
		
		model.getMap().run();
	}
	
	/**
	 * Creates the view of the player and the HUD (Head-up display).
	 * @param player
	 * @param mapView
	 * @param gameController
	 */
	private void initPlayerViewAndHUD(Player player, MapView mapView, GameController gameController) {
		PlayerView playerView = new PlayerView(model.getPlayer(), mapView);
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/HUD.fxml"));
			BorderPane hud = (BorderPane) loader.load();
			HUDController hudController = (HUDController) loader.getController();
			
			hudController.init(model.getPlayer());
			inventoryViewController = hudController.getInventoryViewController();
			gameController.setHudController(hudController);
			this.setBottom(hud);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
