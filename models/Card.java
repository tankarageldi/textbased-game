package models;

import views.UserInput;

/**
 * This interface represents a card in the game.
 * Each card has information, can execute an action, and can be a nightmare card.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public interface Card {
    
    /**
     * Gets the information of the card.
     *
     * @return the information of the card
     */
    public String getInformation();
    
    /**
     * Executes the action of the card.
     *
     * @param player the player who plays the card
     * @param gameBoard the game board on which the action is executed
     * @param input the user input required for the action
     */
    public void executeAction(Player player, GameBoard gameBoard, UserInput input);
    
    /**
     * Checks if the card is a nightmare card.
     *
     * @return true if the card is a nightmare card, false otherwise
     */
    public boolean isNightmare();
}
