// 316443902 Nir Koren

import animations.GameLevel;
import animations.Level3;

/**
 * the final game app.
 */
public class Ass5Game {
    /**
     * create new game, initialize it and runs it.
     * @param args from cmd
     */
    public static void main(String[] args) {
        GameLevel gameLevel = new GameLevel();
        gameLevel.initialize(new Level3());
        gameLevel.run();
    }
}
