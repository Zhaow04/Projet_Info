package game.controller;

import java.util.ArrayList;

import game.model.GameModel;
import game.model.Player;
import game.utilities.Vector2D;
import game.view.GameView;
import game.view.HUDController;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameController implements EventHandler<KeyEvent>, Runnable {

	//****************************** Attributes ******************************

	private GameModel model;
	private GameView view;
	
	private Player player;
	
	private HUDController hudController;

	private ArrayList<KeyCode> keyList = new ArrayList<KeyCode>();
	
	private Thread t;

	//****************************** Constructor ******************************

	public GameController() {
		
	}
	
	public GameController(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
		this.player = model.getPlayer();
		new Thread(this).start();
	}

	//************************** Getters and Setters **************************
	
	public HUDController getHudController() {
		return hudController;
	}

	public void setHudController(HUDController hudController) {
		this.hudController = hudController;
	}
	
	public ArrayList<KeyCode> getKeyList() {
		return keyList;
	}

	//******************************** Methods ********************************
	
	@Override
	public void handle(KeyEvent event) {
		addKey(event.getCode());
	}

	@Override
	public void run() {
		while(true) {
			useFirstKey();
		}
	}
	
	public void init(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
		this.player = model.getPlayer();
		if(t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public synchronized void addKey(KeyCode key) {
		if(getKeyList().size() < 2) {
			if(key.isArrowKey() || key.isDigitKey()) {
				getKeyList().add(key);
				notify();
			}	
		}
	}

	private synchronized void useFirstKey() {
		if(!getKeyList().isEmpty()) {
			KeyCode key = getKeyList().get(0);
			useKey(key);
			getKeyList().remove(0);
		}
		else {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void useKey(KeyCode key) {
		if(key.isArrowKey()) {
			Vector2D direction = convertKeyToDirection(key.getName());
			player.move(direction.getIntX(), direction.getIntY());
		}
		else if(key.isDigitKey()) {
			attack(Integer.parseInt(key.getName()));
		}
	}
	
	private Vector2D convertKeyToDirection(String key) {
		Vector2D c;
		if(key.equals("Up")) {
			c = new Vector2D(0,-1);
		}
		else if(key.equals("Left")) {
			c = new Vector2D(-1,0);
		}
		else if(key.equals("Down")) {
			c = new Vector2D(0,1);
		}
		else {
			c = new Vector2D(1,0);
		}
		return c;
	}

	public void attack(int i) {   
		if(i <= 3) {		// Va falloir ameliorer ca, ou alors mettre un if pour chaque attack	
			player.useSkill(i-1);
		}
	}

}
