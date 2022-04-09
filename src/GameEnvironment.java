// 316443902 Nir Koren

import java.util.ArrayList;
import java.util.List;

public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<>();

    // add the given collidable to the environment.
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.
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