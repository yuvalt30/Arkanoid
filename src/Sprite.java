// ID 311127120

import biuoop.DrawSurface;

/**
 * Sprite is a game object that can be drawn to the screen.
 */
public interface Sprite {
    /**
     * draws sprite on screen.
     * @param d is  the draw surface this sprite is drawn on
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}