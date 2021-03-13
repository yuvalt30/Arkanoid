//ID 311127120

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * game's first level's information.
 */
public class FirstLevel implements LevelInformation {
    private  int height;
    private int width;

    /**
     * constructor.
     * @param width game's GUI's width
     * @param height game's GUI's height
     */
    public FirstLevel(int width, int height) {
        this.height = height;
        this.width = width;
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, -5));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        return new Background(0, 0, width, height, Color.BLACK);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(new Point(390, 100), 20, 20, Color.RED));
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
