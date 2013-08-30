/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package screen_elem;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import static org.lwjgl.opengl.GL11.*;

/**
 *
 * @author ArKanJiL
 */
public class Line {

    /**
     * Renders a line between the passed locations.
     * 
     * @param x
     * @param y
     * @param x2
     * @param y2
     */
    public static void render(Color color, int x, int y, int x2, int y2) {
        // store the current model matrix
        glPushMatrix();
        color.bind();
        glBegin(GL_LINES);
        {
            glVertex2f(x, y);
            glVertex2f(x2, y2);
        }
        glEnd();
        Color.white.bind();
        glPopMatrix();
    }

    /**
     * Renders a line between the passed locations.
     * 
     * @param x
     * @param y
     * @param x2
     * @param y2
     */
    public static void render(Color color, int size, int x, int y, int x2, int y2) {
        // store the current model matrix
        glPushMatrix();
        glDisable(GL_BLEND);
        glLineWidth(size);
        color.bind();
        glBegin(GL_LINES);
        {
            glVertex2f(x, y);
            glVertex2f(x2, y2);
        }
        glEnd();
        glEnable(GL_BLEND);
        Color.white.bind();
        glLineWidth(1);
        glPopMatrix();
    }

    /**
     * Renders a line between the passed locations.
     * 
     * @param x
     * @param y
     * @param x2
     * @param y2
     */
    public static void renderbox(Color color, int x, int y, int x2, int y2) {
        // store the current model matrix
        glPushMatrix();
        //glBlendFunc(GL_SRC_ALPHA, GL_ZERO);
        glDisable(GL_BLEND);
        color.bind();
        glBegin(GL_LINES);
        {
            glVertex2f(x, y);
            glVertex2f(x, y2);
            glVertex2f(x2, y);
            glVertex2f(x, y);
            glVertex2f(x2, y);
            glVertex2f(x2, y2);
            glVertex2f(x, y2);
            glVertex2f(x2, y2);
        }
        glEnd();
        glEnable(GL_BLEND);
        //glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        Color.white.bind();
        glPopMatrix();
    }

    /**
     * Returns the angle of the line defined by the passed (x, y) (x2, y2) pair.
     * 
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @return
     */
    public static float angleOfDegrees(float x1, float y1, float x2, float y2) {
        return (float) Math.toDegrees(angleOf(x1, y1, x2, y2));
    }

    /**
     * Returns the angle of the line defined by the passed (x, y) (x2, y2) pair (in radians).
     * 
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @return
     */
    public static float angleOf(float x1, float y1, float x2, float y2) {
        float theta = (float) Math.atan2(y2 - y1, x2 - x1);
        if (theta < 0) {
            theta += Math.PI + Math.PI;
        }
        return (float) Math.PI + (float) Math.PI - theta;
    }

    /**
     * Returns sin of the angle made from the 2 vectors, with 0 pointing up. Corrects with -PI for angles.
     * 
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @return
     * Math gotten from:
     * http://en.wikipedia.org/wiki/Unit_circle
     */
    public static float sin(int x1, int y1, int x2, int y2) {
        float theta = angleOf(x1, y1, x2, y2);
        float deg = (float) Math.toDegrees(theta);
        return sin(deg);
    }

    /**
     * Returns sin of the passed angle, with 0 pointing up. Corrects with -PI for angles.
     * 
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @return
     * Math gotten from:
     * http://en.wikipedia.org/wiki/Unit_circle
     */
    public static float sin(float angleInDegrees) {
        float theta = (float) Math.toRadians(angleInDegrees);
        return (float) Math.sin(theta);
    }

    /**
     * Returns cos of the angle made from the 2 vectors, with 0 pointing up. Corrects with -PI for angles.
     * 
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @return
     * Math gotten from:
     * http://en.wikipedia.org/wiki/Unit_circle
     */
    public static float cos(int x1, int y1, int x2, int y2) {
        float theta = angleOf(x1, y1, x2, y2);
        float deg = (float) Math.toDegrees(theta);
        return cos(deg);
    }

    /**
     * Returns cos of the passed angle, with 0 pointing up. 
     * 
     * Artificially negates the cosine (cos never goes negative but needs to for movement) if
     * the angle is > 270 AND < 90
     * 
     * @param x
     * @param y
     * @param x2
     * @param y2
     * @return
     * Math gotten from:
     * http://en.wikipedia.org/wiki/Unit_circle
     */
    public static float cos(float angleInDegrees) {
        float theta = (float) Math.toRadians(angleInDegrees);
        if (angleInDegrees < 90) {
            return -(float) Math.cos(theta);
        } else if (angleInDegrees < 180) {
            return (float) Math.cos(Math.PI - theta);
        } else if (angleInDegrees < 270) {
            return (float) Math.cos(Math.PI + theta);
        } else {
            return -(float) Math.cos(Math.PI + Math.PI - theta);
        }
    }

    /**
     * Returns a Vector where its X and Y are the DX, DY between the
     * two passed Vectors.
     * 
     * @param fromX
     * @param fromY
     * @param toX
     * @param toY
     * @param speed
     * @return
     */
    public static Vector2f getVectorFromPointsAndSpeed(int fromX, int fromY, int toX, int toY, float speed) {
        float angle = (float)Math.atan2(toY - fromY, toX - fromX);//the angle that it must move
        float dy = (float) (sin(angle) * speed);//calculate how much it should move the enemy vertically
        float dx = (float) (cos(angle) * speed);//calculate how much it should move the enemy horizontally
        return new Vector2f(dx, dy);
    }

    /**
     * Gets the distance between the passed two points.
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public static float distance(int x1, int y1, int x2, int y2) {
        return (float) Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }

    /**
     * Returns the difference between the two passed angles.
     * @param angle1
     * @param angle2
     * @return
     */
    public static float absAngleDifference(float angle1, float angle2) {
        return (float) Math.abs((angle1 + 180 - angle2) % 360 - 180);
    }

    /**
     * Returns the difference between the two passed angles.
     * @param angle1
     * @param angle2
     * @return
     */
    public static float angleDifference(float angle1, float angle2) {
        return (float) (angle1 + 180 - angle2) % 360 - 180;
    }

    public static void main(String[] args) {
        /* System.out.println("Angle of (1, 1),(5, 5)(\\): " + angleOfDegrees(1, 1, 5, 5));
        System.out.println("Angle of (5, 5),(1, 1)(\\): " + angleOfDegrees(5, 5, 1, 1));
        System.out.println("Angle of (1, 5),(5, 5)(-): " + angleOfDegrees(1, 5, 5, 5));
        System.out.println("Angle of (1, 1),(1, 5)(|): " + angleOfDegrees(1, 1, 1, 5));
        System.out.println("Angle of (1, 1),(1, -5)(|): " + angleOfDegrees(1, 1, 1, -5));
        System.out.println("===============");
        int[] checks = {0, 90, 180, 270, 360, 45, 135, 225, 315};
        for (int i = 0; i < checks.length; i++) {
        System.out.println(checks[i] + ": sin=" + sin(checks[i]) + ", cos: " + cos(checks[i]));
        }*/
        /*System.out.println("get the angle between:");
        System.out.println("350 and 10 =" + angleDifference(350, 10));
        System.out.println("180 and -1 =" + angleDifference(180, -1));
        System.out.println("-10 and 5 =" + angleDifference(-10, 5));
        System.out.println("725 and -45 =" + angleDifference(725, -45));*/
        System.out.println(distance(1, 1, 2, 2));
        System.out.println(distance(-1, -1, -2, -2));
    }
}
