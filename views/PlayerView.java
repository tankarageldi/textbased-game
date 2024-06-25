package views;

/**
 * This class represents the view for a player in the SheepyTime game.
 * It is responsible for updating the view with the player's information and displaying any errors that occur.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class PlayerView {
    
    /**
     * Updates the view with the player's information.
     * Prints the player's name, the color of their sheep, whether their sheep is scared or not,
     * the information of their first and second card (if available), and the number of zzzs they have.
     *
     * @param name the name of the player whose information needs to be displayed
     * @param color the color of the player's sheep whose information needs to be displayed
     * @param isScared the scaredness of the player's sheep whose information needs to be displayed
     * @param NumofZzzs the number of a player's Zzzs whose information needs to be displayed
     */
    public void updateView(String name, String color, boolean isScared, int NumofZzzs) {
        System.out.println("Player: " + name);
        System.out.println("Sheep: " + color);
        System.out.println("Player's sheep is " + (isScared ? "scared" : "not scared"));
        System.out.println("Player has " + NumofZzzs + " zzzs");
    }

    /*
     * Displays the first card of a player's hand
     */
    public void displayFirstCardInformation(String cardInformation){
        System.out.println("First card: " + cardInformation);
    }

    /*
     * Displays the second card of a player's hand
     */
    public void displaySecondCardInformation(String cardInformation){
        System.out.println("Second card: " + cardInformation);
    }
    

    /**
     * Displays an error message.
     *
     * @param message the error message to be displayed
     */
    public void showError(String message) {
        System.err.println("Error: " + message);
    }
}
