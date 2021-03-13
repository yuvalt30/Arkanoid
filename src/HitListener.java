// ID 311127120

/**
 * hitListener object is being notified whenever a block is hit.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * @param beingHit block that is hit
     * @param hitter Ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
