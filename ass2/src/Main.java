// 322453200, Ilanit Berditchevski
import biuoop.GUI;
import biuoop.DrawSurface;

public class Main {

    // constants given in the assignment for screen size and number of lines
    public static final int NUMLINES = 10;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;

    /**
     The main method that runs the program
     @param args Command line arguments, not used in this program
     */
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
}