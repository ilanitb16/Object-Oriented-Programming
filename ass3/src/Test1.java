//import biuoop.DrawSurface;
//import biuoop.GUI;
//import biuoop.Sleeper;
//import sprites.Collidable;
//import geometry.Point;
//import geometry.Rectangle;
//import arkanoid.*;
//import geometry.Velocity;
//
//import java.awt.Color;
//import java.util.Random;
//
//public class Test1 {
//
//    // constants representing the width and height of the GUI
//    private static final int SCREEN_WIDTH = 800;
//    private static final int SCREEN_HEIGHT = 600;
//    private static final int BORDER_WIDTH = 5;
//    private static final Color BACKGROUND_COLOR = Color.WHITE;
//    private static final int BALL_RADIUS = 10;
//
//    private static final int MAXSPEED = 50;
//
//    private GUI gui;
//    private GameEnvironment environment;
//    private Ball ball;
//
//    public Test1() {
//        Random rnd = new Random();
//        this.gui = new GUI("sprites.arkanoid.Game", SCREEN_WIDTH, SCREEN_HEIGHT);
//        this.environment = new GameEnvironment();
//        this.ball = new Ball(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2, BALL_RADIUS, Color.RED, environment);
//        int speed = (rnd.nextInt(50));
//        Velocity velocity = Velocity.fromAngleAndSpeed(60, speed);
//        this.ball.setVelocity(velocity);
//        addBlocks();
//    }
//
//    private void addBlocks() {
//        // create some blocks and add them to the environment
//        // example:
//        Block blockA = new Block((new Rectangle(new Point(100, 100),50,20)));
//        Block blockB = new Block((new Rectangle(new Point(250, 150),50,20)));
//        Block blockC = new Block((new Rectangle(new Point(290, 220),50,20)));
//
//        this.environment.addCollidable(blockA);
//        this.environment.addCollidable(blockB);
//        this.environment.addCollidable(blockC);
//
//        // add top border
//        environment.addCollidable(new Block((new Rectangle(new Point(0, 0),
//                SCREEN_WIDTH,BORDER_WIDTH))));
//
//        // add bottom border
//        environment.addCollidable(new Block((new Rectangle(new Point(0,
//                SCREEN_HEIGHT - BORDER_WIDTH), SCREEN_WIDTH,BORDER_WIDTH))));
//
//        // add left border
//        environment.addCollidable(new Block((new Rectangle(new Point(0, 0),
//                BORDER_WIDTH,SCREEN_HEIGHT))));
//
//        // add right border
//        environment.addCollidable(new Block((new Rectangle(
//                new Point(SCREEN_WIDTH - BORDER_WIDTH, 0), BORDER_WIDTH,SCREEN_HEIGHT))));
//    }
//
//    public void runAnimation() {
//
//        Sleeper sleeper = new Sleeper();
//
//        while (true) {
//            // move the ball
//            this.ball.moveOneStep();
//
//            // draw the ball and the blocks
//            DrawSurface surface = this.gui.getDrawSurface();
//            surface.setColor(BACKGROUND_COLOR);
//            surface.fillRectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
//            this.ball.drawOn(surface);
//
//            for (Collidable collidable : this.environment.getCollidables()) {
//                collidable.drawOn(surface);
//            }
//
//            this.gui.show(surface);
//
//            // wait a short time
//            sleeper.sleepFor(10);
//        }
//    }
//
//    public void runImprovedAnimation(){
//        SpriteCollection sprites = new SpriteCollection();
//
//        sprites.addSprite(ball);
//
//        for (Collidable col: environment.getCollidables()) {
//            sprites.addSprite((Sprite)col);
//        }
//
//        Sleeper sleeper = new Sleeper();
//
//        // animation loop
//        while (true) {
//            DrawSurface surface = this.gui.getDrawSurface();
//            surface.setColor(BACKGROUND_COLOR);
//            surface.fillRectangle(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
//
//            sprites.notifyAllTimePassed();
//            sprites.drawAllOn(surface);
//
//            this.gui.show(surface);
//
//            sleeper.sleepFor(10);
//        }
//    }
//
//    public static void main(String[] args) {
//        Test1 game = new Test1();
//        game.runImprovedAnimation();
//    }
//
//}
