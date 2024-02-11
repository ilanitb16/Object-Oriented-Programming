package arkanoid;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation is a wrapper class for animations that can be stopped by a key press.
 * It implements the Animation interface.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboardSensor;
    private String actionKey;
    private Animation innerAnimation;

    private boolean isStop = false;
    /**
     * Constructs a KeyPressStoppableAnimation.
     *
     * @param sensor    the keyboard sensor to check for key presses
     * @param key       the key that stops the animation
     * @param animation the animation to be wrapped
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key,
                                      Animation animation) {
        keyboardSensor = sensor;
        actionKey = key;
        innerAnimation = animation;
    }
    /**
     * Performs one frame of the animation.
     * If the action key is pressed, sets the isStop flag to true and returns.
     * Otherwise, delegates the doOneFrame method to the inner animation.
     *
     * @param d the drawing surface on which to draw the animation
     */
    public void doOneFrame(DrawSurface d) {
        if (keyboardSensor.isPressed(actionKey)) {
            isStop = true;
            return;
        }

        innerAnimation.doOneFrame(d);
    }
    /**
     * Checks if the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    public boolean shouldStop() {
        return isStop;
    }
}

