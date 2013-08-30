/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import java.io.File;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

/**
 *
 * @author ArKanJiL
 */
public class GameDisplay {

    /**
     * The main method begins program execution, loading the resources,
     * connecting to any appropriate sites and beginning the engine.
     *
     * @param args
     */
    public static void main(String[] args) {
        // this loads the natives from the current location. Should allow the jar to be 
        // packed with natives as well. 
        System.setProperty("org.lwjgl.librarypath", new File(
                new File(System.getProperty("user.dir"), "native"),
                LWJGLUtil.getPlatformName()).getAbsolutePath());
        System.setProperty("net.java.games.input.librarypath",
                System.getProperty("org.lwjgl.librarypath"));

        // create a new instance of the game display
        //GameDisplay display = new GameDisplay(Resources.GAME_TITLE, Resources.SCREEN_WIDTH, Resources.SCREEN_HEIGHT);
        //display.start();
    }

    /**
     * Creates a new GameDisplay with the passed width and height.
     *
     * @param width
     * @param height
     */
    public GameDisplay(String title, int width, int height) {
        initialize(title, width, height);
    }

    /**
     * Intialize the common elements for the game
     */
    public final void initialize(String title, int width, int height) {
        try {
            // initialize the window beforehand
            //Resources.SCREEN_WIDTH = width;
            //Resources.SCREEN_HEIGHT = height;

            Display.setTitle(title);
            //Display.setIcon();
            setDisplayMode();
            Display.create();
            Display.setVSyncEnabled(true);
            //Display.setFullscreen(true);

            // enable textures since we're going to use these for our sprites

            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glShadeModel(GL11.GL_SMOOTH);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glDisable(GL11.GL_LIGHTING);
            
            GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            GL11.glClearDepth(1);

            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            GL11.glViewport(0, 0, width, height);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);

            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GL11.glOrtho(0, width, height, 0, 1, -1);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);

        } catch (LWJGLException e) {
            System.out.println("Problem creating the display");
        }
    }

    /**
     * Sets the display mode for fullscreen mode
     */
    private boolean setDisplayMode() {
        try {
            // get modes
            /*DisplayMode[] dm = org.lwjgl.util.Display.getAvailableDisplayModes(Resources.SCREEN_WIDTH,
                    Resources.SCREEN_HEIGHT, -1, -1, -1, -1, 60, 60);

            org.lwjgl.util.Display.setDisplayMode(dm, new String[]{
                        "width=" + Resources.SCREEN_WIDTH, "height=" + Resources.SCREEN_HEIGHT,
                        "freq=" + 60,
                        "bpp=" + org.lwjgl.opengl.Display.getDisplayMode().getBitsPerPixel()
                    });*/
            return true;
        } catch (Exception e) {
            System.out.println("Unable to enter fullscreen, continuing in windowed mode");
        }
        return false;
    }

    /**
     * Starts this GameDisplay rendering.
     */
    public void start() {
        GameLoop.begin();

        while (!Display.isCloseRequested()) {
            GameLoop.update();

            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);
            GL11.glLoadIdentity();

            //Render stuff in window coordinates here
            GameLoop.render();

            // works in here
            //StringRenderer.glPrint("Check my dubz", 25, 25);
            Display.update();
            Display.sync(60);
        }

        //Communication.kill();
        GameLoop.end();
        Display.destroy();
        System.exit(0);
    }
}
