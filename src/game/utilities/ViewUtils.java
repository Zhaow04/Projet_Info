package game.utilities;

import game.view.MapView;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public abstract class ViewUtils {
	
	public ViewUtils() {
		
	}
	
	public static StackPane initContainer(ImageSettings imageSettings) {
		String imageURL = imageSettings.getImageURL();
		double offsetX = imageSettings.getOffsetX();
		double offsetY = imageSettings.getOffsetY();
		double width = imageSettings.getWidth();
		double height = imageSettings.getHeight();
		
		StackPane container = new StackPane();
		container.setPrefWidth(MapView.currentMapCellSize());
		container.setPrefHeight(MapView.currentMapCellSize());
		Image image = new Image(imageURL);
		ImageView imageContainer = new ImageView(image);
		imageContainer.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
		imageContainer.setFitWidth(MapView.currentMapCellSize()*0.8);
		imageContainer.setFitHeight(MapView.currentMapCellSize()*0.8);
		imageContainer.setPreserveRatio(true);
		container.getChildren().add(imageContainer);
		return container;
	}
	
	public static StackPane initContainer(ImageSettings imageSettings, double d) {
		String imageURL = imageSettings.getImageURL();
		double offsetX = imageSettings.getOffsetX();
		double offsetY = imageSettings.getOffsetY();
		double width = imageSettings.getWidth();
		double height = imageSettings.getHeight();
		
		StackPane container = new StackPane();
		container.setPrefWidth(MapView.currentMapCellSize());
		container.setPrefHeight(MapView.currentMapCellSize());
		Image image = new Image(imageURL);
		ImageView imageContainer = new ImageView(image);
		imageContainer.setViewport(new Rectangle2D(offsetX,offsetY,width,height));
		imageContainer.setFitWidth(MapView.currentMapCellSize()*d);
		imageContainer.setFitHeight(MapView.currentMapCellSize()*d);
		imageContainer.setPreserveRatio(true);
		container.getChildren().add(imageContainer);
		return container;
	}
	
}
