// 316443902 Nir Koren
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    public Paddle(KeyboardSensor keyboard, Rectangle rectangle, Color color) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
        this.color = color;
    }

    public void moveLeft();
    public void moveRight();

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
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    // Collidable
    public Rectangle getCollisionRectangle();
    public Velocity hit(Point collisionPoint, Velocity currentVelocity);

    // Add this paddle to the game.
    public void addToGame(Game g);
}