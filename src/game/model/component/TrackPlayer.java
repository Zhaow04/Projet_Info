package game.model.component;

import java.util.ArrayList;
import java.util.Random;

import game.model.Player;

public class TrackPlayer extends BasicMove {
	

	public TrackPlayer() {
		super();
	}
	
	
	/**
	 * 
	 */
	@Override
	public void move(Movable m) {
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
	
	@Override
	public void faceThePlayer(Movable m){
		Player player=m.getCurrentMap().getPlayer();
			m.setDirectionFacing(player.getX()-m.getX(), player.getY()-m.getY());
			m.notifyObservers("changedDirection");
	}
	
}