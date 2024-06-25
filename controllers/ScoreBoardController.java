package controllers;

import models.Player;
import models.ScoreBoard;
import views.ScoreBoardView;

/**
 * The ScoreBoardController class is responsible for controlling the score board and updating player scores.
 * It provides methods to move the pillow position, gain winks, mark a player as having woken up, and perform end of turn actions.
 * The class also provides methods to check if the game is over, get the score board, and display the score board.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class ScoreBoardController {
    private ScoreBoard scoreBoard;
    private ScoreBoardView scoreBoardView;

    /**
     * Constructs a ScoreBoardController object with the specified ScoreBoard and ScoreBoardView.
     *
     * @param scoreBoard    the score board to be controlled
     * @param scoreBoardView the view for displaying the score board
     */
    public ScoreBoardController(ScoreBoard scoreBoard, ScoreBoardView scoreBoardView) {
        this.scoreBoard = scoreBoard;
        this.scoreBoardView = scoreBoardView;
    }

    /**
     * Updates the pillow position for a player.
     *
     * @param player the player whose pillow position is to be updated
     * @param amount the amount by which the pillow position should be moved
     */
    public void movePillow(Player player, int amount) {
        scoreBoard.movePillow(player, amount);
    }

    /**
     * Updates the winks for a player.
     *
     * @param player the player whose winks are to be updated
     * @param amount the amount of winks to be gained
     */
    public void gainWinks(Player player, int amount) {
        scoreBoard.gainWinks(player, amount);
    }

    /**
     * Marks a player as having woken up.
     *
     * @param player the player to be marked as woken up
     */
    public void wakeUp(Player player) {
        scoreBoard.wakeUp(player);
    }

    /**
     * Performs end of turn actions.
     */
    public void endOfTurn() {
        scoreBoard.endOfTurn();
    }

    /**
     * Checks if the game is over.
     *
     * @return true if the game is over, false otherwise
     */
    public boolean isGameOver() {
        return scoreBoard.isGameOver();
    }

    /**
     * Gets the score board.
     *
     * @return the score board
     */
    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    /**
     * Displays the score board using the score board view.
     */
    public void displayScoreBoard() {
        scoreBoardView.displayScoreBoard();
        scoreBoard.getPlayers().forEach(player -> {
            int score = scoreBoard.getWinks(player);
            int pillowPosition = scoreBoard.getPillowPosition(player);
            scoreBoardView.displayPlayerPoistions(player.getName(), score, pillowPosition);
        });
        
    }
}
