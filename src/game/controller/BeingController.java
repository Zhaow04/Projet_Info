package game.controller;

import java.util.ArrayList;

import game.model.LivingBeing;
import game.model.Monster;
import game.model.Player;
import game.view.BeingView;
import javafx.scene.layout.StackPane;

public class BeingController {
	
	//****************************** Attributes ******************************
	
	private BeingView beingView;
	private Player player;
	private ArrayList<LivingBeing> livingList = new ArrayList<LivingBeing>();
	private ArrayList<String> keyList = new ArrayList<String>();
	
	private ArrayList<StackPane> containerList = new ArrayList<StackPane>();
	
	//****************************** Constructor ******************************
	
	public BeingController(){
		
	}
	
	public BeingController(ArrayList<LivingBeing> objectList){
		setBeingList(objectList);
		setPlayer((Player) objectList.get(0));
	}
	
	//************************** Getters and Setters **************************
	
	public BeingView getBeingView() {
		return beingView;
	}

	public void setBeingView(BeingView beingView) {
		this.beingView = beingView;
	}
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<LivingBeing> getBeingList() {
		return livingList;
	}

	public void setBeingList(ArrayList<LivingBeing> objectList) {
		this.livingList = objectList;
	}

	public ArrayList<StackPane> getContainerList() {
		return containerList;
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
			movePlayer(key);
			moveMonsters();
		}
		else if(key.equals("DIGIT1")) {
			attack(key);
		}
	}
	
	public void addToBeingList(LivingBeing living) {
		getBeingList().add(living);
	}
	
	public void movePlayer(String str){
		Player player = getPlayer();
		int[] pos = player.getPosition();
		StackPane container = BeingView.getContainer(player);
		/*System.out.println(pos[0]-0.2 < beingView.containerPosX(container) &&
				pos[1]-0.2 < beingView.containerPosY(container));
		System.out.println(pos[0] + " " + pos[1] + "<-------");
		System.out.println(beingView.containerPosX(container) + " " +
				beingView.containerPosY(container) + "<-------");*/
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
	
	public void moveMonsters() {
		for(LivingBeing living : livingList) {
			if(living instanceof Monster) {
				Monster monster = (Monster) living;
				int[] pos = living.getPosition();
				StackPane container = BeingView.getContainer(living);
				if(pos[0]-0.1 < BeingView.containerPosX(container) &&
						pos[0]+0.1 > BeingView.containerPosX(container) &&
						pos[1]-0.1 < BeingView.containerPosY(container) &&
						pos[1]+0.1 > BeingView.containerPosY(container)) {
					monster.moveInPattern();
				}
			}
		}
	}
	
	public void attack(String str) {
			getPlayer().useAttack(0);
	}
	

}
