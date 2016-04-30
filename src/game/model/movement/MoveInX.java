package game.model.movement;

import java.util.ArrayList;
import java.util.Random;

import game.model.Movable;

/**
 * Extends {@code BasicMove}. <br/>
 * Public class that represents a type of movement: moving in X. Meant for the enemies.
 * 
 * @see {@link BasicMove}
 *
 */
public class MoveInX extends BasicMove {
	
	//****************************** Attributes ******************************

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int baseX=-1, baseY=-1;
	
	//****************************** Constructor ******************************

	/**
	 * Creates the move in X.
	 */
	public MoveInX() {
		super();
	}

	//************************** Getters and Setters **************************

	@Override
	public void setBaseX(int baseX) {
		this.baseX = baseX;
	}

	@Override
	public void setBaseY(int baseY) {
		this.baseY = baseY;
	}

	//******************************** Methods ********************************

	@Override
	public void move(Movable m) {
		if(baseX == -1 && baseY == -1) {
			setBaseX(m.getX()); setBaseY(m.getY());
		}
		if(m.getY() == baseY && m.getX() == baseX)
			moveRandomly (m);
		else if(m.getY() == baseY + 1 && m.getX() == baseX)
			super.move(m,0,-1);
		else if(m.getY() == baseY - 1 && m.getX() == baseX)
			super.move(m,0,1);
		else if(m.getY() == baseY && m.getX() == baseX + 1)
			super.move(m,-1,0);
		else if(m.getY() == baseY && m.getX() == baseX - 1)
			super.move(m,1,0);

	}
		
	/**
	 * Makes the {@code Movable} m move randomly if possible.
	 * @param m
	 */
	private void moveRandomly (Movable m) {
		int[] AllDirections[]={{0,1},{1,0},{0,-1},{-1,0}};
		ArrayList<int[]> PossibleDirections = new ArrayList<int[]>();
		for (int[] direction : AllDirections){
			if(canMove(m, direction[0],direction[1])) {
				PossibleDirections.add(direction);
			}
		}
		if(!PossibleDirections.isEmpty()) { 
			int[] newDirection = PossibleDirections.get(new Random().nextInt(PossibleDirections.size()));
			move(m,newDirection[0],newDirection[1]);
			}
	}
	
}
		

