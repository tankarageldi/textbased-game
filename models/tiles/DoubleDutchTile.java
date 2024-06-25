package models.tiles;

import models.Card;
import models.GameBoard;
import models.Player;
import models.Tile;
import views.UserInput;


/**
 * Represents a Double Dutch Tile in the game.
 * Double Dutch Tile allows the player to play the other card from their hand.
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class DoubleDutchTile extends Tile {

    /**
     * Constructs a DoubleDutchTile object.
     * Initializes the tile with the name "Double Dutch", description "Play the other card from your hand.",
     * isReversible set to false, and cost set to 3.
     */
    public DoubleDutchTile() {
        super("Double Dutch", "Play the other card from your hand.", false, 3);
    }

    /**
     * Activates the effect of the Double Dutch Tile.
     * If the player has infinite ZZZs or ZZZs, the player can play the other card from their hand.
     * The other card's action is executed, and if the player has no more infinite ZZZs, the player's ZZZ count is decremented.
     *
     * @param player    The player who activated the tile effect.
     * @param board     The game board.
     * @param userInput The user input.
     */
    @Override
    public void activateEffect(Player player, GameBoard board, UserInput userInput) {
        if (playerInfiniteZZZs > 0 || playerZZZs > 0) {
            Card otherCard = player.getOtherCardToPlay();
            otherCard.executeAction(player, board, userInput);
            if (playerInfiniteZZZs == 0) {
                playerZZZs--;
            }
        }
    }
}
