//ID 311127120

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *  in charge of creating the different levels, and moving from one level to the next.
 */
public class GameFlow {
    private final int winBonus = 100;
    private Counter score;
    private final int width = 800;
    private final int height = 600;
    private final int frameWidth = 20;
    private final int framePerSecond = 60;
    private AnimationRunner runner;
    private biuoop.KeyboardSensor keyboardSensor;
    private boolean hasLost = false;

    /**
     * constructor.
     */
    public GameFlow() {
        this.score = new Counter(0);
        this.runner = new AnimationRunner(width, height, framePerSecond, "arkanoid 2020");
        this.keyboardSensor = this.runner.getGui().getKeyboardSensor();
     }

    /**
     * method is in charge of moving from one level to the next in the gameflow.
     * @param levels list of levels to iterate on
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.runner, this.keyboardSensor, this.score, width, height);
            level.initialize();
            level.run();
            // no more balls
            if (level.getRemainingBalls().getValue() == 0) {
                this.hasLost = true;
                break;
            }
            this.score.increase(winBonus);
        }
        this.runner.run(new KeyPressStoppableAnimation(
                new EndScreen(this.score.getValue(), this.hasLost), this.keyboardSensor, "space"));
        this.runner.getGui().close();
    }

    /**
     * method receives array of string and returns list of levels that their indexes appear in the array.
     * @param args array of strings
     * @return list of levels
     */
    public List<LevelInformation> argsToLevels(String[] args) {
        List<LevelInformation> levelsList = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("1")) {
                levelsList.add(new FirstLevel(width, height));
            }
            if (args[i].equals("2")) {
                levelsList.add(new SecondLevel(width, height, frameWidth));
            }
            if (args[i].equals("3")) {
                levelsList.add(new ThirdLevel(width, height, frameWidth));
            }
            if (args[i].equals("4")) {
                levelsList.add(new FourthLevel(width, height, frameWidth));
            }
        }
        if (levelsList.size() == 0) {
            levelsList.add(new FirstLevel(width, height));
            levelsList.add(new SecondLevel(width, height, frameWidth));
            levelsList.add(new ThirdLevel(width, height, frameWidth));
            levelsList.add(new FourthLevel(width, height, frameWidth));
        }
        return levelsList;
    }

}