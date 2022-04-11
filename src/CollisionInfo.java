// 316443902 Nir koren

/**
 * this class holds the collision information.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;
    /**
     * create a CollisionInfo ob from a Point of a collision and a Collidable ob.
     * @param collisionPoint Point of a collision
     * @param collisionObject the collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * @return the collision Point.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}
