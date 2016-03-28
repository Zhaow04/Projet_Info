package game.view;

import java.util.ArrayList;

import game.model.LivingBeing;
import game.model.Monster;
import game.model.Player;
import javafx.scene.layout.StackPane;

public class BeingController {
	
	//****************************** Attributes ******************************
	
	private Player player;
	private ArrayList<LivingBeing> objectList = new ArrayList<LivingBeing>();
	private ArrayList<StackPane> containerList = new ArrayList<StackPane>();
	private StackPane playerContainer;
	private ArrayList<String> keyList = new ArrayList<String>();
	
	//****************************** Constructor ******************************
	
	public BeingController(){
		
	}
	
	public BeingController(ArrayList<LivingBeing> objectList){
		setBeingList(objectList);
		setPlayer((Player) objectList.get(0));
		for(LivingBeing living : objectList){
			living.setBeingController(this);
		}
	}
	
	//************************** Getters and Setters **************************
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<LivingBeing> getBeingList() {
		return objectList;
	}

	public void setBeingList(ArrayList<LivingBeing> objectList) {
		this.objectList = objectList;
	}

	public ArrayList<StackPane> getContainerList() {
		return containerList;
	}

	public StackPane getPlayerContainer(){
		return playerContainer;
	}

	private void setPlayerContainer(StackPane playerContainer) {
		this.playerContainer = playerContainer;
	}
	
	public ArrayList<String> getKeyList() {
		return keyList;
	}
	
	//******************************** Methods ********************************
	
	public void addKey(String key) {
		getKeyList().add(key);
	}
	
	public void addToBeingList(LivingBeing living) {
		getBeingList().add(living);
	}
	
	public void movePlayer(String str){
		//System.out.println(str + " movePlayer");
		if(str.equals("UP")){
			getPlayer().move('N');
		}
		else if(str.equals("LEFT")){
			getPlayer().move('W');
		}
		else if(str.equals("DOWN")){
			getPlayer().move('S');
		}
		else{
			getPlayer().move('E');
		}
	}
	
	public void moveMonsters() {
		for(LivingBeing living : objectList) {
			if(living instanceof Monster) {
				Monster monster = (Monster) living;
				monster.moveInPattern();
			}
		}
	}
	
	public void attack(String str) {
		if(str.equals("DIGIT1")) {
			getPlayer().useAttack(0);
		}
	}
	

}
