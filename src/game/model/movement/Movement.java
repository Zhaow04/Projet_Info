package game.model.movement;

public interface Movement {
	
	public void move(Movable m, int dx, int dy);
		
	public void move(Movable m);
		
	public Movable getMovable();
	
	public int getOldX();
	
	public int getOldY();

	public int getNewX();

	public int getNewY();
	
	public void setBaseX(int baseX);
	
	public void setBaseY(int baseY);
}
