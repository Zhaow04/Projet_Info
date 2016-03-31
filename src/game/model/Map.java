package game.model;

import java.util.ArrayList;
import java.util.Observable;

import game.utilities.ImageSettings;
import game.view.MapView;

/**
 * Map of the game. It knows whether or not a position
 * is occupied and which object is occupying it.
 * 
 * @author ZhaoWen
 * @see {@link ImageSettings}
 *
 */
public class Map extends Observable {
	
	//****************************** Attributes ******************************
	
	private ArrayList<Viewable> viewableOnMap = new ArrayList<Viewable>();
	
	public boolean[][] grid;
	private LivingBeing[][] livingOnMap;
	private Obstacle[][] obstacleOnMap;
	private SafeHouse[][] safehouseOnMap;
	private Dungeon[][] dungeonOnMap;
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
		new MapView(this);
		/*
		RedDragon monster1 = new RedDragon(this,0,1,1);
		viewableOnMap.add(monster1);
		GiantRat monster2 = new GiantRat(this,7,3,1);
		viewableOnMap.add(monster2);
		
		SafeHouse safehouse = new SafeHouse(this,4,4);
		viewableOnMap.add(safehouse);
		
		//Dungeon dungeon = new Dungeon(map, 6,6);
		
		Rock rock = new Rock (this,2,3);
		viewableOnMap.add(rock);
		Tree tree = new Tree (this,6,5);
		viewableOnMap.add(tree);
		Bush bush = new Bush (this,8,8);
		viewableOnMap.add(bush);
		
		HpPotion hpPotion = new HpPotion(this, 100, 2, 8);
		viewableOnMap.add(hpPotion);
		HpPotion hpPotion2 = new HpPotion(this, 100, 8, 2);
		viewableOnMap.add(hpPotion2);*/
	}
	
	/**
	 * Creates a map (matrix) with the same number (defined by {@code size}) of rows and
	 * columns. Sets also the image of the map (ground).
	 * 
	 * @param size
	 */
	public Map(int size){
		setSize(size);
		setLivingOnMap(new LivingBeing[size][size]);
		setObstacleOnMap(new Obstacle[size][size]);
		setSafeHouseOnMap(new SafeHouse[size][size]);
		setDungeonOnMap(new Dungeon[size][size]);
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
		return grid.length;
	}
	
	/**
	 * Sets the size of the map (same number of rows and columns). Sets all the positions
	 * to empty.
	 * 
	 * @param size
	 */
	private void setSize(int size){
		grid = new boolean[size][size];
		emptyGrid();
	}
	
	/**
	 * Gets the matrix of {@code LivingBeing} associated to the map. An instance of
	 * {@code LivingBeing} present at the position (x,y) on the map will figures at the
	 * same position in the matrix.
	 * 
	 * @return matrix of {@code LivingBeing}
	 * @see {@link LivingBeing}
	 */
	public LivingBeing[][] getLivingOnMap() {
		return livingOnMap;
	}
	
	/**
	 * Sets the matrix of {@code LivingBeing} associated to the map. An instance of
	 * {@code LivingBeing} present at the position (x,y) on the map will figure at the
	 * same position in the matrix.
	 * 
	 * @param livingOnMap
	 * @see {@link LivingBeing}
	 */
	private void setLivingOnMap(LivingBeing[][] livingOnMap) {
		this.livingOnMap = livingOnMap;
	}
	
	/**
	 * Gets the matrix of {@code Obstacle} associated to the map. An instance of
	 * {@code Obstacle} present at the position (x,y) on the map will figure at the
	 * same position in the matrix.
	 * 
	 * @return matrix of {@code Obstacle}
	 * @see {@link Obstacle}
	 */
	public Obstacle[][] getObstacleOnMap() {
		return obstacleOnMap;
	}
	
	/**
	 * Sets the matrix of {@code Obstacle} associated to the map. An instance of
	 * {@code Obstacle} present at the position (x,y) on the map will figures at the
	 * same position in the matrix.
	 * 
	 * @param obstacleOnMap
	 * @see {@link Obstacle}
	 */
	private void setObstacleOnMap(Obstacle[][] obstacleOnMap) {
		this.obstacleOnMap = obstacleOnMap;
	}
	
	/**
	 * Gets the matrix of {@code SafeHouse} associated to the map. An instance of
	 * {@code SafeHouse} present at the position (x,y) on the map will figures at the
	 * same position in the matrix.
	 * 
	 * @return matrix of {@code SafeHouse}
	 * @see {@link SafeHouse}
	 */
	public SafeHouse[][] getSafeHouseOnMap() {
		return safehouseOnMap;
	}
	
	/**
	 * Sets the matrix of {@code SafeHouse} associated to the map. An instance of
	 * {@code SafeHouse} present at the position (x,y) on the map will figures at the
	 * same position in the matrix.
	 * 
	 * @param safehouseOnMap
	 * @see {@link SafeHouse}
	 */
	private void setSafeHouseOnMap(SafeHouse[][] safehouseOnMap) {
		this.safehouseOnMap = safehouseOnMap;
	}
	
	/**
	 * Gets the matrix of {@code Dungeon} associated to the map. An instance of
	 * {@code Dungeon} present at the position (x,y) on the map will figures at the
	 * same position in the matrix.
	 * 
	 * @return matrix of {@code Dungeon}
	 * @see {@link Dungeon}
	 */
	public Dungeon[][] getDungeonOnMap() {
		return dungeonOnMap;
	}
	
	/**
	 * Sets the matrix of {@code Dungeon} associated to the map. An instance of
	 * {@code Dungeon} present at the position (x,y) on the map will figures at the
	 * same position in the matrix.
	 * 
	 * @param dungeonOnMap
	 * @see {@link Dungeon}
	 */
	private void setDungeonOnMap(Dungeon[][] dungeonOnMap) {
		this.dungeonOnMap = dungeonOnMap;
	}
	
	//******************************** Methods ********************************
	
	
	public ImageSettings getImageSettings() {
		return imageSettings;
	}

	public void addToViewable(Viewable viewable) {
		viewableOnMap.add(viewable);
	}

	/**
	 * Adds a {@code LivingBeing} to the map.
	 * 
	 * @param living
	 * @see {@link LivingBeing}
	 */
	public void addLivingOnMap(LivingBeing living){
		int[] pos = living.getPosition();
		getLivingOnMap()[pos[1]][pos[0]] = living;
		setOccupied(pos[0],pos[1]);
	}
	
	/**
	 * Adds a {@code Obstacle} to the map.
	 * 
	 * @param living
	 * @see {@link LivingBeing}
	 */
	public void addObstacleOnMap(Obstacle obstacle){
		int[] pos = obstacle.getPosition();
		getObstacleOnMap()[pos[1]][pos[0]] = obstacle;
		setOccupied(pos[0],pos[1]);
	}
	
	/**
	 * Adds a {@code SafeHouse} to the map.
	 * 
	 * @param safeHouse
	 * @see {@link SafeHouse}
	 */
	public void addSafeHouseOnMap(SafeHouse safeHouse){
		int[] pos = safeHouse.getPosition();
		getSafeHouseOnMap()[pos[1]][pos[0]] = safeHouse;
		setOccupied(pos[0],pos[1]);
	}
	
	/**
	 * Adds a {@code Dungeon} to the map.
	 * 
	 * @param dungeon
	 * @see {@link Dungeon}
	 */
	public void addDungeonOnMap(Dungeon dungeon){
		int[] pos = dungeon.getPosition();
		getDungeonOnMap()[pos[1]][pos[0]] = dungeon;
		setOccupied(pos[0],pos[1]);
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
	 * Removes the {@code LivingBeing} at the position (x = column, y = row).
	 * 
	 * @param column
	 * @param row
	 * @see {@link LivingBeing}
	 */
	public void removeLivingOnMap(int column, int row){
		getLivingOnMap()[row][column] = null;
	}
	
	/**
	 * Removes the {@code Obstacle} at the position (x = column, y = row).
	 * 
	 * @param column
	 * @param row
	 * @see {@link Obstacle}
	 */
	public void removeObstacleOnMap(int column, int row){
		getObstacleOnMap()[row][column] = null;
	}
	
	/**
	 * Removes the {@code SafeHouse} at the position (x = column, y = row).
	 * 
	 * @param column
	 * @param row
	 * @see {@link SafeHouse}
	 */
	public void removeSafeHouseOnMap(int column, int row){
		getSafeHouseOnMap()[row][column] = null;
	}
	
	/**
	 * Removes the {@code Dungeon} at the position (x = column, y = row).
	 * 
	 * @param column
	 * @param row
	 * @see {@link Dungeon}
	 */
	public void removeDungeonOnMap(int column, int row){
		getDungeonOnMap()[row][column] = null;
	}
	
	/**
	 * Removes the {@code Item} at the position (x = column, y = row).
	 * 
	 * @param column
	 * @param row
	 * @see {@link Item}
	 */
	public void removeItemOnMap(int column, int row){
		Item item = itemOnMap[row][column];
		item.removedFromMap();
		itemOnMap[row][column] = null;
	}
	
	/**
	 * Gets the {@code LivingBeing} at the position (x = column, y = row).
	 * 
	 * @param column
	 * @param row
	 * @return {@code LivingBeing} at (x,y)
	 */
	public LivingBeing getLiving(int column, int row){
		return getLivingOnMap()[row][column];
	}
	
	/**
	 * Gets the {@code Obstacle} at the position (x = column, y = row).
	 * 
	 * @param column
	 * @param row
	 * @return {@code Obstacle} at (x,y)
	 */
	public Obstacle getObstacle(int column, int row){
		return getObstacleOnMap()[row][column];
	}
	
	/**
	 * Gets the {@code SafeHouse} at the position (x = column, y = row).
	 * 
	 * @param column
	 * @param row
	 * @return {@code SafeHouse} at (x,y)
	 */
	public SafeHouse getSafeHouse(int column, int row){
		return getSafeHouseOnMap()[row][column];
	}
	
	/**
	 * Gets the {@code Dungeon} at the position (x = column, y = row).
	 * 
	 * @param column
	 * @param row
	 * @return {@code Dungeon} at (x,y)
	 */
	public Dungeon getDungeon(int column, int row){
		return getDungeonOnMap()[row][column];
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
	public void setOccupied(int column, int row){
		grid[row][column] = true;
	}
	
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
	public void setEmpty(int column, int row){
		grid[row][column] = false;
	}
	
	/**
	 * Returns whether or not the position (x = column, y = row) is empty.
	 * 
	 * @param column
	 * @param row
	 * @return
	 */
	public boolean isEmpty(int column, int row){
		boolean isEmpty;
		if(grid[row][column])
			isEmpty = false;
		else
			isEmpty = true;
		return isEmpty;
	}
	
	/**
	 * Empty the entire map.
	 * 
	 */
	private void emptyGrid(){
		for(int i = 0; i < grid.length; i++){
			for(int j = 0; j < grid[0].length; j++){
				grid[i][j] = false;
			}
		}
	}
	
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
