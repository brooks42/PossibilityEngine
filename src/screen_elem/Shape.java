/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screen_elem;

import org.lwjgl.util.vector.Vector2f;
import static org.lwjgl.opengl.GL11.*;
import org.newdawn.slick.Color;

/**
 *
 * @author ArKanJiL
 */
public class Shape {

    Vector2f point;
    Vector2f[] points;
    Color color;
    int size = 1;

    /**
     * Draws the passed point.
     * 
     * @param shape
     * @param point
     */
    public Shape(Color color, Vector2f point) {
        this.point = point;
        this.color = color;
    }

    /**
     * Draws the passed array of points.
     * 
     * @param shape
     * @param points
     */
    public Shape(Color color, Vector2f[] points) {
        this.points = points;
        this.color = color;
    }
    
    /**
     * Draws the passed array of points.
     * 
     * @param shape
     * @param points
     */
    public Shape(Color color, int size, Vector2f[] points) {
        this.points = points;
        this.color = color;
        this.size = size;
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
        glPushMatrix();
        glDisable(GL_BLEND);
        color.bind();
        glLineWidth(size);
        if (point != null) {
            glBegin(GL_POINT);
            {
                glVertex2f(point.x, point.y);
            }
            glEnd();
        }
        if (points != null) {
            glBegin(GL_LINES);
            {
                for (Vector2f p : points) {
                    glVertex2f(p.x, p.y);
                }
            }
            glEnd();
        }
        glLineWidth(1);
        glEnable(GL_BLEND);
        Color.white.bind();
        glPopMatrix();
    }
}
