package game.model.component;

import game.model.Player;

public class FaceThePlayer extends BasicMove {
	
	public FaceThePlayer() {
		super();
	}

	
	@Override
	public void move(Movable m){
		Player player=m.getCurrentMap().getPlayer();
			m.setDirectionFacing(player.getX()-m.getX(), player.getY()-m.getY());
			m.notifyObservers("changedDirection");
	}
}
