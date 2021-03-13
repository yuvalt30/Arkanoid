import biuoop.DrawSurface;

/**
 * when player's hit "p" in keyboard, puaseScreen is an animation to freeze game until player press the release key.
 */
public class PauseScreen implements Animation {
    private boolean stop = false;

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(350, d.getHeight() / 2 - 32, "PAUSED", 32);
        d.drawText(250, d.getHeight() / 2 + 32, "Press SPACE to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}