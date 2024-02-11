package arkanoid;

import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;

/** The PauseScreen class is responsible for pausing the game.
 */
public class PauseScreen implements Animation {
    private GUI gui;

    /** The constructor for the PauseScreen class which is responsible for
     * running the pause animation.
     * @param gui the GUI.
     */
    public PauseScreen(GUI gui) {
        this.gui = gui;
    }

    /** Function in charge of the game logic.
     * @param d The surface to operate on.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        d.setColor(Color.red);
        d.drawText(d.getWidth() / 4, d.getHeight() / 2, "paused -- "
                + "press space to continue", 32);
        gui.show(d);
    }

    /** Function indicating if the game is still running or is to be stopped.
     * @return true if the game should be stopped.
     */
    public boolean shouldStop() {
        return false;
    }
}
