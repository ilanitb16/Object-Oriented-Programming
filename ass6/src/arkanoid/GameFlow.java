package arkanoid;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.List;

/** This class will be in charge of creating the different levels, and
 *  moving from one level to the next.
 */
public class GameFlow {
    private AnimationRunner animationRunner;
    private KeyboardSensor keyboardSensor;
    private int score;
    private GUI gui;
    /**
     * Constructs a GameFlow object with the given AnimationRunner and
     * KeyboardSensor.
     *
     * @param animationRunner The AnimationRunner responsible for running the
     *                        game animations.
     * @param keyboardSensor  The KeyboardSensor for receiving keyboard input.
     * @param gui the GUI.
     */
    public GameFlow(AnimationRunner animationRunner,
                    KeyboardSensor keyboardSensor, GUI gui) {
        this.animationRunner = animationRunner;
        this.keyboardSensor = keyboardSensor;
        this.score = 0;
        this.gui = gui;
    }
    /**
     * Runs the given list of levels, one by one.
     *
     * @param levels The list of LevelInformation objects representing the
     *               levels to be played.
     */
    public void runLevels(java.util.List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                    this.animationRunner, score);
            level.initialize();
            level.run();

            score += level.getScore().getValue();
        }

        boolean isWin = allLevelsCleared(levels);

        EndScreen endScreen  =
                new EndScreen(gui, isWin, score);
        KeyPressStoppableAnimation stoppable =
                new KeyPressStoppableAnimation(keyboardSensor,
                        KeyboardSensor.SPACE_KEY, endScreen);

        animationRunner.run(stoppable);
    }

    /**
     * Checks if all levels in the given list have been cleared (i.e., no more
     * blocks to remove).
     *
     * @param levels The list of objects representing the levels.
     * @return true if all levels have been cleared, false otherwise.
     */
    private boolean allLevelsCleared(List<LevelInformation> levels) {
        for (LevelInformation level : levels) {
            if (level.numberOfBlocksToRemove() != 0) {
                return false;
            }
        }
        return true;
    }
}
