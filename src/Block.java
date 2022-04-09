// 316443902 Nir Koren

public class Block implements Collidable {
    private Rectangle rectangle;
    public Block(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity out = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        double leftX = this.rectangle.getUpperLeft().getX();
        double rightX = this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth();
        double upperY = this.rectangle.getUpperLeft().getY();
        double lowerY = this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight();
        double collisionX = collisionPoint.getX();
        double collisionY = collisionPoint.getY();
        if ((collisionX == leftX || collisionX == rightX) && (collisionY == upperY || collisionY == lowerY)) {
            out.setValues(-out.getDx(), -out.getDy());
        } else if (collisionX == leftX || collisionX == rightX) {
            out.setValues(-out.getDx(), out.getDy());
        } else {
            out.setValues(out.getDx(), -out.getDy());
        }
        return out;
    }
}
