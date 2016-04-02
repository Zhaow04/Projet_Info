package game.utilities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class ViewUtils {
	
	public ViewUtils() {
		
	}
	
	public static StackPane initContainer(ImageSettings imageSettings, double size, double d) {
		String imageURL = imageSettings.getImageURL();
		double offsetX = imageSettings.getOffsetX();
		double offsetY = imageSettings.getOffsetY();
		double width = imageSettings.getWidth();
		double height = imageSettings.getHeight();
		
		StackPane container = new StackPane();
		container.setPrefWidth(size);
		container.setPrefHeight(size);
		Image image = new Image(imageURL);
		ImageView imageContainer = new ImageView(image);
		imageContainer.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
		imageContainer.setFitWidth(size*d);
		imageContainer.setFitHeight(size*d);
		imageContainer.setPreserveRatio(true);
		container.getChildren().add(imageContainer);
		return container;
	}
	
	public static StackPane initContainer(ImageSettings imageSettings, double size) {
		return ViewUtils.initContainer(imageSettings,size,0.8);
	}
	
}
