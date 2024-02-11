package arkanoid;

import biuoop.DrawSurface;
/**
 * Represents a Sprite object that can move one step and be added to a Game.
 */
public interface Sprite {

    /** draw the sprite to the screen.
     * @param d the given surface.
     */
    void drawOn(DrawSurface d);

    /** Notify the sprite that time has passed.
     */
    void timePassed();

    /** Adds the sprite object to a given Game object.
     @param g The Game object to which the sprite object will be added.
     */
    void addToGame(GameLevel g);
}
