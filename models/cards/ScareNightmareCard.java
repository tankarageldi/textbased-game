package models.cards;

import models.Card;
import models.GameBoard;
import models.Player;
import views.UserInput;

/**
 * Represents a Scare Nightmare card in the game.
 * When executed, this card scares all sheep on its space and adjacent spaces.
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class ScareNightmareCard implements Card {

    /**
     * Executes the action of the Scare Nightmare card.
     * Scares all sheep on the card's space and adjacent spaces.
     *
     * @param player     The player who played the card.
     * @param gameBoard  The game board.
     * @param input      The user input.
     */
    @Override
    public void executeAction(Player player, GameBoard gameBoard, UserInput input) {
        System.out.println("*Nightmare card was drawn*");
        System.out.println(getInformation());
        int position = gameBoard.getNightmarePos();
        for (int i = position; i <= position+1; i++) {
            // can't scare over the fence
            if(i >=0 && i <= 10){
                gameBoard.scareMovablesAtPosition(i);
            }
        }
    }

    /**
     * Gets the information about the Scare Nightmare card.
     *
     * @return The information about the card.
     */
    @Override
    public String getInformation() {
        return "The nightmare scares all sheep on its space and adjacent spaces.";
    }

    /**
     * Checks if the card is a nightmare card.
     *
     * @return True, as this card is a nightmare card.
     */
    @Override
    public boolean isNightmare() {
        return true;
    }
}