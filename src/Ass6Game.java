// 316443902 Nir Koren

import animations.GameFlow;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;
import levels.LevelInformation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * the final game app.
 */
public class Ass6Game {
    private static List<LevelInformation> levels = new ArrayList<LevelInformation>(Arrays.asList(new Level1(),
            new Level2(), new Level3(), new Level4()));
    private static List<Integer> levelNumbers = new ArrayList<Integer>();
    /**
     * create new game, initialize it and runs it.
     * @param args from cmd
     */
    public static void main(String[] args) {
        GameFlow game = new GameFlow();
        for (int i = 0; i < args.length; i++) {
            try {
                if (Integer.parseInt(args[i]) <= levels.size()) {
                    levelNumbers.add(Integer.parseInt(args[i]));
                }
            } catch (NumberFormatException e) {
            }
        }
        if (levelNumbers.size() == 0) {
            game.runLevels(levels);
        } else {
            List<LevelInformation> levelsToRun = new ArrayList<LevelInformation>();
            for (int i = 0; i < levelNumbers.size(); i++) {
                levelsToRun.add(levels.get(levelNumbers.get(i) - 1));
            }
            game.runLevels(levelsToRun);
        }
    }
}
