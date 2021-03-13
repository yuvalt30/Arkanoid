// ID 311127120

import biuoop.DrawSurface;

import java.awt.Color;


/**
 * class creates ball and can draw it on given sureface and move it.
 */
public class Ball implements Sprite {
    // ball's center point's location
    private Point center;
    // ball's radius
    private int r;
    // balls velocity
    private Velocity vel;
    // game's collidable's list so the ball will know when its collides with objects
    private GameEnvironment envi;

    /**
     * constructor.
     * @param center ball's center point's location
     * @param r ball's radius
     * @param env game environment so the ball will know how to move
     */
    public Ball(Point center, int r, GameEnvironment env) {
        this.r = r;
        this.center = new Point(center.getX(), center.getY());
        this.vel = null;
        this.envi = env;
    }

    /**
     * constructor.
     * @param x x value of ball's center's location
     * @param y y value of ball's center's location
     * @param r ball's radius
     * @param color ball's color
     * @param env game environment so the ball will know how to move
     */
    public Ball(double x, double y, int r, java.awt.Color color, GameEnvironment env) {
        this.r = r;
        this.center = new Point(x, y);
        this.vel = null;
        this.envi = env;
    }

    /**
     * constructor.
     * @param center ball's center point's location
     * @param r ball's radius
     * @param env game environment so the ball will know how to move
     * @param velocity ball's desired velocity
     */
    public Ball(Point center, int r, GameEnvironment env, Velocity velocity) {
        this.r = r;
        this.center = new Point(center.getX(), center.getY());
        this.vel = null;
        this.envi = env;
        this.vel = velocity;
    }


    /**
     * access ball's center's x value.
     * @return ball's center's x value
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * access ball's center's y value.
     * @return ball's center's y value
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * access ball's size (radius).
     * @return ball's radius
     */
    public int getSize() {
        return this.r * 2;
    }

    /**
     * access ball's game environment.
     * @return ball's game environment
     */
    public GameEnvironment getEnvi() {
        return this.envi;
    }

    /**
     * draws s ball on given DrawSurface.
     * @param surface surface on which ball is drawn
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
        surface.setColor(Color.WHITE);
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * sets ball's velocity and predicts it's near trajectory.
     * @param v updated ball's velocity to be v
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
        }

    /**
     * sets ball's velocity (by dx and dy) and predicts it's near trajectory.
     * @param dx horizonal speed (pixels per move)
     * @param dy vertical speed (pixels per move)
     */
    public void setVelocity(double dx, double dy) {
        this.vel = new Velocity(dx, dy);
    }

    /**
     * access ball's velocity.
     * @return ball's velocity
     */
    public Velocity getVelocity() {
        return this.vel;
    }

    /**
     * method moves the ball one step according to it's velocity, so the ball won't come out of the frame.
     * if ball reaches frame limit, by X or Y, it's velocity would reverse accordingly
     * @param width x axis limit (frame width)
     * @param height y axis limit (frame height)
     */
    public void moveOneStep(int width, int height) {
        if (this.vel == null) {
            return;
        }
        // makes temporary step to check if it's valid, if not velocity would revers
        Point temp =  this.vel.applyToPoint(this.center);
        if (temp.getX() < this.r || temp.getX() > width - this.r) {
            this.vel.reverseX();
        }
        if (temp.getY() < this.r || temp.getY() > height - this.r) {
            this.vel.reverseY();
        }
        // makes the step
        this.center = this.vel.applyToPoint(this.center);

    }

    /**
     * method moves the ball one step according to it's velocity, in an inner rectangle inside the frame.
     * the ball won't come out of the frame, if frame limit is reached, by X or Y, velocity would reverse accordingly
     * @param lim inner frame rectangle's upper left top
     * @param width x axis limit (inner rectangle's width)
     * @param height y axis limit (inner rectangle's height)
     */
    public void moveOneStep(Point lim, int width, int height) {
        if (this.vel == null) {
            return;
        }
        Point temp =  this.vel.applyToPoint(this.center);
        if (temp.getX() < this.r + lim.getX() || temp.getX() >  width - this.r + lim.getX()) {
            this.vel.reverseX();
        }
        if (temp.getY() < this.r + lim.getY() || temp.getY() > height - this.r + lim.getY()) {
            this.vel.reverseY();
        }
        this.center = this.vel.applyToPoint(this.center);

    }

    /**
     * method moves the ball according to it's velocity,
     * and in case of collisions with collidable objects changes it's velocity accordingly.
     */
    public void moveOneStep() {
        if (this.vel == null) {
            return;
        }
        // calculate ball's trajectory - where will it move if no collision happen
        Line trajectory = new Line(this.center, this.vel.applyToPoint(this.center));
        // get closest collision of this ball's trajectory with any collidable object
        CollisionInfo info = this.envi.getClosestCollision(trajectory);
        if (info == null) { // if there's no collision with trajectory: move ball one step
            this.center = this.vel.applyToPoint(this.center);
        } else { // move this ball close to collision point and change it's velocity according to the hit object
            this.center = this.moveNearHitPoint(info.getCollisionPoint());
            this.setVelocity(info.getCollisionObject().hit(this, info.getCollisionPoint(), this.vel));
        }
    }

    /**
     * returns very close point to collision point.
     * @param hit Point of collision
     * @return very close point to collision point
     */
    private Point moveNearHitPoint(Point hit) {
        double x = hit.getX();
        double y = hit.getY();
        if (this.vel.getDx() < 0) {
            x += 1;
        }
        if (this.vel.getDx() > 0) {
            x -= 1;
        }
        if (this.vel.getDy() < 0) {
            y += 1;
        }
        if (this.vel.getDy() > 0) {
            y -= 1;
        }
        return new Point(x, y);
    }

    /**
     * add this ball to sprites collection when game initializes.
     * @param g game that ball is add to
     */
    public void addToGame(GameLevel g) {
        g.addSprite((Sprite) this);
    }

    /**
     * remove this ball from game by removing it from sprites collection.
     * @param g game that ball is add to
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}