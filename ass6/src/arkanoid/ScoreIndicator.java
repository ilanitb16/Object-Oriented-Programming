package arkanoid;

import biuoop.DrawSurface;
import java.awt.Color;

/** The Score Indicator class is responsible for displaying the current score
 *  on top of the screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private int startingX;
    private int startingY;

    /** Constructor.
     * @param score representing the GUI.
     * @param startingX representing the starting X position.
     * @param startingY representing the starting Y position.
     */
    public ScoreIndicator(Counter score, int startingX, int startingY) {
        this.score = score;
        this.startingX = startingX;
        this.startingY = startingY;
    }

    /** draw the block to the screen.
     * @param d representing the surface we work on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        d.drawText(startingX, startingY, "Score: " + score.getValue(), 15);
    }
    /** Notify the block that time has passed.
     */
    public void timePassed() {
    }

    /** Adds the block to a given Game object.
     @param game The Game object to which the sprite object will be added.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}
