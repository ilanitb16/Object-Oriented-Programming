// 322453200 Ilanit Berditchevski.
import arkanoid.AnimationRunner;
import arkanoid.Level1;
import arkanoid.Level2;
import arkanoid.Level3;
import arkanoid.GameFlow;
import arkanoid.LevelInformation;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/** Class initializing all needed parameters for the game and running it.
 */
public class Ass6Game {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    /** Main function running the game.
     * @param args arguments
     */
    public static void main(String[] args) {
        AnimationRunner animationRunner = new AnimationRunner();
        KeyboardSensor keyboardSensor = animationRunner.getGui().getKeyboardSensor();
        GUI gui = animationRunner.getGui();
        List<LevelInformation> levels = new ArrayList<LevelInformation>();

        // Create the levels
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "1":
                    levels.add(new Level1());
                    break;

                case "2":
                    levels.add(new Level2());
                    break;

                case "3":
                    levels.add(new Level3());
                    break;
                default:
                    break;
            }
        }

        GameFlow gameFlow = new GameFlow(animationRunner, keyboardSensor, gui);
        gameFlow.runLevels(levels);
    }
}