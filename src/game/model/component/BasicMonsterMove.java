package game.model.component;

import java.util.ArrayList;
import java.util.Random;

import game.model.Player;

public class BasicMonsterMove extends BasicMove {
	
	private int baseX=-1, baseY=-1;
	
	public BasicMonsterMove() {
		super();
	}
	
	private int getBaseX() {
		return baseX;
	}

	private void setBaseX(int baseX) {
		this.baseX = baseX;
	}

	private int getBaseY() {
		return baseY;
	}

	private void setBaseY(int baseY) {
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
	
	public void trackPlayer(Movable m){
		
		Player player=m.getCurrentMap().getPlayer();
		
		if (   canMove(m, 0, (int) (Math.signum(player.getY()-m.getY()))) &&
				
					(	(Math.abs(player.getX()-m.getX()) <= Math.abs(player.getY()-m.getY()))
							
					|| (Math.abs(player.getY()-m.getY()) < Math.abs(player.getX()-m.getX())
					&& !(canMove(m, (int) (Math.signum(player.getX()-m.getX())),0)))  )    ){
			
			super.move(m, 0,  (int) (Math.signum(player.getY()-m.getY())));			

		}
		
		else if (  canMove(m, (int) (Math.signum(player.getX()-m.getX())), 0) &&
				
						(   (Math.abs(player.getY()-m.getY()) < Math.abs(player.getX()-m.getX()))
				
						|| (Math.abs(player.getX()-m.getX()) <= Math.abs(player.getY()-m.getY())
					&& !(canMove(m, 0, (int) (Math.signum(player.getY()-m.getY()))))) )   ) {
			
			super.move(m, (int) (Math.signum(player.getX()-m.getX())), 0);

		}
		
		else {
			moveRandomly(m);
		}
		
		setBaseX(m.getX());
		setBaseY(m.getY());
	}
	
	
	/*@Override
	public void autoMove(Movable m) {
		if(getBaseX() == -1 && getBaseY() == -1) {
			setBaseX(m.getX()); setBaseY(m.getY());
		}
		if(m.getY() == getBaseY() && m.getX() == getBaseX())
			super.move(m,0,1);
		else if(m.getY() == getBaseY() + 1 && m.getX() == getBaseX())
			super.move(m,1,0);
		else if(m.getY() == getBaseY() + 1 && m.getX() == getBaseX() + 1)
			super.move(m,0,-1);
		else if(m.getY() == getBaseY() && m.getX() == getBaseX() + 1)
			super.move(m,-1,0);
	}*/
	
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
	
	@Override
	public void faceThePlayer(Movable m){
		Player player=m.getCurrentMap().getPlayer();
			m.setDirectionFacing(player.getX()-m.getX(), player.getY()-m.getY());
			m.notifyObservers("changedDirection");
	}
	
}
		

