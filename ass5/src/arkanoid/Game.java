//
package arkanoid;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/** The Game class is responsible for running the Arkanoid game.
 */
public class Game {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int BORDER_WIDTH = 10;
    private static final int BLOCK_WIDTH = 50;
    private static final int BLOCK_HEIGHT = 20;
    private static final int BLOCKS_IN_ROW_MAX = 12;
    private static final int BLOCKS_TOP_Y_POSITION = 40;
    private static final int BLOCKS_ROW_COUNT = 6;
    private static final int BALL_RADIUS = 6;
    private static final int BALLS_COUNT = 3;
    private static final int GAME_OVER_SCORE = 100;
    private SpriteCollection sprites;
    private GUI gui;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private List<Sprite> spritesRemove = new ArrayList<>();
    private List<Sprite> ballsRemove = new ArrayList<>();
    private List<Ball> balls;
    private Counter ballsCounter;
    private Counter score;

    /** Constructor for Game. Initialize the GUI, GameEnvironment and
     * SpriteCollection.
     */
    public Game() {
        gui = new GUI("sprites.arkanoid.Game", SCREEN_WIDTH, SCREEN_HEIGHT);
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        ballsCounter = new Counter();
        balls = new ArrayList<>();
        score = new Counter();

    }

    /** Add a collidable object to the game environment.
     @param c The collidable object to be added.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /** Add a sprite to the collection of sprites.
     @param s The sprite to be added.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /** Create a ball and add it to the game.
     * */
    void createBall() {
        Ball ball = new Ball(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 3, BALL_RADIUS,
                Color.RED, environment);

        ball.addToGame(this);
    }

    /** Create blocks and add them to the game.
     */
    public void createBlocks() {
        remainingBlocks = new Counter();

        // create some blocks and add them to the environment
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
                block.addHitListener(new BlockRemover(this,
                        remainingBlocks));
                block.addHitListener(new ScoreTrackingListener(score));

                remainingBlocks.increase(1);
                block.addToGame(this);
                sprites.addSprite(block);
                xPos -= BLOCK_WIDTH;
            }

            yPos += BLOCK_HEIGHT;
            blocksInRow--;
        }

        // Put a death-region block at (or below) the bottom of the screen
        Block deathRegion = new Block(0, SCREEN_HEIGHT - 12,
                SCREEN_WIDTH, 10, Color.WHITE);
        deathRegion.addHitListener(new BallRemover(this, ballsCounter));
        deathRegion.addToGame(this);
        sprites.addSprite(deathRegion);
    }

    /** Create borders and add them to the game.
     */
    void createBorders() {
        // add top border
        Block blockTopBorder = new Block(0, 0, SCREEN_WIDTH, BORDER_WIDTH,
                Color.gray);

        // add bottom border
        Block blockBottomBorder = new Block(0, SCREEN_HEIGHT - BORDER_WIDTH,
                SCREEN_WIDTH, BORDER_WIDTH, Color.gray);

        // add left border
        Block blockLeftBorder = new Block(0, 0, BORDER_WIDTH, SCREEN_HEIGHT,
                Color.gray);

        // add right border
        Block blockRightBorder = new Block(SCREEN_WIDTH - BORDER_WIDTH, 0,
                BORDER_WIDTH, SCREEN_HEIGHT, Color.gray);

        blockTopBorder.addToGame(this);
        blockBottomBorder.addToGame(this);
        blockLeftBorder.addToGame(this);
        blockRightBorder.addToGame(this);
    }

    /** Create paddle and add it to the game.
     */
    void createPaddle() {
        Paddle paddle = new Paddle(gui, BORDER_WIDTH);
        paddle.addToGame(this);
    }

    /** Run the game -- start the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;

        while (remainingBlocks.getValue() > 0 && ballsCounter.getValue() > 0) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.black);
            d.fillRectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

            // Remove the sprites that need to be removed
            if (spritesRemove != null) {
                for (Sprite sprite : spritesRemove) {
                    sprites.removeSprite(sprite);
                }
            }

            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;

            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }

            if (remainingBlocks.getValue() == 0) {
                score.increase(GAME_OVER_SCORE);
            }
        }
        gui.close();
    }
    /** Initialize the game, create blocks, balls, borders and a paddle.
     */
    public void initialize() {
        for (int i = 0; i < BALLS_COUNT; i++) {
            createBall();
        }
        createBorders();
        createBlocks();
        createPaddle();

        ballsCounter.increase(BALLS_COUNT);

        ScoreIndicator scoreIndicator = new ScoreIndicator(score,
                SCREEN_WIDTH / 2, 10);
        scoreIndicator.addToGame(this);
        sprites.addSprite(scoreIndicator);
    }
    /** Remove collidable object from the game when required.
     * @param c Collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.getCollidables().remove(c);
    }
    /** Add sprites that need to be removed to the remove list.
     * @param s sprite to remove.
     */
    public void removeSprite(Sprite s) {
        spritesRemove.add(s);
    }

}