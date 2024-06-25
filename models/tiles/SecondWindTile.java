package models.tiles;

import models.GameBoard;
import models.Player;
import models.Tile;
import views.UserInput;

/**
 * Represents a Second Wind tile in the game.
 * This tile allows the player to discard their hand and potentially become brave.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class SecondWindTile extends Tile {

    /**
     * Constructs a SecondWindTile object with the specified name, description, availability, and cost.
     */
    public SecondWindTile() {
        super("Second Wind", "Discard your hand. You may become BRAVE.", true, 1);
    }

    /**
     * Activates the effect of the Second Wind tile.
     * If the player has infinite ZZZs or ZZZs, their hand is discarded.
     * If the player is scared, they become brave.
     * If the player has no infinite ZZZs, their ZZZ count is decremented.
     *
     * @param player    the player who activated the tile effect
     * @param board     the game board
     * @param userInput the user input
     */
    @Override
    public void activateEffect(Player player, GameBoard board, UserInput userInput) {
        if (playerInfiniteZZZs > 0 || playerZZZs > 0) {
            player.discardHand();
            if (player.isScared()) {
                player.becomeBrave();
            }
            if (playerInfiniteZZZs == 0) {
                playerZZZs--;
            }
        }
    }
}
