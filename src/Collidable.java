// ID 311127120

/**
 * Collidable is an object that can be collided with.
 */
public interface Collidable {
    /**
     * Returns the "collision shape" of the object.
     * @return object's collision rectangle shape
     */
    Rectangle getCollisionRectangle();

    /**
     * methods returns new velocity that was changes according to collision with this collidable.
     * @param hitter is the hitting ball
     * @param collisionPoint point of collision
     * @param currentVelocity collided ball's current velocity
     * @return new velocity defined by collision with this collidable
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}