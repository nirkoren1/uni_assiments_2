// 316443902 Nir Koren
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private double speed = 5;
    public Paddle(KeyboardSensor keyboard, Rectangle rectangle, Color color) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
        this.color = color;
    }
    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX() <= 30) {
            return;
        }
        this.rectangle.setUpperLeft(this.rectangle.getUpperLeft().getX() - speed, this.rectangle.getUpperLeft().getY());
    }
    public void moveRight() {
        if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() >= 800 - 30) {
            return;
        }
        this.rectangle.setUpperLeft(this.rectangle.getUpperLeft().getX() + speed, this.rectangle.getUpperLeft().getY());
    }

    // Sprite
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX() - 1, (int) this.rectangle.getUpperLeft().getY() - 1,
                (int) this.rectangle.getWidth() + 2, (int) this.rectangle.getHeight() + 2);
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    // Collidable
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity outVel = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        double leftX = this.rectangle.getUpperLeft().getX();
        double rightX = this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth();
        double upperY = this.rectangle.getUpperLeft().getY();
        double lowerY = this.rectangle.getUpperLeft().getY() + this.rectangle.getHeight();
        double region0 = this.rectangle.getUpperLeft().getX();
        double region1 = this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() / 5;
        double region2 = this.rectangle.getUpperLeft().getX() + 2 * this.rectangle.getWidth() / 5;
        double region3 = this.rectangle.getUpperLeft().getX() + 3 * this.rectangle.getWidth() / 5;
        double region4 = this.rectangle.getUpperLeft().getX() + 4 * this.rectangle.getWidth() / 5;
        double region5 = this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth();
        double collisionX = collisionPoint.getX();
        double collisionY = collisionPoint.getY();
        if (collisionX == leftX || collisionX == rightX) {
            outVel.setValues(-outVel.getDx(), outVel.getDy());
        } else if (region0 <= collisionX && collisionX < region1) {
            outVel = Velocity.fromAngleAndSpeed(300, outVel.getDx(), outVel.getDy());
        } else if (region1 <= collisionX && collisionX < region2) {
            outVel = Velocity.fromAngleAndSpeed(330, outVel.getDx(), outVel.getDy());
        } else if (region2 <= collisionX && collisionX < region3) {
            outVel = Velocity.fromAngleAndSpeed(0, outVel.getDx(), outVel.getDy());
        } else if (region3 <= collisionX && collisionX < region4) {
            outVel = Velocity.fromAngleAndSpeed(30, outVel.getDx(), outVel.getDy());
        } else if (region4 <= collisionX && collisionX <= region5) {
            outVel = Velocity.fromAngleAndSpeed(60, outVel.getDx(), outVel.getDy());
        }
        return outVel;
    }

    // Add this paddle to the game.
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}