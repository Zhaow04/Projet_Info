package game.model;

import game.view.MapController;

/**
 * Extends from {@code ViewableObject}. <br/>
 * Map of the game. It knows whether or not a position
 * is occupied and which object is occupying it.
 * 
 * @author ZhaoWen
 * @see {@link ViewableObject}
 *
 */
public class Map extends ViewableObject {
	
	//****************************** Attributes ******************************
	
	public boolean[][] grid;
	private LivingBeing[][] livingOnMap;
	private Obstacle[][] obstacleOnMap;
	
	private MapController mapController;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a map (matrix) with the same number (defined by {@code size}) of rows and
	 * columns. Sets also the image of the map (ground).
	 * 
	 * @param size
	 */
	public Map(int size){
		setSize(size);
		setLivingOnMap(new LivingBeing[size][size]);
		setImageURL("game/view/plains.png");
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
	 * Gets the controller of the map.
	 * 
	 * @return controller of the map
	 * @see {@link MapController}
	 */
	public MapController getMapController() {
		return mapController;
	}
	
	/**
	 * Sets the controller of the map.
	 * 
	 * @param mapController
	 * @see {@link MapController}
	 */
	public void setMapController(MapController mapController) {
		this.mapController = mapController;
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
	 * {@code LivingBeing} present at the position (x,y) on the map will figures at the
	 * same position in the matrix.
	 * 
	 * @param livingOnMap
	 * @see {@link LivingBeing}
	 */
	private void setLivingOnMap(LivingBeing[][] livingOnMap) {
		this.livingOnMap = livingOnMap;
	}
	
	//******************************** Methods ********************************
	
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

}
