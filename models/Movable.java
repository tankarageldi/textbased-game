package models;
/**
 * The Movable interface represents an entity that can move.
 * This interface provides methods to get the name of the entity,
 * make the entity scared, make the entity cross a fence, check if
 * the entity is scared, make the entity brave, and wake up the entity.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public interface Movable {
    /**
     * Gets the name of the entity.
     *
     * @return the name of the entity
     */
    public String getName();

    /**
     * Makes the entity scared.
     */
    public void becomeScared();

    /**
     * Makes the entity cross a fence.
     */
    public void crossFence();

    /**
     * Checks if the entity is scared.
     *
     * @return true if the entity is scared, false otherwise
     */
    public boolean isScared();

    /**
     * Makes the entity brave.
     */
    public void becomeBrave();

    /**
     * Wakes up the entity.
     */
    public void wakeUp();
}
