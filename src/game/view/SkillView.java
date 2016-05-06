package game.view;

import game.model.skill.Skill;
import game.utilities.MovementAnimation;
import game.utilities.ViewSettings;
import game.utilities.ViewUtils;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Extends from {@code StackPane}. <br/>
 * View of a {@code Skill}.
 * 
 * @author ZhaoWen
 *
 */
public class SkillView extends StackPane {
	
	/**
	 * Creates the view of {@code skill}, adds to {@code mapView} and removes it after the animation ends.
	 * @param skill
	 * @param mapView
	 */
	public SkillView(Skill skill, MapView mapView) {
		super();
		ViewSettings viewSettings = skill.getViewSettings();
		ImageView imageView = ViewUtils.initImageView(viewSettings,mapView.cellSize());
		imageView.setPreserveRatio(false);
		this.getChildren().addAll(imageView);
		SkillView container = this;
		this.setTranslateX(viewSettings.getX()*mapView.cellSize());
		this.setTranslateY(viewSettings.getY()*mapView.cellSize());
		mapView.add(this);
		int t = 300;
		if(viewSettings.getCount() > 6)
			t = 600;
		MovementAnimation ma = new MovementAnimation(t, container, imageView, viewSettings,
				mapView.cellSize());
		ma.addOnFinished((action) -> mapView.remove(container));
		Platform.runLater(() -> ma.play());
	}
	
}
