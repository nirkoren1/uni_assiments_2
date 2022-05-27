package levels;
// Nir Koren 316443902
import geometry_primitives.Point;
import geometry_primitives.Rectangle;
import infos.Velocity;
import sprites.Block;
import sprites.FillCircleSprite;
import sprites.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * Level 3.
 */
public class Level3 extends Level implements LevelInformation {
    // creating a level
    private static List<Block> blocksList = Level3.createBlocks();
    private static List<Sprite> spritesList = Level3.createBackGround();
    private static List<Velocity> velocities = Level3.createVelocities();
    /**
     * constructor.
     */
    public Level3() {
        super(2,
                velocities,
                5,
                100,
                "Green 3",
                spritesList,
                blocksList);
    }
    /**
     * create blocks.
     * @return the list
     */
    public static List<Block> createBlocks() {
        List<Block> blocks = new ArrayList<Block>();
        // creating 4 rows of blocks with different colors and length
        for (int i = 0; i < 4; i++) {
            for (int j = 10; j > i; j--) {
                Point center = new Point(j * 50 + 200, i * 50 + 200);
                Rectangle rect = new Rectangle(center, 50, 20);
                if (i == 0) {
                    Block block = new Block(rect, Color.GRAY);
                    blocks.add(block);
                } else if (i == 3) {
                    Block block = new Block(rect, Color.GREEN);
                    blocks.add(block);
                } else {
                    Block block = new Block(rect, Color.getHSBColor(i * 0.2f, 1, 1));
                    blocks.add(block);
                }
            }
        }
        return blocks;
    }
    private static List<Sprite> createBackGround() {
        List<Sprite> sprites = new ArrayList<Sprite>();
        sprites.add(new Block(new Rectangle(new Point(0, 0), 800, 600), Color.getHSBColor(0.5f, 0.5f, 0.5f)));
        // creating a tower drawing
        sprites.add(new Block(new Rectangle(new Point(80, 350), 100, 300), Color.DARK_GRAY));
        // adding windows to the tower
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                Point center = new Point(i * 24 + 90, j * 50 + 360);
                Rectangle rect = new Rectangle(center, 10, 20);
                sprites.add(new Block(rect, Color.WHITE));
            }
        }
        // adding an antenna to the tower
        sprites.add(new Block(new Rectangle(new Point(127, 200), 15, 150), Color.DARK_GRAY));
        // adding a red light at the top of the antenna
        sprites.add(new FillCircleSprite(new Point(134, 200), 13, Color.RED));
        sprites.add(new FillCircleSprite(new Point(134, 200), 6, Color.GREEN));
        sprites.add(new FillCircleSprite(new Point(134, 200), 3, Color.YELLOW));
        return sprites;
    }
    private static List<Velocity> createVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        // create a list of velocities
        for (int i = 0; i < 2; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(i * 30 - 15, 3));
        }
        return velocities;
    }
}


