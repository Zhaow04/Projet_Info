package game.utilities;

import javafx.scene.image.Image;

/**
 * Class that serves for display. Stocks all the informations needed for display such as
 * the URL of the image, the x and y offsets, the width and the height of the wanted region.
 * 
 * @author ZhaoWen
 *
 */
public class ImageSettings {
	
	//****************************** Attributes ******************************
	
	private String imageURL;
	private double offsetX, offsetY, width, height;
	
	//****************************** Constructor ******************************
	
	public ImageSettings(String imageURL) {
		this(imageURL,0,0,-1,-1);
		Image image = new Image(imageURL);
		this.width = image.getWidth();
		this.height = image.getHeight();
	}
	
	/**
	 * 
	 */
	public ImageSettings(String imageURL, int offsetX, int offsetY, int width, int height){
		this.imageURL = imageURL;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		this.width = width;
		this.height = height;
	}
	
	//************************** Getters and Setters **************************
	
	/**
	 * Gets the image URL.
	 * 
	 * @return URL
	 */
	public String getImageURL() {
		return imageURL;
	}
	
	/**
	 * Gets the x offset of the image to be displayed.
	 * 
	 * @return double
	 */
	public double getOffsetX() {
		return offsetX;
	}
	
	/**
	 * Gets the y offset of the image to be displayed.
	 * 
	 * @return double
	 */
	public double getOffsetY() {
		return offsetY;
	}
	/**
	 * Sets the y offset of the image to be displayed.
	 * 
	 * @param offsetY
	 */
	public void setOffsetY(double offsetY) {
		this.offsetY = offsetY;
	}

	/**
	 * Gets the width of the image to be displayed.
	 * 
	 * @return double
	 */
	public double getWidth() {
		return width;
	}
	
	/**
	 * Gets the height of the image to be displayed.
	 * 
	 * @return double
	 */
	public double getHeight() {
		return height;
	}
	
	//******************************** Methods ********************************
	
	public void updateDirection(int i) {
		offsetY = height*i;
	}
}
