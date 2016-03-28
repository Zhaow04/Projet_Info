package game.model;

import java.util.ArrayList;

/**
 * Implements {@code Model}. <br/>
 * A class that serves to create a model of the game.
 * 
 * @author ZhaoWen
 *
 */
public class GameModel implements Model {
	
	//****************************** Attributes ******************************
	
	private Map map;
	private Player player;
	private ArrayList<LivingBeing> livingList = new ArrayList<LivingBeing>();
	private ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
	private ArrayList<SafeHouse> safehouseList = new ArrayList<SafeHouse>();

	
	//****************************** Constructor ******************************
	
	/**
	 * Creates all the objects of the model.
	 */
	public GameModel(){
		Map map = new Map(10);
		setMap(map);
		
		Player player = new Player(map);
		setPlayer(player);
		
		Dragon monster1 = new Dragon(map,1,0);
		Dragon monster2 = new Dragon(map,7,3);
		
		SafeHouse safehouse = new SafeHouse(map,4,4);
		
		Rock rock = new Rock (map,2,3);
		Tree tree = new Tree (map,6,5);
		Bush bush = new Bush (map,8,8);
		
		getSafeHouseList().add(safehouse);
		
		getObstacleList().add(rock);
		getObstacleList().add(tree);
		getObstacleList().add(bush);
		
		getLivingList().add(player);
		getLivingList().add(monster1);
		getLivingList().add(monster2);
		
		for(LivingBeing living : getLivingList()){
			map.addLivingOnMap(living);
		}
		
		for(Obstacle obstacle : getObstacleList()){
			map.addObstacleOnMap(obstacle);
		}
		
		for(SafeHouse Safehouse : getSafeHouseList()){
			map.addSafeHouseOnMap(Safehouse);
		}
	}
	
	//************************** Getters and Setters **************************
	
	@Override
	public Map getMap() {
		return map;
	}
	
	/**
	 * Sets the map.
	 * 
	 * @param map
	 */
	private void setMap(Map map) {
		this.map = map;
	}
	
	@Override
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * Sets the player.
	 * 
	 * @param player
	 */
	private void setPlayer(Player player) {
		this.player = player;
	}
	
	@Override
	public ArrayList<LivingBeing> getLivingList() {
		return livingList;
	}
	
	@Override
	public ArrayList<Obstacle> getObstacleList() {
		return obstacleList;
	}
	
	@Override
	public ArrayList<SafeHouse> getSafeHouseList() {
		return safehouseList;
	}
}
