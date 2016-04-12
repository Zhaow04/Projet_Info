package game.model.component;

/**
 * Extends from {@code Skill}. <br/>
 * Abstract class that serves as a super class for all the direct attacks (range 1).
 * 
 * @author ZhaoWen
 * @see {@link Skill}
 *
 */
public abstract class DirectAttack extends Skill {
	
	private SkillTarget target;
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates a direct attack (range 1).
	 */
	public DirectAttack(int damage, ViewSettings viewSettings){
		super(damage, 1, viewSettings);
	}

	protected SkillTarget getTarget() {
		return target;
	}

	protected void setTarget(SkillTarget target) {
		this.target = target;
	}
	
}
