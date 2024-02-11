// 322453200, Ilanit Berditchevski
import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.Random;

public class BouncingBallAnimation {
    /**
     The main method that runs the program
     @param args Command line arguments, not used in this program
     */
    public static void main(String[] args) {

        // validation
        if(args.length >=4 ){
            int x = Math.abs(Integer.parseInt(args[0]));
            int y = Math.abs(Integer.parseInt(args[1]));
            int dx =Math.abs(Integer.parseInt(args[2]));
            int dy = Math.abs(Integer.parseInt(args[3]));

            Point p = new Point(x, y);

            drawAnimation(p,dx,dy);
        }
        else{
            int x = 12;
            int y = 2;
            int dx = 3;
            int dy = 4;

            Point p = new Point(x, y);

            drawAnimation(p,dx,dy);
        }
    }
    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;
    public static int RADIUS = 30;

    /**
     *Animates the balls moving around the surface.
     @param start starting point
     @param dx horizontal change
     @param dy vertical change
     */
    static private void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", WIDTH, HEIGHT);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        Random rnd = new Random();
        int r = rnd.nextInt(256);
        int g = rnd.nextInt(256);
        int b = rnd.nextInt(256);

        Ball ball = new Ball((int) start.getX(), (int) start.getY(), RADIUS,
                new Color(r,g,b));
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
