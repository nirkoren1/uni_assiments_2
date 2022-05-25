// 316443902 Nir Koren

import animations.GameLevel;

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
        gameLevel.initialize();
        gameLevel.run();
    }
}
