package arkanoid;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/** The AnimationRunner will be responsible for running the animations.
 */
public class AnimationRunner {
    private GUI gui;
    private Sleeper sleeper;
    private int framesPerSecond;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;

    /** The AnimationRunner takes an Animation object and runs it.
     *  We implement the task-specific information in the Animation object,
     *  and run it using the loop in the AnimationRunner class.
     */
    public AnimationRunner() {
        this.gui = new GUI("Arkanoid", SCREEN_WIDTH, SCREEN_HEIGHT);
        this.sleeper = new Sleeper();
        this.framesPerSecond = 60;
    }

    /** Run the game -- start the animation loop.
     * @param animation to indicate which operation will be run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;

        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            if ((animation instanceof EndScreen)) {
                gui.show(d);
            }

            long usedTime = System.currentTimeMillis() - startTime;
            long millisecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (millisecondLeftToSleep > 0) {
                sleeper.sleepFor(millisecondLeftToSleep);
            }
        }
    }

    /** Getter method for gui.
     * @return gui.
     */
    public GUI getGui() {
        return this.gui;
    }
}
