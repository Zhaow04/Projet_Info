package game.utilities;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Class that handles the animation of a sprite.
 * 
 *
 */
public class SpriteAnimation extends Transition {

	//****************************** Attributes ******************************

    private final ImageView imageView;
    private final int count;
    private final int columns;
    private int offsetX;
    private int offsetY;
    private int width;
    private int height;
    private int lastIndex;
    
	//****************************** Constructor ******************************

    /**
     * Constructor of {@link SpriteAnimation}.
     * @param viewSettings The {@code ViewSettings} which contains the information concerning the animation
     * @param imageView The {@code ImageView} which will be animated
     * @param millis The duration in milliseconds of the animation
     */
    public SpriteAnimation(ViewSettings viewSettings, ImageView imageView, int millis) {
        this.imageView = imageView;
        this.count     = viewSettings.getCount();
        this.columns   = viewSettings.getColumns();
        this.offsetX   = (int) viewSettings.getOffsetX();
        this.offsetY   = (int) viewSettings.getOffsetY();
        this.width     = (int) viewSettings.getWidth();
        this.height    = (int) viewSettings.getHeight();
        setCycleDuration(Duration.millis(millis));
        setInterpolator(Interpolator.LINEAR);
        setCycleCount(1);
        this.setOnFinished((value) -> imageView.setViewport((
        		new Rectangle2D(offsetX, offsetY, width, height))));
    }
   
	//******************************** Methods ********************************

    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {
            final int x = (index % columns) * width  + offsetX;
            final int y = (index / columns) * height + offsetY;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }
    }
    
    /**
     * Updates the x and y offsets, the width and the height of the {@code ImageView}'s viewport.
     * @param viewSettings
     */
    public void update(ViewSettings viewSettings) {
    	this.offsetX   = (int) viewSettings.getOffsetX();
        this.offsetY   = (int) viewSettings.getOffsetY();
        this.width     = (int) viewSettings.getWidth();
        this.height    = (int) viewSettings.getHeight();
    }
}