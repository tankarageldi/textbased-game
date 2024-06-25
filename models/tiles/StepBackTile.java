package models.tiles;

import models.GameBoard;
import models.Player;
import models.Tile;
import views.UserInput;

/**
 * Represents a Step Back tile in the game.
 * When activated, it moves the player backward 1 space and may make the player brave.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class StepBackTile extends Tile {

    /**
     * Constructs a StepBackTile object.
     * Sets the name, description, movable status, and movement value of the tile.
     */
    public StepBackTile() {
        super("Step Back", "Move backward 1 space. You may become BRAVE.", true, 1);
    }

    /**
     * Activates the effect of the Step Back tile.
     * Moves the player backward 1 space and may make the player brave.
     *
     * @param player    the player who activated the tile
     * @param board     the game board
     * @param userInput the user input
     */
    @Override
    public void activateEffect(Player player, GameBoard board, UserInput userInput) {
        
        if (playerInfiniteZZZs > 0 || playerZZZs > 0) {
            board.moveMovable(player, -1);
            if (player.isScared()) {
                player.becomeBrave();
            }
            if (playerInfiniteZZZs == 0) {
                playerZZZs--;
            }
        }
    }
}
