package models.cards;

import models.Card;
import models.GameBoard;
import models.Player;
import views.UserInput;

/**
 * Represents a card that moves the nightmare on the game board.
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class MoveNightmareCard implements Card {
    private int spaces;

    /**
     * Constructs a MoveNightmareCard object with the specified number of spaces to move.
     *
     * @param spaces the number of spaces to move the nightmare
     */
    public MoveNightmareCard(int spaces) {
        this.spaces = spaces;
    }

    /**
     * Executes the action of the card, which moves the nightmare on the game board.
     *
     * @param player    the player who drew the card
     * @param gameBoard the game board
     * @param input     the user input
     */
    @Override
    public void executeAction(Player player, GameBoard gameBoard, UserInput input) {
        System.out.println("*Nightmare card was drawn*");
        System.out.println(getInformation());
        gameBoard.moveNightmare(spaces);
    }

    /**
     * Returns the information about the card.
     *
     * @return the information about the card
     */
    @Override
    public String getInformation() {
        if (spaces < 0) {
            return "The nightmare moves " + spaces + " spaces backward.";
        }
        return "The nightmare moves " + spaces + " spaces forward.";
    }

    /**
     * Checks if the card is a nightmare card.
     *
     * @return true if the card is a nightmare card, false otherwise
     */
    @Override
    public boolean isNightmare() {
        return true;
    }
}