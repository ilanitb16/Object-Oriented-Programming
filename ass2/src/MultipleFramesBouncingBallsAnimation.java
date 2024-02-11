// 322453200, Ilanit Berditchevski
import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.*;
import java.util.Random;

public class MultipleFramesBouncingBallsAnimation {

    // constants for width and height of new rectangles
    public static final int WIDTH1 = 450;
    public static final int HEIGHT1 = 450;
    public static final int WIDTH2 = 150;
    public static final int HEIGHT2 = 150;
    public static final int MAXSPEED = 50;

    // Constants for starting and ending positions for rectangle 1
    public static final int Start1_X = 50;
    public static final int Start1_Y = 50;
    public static final int End1_X = 500;
    public static final int End1_Y = 500;

    // Constants for starting and ending positions for rectangle 2
    public static final int Start2_X = 450;
    public static final int Start2_Y = 450;
    public static final int End2_X = 600;
    public static final int End2_Y = 600;

    /**
     *Creates a GUI object, generates arrays of Balls with random
     starting locations and colors and places them on surface. Animates
     the balls bouncing on the screen
    @param args the arguments when the program is run
    */
    public static void main(String[] args) {
        GUI gui = new GUI("title", 1000, 1000);
        Ball[] balls1;
        Ball[] balls2;
        if(args.length == 0){
            balls1 = new Ball[2];
            balls2 = new Ball[2];
        }
        else{
            balls1 = new Ball[args.length/2];
            balls2 = new Ball[args.length/2];
        }


        Random rnd = new Random();

        // starting location of each ball
        Point [] start1 = new Point[balls1.length];
        Point [] start2 = new Point[balls2.length];

        int x;
        int y;

        for(int i = 0; i < start1.length; i++){
            x = rnd.nextInt(End1_X - Start1_X - 1) + Start1_X + 1;
            y = rnd.nextInt(End1_Y - Start1_Y - 1) + Start1_Y + 1;
            start1[i] = new Point(x,y);
        }
        for(int i = 0; i < start2.length; i++){
            x = rnd.nextInt(End2_X - Start2_X - 1) + Start2_X + 1;
            y = rnd.nextInt(End2_Y - Start2_Y - 1) + Start2_Y + 1;
            start2[i] = new Point(x,y);
        }

        int r;
        int g;
        int b;

        int j = 0;
        int n = args.length;
        if(args.length == 0){
            n = 4;
        }

        for(int i = 0; i< balls1.length+ balls2.length; i++){
            int rd;
            if(args.length!=0){
                 rd= Integer.parseInt(args[i]);
            }
            else{
                 rd = i;
            }

            r = rnd.nextInt(256);
            g = rnd.nextInt(256);
            b = rnd.nextInt(256);

            if(i<n/2){
                balls1[i]= new Ball((int)start1[i].getX(),
                        (int)start1[i].getY(), rd,
                        new Color(r, g, b));
            }
            else{
                balls2[j]= new Ball((int)start2[j].getX(),
                        (int)start2[j].getY(), rd, new Color(r, g, b));
                j++;
            }
        }

        balls1 = sort(balls1);
        balls2 = sort(balls2);

        drawAnimation(balls1,balls2,gui);
    }

    /**
    *Animates the balls moving around the surface.
    @param balls1 an array of Ball objects representing the balls in the
    top-left side
    @param balls2 an array of Ball objects representing the balls in the
    bottom-right side
    @param gui a GUI object, the interface that the balls will be bounced on
    */
    static private void drawAnimation(Ball [] balls1,Ball [] balls2, GUI gui){

        // starting speed of each ball
        int [] speeds1 = new int[balls1.length];
        int [] speeds2 = new int[balls2.length];

        Random rnd = new Random();

        // creating two arrays to store speeds by index
        int speed;
        for( int i = 0; i < speeds1.length; i++){
            speed = (rnd.nextInt(MAXSPEED-i));
            speeds1[i] = speed;
        }
        for( int i = 0; i < speeds2.length; i++){
            speed = (rnd.nextInt(MAXSPEED-i));
            speeds2[i] = speed;
        }

        // sorting speeds with bubble sort
        int temp;
        for (int i = 0; i < speeds1.length; i++) {
            for (int j = 0; j < speeds1.length - i - 1; j++) {
                if (speeds1[j] > speeds1[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    temp = speeds1[j];
                    speeds1[j] = speeds1[j + 1];
                    speeds1[j + 1] = temp;
                }
            }
        }

        // sorting speeds with bubble sort
        for (int i = 0; i < speeds2.length; i++) {
            for (int j = 0; j < speeds2.length - i - 1; j++) {
                if (speeds2[j] > speeds2[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    temp = speeds2[j];
                    speeds2[j] = speeds2[j + 1];
                    speeds2[j + 1] = temp;
                }
            }
        }

        biuoop.Sleeper sleeper = new biuoop.Sleeper();

        // setting velocities for all balls
        for(int i = 0; i < balls1.length; i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed(60, speeds1[i]);
            balls1[i].setVelocity(velocity);
        }
        for(int i = 0; i < balls2.length; i++) {
            Velocity velocity = Velocity.fromAngleAndSpeed(60, speeds2[i]);
            balls2[i].setVelocity(velocity);
        }

        // animating
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.gray);
            d.fillRectangle(50,50,WIDTH1,HEIGHT1);
            d.setColor(Color.YELLOW);
            d.fillRectangle(450,450,WIDTH2,HEIGHT2);

            for(int i = 0; i < balls1.length; i++){
                balls1[i].moveOneStep(WIDTH1,HEIGHT1,50);
                balls1[i].drawOn(d);
            }
            for(int i = 0; i < balls2.length; i++){
                balls2[i].moveOneStep(WIDTH2,HEIGHT2,450);
                balls2[i].drawOn(d);
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
