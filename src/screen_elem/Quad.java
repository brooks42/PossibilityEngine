/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screen_elem;

import java.awt.Rectangle;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;

/**
 *
 * @author ArKanJiL
 */
public class Quad {

    Vector2f v1, v2, v3, v4;
    Color color;
    int size = 1;

    /**
     * Draws the passed point.
     *
     * @param shape
     * @param point
     */
    public Quad(Color color, Vector2f v1, Vector2f v2, Vector2f v3, Vector2f v4) {
        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
        this.v4 = v4;
        this.color = color;
    }

    /**
     * Builds a Quad around the passed rectangle.
     */
    public Quad(Color color, Rectangle rect) {
        this.v1 = new Vector2f((int)rect.getX(), (int)rect.getY());
        this.v2 = new Vector2f((int)rect.getX() + (int)rect.getWidth(), (int)rect.getY());
        this.v3 = new Vector2f((int)rect.getX() + (int)rect.getWidth(), (int)rect.getY() + (int)rect.getHeight());
        this.v4 = new Vector2f((int)rect.getX(), (int)rect.getY() + (int)rect.getHeight());
        this.color = color;
    }

    /**
     * Renders a line between the passed locations.
     *
     * @param x
     * @param y
     * @param x2
     * @param y2
     */
    public void render() {
        GL11.glPushMatrix();
        GL11.glColor4f(color.r, color.g, color.b, color.a);
        GL11.glBegin(GL11.GL_QUADS);
        {
            GL11.glVertex2f(v1.x, v1.y);
            GL11.glVertex2f(v2.x, v2.y);
            GL11.glVertex2f(v3.x, v3.y);
            GL11.glVertex2f(v4.x, v4.y);
        }
        GL11.glEnd();
        GL11.glColor3f(1f, 1f, 1f);
        GL11.glPopMatrix();
    }
}
