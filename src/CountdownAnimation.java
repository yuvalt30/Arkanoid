//ID 311127120

import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.*;

/**
 * animation of countdown with Sprites in background.
 */
public class CountdownAnimation implements Animation {
    private int numOfMillis;
    private int millisecondsPerFrame;
    private SpriteCollection gameScreen;
    private long startTime;

    /**
     * constructor.
     * @param numOfSeconds number of second countdown from countFrom will take
     * @param countFrom number to count from
     * @param gameScreen sprites to be in background
     */
    public CountdownAnimation(int numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfMillis = numOfSeconds * 1000;
        this.millisecondsPerFrame = this.numOfMillis / (countFrom + 1);
        this.gameScreen = gameScreen;
        this.startTime = System.currentTimeMillis();
    }

    /**
     * return time of animation run until now, in milliseconds.
     * @return animation run time until now
     */
    private int usedTime() {
        return (int) (System.currentTimeMillis() - startTime);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        Sleeper sleeper = new Sleeper();
        double remainingMilliseconds = numOfMillis - usedTime();
        double currentCount = remainingMilliseconds / millisecondsPerFrame + 1;
        d.setColor(Color.ORANGE);
        d.drawText(d.getWidth() / 2, d.getHeight() / 2, String.valueOf((int) currentCount), 100);
        double milliSecondLeftToSleep =  millisecondsPerFrame - usedTime();
        if (milliSecondLeftToSleep > 0) {
            sleeper.sleepFor((long) milliSecondLeftToSleep);
        }

}

    @Override
    public boolean shouldStop() {
        if (usedTime() >= numOfMillis) {
            return true;
        }
        return false;
    }
}
