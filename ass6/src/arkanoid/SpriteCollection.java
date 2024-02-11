package arkanoid;
import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the collection of all the sprites in the game.
 */
public class SpriteCollection {
    private List<Sprite> sprites = new ArrayList<>();

    /** Adds a sprite to the list of sprites.
     @param s The sprite to add.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /** Removes Sprite off list of sprites.
     @param s The sprite to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.remove(s);
    }

    /** Notifies all sprites and updates their state.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /** call drawOn(d) on all sprites.
     * @param d the given surface.
     */
    public void drawAllOn(DrawSurface d) {
        // draw all sprites
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }

    /** Gets all sprites.
     * @return all sprites.
     */
    public List<Sprite> getSprites() {
        return sprites;
    }
}
