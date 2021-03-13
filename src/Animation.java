//ID 311127120

import biuoop.DrawSurface;
/**
 * Animation is an object of animation, that runs and preforms frames until it should stop by defined condition.
 **/
public interface Animation {
    /**
     * in each single frame, the animation preforms doOneFrame method, this is the actual animation.
     * @param d DrawSurface animation is drawn on
     */
    void doOneFrame(DrawSurface d);

    /**
     * animation's stop condition.
     * @return true if animation's stop condition occurred, false otherwise
     */
    boolean shouldStop();
}