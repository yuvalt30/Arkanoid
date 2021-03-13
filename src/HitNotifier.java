// ID 311127120

/**
 * objects implementing HitNotifier send notifications when they are being hit.
 */
public interface HitNotifier {
    /**
     * add hl as a listener to hit events.
     * @param hl listener object to be added
     */
    void addHitListener(HitListener hl);

    /**
     * remove hl from the list of listeners to hit events.
     * @param hl listener object to be removed
     */
    void removeHitListener(HitListener hl);
}
