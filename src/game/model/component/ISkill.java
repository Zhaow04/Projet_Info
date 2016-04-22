package game.model.component;

import game.utilities.ViewSettings;

public interface ISkill {				
	
	
	boolean usable(SkillUser user);
	
	int getDamage();
	
	void use(SkillUser user);
	
	/**
	 * Gets the {@code ViewSettings} associated with the object.
	 * 
	 * @return {@code ViewSettings}
	 * @see {@link ViewSettings}
	 * 
	 */
	public ViewSettings getViewSettings();
	
	
}
