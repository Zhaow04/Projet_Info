package game.utilities;

import java.io.Serializable;

import javafx.scene.image.Image;

/**
 * Implements {@link Serializable}. <br/>
 * Class that serves for display. Stocks all the informations needed for display such as
 * the URL of the image, the x and y offsets, the width and the height of the wanted region, how much
 * the image should span in x and y.
 * 
 * @author ZhaoWen
 *
 */
public class ViewSettings implements Serializable {
	
	//****************************** Attributes ******************************
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String imageURL;
	private double offsetX, offsetY, width, height;
	private int count, columns;
	private int spanX = 1, spanY = 1; // Number of cells in x and y the image should take on the map
	// MapInfos (x,y) of the image on the map. By default use the map component's position.
	private int[] position; // End position for a skill
	private int startX, startY; // Start position for a skill
	
	//****************************** Constructor ******************************
	
	/**
	 * 
	 * @param imageURL
	 */
	public ViewSettings(String imageURL) {
		setImageURL(imageURL);
		setOffsetX(0); setOffsetY(0);
		Image image = new Image(imageURL);
		setWidth(image.getWidth());
		setHeight(image.getHeight());
	}
	
	public ViewSettings(String imageURL, int offsetX, int offsetY, int width, int height, int[] position) {
		setImageURL(imageURL);
		setOffsetX(offsetX);
		setOffsetY(offsetY);
		setWidth(width);
		setHeight(height);
		setPosition(position);
	}
	
	/**
	 * 
	 */
	public ViewSettings(String imageURL, int offsetX, int offsetY, int width, int height,
			int count, int columns, int[] position) {
		setImageURL(imageURL);
		setOffsetX(offsetX);
		setOffsetY(offsetY);
		setWidth(width);
		setHeight(height);
		setCount(count);
		setColumns(columns);
		setPosition(position);
	}
	
	public ViewSettings(String imageURL, int offsetX, int offsetY, int width, int height,
			int count, int columns, int[] position, int spanX, int spanY) {
		this(imageURL,offsetX,offsetY,width,height, count, columns, position);
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
	
	public int getCount() {
		return count;
	}

	private void setCount(int count) {
		this.count = count;
	}

	public int getColumns() {
		return columns;
	}

	private void setColumns(int columns) {
		this.columns = columns;
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
	
	public void setSpan(int spanX, int spanY) {
		this.spanX = spanX; this.spanY = spanY;
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
	
	public int getStartX() {
		return startX;
	}
	
	public int getStartY() {
		return startY;
	}

	public void setStartPos(int startX, int startY) {
		this.startX = startX; this.startY = startY;
	}
	
	//******************************** Methods ********************************

	public void updateDirection(int i) {
		offsetY = height*i;
	}
}
