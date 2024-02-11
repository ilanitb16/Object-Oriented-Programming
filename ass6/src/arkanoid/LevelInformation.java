package arkanoid;

import geometry.Velocity;
import java.util.List;

/** The LevelInformation interface specifies the information required to fully
 * describe a level.
 */
public interface LevelInformation {
    /** Returns number of balls.
     * @return number of balls.
     */
    int numberOfBalls();
    /** The initial velocity of each ball.
     * initialBallVelocities().size() == numberOfBalls()
     * @return velocity list.
     */
    List<Velocity> initialBallVelocities();
    /** Returns paddle speed.
     * @return paddle speed.
     */
    int paddleSpeed();
    /** Returns paddle width.
     * @return paddle width.
     */
    int paddleWidth();
    /** The level name will be displayed at the top of the screen.
     * @return string name of the level.
     */
    String levelName();
    /** Returns a sprite with the background of the level.
     * @return background.
     */
    Sprite getBackground();
    /** The Blocks that make up this level, each block contains its size,
     * color and location.
     * @return list of blocks.
     */
    List<Block> blocks();
    /** Number of blocks that should be removed before the level is considered
     *  to be "cleared". This number should be <= blocks.size();
     * @return number of blocks to remove.
     */
    int numberOfBlocksToRemove();
}
