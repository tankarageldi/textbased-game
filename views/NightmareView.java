package views;

/**
 * This class represents the view for the Nightmare game mode.
 * It is responsible for updating and displaying the game view.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class NightmareView {

    /**
     * Updates the view with the given Nightmare model.
     * Prints the name, description, and difficulty level of the Nightmare.
     *
     * @param model The Nightmare model to update the view with.
     */
    public void updateView(String name, String discription, int difficulty) {
        System.out.println("Nightmare: " + name);
        System.out.println(discription);
        System.out.println("Difficulty level: " + difficulty);
    }

}
