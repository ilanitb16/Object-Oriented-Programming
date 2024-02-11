// 322453200, Ilanit Berditchevski
import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.Random;

public class MultipleBouncingBallsAnimation {
    // constants representing the width and height of the GUI
    public static final int WIDTH = 200;
    public static final int HEIGHT = 200;
    public static final int MAXSPEED = 50; // maximum speed given to the ball

    /**
     *Creates a GUI object, generates arrays of Balls with random
     starting locations and colors and places them on surface. Animates
     the balls bouncing on the screen
     @param args the arguments when the program is run
     */
    public static void main(String[] args) {
        Ball[] balls;
        if(args.length == 0){
            balls = new Ball[6];
        }
        else {
            balls = new Ball[args.length];
        }

    // starting location of each ball
    Point [] start = new Point[balls.length];

    Random rnd = new Random();

    int r;
    int g;
    int b;

    int x;
    int y;

    for(int i = 0; i< start.length; i++){
        x = (rnd.nextInt(WIDTH + 1));
        y = (rnd.nextInt(HEIGHT + 1));
        start[i] = new Point(x,y);
    }

    if(args.length!=0){
        for(int i = 0; i< args.length; i++){
            int rd = Math.abs(Integer.parseInt(args[i]));

            r = rnd.nextInt(256);
            g = rnd.nextInt(256);
            b = rnd.nextInt(256);

            balls[i]= new Ball((int)start[i].getX(),(int)start[i].getY() , rd,
                    new Color(r, g, b));
        }
    }
    else{
        for(int i = 0; i< 6; i++){
            int rd = rnd.nextInt(12);

            r = rnd.nextInt(256);
            g = rnd.nextInt(256);
            b = rnd.nextInt(256);

            balls[i]= new Ball((int)start[i].getX(),(int)start[i].getY() , rd,
                    new Color(r, g, b));
        }
    }
        balls = sort(balls);
        drawAnimation(balls);
}

    /**
     *Animates the balls moving around the surface.
     @param balls an array of Ball objects representing the balls in the
     *top-left side
     */
    static private void drawAnimation(Ball [] balls) {
        GUI gui = new GUI("title", WIDTH, HEIGHT);

        // starting speed of each ball
        int [] speeds = new int[balls.length];

        Random rnd = new Random();

        int speed;
        for( int i = 0; i < speeds.length; i++){
            speed = (rnd.nextInt(MAXSPEED-i));
            speeds[i] = speed;
        }

        int temp;

        for (int i = 0; i < speeds.length; i++) {
            for (int j = 0; j < speeds.length - i - 1; j++) {
                if (speeds[j] > speeds[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    temp = speeds[j];
                    speeds[j] = speeds[j + 1];
                    speeds[j + 1] = temp;
                }
            }
        }

        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        for(int i = 0; i < balls.length; i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed(60, speeds[i]);
            balls[i].setVelocity(velocity);
        }

        for(int i =0;i< balls.length; i++){
            System.out.println(i+1 + ": " + speeds[i]);
        }

        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for(int i = 0; i< balls.length; i++){
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }

            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }

    }

    /**
     *Sorts an array of Ball objects by their radius using bubble sort.
     *@param arr array of Balls sorted by the radius
     *@return Ball[] the sorted array of Balls
     */
    public static Ball [] sort(Ball [] arr) {
        int n = arr.length;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].getSize() < arr[j + 1].getSize()) {
                    // Swap arr[j] and arr[j+1]
                    Ball temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}
