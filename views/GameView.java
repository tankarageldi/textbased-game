package views;

/**
 * This class represents the game view of the Sheepy Time game.
 * It is responsible for displaying messages and information to the players.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class GameView {
    
    /**
     * Displays a welcome message to the players.
     */
    public void showWelcomeMessage() {
        System.out.println("\nWelcome to Sheepy Time! Prepare for an adventure in dreamland.");
    }

    /**
     * Displays the player's turn message.
     *
     * @param playerName    the name of the player
     * @param isRacingPhase true if it is the racing phase, false if it is the resting phase
     */
    public void showPlayerTurn(String playerName, boolean isRacingPhase) {
        String phase = isRacingPhase ? "racing" : "resting";
        System.out.println("\nIt's " + playerName + "'s turn in the " + phase + " phase. Choose your action wisely.");
    }

    /**
     * Displays the winner message.
     *
     * @param playerName the name of the winner
     */
    public void showWinner(String playerName) {
        System.out.println("Congratulations, " + playerName + "! You have won the game!");
    }

    /**
     * Displays the game over message.
     */
    public void showGameOver() {
        System.out.println("Thank you for playing. The game has now ended.");
    }
}
