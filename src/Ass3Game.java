// 316443902 Nir Koren

/**
 * the final game app.
 */
public class Ass3Game {
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
