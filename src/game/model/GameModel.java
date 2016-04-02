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
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates all the components of the model. The size of the map is defined by {@code mapSize}.
	 * 
	 * @param mapSize
	 */
	public GameModel(int mapSize) {
		
		map = new Map(mapSize, "ok");
		
		player = new Player(map,5,5);
		//new Player(map,5,5);
	}
	
	/**
	 * Creates all the components of the model.
	 */
	/*public GameModel(){
		Map map = new Map(11);
		setMap(map);
		
		Player player = new Player(map);
		setPlayer(player);
		RedDragon monster1 = new RedDragon(map,1,0, player.getLevel());
		GiantRat monster2 = new GiantRat(map,7,3, player.getLevel());
		
		SafeHouse safehouse = new SafeHouse(map,4,4);
		
		//Dungeon dungeon = new Dungeon(map, 6,6);
		
		Rock rock = new Rock (map,2,3);
		Tree tree = new Tree (map,6,5);
		Bush bush = new Bush (map,8,8);
		
		HpPotion hpPotion = new HpPotion(100, 2, 8);
		
		getSafeHouseList().add(safehouse);
		
		//getDungeonList().add(dungeon);
		
		getObstacleList().add(rock);
		getObstacleList().add(tree);
		getObstacleList().add(bush);
		
		getLivingList().add(player);
		getLivingList().add(monster1);
		getLivingList().add(monster2);
		
		getItemList().add(hpPotion);
		
		for(LivingBeing living : getLivingList()){
			map.addLivingOnMap(living);
		}
		
		for(Obstacle obstacle : getObstacleList()){
			map.addObstacleOnMap(obstacle);
		}
		
		for(SafeHouse Safehouse : getSafeHouseList()){
			map.addSafeHouseOnMap(Safehouse);
		}
		
		//for(Dungeon Dungeon : getDungeonList()){
		//	map.addDungeonOnMap(dungeon);
		//}
	}*/
	
	//************************** Getters and Setters **************************
	
	@Override
	public Map getMap() {
		return map;
	}
	
	@Override
	public Player getPlayer() {
		return player;
	}
	
}
