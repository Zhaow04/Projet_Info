package game.model;

import java.io.Serializable;
import java.util.ArrayList;

//import game.model.item.IItem;
import game.model.item.Item;
import game.model.monster.Monster;
import game.model.movement.Movement;
import game.model.obstacle.Obstacle;
import game.model.skill.SkillTarget;
import game.utilities.ViewSettings;
import game.view.Observer;

/**
 * Map of the game. It knows whether or not a position
 * is occupied and which object is occupying it.
 * 
 * @see {@link ViewSettings}
 *
 */
public class Map implements Observable, Serializable {
	
	//****************************** Attributes ******************************
	
	private static final long serialVersionUID = 1L;
	private int[][] grid;
	private final int mapCompoID = 1;
	private final int damageableID = 2;
	
	private Player player;
	private ArrayList<MapComponent> mapCompos = new ArrayList<MapComponent>();
	private ArrayList<Monster> monsters = new ArrayList<Monster>();
	private ArrayList<Item> items = new ArrayList<Item>();
	
	private ViewSettings viewSettings = new ViewSettings("game/model/images/plains.png");
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a map (matrix) with the same number (defined by {@code size}) of rows and
	 * columns. Sets also the image of the map (ground).
	 * 
	 * @param size
	 */
	public Map(int size) {
		initGrid(size);
		createAllComponents();
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the grid associated to the map (matrix with the same number of rows and columns).
	 * @return grid
	 */
	private int[][] getGrid() {
		return grid;
	}
	/**
	 * Sets the grid associated to the map (matrix with the same number of rows and columns).
	 * @param grid
	 */
	private void setGrid(int[][] grid) {
		this.grid = grid;
	}
	
	/**
	 * Sets the the ID of the element grid[y][x].
	 * @param int id
	 * @param x
	 * @param y
	 */
	private void setGrid(int id, int x, int y) {
		this.grid[y][x] = id;
	}
	
	/**
	 * Gets the player.
	 * @return player
	 */
	public Player getPlayer() {
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
	 * Gets the map components' list.
	 * @return list of map components
	 */
	public ArrayList<MapComponent> getMapCompos() {
		return mapCompos;
	}

	/**
	 * Gets the list of monsters living on the map.
	 * @return list of monsters
	 */
	public ArrayList<Monster> getMonsters() {
		return monsters;
	}

	/**
	 * Gets the list of items available on the map.
	 * @return list of items
	 */
	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * Gets the size of the map (same number of rows and columns).
	 * @return map size.
	 */	
	public int getSize(){
		return getGrid().length;
	}
	
	/**
	 * Gets the view settings of the map.
	 * @return viewSettings
	 */
	public ViewSettings getViewSettings() {
		return viewSettings;
	}
	
	
	//******************************** Methods ********************************

	/**
	 * Initializes the grid: all the elements are set to 0.
	 * @param size
	 */
	private void initGrid(int size) {
		setGrid(new int[size][size]);
		for(int i = 0; i < getGrid().length; i++){
			for(int j = 0; j < getGrid()[0].length; j++) {
				setGrid(0, j, i);
			}
		}
	}
	
	/**
	 * Creates all the components of the map.
	 */
	private void createAllComponents() {
		CreationUnit.createMap(this.getSize(), this);
	}

	/**
	 * Adds an obstacle to the map.
	 * @param obstacle
	 * @param x
	 * @param y
	 */
	public void addToMap(Obstacle compo, int x, int y) {
		if(getGrid()[y][x] == 0) {
			setGrid(mapCompoID, x, y);
			getMapCompos().add(compo);
			compo.addToMap(this, x, y);
		}
	}
	/**
	 * Adds a monster to the map.
	 * @param monster
	 * @param x
	 * @param y
	 */
	public void addToMap(Monster monster, int x, int y) {
		if(getGrid()[y][x] == 0) {
			setGrid(damageableID, x, y);
			getMonsters().add(monster);
			monster.addToMap(this, x, y);
		}
	}
	/**
	 * Adds an item to the map.
	 * @param item
	 * @param x
	 * @param y
	 */
	public void addToMap(Item item, int x, int y) {
		if(getGrid()[y][x] == 0) {
			getItems().add(item);
			item.addToMap(this, x, y);
		}
	}
	/**
	 * Adds the player to the map.
	 * @param player
	 * @param x
	 * @param y
	 */
	public void addToMap(Player player, int x, int y) {
		if(getGrid()[y][x] == 0) {
			setGrid(damageableID, x, y);
			setPlayer(player);
			player.addToMap(this, x, y);
		}
	}

	
	/**
	 * Removes the {@code SkillTarget} from the map.
	 * 
	 * @param SkillTarget
	 */
	private void removeFromMap(SkillTarget skillTarget) {
		setNoCollision(skillTarget.getX(), skillTarget.getY());
		getMonsters().remove(skillTarget);
	}
	
	/**
	 * Gets the skill target present at the position (x,y).
	 * @param x
	 * @param y
	 * @return skillTarget
	 */
	public SkillTarget getTargetAt(int x, int y) {
		SkillTarget skillTarget = null;
		if (getPlayer().getX()== x && getPlayer().getY() == y){
			skillTarget = getPlayer();
		}
		else {
			for(SkillTarget d : getMonsters()) {
					if(d.getX() == x && d.getY() == y) {
							skillTarget = d;
							break;
					}
			}
		}
		return skillTarget;
	}

	/**
	 * Gets the {@code Item} at the position (x = column, y = row).
	 * 
	 * @param x
	 * @param y
	 * @return {@code Item} at (x,y)
	 */
	private Item getItem(int x, int y){
		Item item = null;
		for(Item i : getItems()) {
			if(i.getX() == x && i.getY() == y) {
				item = i;
				break;
			}
		}
		return item;
	}
	
	/**
	 * Sets the position (x = column, y = row) as empty.
	 * 
	 * @param column
	 * @param row
	 */
	private void setNoCollision(int column, int row){
		getGrid()[row][column] = 0;
	}
	
	/**
	 * Returns whether or not the position (x = column, y = row) is empty.
	 * @param column
	 * @param row
	 * @return boolean
	 */
	public boolean noCollision(int column, int row){
		return getGrid()[row][column] < 1;
	}
	
	/**
	 * Returns whether or not an item is at (x,y).
	 * @param x
	 * @param y
	 * @return boolean
	 */
	public boolean isItemAt(int x, int y) {
		return getItem(x, y) != null;
	}
	
	/**
	 * Gets the {@code Item} at the position (x = column, y = row), if there is, and removes it from the map.
	 * 
	 * @param column
	 * @param row
	 * @return {@code Item} at (x,y)
	 */
	public Item getAndRemoveItem(int x, int y) {
		Item item = null;
		if(isItemAt(x, y)) {
			item = getItem(x, y);
			getItems().remove(item);
		}
		return item;
	}

	/**
	 * Returns whether or not a skill target is at (x,y).
	 * @param x
	 * @param y
	 * @return boolean
	 */
	public boolean isTargetAt(int x, int y) {
		return getTargetAt(x, y) != null;
	}

	/**
	 * Makes the movement changes relative to the map (ID changes).
	 * @param movement
	 */
	public void notifyMovement(Movement movement) {
		setNoCollision(movement.getOldX(), movement.getOldY());
		getGrid()[movement.getNewY()][movement.getNewX()] = damageableID;
		setGrid(damageableID, movement.getNewX(), movement.getNewY());		
	}

	/**
	 * Notifies the observer that the skill target given is dead,
	 * @param skillTarget
	 */
	public void notifyDead(SkillTarget target) {
		removeFromMap(target);
		if(getMonsters().isEmpty()) {
			CreationUnit.addMonsters(this);
			notifyObservers("addMonsters");
		}
	}
	
	@Override
	public void addObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void notifyObservers() {
		notifyObservers(null);
	}
	
	@Override
	public void notifyObservers(Object arg) {
		for(Observer o : observers) {
			o.update(this, arg);
		}
	}
}
