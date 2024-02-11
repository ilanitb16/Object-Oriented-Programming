package arkanoid;
/** This class is in charge of removing balls, and updating an availabe-balls counter.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter ballsCounter;

    /** A constructor.
     @param game The game object.
     @param ballsCounter counter for amount of balls.
     */
    public BallRemover(GameLevel game, Counter ballsCounter) {
        this.game = game;
        this.ballsCounter = ballsCounter;
    }

    /** This method is called whenever the beingHit object is hit. The hitter
     *  parameter is the Ball that's doing the hitting.
     @param beingHit The object being hit.
     @param hitter The object hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(game);
        ballsCounter.decrease(1);
        game.removeSprite(hitter);
    }
}
