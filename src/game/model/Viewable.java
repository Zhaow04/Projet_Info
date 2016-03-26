package game.model;


/**
 * A class implements {@code Viewable} when it needs an image to be displayed on screen
 * 
 * @author ZhaoWen
 *
 */
public interface Viewable {
	
	/**
	 * Gets the image URL.
	 * 
	 * @return URL
	 */
	public String getImageURL();
	
	/**
	 * Gets the x offset of the image to be displayed.
	 * 
	 * @return double
	 */
	public double getOffsetX();
	
	/**
	 * Gets the y offset of the image to be displayed.
	 * 
	 * @return double
	 */
	public double getOffsetY();
	
	/**
	 * Gets the width of the image to be displayed.
	 * 
	 * @return double
	 */
	public double getWidth();

	/**
	 * Gets the height of the image to be displayed.
	 * 
	 * @return double
	 */
	public double getHeight();
	
}
