// 322453200, Ilanit Berditchevski
import biuoop.GUI;
import biuoop.DrawSurface;
public class BallsTest1 {

    // constants for height and width of gui
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    /**
     *Creates a GUI object, generates balls and animated them on the screen
     @param args the arguments when the program is run
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Balls Test 1", WIDTH, HEIGHT);
        DrawSurface d = gui.getDrawSurface();

        Ball b1 = new Ball(100,100,30,java.awt.Color.RED);
        Ball b2 = new Ball(100,150,10,java.awt.Color.BLUE);
        Ball b3 = new Ball(80,249,50,java.awt.Color.GREEN);

        b1.drawOn(d);
        b2.drawOn(d);
        b3.drawOn(d);

        Point p = new Point(100,100);
        drawAnimation(p,50,10);
        gui.show(d);
    }

    /**
     *Animates the balls moving around the surface.
     @param start starting point of a ball
     @param dx horizontal change
     @param dy vertical change
     @return void
     */
    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        Ball ball = new Ball((int) start.getX(), (int) start.getY(), 30,
                java.awt.Color.BLACK);
        ball.setVelocity(dx,dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
