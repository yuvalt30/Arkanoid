// ID 311127120

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * is a rectangle that is controlled by the arrow keys, and moves according to the player key presses.
 */
public class Paddle implements Collidable, Sprite {
    private Rectangle body;
    private java.awt.Color color = Color.ORANGE;
    // keyboard sensor to know if a key is currently pressed
    private biuoop.KeyboardSensor keyboard;
    private int width;
    private double height;
    private int speed;
    // 1/5 of paddle's width
    private int sector;
    // paddle's rectangle's top line's y value
    private final double y;
    // game's GUI X dimension
    private double dimX;
    // game's frame width
    private double frameW;
    // number of sectors in paddle's body
    private final int partition = 5;
    // paddle's start position
    private double startX;

    /**
     * constructor.
     * @param frameW game's frame width
     * @param dimX game's GUI X dimension
     * @param dimY game's GUI Y dimension
     * @param keyboard keyboard sensor to know if a key is currently pressed
     * @param height paddle's height
     * @param speed paddle's speed
     * @param width paddle's width
     */
    public Paddle(double frameW, int dimX, int dimY, biuoop.KeyboardSensor keyboard, int width, int height, int speed) {
        this.startX = dimX / 2 - width / 2;
        this.keyboard = keyboard;
        this.y = dimY - height;
        this.frameW = frameW;
        this.dimX = dimX;
        this.width = width;
        this.body = new Rectangle(new Point(this.startX, this.y), this.width, height);
        this.sector = this.width / this.partition;
        this.speed = speed;
        this.height = height;
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.body;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint.getY() < this.y) { // case ball hit the paddle not on it's top : ignore hit
            return currentVelocity;
        }
        double degrees, vel = currentVelocity.calcSpeed();
        // sectorHit is at which of paddle's sectors ball hit
        int sectorHit = (int) ((collisionPoint.getX() - this.body.getUpperLeft().getX()) / this.sector);
        if (sectorHit == 2) {
            currentVelocity.reverseY();
            return currentVelocity;
        }
        degrees = 210 + 30 * sectorHit;
        Velocity newVel = currentVelocity.fromAngleAndSpeed(degrees, vel);

        return newVel;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        this.body.drawOn(surface, this.color);
    }

    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }

    }

    /**
     * move paddle left (no more than left border frame).
     */
    public void moveLeft() {
        double x = this.body.getUpperLeft().getX();
        if (x - this.speed > this.frameW) {
            this.body.setUpperLeft(new Point(x - this.speed, this.y));
        } else {
            this.body.setUpperLeft(new Point(this.frameW + 1, this.y));;
        }
        // update lines rectangle is made of after movement
        this.body.updateRectangleLines();
    }

    /**
     * move paddle right (no more than left border frame).
     */
    public void moveRight() {
        double x = this.body.getUpperLeft().getX();
        if (x + this.width + this.speed < this.dimX - this.frameW) {
            this.body.setUpperLeft(new Point(x + this.speed, this.y));
        } else {
            this.body.setUpperLeft(new Point(this.dimX - this.frameW - this.width - 1, this.y));;
        }
        // update lines rectangle is made of after movement
        this.body.updateRectangleLines();
    }

    /**
     * add paddle to game.
     * @param g game to add paddle to
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}