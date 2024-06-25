package models.tiles;

import models.GameBoard;
import models.Player;
import models.Tile;
import views.UserInput;

/**
 * Represents a Final Sprint tile in the game.
 * If a player is scared, they move forward 7 spaces when landing on this tile.
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class FinalSprintTile extends Tile {

    /**
     * Constructs a FinalSprintTile object.
     * Sets the name, description, movable status, and weight of the tile.
     */
    public FinalSprintTile() {
        super("Final Sprint", "If you are SCARED, move forward 7 spaces.", false, 3);
    }

    /**
     * Activates the effect of the Final Sprint tile.
     * If the player is scared and has available moves, they move forward 7 spaces.
     * If the player has infinite ZZZs, their ZZZ count is not decremented.
     * If the player has finite ZZZs, their ZZZ count is decremented by 1.
     * 
     * @param player the player who landed on the tile
     * @param board the game board
     * @param userInput the user input
     */
    @Override
    public void activateEffect(Player player, GameBoard board, UserInput userInput) {
        if (player.isScared()) {
            if (playerInfiniteZZZs > 0 || playerZZZs > 0) {
                board.moveMovable(player, 7);
                if (playerInfiniteZZZs == 0) {
                    playerZZZs--;
                }
            }
        }
    }
}
