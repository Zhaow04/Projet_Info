package game.model;

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
		
		map = new Map(mapSize);
		
		player = new Player();
		map.addToMap(player, 5, 5);
		//new Player(map,5,5);
	}
	
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
