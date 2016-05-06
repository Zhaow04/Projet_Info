package game.view;

import game.model.MapComponent;
import game.utilities.ViewSettings;
import game.utilities.ViewUtils;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/**
 * Extends from {@code StackPane} <br/>
 * View of a {@code MapComponent}.
 * 
 * @author ZhaoWen
 *
 */
public class MapComponentView extends StackPane {
	
	//****************************** Attributes ******************************
	
	//****************************** Constructor ******************************
	
	/**
	 * Creates the view of a {@code MapComponent}.
	 * @param mapComponent
	 * @param cellSize
	 * @see {@link MapComponent}
	 */
	public MapComponentView(MapComponent mapComponent, double cellSize) {
		super();
		this.setPrefSize(cellSize, cellSize);
		ViewSettings viewSettings = mapComponent.getViewSettings();
		ImageView imageContainer = ViewUtils.initImageView(viewSettings,cellSize*0.8);
		this.getChildren().add(imageContainer);
		this.setTranslateX(viewSettings.getX()*cellSize);
		this.setTranslateY(viewSettings.getY()*cellSize);
	}
	
}
