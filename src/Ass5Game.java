// 316443902 Nir Koren

import game.Game;

/**
 * the final game app.
 */
public class Ass5Game {
    /**
     * create new game, initialize it and runs it.
     * @param args from cmd
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
