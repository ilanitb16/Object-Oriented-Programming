package arkanoid;

import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;

/** The EndScreen class is responsible for the animation at the end
 * of the game.
 */
public class EndScreen implements Animation {
    private GUI gui;

    private boolean isWin;
    private int finalScore;

    /** The EndScreen constructor.
     * @param isWin to handle the title at the end of the game accordingly.
     * @param finalScore the score at the end of the game.
     * @param gui the gui.
     */
    public EndScreen(GUI gui, boolean isWin, int finalScore) {
        this.gui = gui;
        this.isWin = isWin;
        this.finalScore = finalScore;
    }

    /** Function in charge of the game logic.
     * @param d The surface to operate on.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());

        // Draw the final score
        String scoreText = getEndScreenMessage();

        d.setColor(Color.white);
        d.drawText(220, 200, scoreText, 30);

        gui.show(d);
    }

    /** Function indicating if the game is still running or is to be stopped.
     * @return true if the game should be stopped.
     */
    public boolean shouldStop() {
        return false;
    }
    /** Handeling the messages appearing at the end of the screen according
     * to the win or loss of the player.
     * @return correct message.
     */
    private String getEndScreenMessage() {
        return isWin ? "You Win! Your score is" + this.finalScore
                : "Game Over. Your score is " + this.finalScore;
    }
}
