package game.view;

import game.model.component.ISkill;
import game.model.component.ViewSettings;
import game.utilities.SpriteAnimation;
import game.utilities.ViewUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class SkillView extends StackPane {
	
	public SkillView(ISkill skill, MapView mapView) {
		super();
		ViewSettings imageSettings = skill.getViewSettings();
		ImageView imageView = ViewUtils.initImageView(imageSettings,mapView.cellSize());
		this.getChildren().add(imageView);
		SkillView container = this;
		mapView.addToMap(container, imageSettings.getX(), imageSettings.getY());
		SpriteAnimation anim = new SpriteAnimation(imageSettings,
				imageView, Duration.millis(300), 5, 4);
		anim.setOnFinished(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent disappear) {
				mapView.remove(container);
				skill.notifyAnimationEnd();
			}
		});
	}
	
}
