// ID 311127120

/**
 * collision info contains details about occurs collision.
 */
public class CollisionInfo {
    // the point at which the collision occurs
    private Point collisionPoint;
    // object that ball collided with
    private Collidable collisionObject;

    /**
     * constructor.
     * @param collisionPoint the point at which the collision occurs.
     * @param collisionObject object that ball collided with
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionObject = collisionObject;
        this.collisionPoint = collisionPoint;
    }

    /**
     * get the point at which the collision occurs.
     * @return point of collision
     */
    public Point getCollisionPoint() {
        return this.collisionPoint;
    }

    /**
     * get the collidable object involved in the collision.
     * @return the collidable object involved in the collision
     */
    public Collidable getCollisionObject() {
        return this.collisionObject;
    }
}