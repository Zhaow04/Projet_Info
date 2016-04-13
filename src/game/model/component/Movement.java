package game.model.component;

public interface Movement {
	
	public void move(Movable m, int dx, int dy);
		
	public void MoveInX (Movable m);
	
	public void FaceThePlayer(Movable m);
	
	public Movable getMovable();
	
	public int getOldX();
	
	public int getOldY();

	public int getNewX();

	public int getNewY();

	public void MoveRandomly(Movable m);

	public void TrackPlayer(Movable m);
	
}
