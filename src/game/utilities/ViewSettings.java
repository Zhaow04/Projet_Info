package game.utilities;

import java.io.Serializable;

import javafx.scene.image.Image;

/**
 * Implements {@link Serializable}. <br/>
 * Class that serves for display. Stocks all the informations needed for display such as
 * the URL of the image, the x and y offsets, the width and the height of the wanted region, how much
 * the image should span in x and y, the count (for animation purpose: how many "small images" on the sprite
 * sheet the animation should use), the number of column of the sprite sheet, the position.
 * 
 *
 */
public class ViewSettings implements Serializable {
	
	//****************************** Attributes ******************************
	
	private static final long serialVersionUID = 1L;
	
	private String imageURL;
	private double offsetX, offsetY, width, height;
	private int count, columns;
	private int spanX = 1, spanY = 1; // Number of cells in x and y the image should take on the map
	// MapInfos (x,y) of the image on the map. By default use the map component's position.
	private int[] position; // End position for a skill
	
	//****************************** Constructor ******************************
	
	/**
	 * Constructor of {@link ViewSettings}. Sets the URL of the image, the x and y offsets,
	 * the width and the height of the wanted region.
	 * @param imageURL
	 */
	public ViewSettings(String imageURL) {
		setImageURL(imageURL);
		setOffsetX(0); setOffsetY(0);
		Image image = new Image(imageURL);
		setWidth(image.getWidth());
		setHeight(image.getHeight());
	}
	
	/**
	 * Constructor of {@link ViewSettings}. Sets the URL of the image, the x and y offsets,
	 * the width and the height of the wanted region and the position.
	 * @param imageURL
	 * @param offsetX
	 * @param offsetY
	 * @param width
	 * @param height
	 * @param position
	 */
	public ViewSettings(String imageURL, int offsetX, int offsetY, int width, int height, int[] position) {
		setImageURL(imageURL);
		setOffsetX(offsetX);
		setOffsetY(offsetY);
		setWidth(width);
		setHeight(height);
		setPosition(position);
	}
	
	/**
	 * Constructor of {@link ViewSettings}. Sets the URL of the image, the x and y offsets,
	 * the width and the height of the wanted region, the count, the number of column and the position.
	 * @param imageURL
	 * @param offsetX
	 * @param offsetY
	 * @param width
	 * @param height
	 * @param count
	 * @param columns
	 * @param position
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
	
	/**
	 * Constructor of {@link ViewSettings}. Sets the URL of the image, the x and y offsets,
	 * the width and the height of the wanted region, the count, the number of column, how much the image
	 * should span in x and y and the position.
	 * @param imageURL
	 * @param offsetX
	 * @param offsetY
	 * @param width
	 * @param height
	 * @param count
	 * @param columns
	 * @param position
	 * @param spanX
	 * @param spanY
	 */
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
	
	/**
	 * Gets the count.
	 * @return
	 */
	public int getCount() {
		return count;
	}

	/**
	 * Sets the count.
	 * @param count
	 */
	private void setCount(int count) {
		this.count = count;
	}

	/**
	 * Gets the number of columns.
	 * @return
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * Sets the number of columns.
	 * @param columns
	 */
	private void setColumns(int columns) {
		this.columns = columns;
	}

	/**
	 * Gets the span in x.
	 * @return
	 */
	public int getSpanX() {
		return spanX;
	}

	/**
	 * Sets the span in x.
	 * @param spanX
	 */
	private void setSpanX(int spanX) {
		this.spanX = spanX;
	}

	/**
	 * Gets the span in y.
	 * @return
	 */
	public int getSpanY() {
		return spanY;
	}

	/**
	 * Sets the span in y.
	 * @param spanY
	 */
	private void setSpanY(int spanY) {
		this.spanY = spanY;
	}
	
	/**
	 * Sets the span in x and y.
	 * @param spanX
	 * @param spanY
	 */
	public void setSpan(int spanX, int spanY) {
		this.spanX = spanX; this.spanY = spanY;
	}
	
	/**
	 * Gets the position.
	 * @return
	 */
	public int[] getPosition() {
		return position;
	}

	/**
	 * Sets the position.
	 * @param position
	 */
	private void setPosition(int[] position) {
		this.position = position;
	}
	
	/**
	 * Gets the x coordinate.
	 * @return
	 */
	public int getX() {
		return position[0];
	}

	/**
	 * Gets the y coordinate.
	 * @return
	 */
	public int getY() {
		return position[1];
	}
	
	/**
	 * Sets the x and y coordinates.
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y) {
		position[0] = x; position[1] = y;
	}
	
	
	//******************************** Methods ********************************

	/**
	 * Updates the y offset to match the corresponding line of the sprite sheet.
	 * @param i
	 */
	public void updateDirection(int i) {
		offsetY = height*i;
	}
}
