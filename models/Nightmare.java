package models;

/**
 * The Nightmare abstract class represents a nightmare in the game.
 * Nightmares have a name, description, and difficulty level.
 * 
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public abstract class Nightmare {
    protected String name;
    protected String description;
    protected int difficulty;

    /**
     * Gets the name of the nightmare.
     * 
     * @return the name of the nightmare
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description of the nightmare.
     * 
     * @return the description of the nightmare
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the difficulty level of the nightmare.
     * 
     * @return the difficulty level of the nightmare
     */
    public int getDifficulty() {
        return difficulty;
    }
}