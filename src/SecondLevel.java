//ID 311127120

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * game's second level's information.
 */
public class SecondLevel implements LevelInformation {
    private int frameWidth;
    private  int height;
    private int width;
    private int blockW;
    private final int blockH = 30;
    private final int blockNum = 19;

    /**
     * constructor.
     * @param width GUI's width
     * @param height GUI's height
     * @param frameWidth game's frame's width
     */
    public SecondLevel(int width, int height, int frameWidth) {
        this.height = height;
        this.width = width;
        this.frameWidth = frameWidth;
        this.blockW = (width - 2 * frameWidth) / blockNum;
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 1; i <= this.numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed(45 + 9 * i, 25));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 10;
    }

    @Override
    public int paddleWidth() {
        return 650;
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }

    @Override
    public Sprite getBackground() {
        return new Background(0, 0, width, height, Color.WHITE);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>(
                Arrays.asList(Color.RED,
                        Color.ORANGE,
                        Color.YELLOW,
                        Color.GREEN,
                        Color.BLUE,
                        Color.PINK,
                        Color.CYAN));
        for (int i = 0; i < blockNum; i++) {
                blocks.add(new Block(new Point(frameWidth + (blockW * i),
                        200), blockW, blockH, colors.get((i / 3) % colors.size())));
                }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return blockNum;
    }
}
