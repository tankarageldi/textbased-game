package models;
/**
 * Represents a sheep with a specific color.
 * 
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class Sheep {
    private String color;
    private boolean isScared;

    /**
     * Constructs a sheep with the given color.
     * 
     * @param color the color of the sheep
     */
    public Sheep(String color) {
        this.color = color;
        isScared = false;
    }

    /**
     * Returns the color of the sheep.
     * 
     * @return the color of the sheep
     */
    public String getColor() {
        return color;
    }

    /**
     * Checks if the sheep is scared.
     * 
     * @return true if the sheep is scared, false otherwise
     */
    public boolean isScared() {
        return isScared;
    }

    /**
     * Sets the sheep to be scared.
     */
    public void scare() {
        isScared = true;
    }

    /**
     * Sets the sheep to be brave.
     */
    public void becomeBrave() {
        isScared = false;
    }
}
