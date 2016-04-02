package game.controller;

import java.util.ArrayList;

import game.model.GameModel;
import game.model.Movable;
import game.utilities.Vector2D;
import game.view.GameView;
import game.view.HUDController;

public class GameController {

	//****************************** Attributes ******************************

	private GameModel model;
	private GameView view;
	
	private HUDController hudController;

	private ArrayList<String> keyList = new ArrayList<String>();

	//****************************** Constructor ******************************

	public GameController(GameModel model, GameView view) {
		this.model = model;
		this.view = view;
	}

	//************************** Getters and Setters **************************
	
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
	
	public void addKey(String key) {
		//getKeyList().add(key);
		useKey(key);
		/*getKeyList().add(key);
		useKeys();*/
		//System.out.println(Thread.currentThread().getThreadGroup().activeCount());
	}

	public void useKey(String key) {
		if(key.matches("UP|DOWN|LEFT|RIGHT")) {
			moveAllMovables(convertKeyToDirection(key));
		}
		else if(key.equals("DIGIT1")) {
			attack(key);
		}
	}

	private void moveAllMovables(Vector2D direction) {
		ArrayList<Movable> movableList = model.getMap().getMovableList();
		if(view.isViewUpToDate()) {
			for(Movable movable : movableList) {
				movable.move(direction.getIntX(), direction.getIntY());
			}
		}
	}

	private Vector2D convertKeyToDirection(String key) {
		Vector2D c;
		if(key.equals("UP")) {
			c = new Vector2D(0,-1);
		}
		else if(key.equals("LEFT")){
			c = new Vector2D(-1,0);
		}
		else if(key.equals("DOWN")){
			c = new Vector2D(0,1);
		}
		else{
			c = new Vector2D(1,0);
		}
		return c;
	}
/*
	public void movePlayer(String str){
		int[] pos = player.getPosition();
		StackPane container = BeingView.getContainer(player);
		if(pos[0]-0.2 < BeingView.containerPosX(container) &&
				pos[0]+0.2 > BeingView.containerPosX(container) &&
				pos[1]-0.2 < BeingView.containerPosY(container) &&
				pos[1]+0.2 > BeingView.containerPosY(container)) {
			if(str.equals("UP")){
				player.move('N');
				if(player.isItemAtFeet()) {
					player.takeItem();
				}
			}
			else if(str.equals("LEFT")){
				player.move('W');
				if(player.isItemAtFeet()) {
					player.takeItem();
				}
			}
			else if(str.equals("DOWN")){
				player.move('S');
				if(player.isItemAtFeet()) {
					player.takeItem();
				}
			}
			else{
				player.move('E');
				if(player.isItemAtFeet()) {
					player.takeItem();
				}
			}
		}
	}
*//*
	public void moveMonsters() {
		for(Observable entity : entityList) {
			if(entity instanceof Monster) {
				Monster monster = (Monster) entity;
				int[] pos = entity.getPosition();
				StackPane container = observableView.getContainer(entity);
				if(pos[0]-0.1 < observableView.containerPosX(container) &&
						pos[0]+0.1 > observableView.containerPosX(container) &&
						pos[1]-0.1 < observableView.containerPosY(container) &&
						pos[1]+0.1 > observableView.containerPosY(container)) {
					monster.move('A');
				}
			}
		}
	}
*/
	public void attack(String str) {   
		if(str.equals("DIGIT1")) {		// Va falloir ameliorer ca, ou alors mettre un if pour chaque attack
			model.getPlayer().useDirectAttack(0);
		}
	}

}
