package arkanoid;

/** BlockRemover is in charge of removing blocks from the game, as well as
 * keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    /** A constructor.
     @param game The game object.
     @param remainingBlocks counter for amount of blocks remaining.
     */
    public BlockRemover(GameLevel game, Counter remainingBlocks) {
        this.game = game;
        this.remainingBlocks = remainingBlocks;
    }

    /** Handling hit event. Blocks that are hit should be removed from the
     * game.
     @param beingHit the Block beingHit.
     @param hitter the Ball hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(game);
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
        this.game.getScore().increase(10);
    }
}
