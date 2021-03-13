// ID 311127120

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * GameLevel is a run of playing single level in the game flow.
 */
public class GameLevel implements Animation {
    // game's sprites collection
    private SpriteCollection sprites;
    // game's environment, includes game's collidables collection
    private GameEnvironment environment;
    // dimensions of game (GUI)
    private int dimX;
    private int dimY;
    private final double frameWidth = 20;
    // blocks' dimension
    private final int ballR = 3;
    private final int padH = 5;
    private Paddle pad;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private Counter remainingBalls;
    private ScoreTrackingListener scoreListener;
    private Counter score;
    private AnimationRunner runner;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation info;
    private boolean isRunning = false;

    /**
     * constructor.
     * @param info level's information
     * @param runner game level's AnimationRunner
     * @param ks keyboard sensor associated with runner's GUI
     * @param score score counter contains current score count
     * @param dimX game's GUI's width
     * @param dimY game's GUI's height
     */
    public GameLevel(
            LevelInformation info, AnimationRunner runner, KeyboardSensor ks, Counter score, int dimX, int dimY) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.score = score;
        this.scoreListener = new ScoreTrackingListener(this.score);
        this.runner = runner;
        this.keyboard = ks;
        this.info = info;
        this.dimX = dimX;
        this.dimY = dimY;
    }

    /**
     * add collidable object to game.
     * @param c collidable object to add the game
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add sprite object to game.
     * @param s sprite object to add the game
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * remove collidable object from game.
     * @param c collidable object to be removed from game
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove sprite object from game.
     * @param s sprite object to be removed from game
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * get remaining balls count in this game.
     * @return this game's remaining balls counter
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * initializes game's frame (borders)(are collidable but not sprites).
     */
    private void initializeFrame() {
        ArrayList<Block> frame = new ArrayList<>();
        this.ballRemover = new BallRemover(this);
        this.addSprite(info.getBackground());
        // down - death region
        Block deathRegion = new Block(new Point(0, dimY), dimX, frameWidth, Color.gray);
        deathRegion.addHitListener(this.ballRemover);
        frame.add(deathRegion);
        // up
        frame.add(new Block(new Point(0, frameWidth), dimX - frameWidth, frameWidth, Color.gray));
        // left
        frame.add(new Block(new Point(0, frameWidth), frameWidth, dimY - frameWidth, Color.gray));
        // right
        frame.add(new Block(new Point(dimX - frameWidth, frameWidth), frameWidth, dimY, Color.gray));
        for (Block b : frame) {
            b.addToGame(this);
        }
        ScoreIndicator indicator = new ScoreIndicator(this.score, this.info.levelName(), this.dimX, this.frameWidth);
        this.addSprite(indicator);
    }

    /**
     * initializes game's blocks.
     */
    private void initializeBlocks() {
        List<Block> blocks = info.blocks();
        for (Block b : blocks) {
                b.addHitListener(this.blockRemover);
                b.addHitListener(this.scoreListener);
                b.addToGame(this);
            }
    }

    /**
     * initializes game's balls.
     */
    public void initializeBalls() {
        ArrayList<Ball> balls = new ArrayList<>();
        for (int i = 0; i < info.numberOfBalls(); i++) {
            balls.add(new Ball(new Point(dimX / 2, dimY - padH - 2 * ballR),
                      ballR, this.environment, info.initialBallVelocities().get(i)));
        }
        for (Ball b : balls) {
            b.addToGame(this);
        }
        this.remainingBalls = new Counter(info.numberOfBalls());
    }
    /**
     * Initialize a new game: create the Blocks and Ball and add them to the game.
     */
    public void initialize() {
        this.initializeFrame();
        this.blockRemover = new BlockRemover(this, new Counter(info.numberOfBlocksToRemove()));
        initializeBlocks();
        initializeBalls();
        this.pad = new Paddle(
                this.frameWidth, this.dimX, this.dimY, this.keyboard, info.paddleWidth(), padH, info.paddleSpeed());
        this.pad.addToGame(this);
    }

    /**
     * runs the game and start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.isRunning = true;
        this.runner.run(this);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(d);
        if (this.remainingBalls.getValue() == 0 || this.blockRemover.getRemainingBlocks().getValue() == 0) {
            this.isRunning = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(new PauseScreen(), this.keyboard, "space"));
        }
    }

    @Override
    public boolean shouldStop() {
        return !this.isRunning;
    }
}