	package game.controller;

import java.util.ArrayList;

import game.model.GameModel;
import game.model.Player;
import game.utilities.Vector2D;
import game.view.GameView;
import game.view.HUDController;
import game.view.MapView;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class GameController implements EventHandler<KeyEvent>, Runnable {

	//****************************** Attributes ******************************

	private Player player;
	
	private HUDController hudController;

	private ArrayList<KeyCode> keyList = new ArrayList<KeyCode>();

	//****************************** Constructor ******************************

	public GameController(GameModel model) {
		setPlayer(model.getPlayer());
		new Thread(this).start();   
	}

	//************************** Getters and Setters **************************
	

	/**
	 * Gets the player.
	 * @return player
	 */
	private Player getPlayer() {
		return player;
	}

	/**
	 * Sets the player.
	 * @param player
	 */
	private void setPlayer(Player player) {
		this.player = player;
	}
	
	/**
	 * Gets the Heads Up Display (HUD) controller.
	 * @return HUD controller
	 */
	public HUDController getHudController() {
		return hudController;
	}

	/**
	 * Sets the Heads Up Display (HUD) controller.
	 * @param hudController
	 */
	public void setHudController(HUDController hudController) {
		this.hudController = hudController;
	}
	
	/**
	 * Gets the list of the different keys.
	 * @return keys' list
	 */
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
			getPlayer().move(direction.getIntX(), direction.getIntY());
		}
		else if(key.isDigitKey()) {
			attack(Integer.parseInt(key.getName()));
		}
	}
	
	/**
	 * Converts the key (string) to a direction (Vector2D).
	 * @param key
	 * @return
	 */
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

	/**
	 * Makes the player use his skill (i-1).
	 * @param i
	 */
	public void attack(int i) {   
		if(i <= 3) {	
			getPlayer().useSkill(i-1);
		}
	}

}
