// 316443902 Nir Koren

import animations.GameFlow;
import animations.Level1;
import animations.Level2;
import animations.Level3;
import animations.Level4;
import animations.LevelInformation;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * the final game app.
 */
public class Ass5Game {
    private
    /**
     * create new game, initialize it and runs it.
     * @param args from cmd
     */
    public static void main(String[] args) {
        GameFlow game = new GameFlow();
        game.runLevels(new ArrayList<LevelInformation>(Arrays.asList(new Level1(), new Level2(), new Level3(),
                new Level4())));
    }
}
