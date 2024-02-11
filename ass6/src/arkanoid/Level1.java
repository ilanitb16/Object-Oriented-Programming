package arkanoid;

import geometry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/** The Level1 class specifies the information required to fully
 * describe level 1.
 */
public class Level1 implements LevelInformation {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 20;
    private static final int BORDER_WIDTH = 10;
    private static final int BLOCKS_IN_ROW_MAX = 12;
    private static final int BLOCKS_TOP_Y_POSITION = 40;
    private static final int BLOCKS_ROW_COUNT = 6;
    /** Returns number of balls.
     * @return number of balls.
     */
    public int numberOfBalls() {
        return 15;
    }
    /** The initial velocity of each ball.
     * initialBallVelocities().size() == numberOfBalls()
     * @return velocity list.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, -5)); // example initial velocity
        return velocities;
    }
    /** Returns paddle speed.
     * @return paddle speed.
     */
    public int paddleSpeed() {
        return 5;
    }
    /** Returns paddle width.
     * @return paddle width.
     */
    public int paddleWidth() {
        return 100;
    }
    /** The level name will be displayed at the top of the screen.
     * @return string name of the level.
     */
    public String levelName() {
        return "Direct Hit";
    }
    /** Returns a sprite with the background of the level.
     * @return background.
     */
    public Sprite getBackground() {
        return new Block(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, Color.BLACK);
    }
    /** The Blocks that make up this level, each block contains its size,
     * color and location.
     * @return list of blocks.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        int blocksInRow = BLOCKS_IN_ROW_MAX;
        int yPos = BLOCKS_TOP_Y_POSITION;
        Color[] colors = {
                new Color(125, 125, 125),
                new Color(255, 0, 0),
                new Color(255, 255, 0),
                new Color(0, 0, 255),
                new Color(217, 163, 200, 255),
                new Color(0, 255, 0)
        };

        for (int i = 0; i < BLOCKS_ROW_COUNT; i++) {
            int xPos = SCREEN_WIDTH - BLOCK_WIDTH - BORDER_WIDTH;

            for (int b = 0; b < blocksInRow; b++) {
                Block block = new Block(xPos, yPos, BLOCK_WIDTH, BLOCK_HEIGHT,
                        colors[i]);
                blocks.add(block);
                xPos -= BLOCK_WIDTH;
            }

            yPos += BLOCK_HEIGHT;
            blocksInRow--;
        }
        return blocks;
    }
    /** Number of blocks that should be removed before the level is considered
     *  to be "cleared". This number should be <= blocks.size();
     * @return number of blocks to remove.
     */
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
