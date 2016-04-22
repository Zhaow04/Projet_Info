package game.view;

import game.model.component.ISkill;
import game.utilities.MovementAnimation;
import game.utilities.ViewSettings;
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
		int t = 300;
		if(viewSettings.getCount() > 6)
			t = 600;
		MovementAnimation ma = new MovementAnimation(t, container, imageView, viewSettings,
				mapView.cellSize());
		ma.addOnFinished((action) -> mapView.remove(container));
		Platform.runLater(() -> ma.play());
	}
	
}
