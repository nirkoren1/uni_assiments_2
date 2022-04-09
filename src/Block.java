// 316443902 Nir Koren

public class Block implements Collidable {
    private Rectangle rectangle;
    public Block() {

    }
    Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    Velocity hit(Point collisionPoint, Velocity currentVelocity) {

    }
}
