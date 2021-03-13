import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * game's third level's information.
 */
public class ThirdLevel implements LevelInformation {
    private int width;
    private int height;
    private int frameWidth;
    private final int blockW = 50;
    private final int blockH = 30;
    private final int topBlocksLineY = 150;

    /**
     * constructor.
     * @param width GUI's width
     * @param height GUI's height
     * @param frameWidth game's frame's width
     */
    public ThirdLevel(int width, int height, int frameWidth) {
        this.height = height;
        this.width = width;
        this.frameWidth = frameWidth;
    }


    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return new ArrayList<>(Arrays.asList(new Velocity(6, -6), new Velocity(-8, -8)));
    }

    @Override
    public int paddleSpeed() {
        return 9;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "Green 3";
    }

    @Override
    public Sprite getBackground() {
        return new Background(0, 0, width, height, Color.GREEN);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>(
                Arrays.asList(Color.GRAY,
                        Color.RED,
                        Color.YELLOW,
                        Color.BLUE,
                        Color.WHITE));
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j <= 10 - i; j++) {
                blocks.add(new Block(new Point(width - frameWidth - (blockW * j),
                        topBlocksLineY + blockH * i), blockW, blockH, colors.get(i % colors.size())));

            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
