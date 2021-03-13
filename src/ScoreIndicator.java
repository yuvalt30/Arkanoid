// ID 311127120

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * a scoreIndicator is in charge of displaying game's current score on top of the screen.
 */
public class ScoreIndicator extends Rectangle implements Sprite {
    private Counter score;
    private String levelName;

    /**
     * constructor.
     *
     * @param width rectangle's width
     * @param height rectangle's height
     * @param score score counter to indicate
     * @param levelName is level's name to be shown at top of the screen
     */
    public ScoreIndicator(Counter score, String levelName, double width, double height) {
        super(new Point(0, 0), width, height);
        this.score = score;
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        super.drawOn(d, Color.LIGHT_GRAY);
        d.setColor(Color.BLACK);
        d.drawText(400, 15, "score: " + this.score.getValue(), 15);
        d.drawText(600, 15, "Level Name: " + this.levelName, 15);
    }

    @Override
    public void timePassed() {
    }
}
