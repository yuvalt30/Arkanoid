//ID 311127120

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 *  decorator-class that wrap an existing animation and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation decorated;
    private KeyboardSensor sensor;
    private String key;
    private boolean shouldStop = false;
    private boolean isAlreadyPressed = true;

    /**
     * constructor.
     * @param animation animation to add a "waiting-for-key" behavior to
     * @param sensor keyboard sensor
     * @param key is the key that if is pressed animation would stop
     */
    public KeyPressStoppableAnimation(Animation animation, KeyboardSensor sensor, String key) {
        this.decorated = animation;
        this.sensor = sensor;
        this.key = key;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.decorated.doOneFrame(d);
        if (this.sensor.isPressed(key)) {
            if (!this.isAlreadyPressed) {
                this.shouldStop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.shouldStop;
    }
}