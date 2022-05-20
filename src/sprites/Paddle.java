package sprites;
// 316443902 Nir Koren
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Game;
import infos.Velocity;
import geometry_primitives.Point;
import geometry_primitives.Rectangle;

import java.awt.Color;

/**
 * a paddle which the player can control and make the balls to go to different locations.
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private double speed = 5;

    /**
     * The constructor receives a KeyboardSensor, a Rectangle and color, and creates a Paddle object.
     * @param keyboard Keyboard ob
     * @param rectangle Rectangle shape of the paddle
     * @param color Color
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rectangle, Color color) {
        this.keyboard = keyboard;
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * move the paddle location to the left if it doesn't hit a border.
     */
    public void moveLeft() {
        if (this.rectangle.getUpperLeft().getX() <= 30) {
            return;
        }
        this.rectangle.setUpperLeft(this.rectangle.getUpperLeft().getX() - speed, this.rectangle.getUpperLeft().getY());
    }
    /**
     * move the paddle location to the right if it doesn't hit a border.
     */
    public void moveRight() {
        if (this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() >= 800 - 30) {
            return;
        }
        this.rectangle.setUpperLeft(this.rectangle.getUpperLeft().getX() + speed, this.rectangle.getUpperLeft().getY());
    }

    /**
     * move left or right according to the player key pressing.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * draw the paddle to the surface givven.
     * @param d DrawSurface ob
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX() - 1, (int) this.rectangle.getUpperLeft().getY() - 1,
                (int) this.rectangle.getWidth() + 2, (int) this.rectangle.getHeight() + 2);
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * @return Rectangle shape of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * divide the paddle to different regions.
     * every region has a different influence on the ball speed.
     * @param collisionPoint the collision point expected with the paddle.
     * @param currentVelocity current velocity of the ball.
     * @return Velocity the updated velocity of the ball.
     * @param hitter Ball that hits the paddle
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
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
        // if the ball is stuck int the right or left of the paddle, gets it out
        if (leftX <= collisionX && collisionX < (leftX + rightX) / 2 && upperY < collisionY && collisionY <= lowerY) {
            outVel = Velocity.fromAngleAndSpeed(290, outVel.getDx(), outVel.getDy());
            return outVel;
        }
        if ((leftX + rightX) / 2 < collisionX && collisionX <= rightX && upperY < collisionY && collisionY <= lowerY) {
            outVel = Velocity.fromAngleAndSpeed(70, outVel.getDx(), outVel.getDy());
            return outVel;
        }
        //If the collision point is on the left or right side of the paddle, the ball's x velocity is reversed.
        if (collisionX == leftX || collisionX == rightX) {
            outVel.setValues(-outVel.getDx(), outVel.getDy());
            //If the collision point is on the front-most-left of the paddle, the ball's angle velocity is 300.
        } else if (region0 <= collisionX && collisionX < region1) {
            outVel = Velocity.fromAngleAndSpeed(300, outVel.getDx(), outVel.getDy());
        //If the collision point is on the front-left of the paddle, the ball's angle velocity is 330.
        } else if (region1 <= collisionX && collisionX < region2) {
            outVel = Velocity.fromAngleAndSpeed(330, outVel.getDx(), outVel.getDy());
        //If the collision point is on the front-middle of the paddle, the ball's angle velocity is 0.
        } else if (region2 <= collisionX && collisionX < region3) {
            outVel = Velocity.fromAngleAndSpeed(0, outVel.getDx(), outVel.getDy());
        //If the collision point is on the front-right of the paddle, the ball's angle velocity is 30.
        } else if (region3 <= collisionX && collisionX < region4) {
            outVel = Velocity.fromAngleAndSpeed(30, outVel.getDx(), outVel.getDy());
        //If the collision point is on the front-most-right of the paddle, the ball's angle velocity is 60.
        } else if (region4 <= collisionX && collisionX <= region5) {
            outVel = Velocity.fromAngleAndSpeed(60, outVel.getDx(), outVel.getDy());
        }
        return outVel;
    }

    /**
     * add paddle to game.
     * @param g Game ob
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}