//
package arkanoid;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import geometry.Point;
import geometry.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** The Game class is responsible for running the Arkanoid game.
 */
public class GameLevel implements Animation  {
    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 600;
    private static final int BORDER_WIDTH = 10;
    private static final int BALL_RADIUS = 6;
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
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInfo;

    /** Constructor for Game. Initialize the GUI, GameEnvironment and
     * SpriteCollection.
     * @param levelInfo level info.
     * @param keyboard Keyboard Sensor.
     * @param runner Animation runner.
     * @param score score.
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboard,
                     AnimationRunner runner, int score) {
        this.levelInfo = levelInfo;
        this.keyboard = keyboard;
        this.runner = runner;
        this.gui = runner.getGui();
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.ballsCounter = new Counter();
        this.balls = new ArrayList<>();
        this.score = new Counter();
        this.score.increase(score);
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
        for (Velocity velocity : levelInfo.initialBallVelocities()) {
            Ball ball;
            if (levelInfo.levelName() == "Direct Hit") {
                 ball = new Ball(new Point(SCREEN_WIDTH - 100,
                         SCREEN_HEIGHT / 3),  "meteor.png", environment);
            }
            if (levelInfo.levelName() == "Green 3") {
                ball = new Ball(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 3,
                        BALL_RADIUS, Color.WHITE, environment);
            } else {
                 ball = new Ball(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 3,
                        BALL_RADIUS, Color.RED, environment);
            }
            ball.addToGame(this);
            balls.add(ball);
            ballsCounter.increase(1);
        }
    }

    /** Create blocks and add them to the game.
     */
    public void createBlocks() {
        List<Block> blocks = levelInfo.blocks();

        remainingBlocks = new Counter();
        remainingBlocks.increase(blocks.size());

        for (Block block : blocks) {
            block.addHitListener(new BlockRemover(this, remainingBlocks));
            block.addHitListener(new ScoreTrackingListener(score));
            block.addToGame(this);
        }

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

        // Put a death-region block at (or below) the bottom of the screen
        Block deathRegion = new Block(0, SCREEN_HEIGHT - 12,
                SCREEN_WIDTH, 10, Color.WHITE);
        deathRegion.addHitListener(new BallRemover(this, ballsCounter));
        deathRegion.addToGame(this);
        sprites.addSprite(deathRegion);
    }

    /** Create paddle and add it to the game.
     */
    void createPaddle() {
        Paddle paddle = new Paddle(gui, BORDER_WIDTH);
        paddle.setSpeed(levelInfo.paddleSpeed());
        paddle.setWidth(levelInfo.paddleWidth());
        paddle.addToGame(this);
    }

