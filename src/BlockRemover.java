// ID 311127120

/**
 * BlockRemover is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param gameLevel which from blocks are removed by blockRemover
     * @param remainingBlocks number of current remaining blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * get counter of remaining blocks in game.
     * @return counter of remaining blocks in game
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

    /**
     * method removes blocks that are hit from the game.
     * @param beingHit block that is hit
     * @param hitter hitter ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.gameLevel);
        this.remainingBlocks.decrease(1);
    }
}