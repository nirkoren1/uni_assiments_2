package sprites;
// 316443902 Nir Koren

import biuoop.DrawSurface;
import animations.GameLevel;
import listeners.HitListener;
import listeners.HitNotifier;
import infos.Velocity;
import geometry_primitives.Point;
import geometry_primitives.Rectangle;
import java.util.List;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Block is a rectangle shaped ob that can collide with can be drawn to a surface.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * the constuctor.
     * @param rectangle the rectangle shape of the block
     * @param color Color desired
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * @return the block rectangle shape.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * If the collision point is on the corner of the block, the ball's velocity is reversed in both the x and y
     * directions.
     * If the collision point is on the left or right side of the block, the ball's velocity is reversed in the x
     * direction.
     * If the collision point is on the top or bottom of the block, the ball's velocity is reversed in the y direction.
     * @param collisionPoint the collision Point expected with the block
     * @param currentVelocity current velocity of the ball
     * @return a new velocity according to the place hit
     * @param hitter Ball hitting the block
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
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
        this.notifyHit(hitter);
        return out;
    }

    /**
     * draw the block to the surface.
     * @param surface DrawSurface of the game
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.BLACK);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX() - 1,
                (int) this.rectangle.getUpperLeft().getY() - 1, (int) this.rectangle.getWidth() + 2,
                (int) this.rectangle.getHeight() + 2);
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * currently doing nothing.
     */
    @Override
    public void timePassed() {
    }

    /**
     * add the block to the game.
     * @param g Game given
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * remove the block from the game.
     * @param gameLevel the game
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * notify all the listeners that the block was hit.
     * @param hitter the hitter ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
