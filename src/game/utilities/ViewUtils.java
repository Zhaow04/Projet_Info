package game.utilities;

import game.model.component.ViewSettings;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class ViewUtils {
	
	public ViewUtils() {
		
	}
	
	public static ImageView initImageContainer(ViewSettings viewSettings, double size, double d) {
		String imageURL = viewSettings.getImageURL();
		double offsetX = viewSettings.getOffsetX();
		double offsetY = viewSettings.getOffsetY();
		double width = viewSettings.getWidth();
		double height = viewSettings.getHeight();
		
		Image image = new Image(imageURL);
		ImageView imageContainer = new ImageView(image);
		imageContainer.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
		imageContainer.setFitWidth(size*d);
		imageContainer.setFitHeight(size*d);
		imageContainer.setPreserveRatio(true);
		return imageContainer;
	}
	
	public static ImageView initImageContainer(ViewSettings viewSettings, double size) {
		return ViewUtils.initImageContainer(viewSettings,size,0.8);
	}
	
	public static ImageView initImageView(ViewSettings viewSettings, double size) {
		String imageURL = viewSettings.getImageURL();
		double offsetX = viewSettings.getOffsetX();
		double offsetY = viewSettings.getOffsetY();
		double width = viewSettings.getWidth();
		double height = viewSettings.getHeight();
		double spanX = viewSettings.getSpanX();
		double spanY = viewSettings.getSpanY();
		
		Image image = new Image(imageURL);
		ImageView imageContainer = new ImageView(image);
		imageContainer.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
		imageContainer.setFitWidth(size*spanX);
		imageContainer.setFitHeight(size*spanY);
		imageContainer.setPreserveRatio(true);
		return imageContainer;
	}
	
}
