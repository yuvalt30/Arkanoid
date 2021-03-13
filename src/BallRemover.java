// ID 311127120

/**
 * class can remove balls from game.
 */
public class BallRemover implements HitListener {

    private GameLevel gameLevel;

    /**
     * constructor.
     * @param gameLevel game that balls will be removed from
     */
    public BallRemover(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.gameLevel.getRemainingBalls().decrease(1);
    }
}
