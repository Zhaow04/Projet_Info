package game.model.component;

public class MoveInSquare extends BasicMove {
	
	private int baseX = -1, baseY = - 1;
	
	public MoveInSquare() {
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
	}
	
}
