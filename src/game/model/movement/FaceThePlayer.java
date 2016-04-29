package game.model.movement;

import game.model.Movable;
import game.model.Player;

/**
 * Public class that represents a type of movement : facing the player. <br/>
 * Meant for the enemies. <br/>
 * Extends {@code BasicMove}
 * @see {@link BasicMove}
 *
 */
public class FaceThePlayer extends BasicMove {
	
	//****************************** Constructor ******************************

	/**
	 * Creates a move : facing the player.
	 */
	public FaceThePlayer() {
		super();
	}

	//******************************** Methods ********************************

	@Override
	public void move(Movable m){
		Player player=m.getCurrentMap().getPlayer();
			m.setDirectionFacing(player.getX()-m.getX(), player.getY()-m.getY());
			m.notifyObservers("changedDirection");
	}
}
