package game.model.movement;

import java.util.ArrayList;
import java.util.Random;

import game.model.Movable;
import game.model.Player;

/**
 * Extends {@code BasicMove}. <br/>
 * Public class that represents a type of movement : tracking the player. Meant for the enemies.
 * 
 * @see {@link BasicMove}
 *
 */
public class TrackPlayer extends BasicMove {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//****************************** Constructor ******************************

	public TrackPlayer() {
		super();
	}
	
	//******************************** Methods ********************************

	@Override
	public void move(Movable m) {
		Player player=m.getCurrentMap().getPlayer();
		
		if(canMove(m, 0, (int) (Math.signum(player.getY()-m.getY()))) &&
				
					((Math.abs(player.getX()-m.getX()) <= Math.abs(player.getY()-m.getY()))
							
					|| (Math.abs(player.getY()-m.getY()) < Math.abs(player.getX()-m.getX())
					&& !(canMove(m, (int) (Math.signum(player.getX()-m.getX())),0)))  )) {
			
			super.move(m, 0, (int) (Math.signum(player.getY()-m.getY())));			

		}
		
		else if(canMove(m, (int) (Math.signum(player.getX()-m.getX())), 0) &&
				
						((Math.abs(player.getY()-m.getY()) < Math.abs(player.getX()-m.getX()))
				
						|| (Math.abs(player.getX()-m.getX()) <= Math.abs(player.getY()-m.getY())
					&& !(canMove(m, 0, (int) (Math.signum(player.getY()-m.getY()))))) )) {
			
			super.move(m, (int) (Math.signum(player.getX()-m.getX())), 0);

		}
		
		else {
			moveRandomly(m);
		}

	}
	
	/**
	 * Makes the {@code Movable} m move randomly if possible.
	 * @param m
	 */
	private void moveRandomly (Movable m) {
		int[] AllDirections[]={{0,1},{1,0},{0,-1},{-1,0}};
		ArrayList<int[]> PossibleDirections = new ArrayList<int[]>();
		for (int[] direction : AllDirections){
			if(canMove(m, direction[0],direction[1])){
				PossibleDirections.add(direction);
			}
		}
		if (!PossibleDirections.isEmpty()){ 
			int[] newDirection = PossibleDirections.get(new Random().nextInt(PossibleDirections.size()));
			move(m,newDirection[0],newDirection[1]);
			}
	}
	
}