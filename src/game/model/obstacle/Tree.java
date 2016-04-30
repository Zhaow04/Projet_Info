package game.model.obstacle;

import game.utilities.ImageDB;

/**
 * Extends from {@code Obstacle}. <br/>
 * Basic obstacle: tree.
 * 
 * @see {@link Obstacle}
 *
 */
public final class Tree extends Obstacle {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//****************************** Constructor ******************************

	/**
	 * Creates a tree.
	 */
	public Tree() {
		super(ImageDB.getTreeView());
	}
}
