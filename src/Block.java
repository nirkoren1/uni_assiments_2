// 316443902 Nir Koren

import biuoop.DrawSurface;

import java.awt.Color;

public class Block implements Collidable, Sprite {
    private Rectangle rectangle;
    private Color color;
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * If the collision point is on the corner of the block, the ball's velocity is reversed in both the x and y
     * directions.
     * If the collision point is on the left or right side of the block, the ball's velocity is reversed in the x
     * direction.
     * If the collision point is on the top or bottom of the block, the ball's velocity is reversed in the y direction.
     * @param collisionPoint
     * @param currentVelocity
     * @return
     */
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
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX() - 1, (int) this.rectangle.getUpperLeft().getY() - 1,
                (int) this.rectangle.getWidth() + 2, (int) this.rectangle.getHeight() + 2);
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    @Override
    public void timePassed() {
        return;
    }
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
