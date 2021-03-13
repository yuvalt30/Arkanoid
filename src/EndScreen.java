//ID 311127120

import biuoop.DrawSurface;

/**
 * screen animation to be shown after game ended.
 */
public class EndScreen implements Animation {
    private boolean stop;
    private int finalScore;
    private String message;

    /**
     * constructor.
     * @param score final score in game
     * @param hasLost true if player lost the game, false if he finished all levels
     */
    public EndScreen(int score, boolean hasLost) {
        this.stop = false;
        finalScore = score;
        if (hasLost) {
            this.message = "Game Over.";
        } else {
            this.message = "You Win!";
        }
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(d.getWidth() / 2 - 100, d.getHeight() / 2 - 32, message, 32);
        d.drawText(d.getWidth() / 2 - 100, d.getHeight() / 2 + 32, "Your score is: " + finalScore, 32);
        d.drawText(d.getWidth() / 2 - 100, d.getHeight() / 2 + 148, "Press SPACE to exit", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}