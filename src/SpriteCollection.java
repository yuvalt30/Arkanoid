// ID 311127120

import biuoop.DrawSurface;
import java.util.ArrayList;

/**
 * game's sprites collection.
 */
public class SpriteCollection {
    // sprites collection
    private ArrayList<Sprite> spritesCol;

    /**
     * constructor.
     */
    public SpriteCollection() {
        this.spritesCol = new ArrayList<Sprite>();
    }

    /**
     * add sprite to game's sprites collection.
     * @param s new sprite to be added
     */
    public void addSprite(Sprite s) {
        this.spritesCol.add(s);
    }

    /**
     * remove sprite from game's sprites collection.
     * @param s sprite to be removed
     */
    public void removeSprite(Sprite s) {
        this.spritesCol.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> tempSpriteCol = new ArrayList<>(this.spritesCol);
        for (Sprite s : tempSpriteCol) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d draw surface to draw all sprites on
     */
    public void drawAllOn(DrawSurface d) {
        ArrayList<Sprite> tempSpriteCol = new ArrayList<>(this.spritesCol);
        for (Sprite s : tempSpriteCol) {
            s.drawOn(d);
        }
    }
}