//ID 311127120

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * game's fourth level's information.
 */
public class FourthLevel implements LevelInformation {
    private int frameWidth;
    private  int height;
    private int width;
    private final int blockW;
    private final int blockH = 20;
    private final int blockInRow = 19;
    private final int blockRows = 7;
    private final int topBlocksLineY = 100;

    /**
     * constructor.
     * @param width GUI's width
     * @param height GUI's height
     * @param frameWidth width of game's border frame
     */
    public FourthLevel(int width, int height, int frameWidth) {
        this.height = height;
        this.width = width;
        this.frameWidth = frameWidth;
        this.blockW = (width - 2 * frameWidth) / blockInRow;
    }

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 1; i <= this.numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed(45 * i, 10));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 150;
    }

    @Override
    public String levelName() {
        return "Final Four";
    }

    @Override
    public Sprite getBackground() {
        return new Background(0, 0, width, height, Color.BLUE);
    }


    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>(
                Arrays.asList(Color.DARK_GRAY,
                        Color.RED,
                        Color.YELLOW,
                        Color.GREEN,
                        Color.WHITE,
                        Color.PINK,
                        Color.CYAN));
        for (int i = 0; i < blockRows; i++) {
            for (int j = 0; j < blockInRow; j++) {
                blocks.add(new Block(new Point(frameWidth + (blockW * j),
                        topBlocksLineY + blockH * i), blockW, blockH, colors.get(i % colors.size())));
                }
            }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
