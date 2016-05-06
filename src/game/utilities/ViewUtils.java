package game.utilities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Utility class which contains methods to create an {@link ImageView} with a {@link ViewSettings}.
 *
 */
public abstract class ViewUtils {
	
	/**
	 * Returns an {@link ImageView} based on the {@link ViewSettings} with a given size. The parameter
	 * {@code d} defines the percentage (e.g. 1 for 100%, 0.8 for 80%) of the size the image should span.
	 * @param viewSettings
	 * @param size
	 * @param d
	 * @return
	 */
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
	
	/**
	 * Returns an {@link ImageView} based on the {@link ViewSettings} with a given size. The image will fit
	 * 80% of the size (by default).
	 * @param viewSettings
	 * @param size
	 * @return
	 */
	public static ImageView initImageContainer(ViewSettings viewSettings, double size) {
		return ViewUtils.initImageContainer(viewSettings,size,0.8);
	}
	
	/**
	 * Returns an {@link ImageView} based on the {@link ViewSettings} with a given size. The image will span
	 * according to the settings of {@code ViewSettings} (e.g. spanX = 3 means the image will fit 3 times the
	 * given size).
	 * @param viewSettings
	 * @param size
	 * @return
	 */
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
