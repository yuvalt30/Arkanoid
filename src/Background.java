//ID 311127120
import biuoop.DrawSurface;

/**
 * Background is a rectangle which is a Sprite, uses for game level's background.
 */
public class Background extends Rectangle implements Sprite {
    private java.awt.Color color;

    /**
     * constructor.
     * @param startX background's upper left point X value
     * @param startY background's upper left point Y value
     * @param width background's width
     * @param height background's height
     * @param color background's color
     */
    public Background(int startX, int startY, int width, int height, java.awt.Color color) {
        super(new Point(startX, startY), width, height);
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d, this.color);
    }

    @Override
    public void timePassed() {

    }
}
