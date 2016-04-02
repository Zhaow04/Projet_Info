package game.model;

import java.util.ArrayList;

import game.utilities.ImageSettings;

/**
 * Map of the game. It knows whether or not a position
 * is occupied and which object is occupying it.
 * 
 * @author ZhaoWen
 * @see {@link ImageSettings}
 *
 */
public class Map {
	
	//****************************** Attributes ******************************
	
	//private ArrayList<Observable> viewableOnMap = new ArrayList<Observable>();
	
	private Observable[][] observableMatrix;
	private ArrayList<Movable> movableList = new ArrayList<Movable>();
	private Item[][] itemOnMap;
	
	private ImageSettings imageSettings = new ImageSettings("game/utilities/plains.png");
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a map (matrix) with the same number (defined by {@code size}) of rows and
	 * columns. Sets the image of the map (ground) and creates the view.
	 * 
	 * @param size
	 */
	public Map(int size, String arg){
		this(size);
		
		RedDragon monster1 = new RedDragon(this,0,1,1);
		//viewableOnMap.add(monster1);
		GiantRat monster2 = new GiantRat(this,7,3,1);
		//viewableOnMap.add(monster2);
		
		SafeHouse safehouse = new SafeHouse(this,4,4);
		//viewableOnMap.add(safehouse);
		
		//Dungeon dungeon = new Dungeon(map, 6,6);
		
		Rock rock = new Rock (this,2,3);
		//viewableOnMap.add(rock);
		Tree tree = new Tree (this,6,5);
		//viewableOnMap.add(tree);
		Bush bush = new Bush (this,8,8);
		//viewableOnMap.add(bush);
		
		HpPotion hpPotion = new HpPotion(this, 100, 2, 8);
		//viewableOnMap.add(hpPotion);
		HpPotion hpPotion2 = new HpPotion(this, 100, 8, 2);
		//viewableOnMap.add(hpPotion2);
	}
	
	/**
	 * Creates a map (matrix) with the same number (defined by {@code size}) of rows and
	 * columns. Sets also the image of the map (ground).
	 * 
	 * @param size
	 */
	public Map(int size){
		setSize(size);
		itemOnMap = new Item[size][size];
		
		//setImageURL("game/utilities/plains.png");
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the size of the map (same number of rows and columns).
	 * 
	 * @return size of the map
	 */
	public int getSize(){
		return observableMatrix.length;
	}
	
	/**
	 * Sets the size of the map (same number of rows and columns). Sets all the positions
	 * to empty.
	 * 
	 * @param size
	 */
	private void setSize(int size){
		observableMatrix = new Observable[size][size];
		//emptyGrid();
	}
	
	/**
	 * Gets the matrix of {@code LivingBeing} associated to the map. An instance of
	 * {@code LivingBeing} present at the position (x,y) on the map will figures at the
	 * same position in the matrix.
	 * 
	 * @return matrix of {@code LivingBeing}
	 * @see {@link LivingBeing}
	 */
	public Observable[][] getObservableMatrix() {
		return observableMatrix;
	}

	/**
	 * Sets the matrix of {@code LivingBeing} associated to the map. An instance of
	 * {@code LivingBeing} present at the position (x,y) on the map will figure at the
	 * same position in the matrix.
	 * 
	 * @param livingOnMap
	 * @see {@link LivingBeing}
	 */
	/*private void setEntityMatrix(Observable[][] observableMatrix) {
		this.entityMatrix = observableMatrix;
	}*/
	
	public ArrayList<Movable> getMovableList() {
		return movableList;
	}
	
	public Item[][] getItemOnMap() {
		return itemOnMap;
	}
	
	//******************************** Methods ********************************

	public ImageSettings getImageSettings() {
		return imageSettings;
	}
/*
	private void addToViewable(Observable entityOnMap) {
		viewableOnMap.add(entityOnMap);
	}*/
	
	/**
	 * Adds a {@code Observable} to the map.
	 * 
	 * @param o
	 */
	public void addObservableOnMap(Observable o){
		int[] pos = o.getPosition();
		observableMatrix[pos[1]][pos[0]] = o;
	}
	
	public void addMovable(Movable m) {
		movableList.add(m);
	}
	
	/**
	 * Adds a {@code Item} to the map.
	 * 
	 * @param item
	 * @see {@link Item}
	 */
	public void addItemOnMap(Item item){
		int[] pos = item.getPosition();
		itemOnMap[pos[1]][pos[0]] = item;
	}
	
	/**
	 * Removes the {@code Observable} entity at the position (x = column, y = row).
	 * 
	 * @param column
	 * @param row
	 * @see {@link Observable}
	 */
	public void removeObservableOnMap(int column, int row){
		observableMatrix[row][column] = null;
	}
	
	public void removeMovable(Movable m) {
		movableList.remove(m);
	}
	
	/**
	 * Removes the {@code Item} at the position (x = column, y = row).
	 * 
	 * @param column
	 * @param row
	 * @see {@link Item}
	 */
	public void removeItemOnMap(int column, int row) {
		itemOnMap[row][column] = null;
	}
	
	/**
	 * Gets the {@code Observable} entity at the position (x = column, y = row).
	 * 
	 * @param column
	 * @param row
	 * @return {@code Observable} entity at (x,y)
	 */
	public Observable getObservableOnMap(int column, int row){
		return observableMatrix[row][column];
	}
	
	/**
	 * Gets the {@code Item} at the position (x = column, y = row).
	 * 
	 * @param column
	 * @param row
	 * @return {@code Item} at (x,y)
	 */
	public Item getItem(int column, int row){
		return itemOnMap[row][column];
	}
	
	/**
	 * Sets the position (x = column, y = row) as occupied.
	 * 
	 * @param column
	 * @param row
	 */
	/*private void setOccupied(int column, int row){
		grid[row][column] = true;
	}*/
	
	/**
	 * Returns whether or not the position (x = column, y = row) is occupied.
	 * 
	 * @param column
	 * @param row
	 * @return position occupied or not
	 */
	public boolean isOccupied(int column, int row){
		return !isEmpty(column, row);
	}
	
	/**
	 * Sets the position (x = column, y = row) as empty.
	 * 
	 * @param column
	 * @param row
	 */
	/*private void setEmpty(int column, int row){
		grid[row][column] = false;
	}*/
	
	/**
	 * Returns whether or not the position (x = column, y = row) is empty.
	 * 
	 * @param column
	 * @param row
	 * @return
	 */
	public boolean isEmpty(int column, int row){
		boolean isEmpty;
		if(observableMatrix[row][column] == null)
			isEmpty = true;
		else
			isEmpty = false;
		return isEmpty;
	}
	
	/**
	 * Empty the entire map.
	 * 
	 */
	/*private void emptyGrid(){
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				grid[i][j] = false;
			}
		}
	}*/
	
	/**
	 * Gets the {@code Item} at the position (x = column, y = row) and removes it from the map.
	 * 
	 * @param column
	 * @param row
	 * @return {@code Item} at (x,y)
	 */
	public Item getAndRemoveItem(int column, int row){
		Item item = itemOnMap[row][column];
		removeItemOnMap(column, row);
		return item;
	}

}
