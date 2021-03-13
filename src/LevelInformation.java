//ID 311127120

import java.util.List;

/**
 * specifies the information required to fully describe a level.
 */
public interface LevelInformation {
    /**
     * returns number of balls to start with.
     * @return number of balls to start with.
     */
    int numberOfBalls();

    /**
     * returns list of initial velocity of each ball.
     * @return list of initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * returns paddle's speed.
     * @return paddle's speed
     */
    int paddleSpeed();

    /**
     * returns paddle width.
     * @return paddle's width
     */
    int paddleWidth();

    /**
     * returns level name to be displayed at the top of the screen.
     * @return level's name
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return level's backround
     */
    Sprite getBackground();
    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     * @return list of level's blocks
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     * @return Number of blocks that should be removed before the level is considered to be "cleared"
     */
    int numberOfBlocksToRemove();
}