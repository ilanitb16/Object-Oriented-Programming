// 322453200, Ilanit Berditchevski
import biuoop.DrawSurface;
import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.Color;
public class AbstractArtDrawing {


    // screen size as set in example
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    public static final int NUMLINES = 10;

    public static void main(String[] args) {
        AbstractArtDrawing abs = new AbstractArtDrawing();

        // Create a window with the title "Random lines"
        GUI gui = new GUI("Random lines", WIDTH, HEIGHT);

        // which is 400 pixels wide and 300 pixels high.
        DrawSurface d = gui.getDrawSurface();

        Line[] lines = new Line[NUMLINES];

        for (int i = 0; i < NUMLINES; ++i) {
            Line line = abs.generateRandomLine();
            lines[i] = line;

            abs.drawLine(line,d);
        }
        abs.markIntersections(d,lines);
        gui.show(d);
    }

    /**
     * Draws a given line on a given surface
     *
     * @param line the line to draw
     * @param surface the surface to draw the line on
     */
    public void drawLine(Line line, DrawSurface surface) {
        // set color
        surface.setColor(Color.BLACK);
        // draw line
        surface.drawLine((int) line.start().getX(), (int) line.start().getY(),
                (int) line.end().getX(), (int) line.end().getY());
        // mark middle point in blue
        surface.setColor(Color.BLUE);
        surface.fillCircle((int) line.middle().getX(),
                (int) line.middle().getY(), 3);


    }

    /**
     * Generates a new line
     *
     * @return the random line generated
     */
    public Line generateRandomLine() {

        Random rand = new Random();
        int x1 = rand.nextInt(WIDTH + 1);
        int y1 = rand.nextInt(HEIGHT + 1);
        int x2 = rand.nextInt(WIDTH + 1);
        int y2 = rand.nextInt(WIDTH + 1);

        return new Line(x1, y1, x2, y2);
    }

    /**
     * Marks the intersections between the given lines on the surface
     *
     * @param lines an array of lines to detect the intersections of
     * @param surface the surface to draw the line on
     */
    public void markIntersections(DrawSurface surface,Line [] lines) {
        List<Point> intersections = new ArrayList<>();
        int intersectionCount = 0;

        // Find intersection points
        for (int i = 0; i < lines.length - 1 ; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                // check intersection between two lines

                Line line1 = lines[i];
                Line line2 = lines[j];

                Point intersection = line1.intersectionWith(line2);
                if (intersection != null) {
                    intersections.add(intersection);
                    intersectionCount++;
                }
            }
        }

        // Draw intersection points
        surface.setColor(Color.RED);
        List<Point> temp = intersections;
        for (int i = 0; i < intersectionCount; i++) {
            surface.fillCircle((int) intersections.get(i).getX(),
                    (int) intersections.get(i).getY(), 3);
        }
    }
}
