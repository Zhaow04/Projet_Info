package game.model.component;

public interface Movement {
	
	public void move(Movable m, int dx, int dy);
	
	public void autoMove(Movable m);
	
	public Movable getMovable();
	
	public int getOldX();
	
	public int getOldY();

	public int getNewX();

	public int getNewY();
	
}
