package game.model;

import java.util.ArrayList;

public class GameModel implements Model {
	
	//****************************** Attributes ******************************
	
	private Map map;
	private Player player;
	private ArrayList<LivingBeing> livingList = new ArrayList<LivingBeing>();
	
	//****************************** Constructor ******************************
	
	public GameModel(){
		Map map = new Map(10);
		setMap(map);
		Player player = new Player(map);
		setPlayer(player);
		Dragon monster1 = new Dragon(map,1,0);
		Dragon monster2 = new Dragon(map,7,3);
		getLivingList().add(player);
		getLivingList().add(monster1);
		getLivingList().add(monster2);
		for(LivingBeing living : getLivingList()){
			map.addLivingOnMap(living);
		}
	}
	
	//************************** Getters and Setters **************************
	
	@Override
	public Map getMap() {
		return map;
	}

	private void setMap(Map map) {
		this.map = map;
	}
	
	@Override
	public Player getPlayer() {
		return player;
	}

	private void setPlayer(Player player) {
		this.player = player;
	}
	
	@Override
	public ArrayList<LivingBeing> getLivingList() {
		return livingList;
	}
	
}
