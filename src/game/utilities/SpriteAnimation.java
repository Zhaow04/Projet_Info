package game.utilities;

import game.model.Observable;
import game.model.component.ViewSettings;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {

    private final ImageView imageView;
    private final int count;
    private final int columns;
    private int offsetX;
    private int offsetY;
    private int width;
    private int height;

    private int lastIndex;

    public SpriteAnimation(ViewSettings viewSettings, ImageView imageView, int millis) {
        this.imageView = imageView;
        this.count     = 5;
        this.columns   = 4;
        this.offsetX   = (int) viewSettings.getOffsetX();
        this.offsetY   = (int) viewSettings.getOffsetY();
        this.width     = (int) viewSettings.getWidth();
        this.height    = (int) viewSettings.getHeight();
        setCycleDuration(Duration.millis(millis));
        setInterpolator(Interpolator.LINEAR);
        setCycleCount(1);
    }
    
    public SpriteAnimation(Observable o, ImageView imageView, Duration duration,
    		int count, int columns) {
        this.imageView = imageView;
        this.count     = count;
        this.columns   = columns;
        ViewSettings viewSettings = o.getViewSettings();
        this.offsetX   = (int) viewSettings.getOffsetX();
        this.offsetY   = (int) viewSettings.getOffsetY();
        this.width     = (int) viewSettings.getWidth();
        this.height    = (int) viewSettings.getHeight();
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
        setCycleCount(1);
        this.play();
        
    }
    
    public SpriteAnimation(ViewSettings viewSettings, ImageView imageView, Duration duration,
    		int count, int columns) {
        this.imageView = imageView;
        this.count     = count;
        this.columns   = columns;
        this.offsetX   = (int) viewSettings.getOffsetX();
        this.offsetY   = (int) viewSettings.getOffsetY();
        this.width     = (int) viewSettings.getWidth();
        this.height    = (int) viewSettings.getHeight();
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
        setCycleCount(1);
        this.play();
        
    }

    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {
            final int x = (index % columns) * width  + offsetX;
            final int y = 0*(index / columns) * height + offsetY;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }
    }
    
    public void update(ViewSettings viewSettings) {
    	this.offsetX   = (int) viewSettings.getOffsetX();
        this.offsetY   = (int) viewSettings.getOffsetY();
        this.width     = (int) viewSettings.getWidth();
        this.height    = (int) viewSettings.getHeight();
    }
}