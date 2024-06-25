package models.cards;

import models.Card;
import models.GameBoard;
import models.Player;
import views.UserInput;

/**
 * Represents a Jump Nightmare Card in the game.
 * This card allows the nightmare to jump a certain number of spaces forward or backward on the game board.
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class JumpNightmareCard implements Card {
    private int spaces;

    /**
     * Constructs a JumpNightmareCard object with the specified number of spaces to jump.
     *
     * @param spaces the number of spaces to jump
     */
    public JumpNightmareCard(int spaces) {
        this.spaces = spaces;
    }

    /**
     * Executes the action of the Jump Nightmare Card.
     * Prints a message indicating that a nightmare card was drawn,
     * prints the information about the card, and jumps the player on the game board.
     *
     * @param player    the player who drew the card
     * @param gameBoard the game board
     * @param input     the user input
     */
    @Override
    public void executeAction(Player player, GameBoard gameBoard, UserInput input) {
        System.out.println("*Nightmare card was drawn*");
        System.out.println(getInformation());
        gameBoard.jumpNightmare(spaces);
    }

    /**
     * Returns the information about the Jump Nightmare Card.
     * If the number of spaces is negative, it indicates that the nightmare jumps backward.
     * If the number of spaces is positive, it indicates that the nightmare jumps forward.
     *
     * @return the information about the Jump Nightmare Card
     */
    @Override
    public String getInformation() {
        if (spaces < 0) {
            return "The nightmare jumps " + spaces + " spaces backward.";
        }
        return "The nightmare jumps " + spaces + " spaces forward.";
    }

    /**
     * Checks if the Jump Nightmare Card is a nightmare card.
     *
     * @return true, indicating that the Jump Nightmare Card is a nightmare card
     */
    @Override
    public boolean isNightmare() {
        return true;
    }
}