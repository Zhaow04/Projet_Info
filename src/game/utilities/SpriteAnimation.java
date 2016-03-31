package game.utilities;

import game.model.LivingBeing;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {

    private final ImageView imageView;
    private final int count;
    private final int columns;
    private final int offsetX;
    private final int offsetY;
    private final int width;
    private final int height;

    private int lastIndex;

    public SpriteAnimation(LivingBeing living, ImageView imageView, Duration duration,
    		int count, int columns) {
        this.imageView = imageView;
        this.count     = count;
        this.columns   = columns;
        ImageSettings imageSettings = living.getImageSettings();
        this.offsetX   = (int) imageSettings.getOffsetX();
        this.offsetY   = (int) imageSettings.getOffsetY();
        this.width     = (int) imageSettings.getWidth();
        this.height    = (int) imageSettings.getHeight();
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
        setCycleCount(1);
        //System.out.println(offsetY);
        //System.out.println("ok2");
        this.play();
        
    }

    protected void interpolate(double k) {
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        //System.out.println(index);
        if (index != lastIndex) {
            final int x = (index % columns) * width  + offsetX;
            final int y = 0*(index / columns) * height + offsetY;
            //System.out.println(x);
            imageView.setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }
    }
    /*
	@Override
	public void run() {
		this.play();
	}*/
}