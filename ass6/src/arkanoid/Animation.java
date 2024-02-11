package arkanoid;

import biuoop.DrawSurface;
/** The Animation will be the base class for other more complex animations.
 */
public interface Animation {
    /** Function in charge of the game logic.
     * @param d The surface to operate on.
     */
    void doOneFrame(DrawSurface d);
    /** Function indicating if the game is still running or is to be stopped.
     * @return true if the game should be stopped.
     */
    boolean shouldStop();
}
