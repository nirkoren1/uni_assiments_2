package game;
// 316443902 Nir Koren

import java.util.ArrayList;
import java.util.List;

/**
 * this class holds all the collidable obs and can the determined the closet intersection of a trajectory with the obs.
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<>();
    /**
     * add the given collidable to the environment.
     * @param c Coollidable ob
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }
    /**
     * gets the closest collision Point for the trajectory.
     * iterates over all the collidables in the game environment.
     * For each collidable, it checks if the trajectory intersects with it.
     * If it does, check if the intersection point is the closest one so far.
     * If it is, update the closest collision information.
     * If no collision occurred, returns null.
     * @param trajectory Line of the trajectory
     * @return the collision info of the collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        double smallestLength = -1;
        Collidable finalCollideOb = null;
        Point finalCollisionPoint = null;
        for (Collidable c:collidables) {
            Point intersection = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (intersection == null) {
                continue;
            }
            if (smallestLength == -1 || smallestLength > trajectory.start().distance(intersection)) {
                finalCollideOb = c;
                finalCollisionPoint = intersection;
                smallestLength = trajectory.start().distance(intersection);
            }
        }
        if (finalCollideOb == null) {
            return null;
        }
        return new CollisionInfo(finalCollisionPoint, finalCollideOb);
    }
}