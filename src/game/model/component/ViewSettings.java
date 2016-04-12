package game.model.component;

import javafx.scene.image.Image;

/**
 * Class that serves for display. Stocks all the informations needed for display such as
 * the URL of the image, the x and y offsets, the width and the height of the wanted region.
 * 
 * @author ZhaoWen
 *
 */
public class ViewSettings {
	
	//****************************** Attributes ******************************
	
	private String imageURL;
	private double offsetX, offsetY, width, height;
	private int spanX = 1, spanY = 1; // Number of cells in x and y the image should take on the map
	// MapInfos (x,y) of the image on the map. By default use the map component's position.
	private int[] position;
	
	//****************************** Constructor ******************************
	
	public ViewSettings(String imageURL) {
		setImageURL(imageURL);
		setOffsetX(0); setOffsetY(0);
		Image image = new Image(imageURL);
		setWidth(image.getWidth());
		setHeight(image.getHeight());
	}
	
	/**
	 * 
	 */
	public ViewSettings(String imageURL, int offsetX, int offsetY, int width, int height, int[] position) {
		setImageURL(imageURL);
		setOffsetX(offsetX);
		setOffsetY(offsetY);
		setWidth(width);
		setHeight(height);
		setPosition(position);
	}
	
	public ViewSettings(String imageURL, int offsetX, int offsetY, int width, int height, int[] position,
			int spanX, int spanY) {
		this(imageURL,offsetX,offsetY,width,height, position);
		setSpanX(spanX);
		setSpanY(spanY);
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
	 * Sets the image URL.
	 * 
	 * @param imageURL
	 */
	private void setImageURL(String imageURL) {
		this.imageURL = imageURL;
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
	 * Sets the x offset of the image to be displayed.
	 * 
	 * @param offsetX
	 */
	private void setOffsetX(double offsetX) {
		this.offsetX = offsetX;
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
	private void setOffsetY(double offsetY) {
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
	 * Sets the width of the image to be displayed.
	 * 
	 * @param width
	 */
	private void setWidth(double width) {
		this.width = width;
	}

	/**
	 * Gets the height of the image to be displayed.
	 * 
	 * @return double
	 */
	public double getHeight() {
		return height;
	}
	
	/**
	 * Sets the height of the image to be displayed.
	 * 
	 * @param height
	 */
	private void setHeight(double height) {
		this.height = height;
	}
	
	public int getSpanX() {
		return spanX;
	}

	private void setSpanX(int spanX) {
		this.spanX = spanX;
	}

	public int getSpanY() {
		return spanY;
	}

	private void setSpanY(int spanY) {
		this.spanY = spanY;
	}
	
	public int[] getPosition() {
		return position;
	}

	private void setPosition(int[] position) {
		this.position = position;
	}
	
	public int getX() {
		return position[0];
	}

	public int getY() {
		return position[1];
	}
	
	public void setPosition(int x, int y) {
		position[0] = x; position[1] = y;
	}
	
	//******************************** Methods ********************************

	public void updateDirection(int i) {
		offsetY = height*i;
	}
}
