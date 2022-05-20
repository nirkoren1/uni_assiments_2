package sprites;
// 316443902 Nir Koren

import infos.Velocity;
import geometry_primitives.Point;
import geometry_primitives.Rectangle;

/**
 * interface for the the Collidable classes.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     * @return Rectangle shape of the collision.
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     * @param collisionPoint Point of collision
     * @param currentVelocity the current Velocity of the ball
     * @return new velocity expected after the hit
     * @param hitter Ball that hits the collidable
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}