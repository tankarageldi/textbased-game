package models.tiles;

import models.GameBoard;
import models.Player;
import models.Tile;
import views.UserInput;


/**
 * Represents a Resting Spot Tile in the game.
 * When a player lands on this tile, they catch 1 zzz.
 * If the player is scared, they may become brave.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class RestingSpotTile extends Tile {

    /**
     * Constructs a RestingSpotTile object.
     * Initializes the tile with the name "Resting Spot", the effect description "Catch 1 zzz. You may become BRAVE.",
     * the availability status as true, and the number of zzzs as 1.
     */
    public RestingSpotTile() {
        super("Resting Spot", "Catch 1 zzz. You may become BRAVE.", true, 1);
    }

    /**
     * Activates the effect of the resting spot tile.
     * If the player has infinite zzzs or at least 1 zzz, they catch 1 zzz.
     * If the player is scared, they become brave.
     * If the player has no infinite zzzs, they lose 1 zzz.
     *
     * @param player    the player who landed on the tile
     * @param board     the game board
     * @param userInput the user input
     */
    @Override
    public void activateEffect(Player player, GameBoard board, UserInput userInput) {
        
        if (playerInfiniteZZZs > 0 || playerZZZs > 0) {
            player.catchZZZs(1);
            if (player.isScared()) {
                player.becomeBrave();
            }
            if (playerInfiniteZZZs == 0) {
                playerZZZs--;
            }
        }
    }
}
