package arkanoid;

import geometry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/** The Level3 class specifies the information required to fully
 * describe level 3.
 */
public class Level3 implements LevelInformation {
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
        return 3;
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
        return SCREEN_WIDTH / 2;
    }
    /** The level name will be displayed at the top of the screen.
     * @return string name of the level.
     */
    public String levelName() {
        return "Green 3";
    }
    /** Returns a sprite with the background of the level.
     * @return background.
     */
    public Sprite getBackground() {
        return new Block(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT,
                Color.getHSBColor(153, 217, 255));
    }
    /** The Blocks that make up this level, each block contains its size,
     * color and location.
     * @return list of blocks.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        int blocksInRow = 7;
        int yPos = BLOCKS_TOP_Y_POSITION;
        Color[] colors = {
                new Color(0, 128, 0),  // Green color for the field
                Color.GRAY  // Gray color for the frame blocks
        };

        int frameWidth = BLOCK_WIDTH * blocksInRow;
        int frameHeight = BLOCK_HEIGHT * 5;
        int frameXPos = (SCREEN_WIDTH - frameWidth) / 2; // X position to center the frame
        int frameYPos = yPos;

        // Create the gray frame blocks on top
        for (int i = 0; i < blocksInRow; i++) {
            Block block = new Block(frameXPos, frameYPos, BLOCK_WIDTH, BLOCK_HEIGHT, colors[1]);
            blocks.add(block);
            frameXPos += BLOCK_WIDTH;
        }

        // Create the gray frame blocks on the right and left sides
        int frameColumnXPosLeft = (SCREEN_WIDTH - frameWidth) / 2 - BLOCK_WIDTH;
        int frameColumnXPosRight = (SCREEN_WIDTH + frameWidth) / 2;
        int frameColumnYPos = frameYPos + BLOCK_HEIGHT;
        for (int i = 0; i < 3; i++) {
            Block blockLeft = new Block(frameColumnXPosLeft, frameColumnYPos, BLOCK_WIDTH, BLOCK_HEIGHT, colors[1]);
            Block blockRight = new Block(frameColumnXPosRight, frameColumnYPos, BLOCK_WIDTH, BLOCK_HEIGHT, colors[1]);
            blocks.add(blockLeft);
            blocks.add(blockRight);
            frameColumnYPos += BLOCK_HEIGHT;
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
