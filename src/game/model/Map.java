package game.model;

import java.util.ArrayList;

import game.model.component.CreationUnit;
import game.model.component.Movement;
import game.model.component.SkillTarget;
import game.model.item.IItem;
import game.model.item.Item;
import game.model.monster.Monster;
import game.utilities.ViewSettings;
import game.view.Observer;

/**
 * Map of the game. It knows whether or not a position
 * is occupied and which object is occupying it.
 * 
 * @author ZhaoWen
 * @see {@link ViewSettings}
 *
 */
public class Map implements IMap, Observable {
	
	//****************************** Attributes ******************************
	
	private int[][] grid;
	private final int mapCompoID = 1;
	//private final int itemID = 1;
	private final int damageableID = 2;
	
	private Player player;
	private ArrayList<MapComponent> mapCompos = new ArrayList<MapComponent>();
	private ArrayList<Monster> monsters = new ArrayList<Monster>();
	private ArrayList<Item> items = new ArrayList<Item>();
	
	private ViewSettings viewSettings = new ViewSettings("game/utilities/plains.png");
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
	
	private int[][] getGrid() {
		return grid;
	}
	
	private void setGrid(int[][] grid) {
		this.grid = grid;
	}
	
	private void setGrid(int id, int x, int y) {
		this.grid[y][x] = id;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	private void setPlayer(Player player) {
		this.player = player;
	}
	
	public ArrayList<MapComponent> getMapCompos() {
		return mapCompos;
	}

	public ArrayList<Monster> getMonsters() {
		return monsters;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	/**
	 * Gets the size of the map (same number of rows and columns).
	 * 
	 * @return size of the map
	 */
	public int getSize(){
		return getGrid().length;
	}
	
	/*public ArrayList<Observable> getObservables() {
		ArrayList<Observable> observableList = new ArrayList<Observable>();
		for(int i = 0; i < dynamicLayer.length; i++) {
			for(int j = 0; j < dynamicLayer.length; j++) {
				if(dynamicLayer[i][j] != null) {
					observableList.add(dynamicLayer[i][j]);
				}
				if(stepOnLayer[i][j] != null) {
					observableList.add(stepOnLayer[i][j]);
				}
			}
		}
		return observableList;
	}*/

	/**
	 * Sets the matrix of {@code LivingBeing} associated to the map. An instance of
	 * {@code LivingBeing} present at the position (x,y) on the map will figure at the
	 * same position in the matrix.
	 * 
	 * @param livingOnMap
	 * @see {@link LivingBeing}
	 */
	/*private void setEntityMatrix(Observable[][] bottomLayer) {
		this.entityMatrix = bottomLayer;
	}*/
	
	//******************************** Methods ********************************

	public ViewSettings getViewSettings() {
		return viewSettings;
	}
	
	private void initGrid(int size) {
		setGrid(new int[size][size]);
		for(int i = 0; i < getGrid().length; i++){
			for(int j = 0; j < getGrid()[0].length; j++) {
				setGrid(0, j, i);
			}
		}
	}
	
	private void createAllComponents() {
		CreationUnit.createMap(this.getSize(), this);
	}

	/**
	 * Adds {@code Viewable} to the map.
	 * 
	 * @param o
	 */
	public void addToMap(MapComponent compo, int x, int y) {
		if(getGrid()[y][x] == 0) {
			setGrid(mapCompoID, x, y);
			getMapCompos().add(compo);
			compo.addToMap(this, x, y);
		}
	}
	
	public void addToMap(Monster monster, int x, int y) {
		if(getGrid()[y][x] == 0) {
			setGrid(damageableID, x, y);
			getMonsters().add(monster);
			monster.addToMap(this, x, y);
		}
	}
	
	public void addToMap(Item item, int x, int y) {
		if(getGrid()[y][x] == 0) {
			getItems().add(item);
			item.addToMap(this, x, y);
		}
	}
	
	public void addToMap(Player player, int x, int y) {
		if(getGrid()[y][x] == 0) {
			setGrid(damageableID, x, y);
			setPlayer(player);
			player.addToMap(this, x, y);
		}
	}

	/**
	 * Removes the {@code Viewable} entity at the position (x = column, y = row).
	 * 
	 * @param column
	 * @param row
	 * @see {@link Viewable}
	 */
	/*public void removeFromMap(MapComponent c) {
		int[] pos = c.getPosition();
		setNoCollision(pos[0], pos[1]);
		getMapCompos().remove(c);
	}*/
	
	/**
	 * Removes the {@code Observable} entity at the position (x = column, y = row).
	 * 
	 * @param column
	 * @param row
	 * @see {@link Observable}
	 */
	private void removeFromMap(SkillTarget skillTarget) {
		setNoCollision(skillTarget.getX(), skillTarget.getY());
		getMonsters().remove(skillTarget);
	}
	
	/**
	 * Gets the {@code Viewable} entity at the position (x = column, y = row).
	 * 
	 * @param column
	 * @param row
	 * @return {@code Viewable} entity at (x,y)
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
	 * Gets the {@code AbstractItem} at the position (x = column, y = row).
	 * 
	 * @param column
	 * @param row
	 * @return {@code AbstractItem} at (x,y)
	 */
	private IItem getItem(int x, int y){
		IItem item = null;
		for(IItem i : getItems()) {
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
	 * 
	 * @param column
	 * @param row
	 * @return
	 */
	public boolean noCollision(int column, int row){
		return getGrid()[row][column] < 1;
	}
	
	public boolean isItemAt(int x, int y) {
		return getItem(x, y) != null;
	}
	
	/**
	 * Gets the {@code AbstractItem} at the position (x = column, y = row) and removes it from the map.
	 * 
	 * @param column
	 * @param row
	 * @return {@code AbstractItem} at (x,y)
	 */
	public IItem getAndRemoveItem(int x, int y) {
		IItem item = null;
		if(isItemAt(x, y)) {
			item = getItem(x, y);
			getItems().remove(item);
		}
		return item;
	}

	@Override
	public boolean isTargetAt(int x, int y) {
		return getTargetAt(x, y) != null;
	}

	public void notifyMovement(Movement movement) {
		setNoCollision(movement.getOldX(), movement.getOldY());
		getGrid()[movement.getNewY()][movement.getNewX()] = damageableID;
		setGrid(damageableID, movement.getNewX(), movement.getNewY());		
	}

	@Override
	public void notifyDead(SkillTarget target) {
		removeFromMap(target);
		if(getMonsters().isEmpty()) {
			CreationUnit.addMonsters(this);
			notifyObservers("addMonsters");
		}
	}
	
	public void startThreads() {
		ArrayList<Monster> monsters = getMonsters();
		for(Monster m : monsters) {
			new Thread(m).start();
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

	@Override
	public void addToMap(IMap map, int x, int y) {
		// TODO Auto-generated method stub
		
	}

}
