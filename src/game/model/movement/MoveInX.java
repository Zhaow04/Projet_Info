package game.model.movement;

import java.util.ArrayList;
import java.util.Random;

import game.model.Player;

public class MoveInX extends BasicMove {
	
	private int baseX=-1, baseY=-1;
	
	public MoveInX() {
		super();
	}
	
	private int getBaseX() {
		return baseX;
	}

	@Override
	public void setBaseX(int baseX) {
		this.baseX = baseX;
	}

	private int getBaseY() {
		return baseY;
	}

	@Override
	public void setBaseY(int baseY) {
		this.baseY = baseY;
	}

	/**
	 * 
	 */
	@Override
	public void move(Movable m) {
		if(getBaseX() == -1 && getBaseY() == -1) {
			setBaseX(m.getX()); setBaseY(m.getY());
		}
		if(m.getY() == getBaseY() && m.getX() == getBaseX())
			moveRandomly (m);
		else if(m.getY() == getBaseY() + 1 && m.getX() == getBaseX())
			super.move(m,0,-1);
		else if(m.getY() == getBaseY() - 1 && m.getX() == getBaseX())
			super.move(m,0,1);
		else if(m.getY() == getBaseY() && m.getX() == getBaseX() + 1)
			super.move(m,-1,0);
		else if(m.getY() == getBaseY() && m.getX() == getBaseX() - 1)
			super.move(m,1,0);

	}
		
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
		

