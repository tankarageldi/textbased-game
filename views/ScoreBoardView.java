package views;

/**
 * This class represents the view for the scoreboard in the SheepyTime game.
 * It provides methods to display the scoreboard and show error messages.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class ScoreBoardView {

    /**
     * Displays the scoreboard with the current game state.
     *
     * 
     */
    public void displayScoreBoard() {
        System.out.println("Current Game State:");
        System.out.println("Player\tScore\tPillow Position");
    }

     /**
     * Displays the scoreboard with the current game state.
     *
     * @param name,score,pillowPosition player information
     */
    public void displayPlayerPoistions(String name, int score, int pillowPosition){
        System.out.printf("%s\t%d\t%d%n", name, score, pillowPosition);
    }

    /**
     * Displays an error message.
     *
     * @param message the error message to be displayed
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }
}
