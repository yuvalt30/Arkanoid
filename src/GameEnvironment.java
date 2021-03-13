// ID 311127120

import java.util.ArrayList;

/**
 * a game environment has collection of the collidable objects in the game, and is able to detect collisions.
 */
public class GameEnvironment {
    // game's collidable objects collection
    private ArrayList<Collidable> collidables;

    /**
     * constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * adds collidable objects to game's environment.
     * @param c collidable object to be added
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * remove collidable object from game's environment.
     * @param c collidable object to be removed
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * if moving object will not collide any other object on it's one step (=trajectory) return null.
     * otherwise, return information about the closest collision that is going to occur.
     * @param trajectory line of object's movement on one step
     * @return information about the closest collision that is going to occur,
     * if trajectory won't collide with any other object, return null
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo closestCollisionInfo = null;
        Point temp;
        // check if object's trajectory collide with any collidable object
        for (Collidable c : this.collidables) {
            // temp is closest intersection point with c
            temp = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (temp != null) { // if there is a collision
                // if this collision is the closest - update closestCollision
                if (closestCollisionInfo == null
                        || trajectory.getStart().distance(temp)
                        < trajectory.getStart().distance(closestCollisionInfo.getCollisionPoint())) {
                    closestCollisionInfo = new CollisionInfo(temp, c);
                }
            }
        }
        return closestCollisionInfo;
    }

}