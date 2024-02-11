// 322453200 Ilanit Berditchevski.
import arkanoid.Game;

/** Class initializing all needed parameters for the game and running it.
 */
public class Ass5Game {
    /** Main function running the game.
     * @param args arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}