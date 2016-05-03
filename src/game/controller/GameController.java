package game.controller;

import java.util.ArrayList;
import game.Main;
import game.model.GameModel;
import game.model.Player;
import game.utilities.ResourceManager;
import game.utilities.Vector2D;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Main controller class for the game. It handles keyboard inputs.
 *
 */
public class GameController implements EventHandler<KeyEvent>, Runnable {

	//****************************** Attributes ******************************

	private GameModel model;
	private Player player;

	private ArrayList<KeyCode> keyList = new ArrayList<KeyCode>();

	//****************************** Constructor ******************************

	/**
	 * Creates a controller that handles keyboard inputs.
	 * @param model
	 */
	public GameController(GameModel model) {
			this.model = model;
			this.player = model.getPlayer();
	}

	//******************************** Methods ********************************
	
	@Override
	public void handle(KeyEvent event) {
		addKey(event.getCode());
		Main.execute(this);
	}

	@Override
	public void run() {
		if(player.isAlive() && GameModel.isRunning())
			useFirstKey();
	}
	
	
	/**
	 * Adds a key (user input) to a list of keys which can contain a maximum of two keys.
	 * @param key
	 */
	public synchronized void addKey(KeyCode key) {
		if(keyList.size() < 2) {
			if(key.isArrowKey() || key.isDigitKey()) {
				keyList.add(key);
			}	
		}
	}

	/**
	 * Uses the first key in the list of keys.
	 * @see {@link #useKey(KeyCode)}
	 */
	private synchronized void useFirstKey() {
		if(!keyList.isEmpty()) {
			KeyCode key = keyList.get(0);
			useKey(key);
			keyList.remove(0);
		}
	}

	/**
	 * Processes a key (user input), e.g. arrow keys for movement, digit keys for attacking.
	 * @param key
	 */
	public synchronized void useKey(KeyCode key) {
		if(key.isArrowKey()) {
			Vector2D direction = convertKeyToDirection(key.getName());
			player.move(direction.getIntX(), direction.getIntY());
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
			player.useSkill(i-1);
		}
	}
	
	/**
	 * Pauses the game if it's running and restart it if it's paused.
	 */
	public void pauseAndStart() {
		if(GameModel.isRunning())
			model.stop();
		else
			model.start();
	}
	
	/**
	 * Saves the game.
	 * @see {@link ResourceManager#save(java.io.Serializable)}
	 */
	public void save() {
		ResourceManager.save(model);
	}

}
