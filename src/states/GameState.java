/**
 * Acts as a base for a GameState (for example, main menu or the screen where 
 * the game is played) allowing for input(), update() and draw() methods
 * to be overwritten.
 */
package states;

import imps.Renderable;
import imps.Updateable;
import org.lwjgl.util.Rectangle;

/**
 * @author ArKanJiL
 *
 */
public abstract class GameState implements Renderable, Updateable {

    /**
     * Marked whether or not this Menu is a full screen or a windowed menu.
     */
    public boolean isScreen = true;
    /**
     * The bounds of this menu, if it's windowed.
     */
    public Rectangle bounds = null;

    /**
     * Performs setup of the menu.
     */
    public abstract void setup();

    /**
     * Tears down the menu (performed on exiting from the menu)
     */
    public abstract void teardown();

    /**
     * Performs any input for this menu
     */
    public abstract void input();

    /**
     * Updates the menu's state
     */
    @Override
    public abstract void update(long dt);

    /**
     * Draws the menu to the window
     */
    @Override
    public abstract void render();
}