    /** Initialize the game, create blocks, balls, borders and a paddle.
     */
    public void initialize() {
        for (int i = 0; i < levelInfo.numberOfBalls(); i++) {
            createBall();
        }
        createBorders();
        createBlocks();
        createPaddle();

        ScoreIndicator scoreIndicator = new ScoreIndicator(score,
                SCREEN_WIDTH / 2, 10);
        scoreIndicator.addToGame(this);
        sprites.addSprite(scoreIndicator);
       // run();
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

    /** Function in charge of the game logic.
     * @param d The surface to operate on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        switch (levelInfo.levelName()) {
            case "Direct Hit":
                printBackground1(d);
                break;
            case "Wide Easy":
                printBackground2(d);
                break;
            case "Green 3":
                printBackground3(d);
                break;
            default:
                d.setColor(Color.BLACK);
                d.fillRectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
                break;
        }

        // Remove the sprites that need to be removed
        if (spritesRemove != null) {
            for (Sprite sprite : spritesRemove) {
                sprites.removeSprite(sprite);
            }
        }

        spritesRemove.clear();
        this.sprites.drawAllOn(d);
        gui.show(d);
        this.sprites.notifyAllTimePassed();

        if (remainingBlocks.getValue() <= 0) {
            score.increase(GAME_OVER_SCORE);
            this.running = false;
        }

        int ballsCount = getBallsCount().getValue();

        if (ballsCount == 0) {
            this.running = false;
        }

        if (this.keyboard.isPressed("p")) {
            PauseScreen pauseScreen = new PauseScreen(this.gui);
            KeyPressStoppableAnimation stoppable =
                    new KeyPressStoppableAnimation(this.keyboard,
                            KeyboardSensor.SPACE_KEY, pauseScreen);

            this.runner.run(stoppable);
        }
    }

    /** Function indicating if the game is still running or is to be stopped.
     * @return true if the game should be stopped.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /** Run the game -- start the animation loop.
     */
    public void run() {
        CountdownAnimation countdownAnimation =
                new CountdownAnimation(2, 3, this.sprites,
                        gui);
        this.runner.run(countdownAnimation);
        this.running = true;
        this.runner.run(this);

        EndScreen endScreen =
                new EndScreen(gui, false, score.getValue());
        KeyPressStoppableAnimation stoppable =
                new KeyPressStoppableAnimation(keyboard,
                        KeyboardSensor.SPACE_KEY, endScreen);

        runner.run(stoppable);
    }

    /** Get method for score.
     * @return score.
     */
    public Counter getScore() {
        return this.score;
    }
    /** Get method for remaining blocks.
     * @return how many blocks are left.
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }
    /** Get method for Animation Runner.
     * @return AnimationRunner.
     */
    public AnimationRunner getRunner() {
        return this.runner;
    }
    /** Get method for balls counter.
     * @return counter for balls.
     */
    public Counter getBallsCount() {
        return this.ballsCounter;
    }
    /**
     * Prints the first background with a dark blue color, background stars, and distant galaxies.
     *
     * @param surface the drawing surface
     */
    public void printBackground1(DrawSurface surface) {
        surface.setColor(new Color(0, 0, 78));
        surface.fillRectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        // Draw the background stars
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(SCREEN_WIDTH);
            int y = random.nextInt(SCREEN_HEIGHT);
            surface.setColor(Color.YELLOW);
            surface.fillCircle(x, y, 1);
        }

        surface.setColor(Color.white);
        surface.drawText(SCREEN_WIDTH - 125, 40,
                levelInfo.levelName(), 25);
    }
    /**
     * Prints the second background with a yellow color, a large circle,
     * and a river at the bottom.
     *
     * @param surface the drawing surface
     */
    public void printBackground2(DrawSurface surface) {
        surface.setColor(new Color(255, 218, 109));
        surface.fillRectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        surface.setColor(new Color(236, 170, 25));
        surface.fillCircle(SCREEN_WIDTH - 100, 0, 300);

        int riverWidth = SCREEN_WIDTH;
        int riverHeight = 70;
        int riverXPos = 0;
        int riverYPos = SCREEN_HEIGHT - riverHeight;

        surface.setColor(new Color(166, 203, 234));
        surface.fillRectangle(riverXPos, riverYPos, riverWidth, riverHeight);

        surface.setColor(Color.green.darker());
        surface.fillRectangle(riverXPos, riverYPos - 3, riverWidth, 3);
        surface.drawText(SCREEN_WIDTH - 150, 40, levelInfo.levelName(), 25);
    }
    /**
     * Prints the third background with a green color, a white rectangle, and
     * a white circle at the bottom.
     *
     * @param surface the drawing surface
     */
    public void printBackground3(DrawSurface surface) {

        surface.setColor(new Color(38, 86, 22));
        surface.fillRectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        surface.setColor(Color.WHITE);
        surface.fillRectangle(20, 20, SCREEN_WIDTH - 40, SCREEN_HEIGHT - 40);

        surface.setColor(new Color(38, 86, 22));
        surface.fillRectangle(25, 25, SCREEN_WIDTH - 50,
                SCREEN_HEIGHT - 50);

        // Draw white circle at the bottom
        surface.setColor(Color.WHITE);
        int circleDiameter = 200; // Adjust circle diameter as desired
        int circleXPos = (SCREEN_WIDTH) / 2;
        int circleYPos = SCREEN_HEIGHT;
        surface.fillCircle(circleXPos, circleYPos, circleDiameter);

        surface.setColor(new Color(38, 86, 22));
        int circleDiameter2 = 195; // Adjust circle diameter as desired
        int circleXPos2 = (SCREEN_WIDTH) / 2;
        int circleYPos2 = SCREEN_HEIGHT;
        surface.fillCircle(circleXPos2, circleYPos2, circleDiameter2);

        surface.drawText(SCREEN_WIDTH - 125, 40, levelInfo.levelName(), 25);
    }
}