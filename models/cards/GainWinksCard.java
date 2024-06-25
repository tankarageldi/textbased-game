package models.cards;

import models.Card;
import models.GameBoard;
import models.Player;
import views.UserInput;

/**
 * Represents a card that allows the player to gain winks.
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class GainWinksCard implements Card {
    private int winks;

    /**
     * Constructs a GainWinksCard object with the specified number of winks.
     *
     * @param winks the number of winks to be gained
     */
    public GainWinksCard(int winks) {
        this.winks = winks;
    }

    /**
     * Executes the action of the card, which is to make the player gain winks.
     *
     * @param player    the player who will gain the winks
     * @param gameBoard the game board object
     * @param input     the user input object
     */
    @Override
    public void executeAction(Player player, GameBoard gameBoard, UserInput input) {
        player.gainWinks(winks);
    }

    /**
     * Returns the information about the card.
     *
     * @return the information about the card
     */
    @Override
    public String getInformation() {
        if (winks == 1) {
            return "Gain one wink";
        }
        return "Gain " + winks + " winks";
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
