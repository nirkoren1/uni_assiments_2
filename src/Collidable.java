// 316443902 Nir Koren

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
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}