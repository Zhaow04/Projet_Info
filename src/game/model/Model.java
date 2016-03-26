package game.model;

import java.util.ArrayList;

public interface Model {
	
	public Map getMap();
	public Player getPlayer();
	public ArrayList<LivingBeing> getLivingList();
	
	
}
