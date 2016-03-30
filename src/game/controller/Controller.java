package game.controller;

import game.model.GameModel;
import game.view.GameView;

public class Controller {
	
	//*********************************************
	// Attributes
	//*********************************************
	
	private GameModel gameModel;
	private GameView gameView;
	
	private MapController mapController;
	private BeingController beingController;
	
	//*********************************************
	// Constructor
	//*********************************************
	
	public Controller(){
		setMapController(new MapController());
		setBeingController(new BeingController());
		
	}
	
	//*********************************************
	// Getters and Setters
	//*********************************************
	
	public GameModel getGameModel() {
		return gameModel;
	}
	public void setGameModel(GameModel gameModel) {
		this.gameModel = gameModel;
	}
	public GameView getGameView() {
		return gameView;
	}
	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

	public MapController getMapController() {
		return mapController;
	}

	public void setMapController(MapController mapController) {
		this.mapController = mapController;
	}

	public BeingController getBeingController() {
		return beingController;
	}

	public void setBeingController(BeingController beingController) {
		this.beingController = beingController;
	}
	
}
