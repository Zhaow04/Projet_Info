package game.model.obstacle;

import game.utilities.ImageDB;

/**
 * Extends from {@code Obstacle} <br/>
 * Basic obstacle : tree.
 * 
 * @see {@link Obstacle}
 *
 */
public class Tree extends Obstacle{

	//****************************** Constructor ******************************

	/**
	 * Creates a tree.
	 */
	public Tree () {
		super(ImageDB.getTreeView());
	}
}
