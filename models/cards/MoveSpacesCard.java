package models.cards;

import models.Card;
import models.GameBoard;
import models.Player;
import views.UserInput;

/**
 * Represents a card that allows a player to move a certain number of spaces on the game board.
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class MoveSpacesCard implements Card {
    private int spaces;

    /**
     * Constructs a MoveSpacesCard object with the specified number of spaces to move.
     *
     * @param spaces the number of spaces to move
     */
    public MoveSpacesCard(int spaces) {
        this.spaces = spaces;
    }

    /**
     * Executes the action of the card, which is to move the player a certain number of spaces on the game board.
     *
     * @param player    the player who plays the card
     * @param gameBoard the game board on which the player moves
     * @param input     the user input for the game
     */
    @Override
    public void executeAction(Player player, GameBoard gameBoard, UserInput input) {
        gameBoard.moveMovable(player, spaces);
    }

    /**
     * Returns the information about the card.
     *
     * @return the information about the card
     */
    @Override
    public String getInformation() {
        if (spaces == 1) {
            return "Move one space";
        }
        return "Move " + spaces + " spaces";
    }

    /**
     * Checks if the card is a nightmare card.
     *
     * @return true if the card is a nightmare card, false otherwise
     */
    @Override
    public boolean isNightmare() {
        return false;
    }
}