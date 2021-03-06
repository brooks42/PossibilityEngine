/**
 * Represents one of the Sprites in the game.
 *
 * Contains methods to draw the sprite, as well as methods for handling hit
 * tests.
 */
package sprite;

import imps.Renderable;
import java.awt.Rectangle;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;

/**
 * @author ArKanJiL
 */
public class Sprite implements Renderable {

    /*
     * The texture used for this Sprite's face.
     */
    private Texture texture;
    /*
     * The x, y, width and height values for the Sprite.
     */
    private int x, y, width, height;
    /*
     * The alpha value (transparency) for this Sprite. Should be between 0 and 1
     */
    private float alpha;
    /*
     * The hitbox for this Sprite. Generated from the (x, y) and (width, height).
     */
    private Rectangle hitbox;

    /**
     * Creates a new Sprite around the passed Texture, at the passed (x, y) and
     * with the passed width and height.
     *
     * @param texture
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Sprite(Texture texture, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        calculateHitBox();
    }

    /**
     * Gets this Sprite's x position.
     * 
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Gets this Sprite's y position.
     * 
     * @return y
     */
    public int getY() {
        return y;
    }
    
    /**
     * Gets the width of this Sprite.
     * 
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of this Sprite.
     * 
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Gets this Texture for this Sprite.
     * 
     * @return the texture for this Sprite
     */
    public Texture getTexture() {
        return texture;
    }

    /**
     * Moves this Sprite by the passed x and y amounts.
     *
     * @param x
     * @param y
     */
    public void move(int x, int y) {
        this.x += x;
        this.y += y;
        this.calculateHitBox();
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
        this.calculateHitBox();
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
        this.calculateHitBox();
    }

    /**
     * Sets the height of the sprite.
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
        this.calculateHitBox();
    }

    /**
     * Sets the width of the sprite.
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
        this.calculateHitBox();
    }

    /**
     * Returns the Bounds of this Sprite.
     *
     * @return
     */
    public Rectangle getBounds() {
        return hitbox;
    }

    /**
     * Returns whether or not the passed (x, y) is within the
     * bounds of this Sprite.
     *
     * @param point_x
     * @param point_y
     * @return
     */
    public boolean hittest(int x, int y) {
        return getBounds().contains(x, y);
    }

    /**
     * Returns whether or not the passed Rectangle is hit-testing with this
     * Sprite.
     *
     * @param bounds the Rectangle to hittest against
     * @return
     */
    public boolean hittest(Rectangle bounds) {
        return hitbox.intersects(bounds);
    }

    /**
     * Returns whether or not the passed Sprite is hit-testing with this Sprite.
     *
     * @param sprite the sprite to test against
     * @return
     */
    public boolean hittest(Sprite sprite) {
        return hitbox.intersects(sprite.getBounds());
    }

    /*
     * Calculates this Sprite's hit box
     */
    private void calculateHitBox() {
        this.hitbox = new Rectangle(x, y, width, height);
    }

    /**
     * Returns the center of this sprite.
     *
     * @return
     */
    public Vector2f center() {
        return new Vector2f(x + (width / 2), y + (height / 2));
    }

    /*
     * Renders this Sprite to the screen.
     */
    @Override
    public void render() {
        glPushMatrix();
        // bind to the appropriate texture for this sprite
        texture.bind();
        if (alpha != 0) {
            glColor4f(1f, 1f, 1f, alpha);
        }
        // draw a quad textured to match the sprite
        glTranslatef(x, y, 0);
        glBegin(GL_QUADS);
        {
            glTexCoord2f(0, 0);
            glVertex2f(0, 0);
            glTexCoord2f(0, texture.getHeight());
            glVertex2f(0, height);
            glTexCoord2f(texture.getWidth(), texture.getHeight());
            glVertex2f(width, height);
            glTexCoord2f(texture.getWidth(), 0);
            glVertex2f(width, 0);
        }
        glEnd();
        glPopMatrix();
    }
}
