package arkanoid;

/** The Score Indicator class is used to update this counter when blocks are
 * being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    private static final int DEFAULT_SCORE = 10;
    private Counter currentScore;
    /** A constructor.
     @param scoreCounter counter for score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    /** Handling hit event. Blocks that are hit should be removed from the
     * game.
     @param beingHit the Block beingHit.
     @param hitter the Ball hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(DEFAULT_SCORE);
    }
}
