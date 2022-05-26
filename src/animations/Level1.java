package animations;
// Nir koren 316443902
import geometry_primitives.Line;
import geometry_primitives.Point;
import geometry_primitives.Rectangle;
import infos.Velocity;
import sprites.Block;
import sprites.CircleSprite;
import sprites.LineSprite;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Level 1.
 */
public class Level1 extends Level implements LevelInformation {
    // creating a level
    private static List<Block> blocksList = Level1.createBlocks();
    private static List<Sprite> spritesList = Level1.createBackGround();
    /**
     * constructor.
     */
    public Level1() {
        super(1,
                new ArrayList<Velocity>(Arrays.asList(Velocity.fromAngleAndSpeed(30, 3))),
                5,
                100,
                "Direct Hit",
                spritesList,
                blocksList);
    }
    /**
     * create blocks.
     * @return the list
     */
    public static List<Block> createBlocks() {
        List<Block> blocks = new ArrayList<Block>();
        // create one red block in the top center of the screen
        Point center = new Point(390, 150);
        Rectangle rect = new Rectangle(center, 20, 20);
        Block block = new Block(rect, Color.RED);
        blocks.add(block);
        return blocks;
    }
    private static List<Sprite> createBackGround() {
        List<Sprite> sprites = new ArrayList<Sprite>();
        sprites.add(new Block(new Rectangle(new Point(0, 0), 800, 600), Color.BLACK));
        // create a target drawing around the middle of the red block
        sprites.add(new CircleSprite(new Point(400, 160), 30, Color.RED));
        sprites.add(new CircleSprite(new Point(400, 160), 60, Color.RED));
        sprites.add(new CircleSprite(new Point(400, 160), 90, Color.RED));
        sprites.add(new LineSprite(new Line(new Point(400, 180), new Point(400, 270)), Color.RED));
        sprites.add(new LineSprite(new Line(new Point(380, 160), new Point(290, 160)), Color.RED));
        sprites.add(new LineSprite(new Line(new Point(420, 160), new Point(510, 160)), Color.RED));
        sprites.add(new LineSprite(new Line(new Point(400, 180), new Point(400, 50)), Color.RED));
        return sprites;
        }
}
