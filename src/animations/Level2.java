package animations;
// Nir Koren 316443902
import geometry_primitives.Line;
import geometry_primitives.Point;
import geometry_primitives.Rectangle;
import infos.Velocity;
import sprites.Block;
import sprites.FillCircleSprite;
import sprites.LineSprite;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Level 2.
 */
public class Level2 extends Level implements LevelInformation {
    // creating a level
    private static List<Block> blocksList = Level2.createBlocks();
    private static List<Sprite> spritesList = Level2.createBackGround();
    private static List<Velocity> velocities = Level2.createVelocities();
    /**
     * constructor.
     */
    public Level2() {
        super(8,
                velocities,
                5,
                500,
                "Wide Easy",
                spritesList,
                blocksList);
    }
    /**
     * create blocks.
     * @return the list
     */
    public static List<Block> createBlocks() {
        List<Block> blocks = new ArrayList<Block>();
        // create blocks like a rainbow
        for (int i = 0; i < 14; i++) {
            Point center = new Point(i * 50 + 40, 270);
            Rectangle rect = new Rectangle(center, 50, 20);
            Block block = new Block(rect, Color.getHSBColor(i * 0.2f, 1, 1));
            blocks.add(block);
        }
        return blocks;
    }
    private static List<Sprite> createBackGround() {
        List<Sprite> sprites = new ArrayList<Sprite>();
        sprites.add(new Block(new Rectangle(new Point(0, 0), 800, 600), Color.WHITE));
        // create a sun drawing
        sprites.add(new FillCircleSprite(new Point(200, 100), 65, new Color(255, 102, 0)));
        // dark yellow
        sprites.add(new FillCircleSprite(new Point(200, 100), 55, new Color(255, 204, 51)));
        sprites.add(new FillCircleSprite(new Point(200, 100), 45, Color.YELLOW));
        // creating lines coming from the sun to the blocks
        for (int i = 0; i < 14; i++) {
            Line line = new Line(new Point(200, 100), new Point(i * 50 + 40, 270));
            LineSprite lineSprite = new LineSprite(line, Color.YELLOW);
            sprites.add(lineSprite);
            Line line2 = new Line(new Point(200, 100), new Point(i * 50 + 67, 270));
            LineSprite lineSprite2 = new LineSprite(line2, Color.YELLOW);
            sprites.add(lineSprite2);
        }
        return sprites;
    }
    private static List<Velocity> createVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        // create a list of velocities
        for (int i = 0; i < 8; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(i * 30 - 60, 3));
        }
        return velocities;
    }
}

