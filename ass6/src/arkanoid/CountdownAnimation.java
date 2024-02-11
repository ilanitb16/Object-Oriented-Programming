package arkanoid;

import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;

/** The CountdownAnimation class is responsible for the countdown animation
 * before the start of the game.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int currentCount;
    private int countDuration;
    private long startTime;
    private GUI gui;
    private boolean stop;

    /** The CountdownAnimation will display the given gameScreen,
     * for numOfSeconds seconds, and on top of them it will show
     * a countdown from countFrom back to 1, where each number will
     * appear on the screen for (numOfSeconds / countFrom) seconds, before
     * it is replaced with the next one.
     * @param numOfSeconds number of seconds to display animation for.
     * @param countFrom number to count back from.
     * @param gameScreen the surface.
     * @param gui the given gui.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen, GUI gui) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.currentCount = countFrom;
        this.countDuration = (int) (numOfSeconds * 1000 / countFrom);
        this.startTime = System.currentTimeMillis();
        this.gui = gui;
        this.stop = false;
    }
    /** Function in charge of the game logic.
     * @param d The surface to operate on.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        this.gameScreen.drawAllOn(d);
        long elapsedTime = System.currentTimeMillis() - this.startTime;

        int currentCount = this.countFrom - (int) (elapsedTime / this.countDuration);
        if (currentCount > 0) {
            d.setColor(Color.WHITE);
            d.drawText(d.getWidth() / 2, d.getHeight() / 2, Integer.toString(currentCount), 72);
        }
        if (currentCount <= 0) {
            this.stop = true;
        }

        gui.show(d);
    }
    /** Function indicating if the game is still running or is to be stopped.
     * @return true if the game should be stopped.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
