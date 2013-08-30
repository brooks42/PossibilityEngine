/*
 * An interface for things that can be updated, such as AnimatedSprites.
 */
package imps;

/**
 *
 * @author ArKanJiL
 */
public interface Updateable {
    
    /**
     * Updates this Updateable thing.
     * 
     * @param dt the time since the last call to update, in milliseconds. In
     * other words, the change in time.
     */
    public abstract void update(long dt);
}
