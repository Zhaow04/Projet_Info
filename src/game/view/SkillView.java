package game.view;

import game.model.component.ISkill;
import game.model.component.ViewSettings;
import game.utilities.MovementAnimation;
import game.utilities.ViewUtils;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class SkillView extends StackPane {
	
	public SkillView(ISkill skill, MapView mapView) {
		super();
		ViewSettings viewSettings = skill.getViewSettings();
		ImageView imageView = ViewUtils.initImageView(viewSettings,mapView.cellSize());
		imageView.setPreserveRatio(false);
		this.getChildren().addAll(imageView);
		SkillView container = this;
		mapView.addToMap(container, viewSettings.getStartX(), viewSettings.getStartY());
		MovementAnimation ma = new MovementAnimation(300, container, imageView, viewSettings,
				mapView.cellSize());
		ma.addOnFinished((action) -> mapView.remove(container));
		Platform.runLater(() -> ma.play());
	}
	
}
