//ID 311127120

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * AnimationRunner takes an Animation object and runs it.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private int dimX;
    private int dimY;

    /**
     * constructor.
     * @param dimX GUI's width
     * @param dimY GUI's height
     * @param framesPerSecond running animation's frame rate
     * @param title animation title
     */
    public AnimationRunner(int dimX, int dimY, int framesPerSecond, String title) {
        this.dimX = dimX;
        this.dimY = dimY;
        this.framesPerSecond = framesPerSecond;
        this.gui = new biuoop.GUI(title, this.dimX, this.dimY);

    }

    /**
     * accesor.
     * @return this AnimationRunner's GUI
     */
    public GUI getGui() {
        return this.gui;
    }

    /**
     * run given animation.
     * @param animation is the animation AnimationRunner runs
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}