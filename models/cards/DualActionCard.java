package models.cards;

import models.Card;
import models.GameBoard;
import models.Player;
import views.UserInput;

/**
 * Represents a card that combines multiple actions into a single action.
 * This card executes each action in the given order when its action is executed.
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class DualActionCard implements Card {
    private Card[] actions;

    /**
     * Constructs a DualActionCard with the given array of actions.
     * @param actions the array of actions to be executed in order
     */
    public DualActionCard(Card[] actions) {
        this.actions = actions;
    }

    /**
     * Executes each action in the array of actions in order.
     * @param player the player who plays the card
     * @param gameBoard the game board on which the card is played
     * @param input the user input for the action
     */
    @Override
    public void executeAction(Player player, GameBoard gameBoard, UserInput input) {
        for (Card action : actions) {
            action.executeAction(player, gameBoard, input);
        }
    }

    /**
     * Returns the information about the card by concatenating the information of each action.
     * @return the information about the card
     */
    @Override
    public String getInformation() {
        return actions[0].getInformation() + " and " + actions[1].getInformation();
    }

    /**
     * Checks if the card is a nightmare card.
     * @return false, as DualActionCard is not a nightmare card
     */
    @Override
    public boolean isNightmare() {
        return false;
    }
}
