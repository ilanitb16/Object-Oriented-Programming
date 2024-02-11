package arkanoid;

/**
 * The HitNotifier interface indicate that objects that implement it send
 * notifications when they are being hit.
 */
public interface HitListener {
    /** This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     @param beingHit represents the block being currently hit.
     @param hitter represents the hitting ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
