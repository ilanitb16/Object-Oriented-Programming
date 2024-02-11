package arkanoid;

import geometry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/** The Level2 class specifies the information required to fully
 * describe level 2.
 */
public class Level2 implements LevelInformation {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 20;
    private static final int BORDER_WIDTH = 10;

    /**
     * Returns number of balls.
     *
     * @return number of balls.
     */
    public int numberOfBalls() {
        return 5;
    }

    /**
     * The initial velocity of each ball.
     * initialBallVelocities().size() == numberOfBalls()
     *
     * @return velocity list.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, -5)); // example initial velocity
        return velocities;
    }

    /**
     * Returns paddle speed.
     *
     * @return paddle speed.
     */
    public int paddleSpeed() {
        return 5;
    }

    /**
     * Returns paddle width.
     *
     * @return paddle width.
     */
    public int paddleWidth() {
        return SCREEN_WIDTH / 2;
    }

    /**
     * The level name will be displayed at the top of the screen.
     *
     * @return string name of the level.
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return background.
     */
    public Sprite getBackground() {
        return new Block(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT,
                Color.getHSBColor(255, 218, 109));
    }

    /**
     * The Blocks that make up this level, each block contains its size,
     * color and location.
     *
     * @return list of blocks.
     */
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();

        int pyramidHeight = 5;
        int xOffset = BLOCK_WIDTH * (pyramidHeight - 1);
        int yPos = (SCREEN_HEIGHT - (pyramidHeight * BLOCK_HEIGHT)) / 2;
        Color[] colors = new Color[pyramidHeight];
        for (int i = 0; i < pyramidHeight; i++) {
            int shade = 255 - (i * (255 / pyramidHeight));
            int r = 255;
            int g = shade;
            int b = 0;
            colors[i] = new Color(r, g, b);
        }

        for (int i = 1; i <= pyramidHeight; i++) {
            int blocksInRow = i;
            int xPos = (SCREEN_WIDTH - (blocksInRow * BLOCK_WIDTH)) / 2 - xOffset;

            for (int b = 0; b < blocksInRow; b++) {
                Block block = new Block(xPos, yPos, BLOCK_WIDTH, BLOCK_HEIGHT, colors[pyramidHeight - i]);
                blocks.add(block);
                xPos += BLOCK_WIDTH;
            }

            yPos += BLOCK_HEIGHT;
        }

        int treeCount = 5;
        int gap = BLOCK_WIDTH;

        int trunkWidth = BLOCK_WIDTH / 4;
        int trunkHeight = BLOCK_HEIGHT * 3;
        int leavesWidth = BLOCK_WIDTH * 2;
        int leavesHeight = BLOCK_HEIGHT * 3;


        for (int i = 0; i < treeCount; i++) {
            int trunkXPos =
                   3 * BORDER_WIDTH + (i * (trunkWidth + leavesWidth + gap)) + gap;
            int trunkYPos = SCREEN_HEIGHT - trunkHeight;

            Color trunkColor = new Color(139, 69, 19); // Brown
            Block trunk = new Block(trunkXPos, trunkYPos, trunkWidth, trunkHeight, trunkColor);
            blocks.add(trunk);

            Color leavesColor = new Color(34, 139, 34); // Dark Green
            int leavesXPos = trunkXPos - (leavesWidth - trunkWidth) / 2;
            int leavesYPos = trunkYPos - leavesHeight;
            Block leaves = new Block(leavesXPos, leavesYPos, leavesWidth, leavesHeight, leavesColor);
            blocks.add(leaves);
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
