package states;

import java.util.ArrayList;

/**
 * The GameState is a singleton-pattern class containing data for the current
 * state of the game.
 *
 * It also takes action when the user produces input of some sort.
 *
 * @author ArKanJiLpc
 *
 */
public class GameStateController {

    public static GameStateController instance;
    public static final int LOADING_SCREEN = 0;    // the screen that's shown while loading the game
    public static final int PLAY_SCREEN = 1;
    public static int currentState = LOADING_SCREEN;
    public ArrayList<GameState> menuList;
    static boolean lobby = false;

    // sets up the GameState
    protected GameStateController() {
        System.out.println("GameState init()");
        menuList = new ArrayList<GameState>();

        /*menuList.add(new LoadingScreen());
        menuList.add(new PlayScreen());*/

        currentState = LOADING_SCREEN;
        menuList.get(LOADING_SCREEN).setup();
    }

    /**
     * Tears down the current menu, and then changes the current menu to the
     * passed one and sets it up.
     *
     * @param state
     */
    public void setState(int state) {

        GameState menu = menuList.get(currentState);
        menu.teardown();

        currentState = state;
        menu = menuList.get(currentState);
        menu.setup();
    }

    /**
     * Returns the current instance of this GameState.
     *
     * @return
     */
    public static GameStateController getInstance() {
        if (instance == null) {
            instance = new GameStateController();
        }
        return instance;
    }

    /**
     * Returns the current game's Menu object.
     *
     * @return
     */
    public static GameState getState() {
        return getInstance().menuList.get(currentState);
    }

    /**
     * Updates this GameState.
     */
    public static void update(long dt) {
        GameState menu = getInstance().menuList.get(currentState);
        menu.update(dt);
    }

    /**
     * Draws the GameState.
     */
    public static void render() {
        GameState menu = getInstance().menuList.get(currentState);
        menu.render();
    }

    public static void mouseInput() {
        // does the mouse input for the current game state
        //((MouseListener)getInstance().menuList.get(currentState)).
        /*MouseEvent event = MouseX.getEvent();
        boolean do_press = event.isPressed || event.isRightPressed;
        boolean do_click = event.isClicked || event.isRightClicked;
        boolean do_move = event.dx != 0 || event.dy != 0;
        boolean do_drag = do_press && do_move;
        if (do_press) {
            ((MouseListener) getInstance().menuList.get(currentState)).mousePressed(event);
        }
        if (do_click) {
            ((MouseListener) getInstance().menuList.get(currentState)).mouseClicked(event);
        }
        if (do_move) {
            ((MouseListener) getInstance().menuList.get(currentState)).mouseMoved(event);
        }
        if (do_drag) {
            ((MouseListener) getInstance().menuList.get(currentState)).mouseDragged(event);
        }*/
    }
}
