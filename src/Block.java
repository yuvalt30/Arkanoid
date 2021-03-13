// ID 311127120

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * bloack is a collidable rectangle wich is also a sprite.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // block's body (a rectangle)
    private Rectangle body;
    // block's color
    private java.awt.Color color;
    // list of block's vertical borders (lines)
    private java.util.List<Line> verticals = new ArrayList<>();
    // list of block's horizontals borders (lines)
    private java.util.List<Line> horizontals = new ArrayList<>();
    //
    private List<HitListener> hitListeners;

    /**
     * constructor.
     * @param upperLeft upper left point of block's rectangle body
     * @param width block's width
     * @param height block's height
     * @param color block's color
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.body = new Rectangle(upperLeft, width, height);
        this.color = color;
        for (Line l : this.body.getRectangleLines()) {
            if (l.getEquation().isVertical()) {
                verticals.add(l);
            } else {
                horizontals.add(l);
            }
        }
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public void drawOn(DrawSurface surface) {
        this.body.drawOn(surface, this.color);
    }

    @Override
    public void timePassed() {
        return;
    }

    // Return the "collision shape" of the object.
    @Override
    public Rectangle getCollisionRectangle() {
        return this.body;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        for (Line l : this.horizontals) {
            if (l.isPointInLineSeg(collisionPoint)) {
                currentVelocity.reverseY();
            }
        }
        for (Line l : this.verticals) {
            if (l.isPointInLineSeg(collisionPoint)) {
                currentVelocity.reverseX();
            }
        }
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * notifies this block's listeners that hitter ball hit this block.
     * @param hitter ball hit this block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * adds this block to game when it initializes by adding it to game's sprites and collidables collections.
     * @param g game this block is added to
     */
    public void addToGame(GameLevel g) {
        g.addSprite((Sprite) this);
        g.addCollidable((Collidable) this);
    }

    /**
     * remove this block from game.
     * @param g game that block is removed from
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}
