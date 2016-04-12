package game.controller;

import java.util.ArrayList;

import game.model.GameModel;
import game.model.Player;
import game.utilities.Vector2D;
import game.view.GameView;
import game.view.HUDController;
import game.view.MapView;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class GameController implements EventHandler<KeyEvent> {

	//****************************** Attributes ******************************

	private GameModel model;
	private GameView view;
	private MapView mapView;
	
	private Player player;
	
	private HUDController hudController;

	private ArrayList<String> keyList = new ArrayList<String>();

	//****************************** Constructor ******************************

	public GameController(GameModel model, GameView view) {
		setModel(model);
		setView(view);
		setMapView(view.mapView);
		setPlayer(model.getPlayer());
	}

	//************************** Getters and Setters **************************
	
	private GameModel getModel() {
		return model;
	}

	private void setModel(GameModel model) {
		this.model = model;
	}

	private GameView getView() {
		return view;
	}

	private void setView(GameView view) {
		this.view = view;
	}

	private MapView getMapView() {
		return mapView;
	}

	private void setMapView(MapView mapView) {
		this.mapView = mapView;
	}

	private Player getPlayer() {
		return player;
	}

	private void setPlayer(Player player) {
		this.player = player;
	}
	
	public HUDController getHudController() {
		return hudController;
	}

	public void setHudController(HUDController hudController) {
		this.hudController = hudController;
	}
	
	public ArrayList<String> getKeyList() {
		return keyList;
	}

	//******************************** Methods ********************************
	
	@Override
	public void handle(KeyEvent event) {
		addKey(event.getCode().toString().toUpperCase());
		useFirstKey();
	}

	public void addKey(String key) {
		if(getKeyList().size() <= 2) {
			if(key.matches("UP|DOWN|LEFT|RIGHT")) {
				getKeyList().add(key);
			}
			else if(key.equals("DIGIT1")) {
				getKeyList().add(key);
			}	
		}
	}

	private void useFirstKey() {
		if(!getKeyList().isEmpty()) {
			String key = getKeyList().get(0);
			useKey(key);
			getKeyList().remove(0);
		}
	}

	public void useKey(String key) {
		if(key.matches("UP|DOWN|LEFT|RIGHT")) {
			Vector2D direction = convertKeyToDirection(key);
			getPlayer().move(direction.getIntX(), direction.getIntY());
		}
		else if(key.equals("DIGIT1")) {
			attack(key);
		}
	}
	
	private Vector2D convertKeyToDirection(String key) {
		Vector2D c;
		if(key.equals("UP")) {
			c = new Vector2D(0,-1);
		}
		else if(key.equals("LEFT")) {
			c = new Vector2D(-1,0);
		}
		else if(key.equals("DOWN")) {
			c = new Vector2D(0,1);
		}
		else {
			c = new Vector2D(1,0);
		}
		return c;
	}

	public void attack(String str) {   
		if(str.equals("DIGIT1")) {		// Va falloir ameliorer ca, ou alors mettre un if pour chaque attack
			/*ISkill skill = player.getSkill(0);
			skill.preUse(player);
			if(skill.usable()) {
				new SkillView(skill, mapView);
			}
			player.useSkill(0);*/
		}
	}

}
