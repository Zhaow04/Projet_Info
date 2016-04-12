package game.model.component;

public interface ISkill {
	
	void preUse(SkillUser user);
	
	boolean usable();
	
	void use(SkillUser user);
	
	/**
	 * Gets the {@code ViewSettings} associated with the object.
	 * 
	 * @return {@code ViewSettings}
	 * @see {@link ViewSettings}
	 * 
	 */
	public ViewSettings getViewSettings();
	
	void notifyAnimationEnd();
	
}
