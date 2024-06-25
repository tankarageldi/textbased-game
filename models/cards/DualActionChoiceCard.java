package models.cards;

import models.Card;
import models.GameBoard;
import models.Player;
import views.UserInput;

/**
 * Represents a card that allows the player to choose between two actions.
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class DualActionChoiceCard implements Card {
    private Card[] actions;

    /**
     * Constructs a DualActionChoiceCard with the specified actions.
     *
     * @param actions an array of Card objects representing the available actions
     */
    public DualActionChoiceCard(Card[] actions) {
        this.actions = actions;
    }

    /**
     * Executes the selected action based on the user's input.
     *
     * @param player     the player who plays the card
     * @param gameBoard  the game board on which the card is played
     * @param input      the user input containing the selection
     * @throws IllegalArgumentException if the user input is invalid
     */
    @Override
    public void executeAction(Player player, GameBoard gameBoard, UserInput input) {
        int userSelection = input.getOrComboSelection();
        if (userSelection == 1) {
            actions[0].executeAction(player, gameBoard, input);
        } else if (userSelection == 2) {
            actions[1].executeAction(player, gameBoard, input);
        } else {
            throw new IllegalArgumentException(
                    "Invalid input: " + userSelection + "\nValid inputs are: 1 - for action 1; 2 - for action 2");
        }
    }

    /**
     * Returns the information about the available actions.
     *
     * @return a string representing the information about the available actions
     */
    @Override
    public String getInformation() {
        return "'" + actions[0].getInformation() + "'" + " or " + "'" + actions[1].getInformation() + "'";
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
