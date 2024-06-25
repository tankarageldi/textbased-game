package models.tiles;

import models.GameBoard;
import models.Player;
import models.Tile;
import views.UserInput;


/**
 * Represents a Perfect Landing Tile in the game.
 * If the player crossed the fence before landing on this tile, they gain winks equal to the space this tile is on.
 *
 * @author Adil Alimzhanov, Tan Karageldi, Tolga Cohce, Derrick Ansah
 */
public class PerfectLandingTile extends Tile {

    /**
     * Constructs a PerfectLandingTile object.
     * Initializes the tile with the name "Perfect Landing", description, activation status, and the number of winks.
     */
    public PerfectLandingTile() {
        super("Perfect Landing", "If you crossed the fence this turn before landing here, gain winks equal to the space this is on.", true, 1);
    }

    /**
     * Activates the effect of the Perfect Landing tile.
     * If the player has crossed the fence and has infinite ZZZs or ZZZs, the player gains winks equal to the space this tile is on.
     * If the player has no infinite ZZZs left, one ZZZ is decremented.
     *
     * @param player    the player who activated the effect
     * @param board     the game board
     * @param userInput the user input
     */
    @Override
    public void activateEffect(Player player, GameBoard board, UserInput userInput) {
        if (player.hasCrossedFence()) {
            if (playerInfiniteZZZs > 0 || playerZZZs > 0) {
                int currentSpace = board.getMovablePosition(player); 
                player.gainWinks(currentSpace);
                if (playerInfiniteZZZs == 0) {
                    playerZZZs--;
                }
            }    
        }
    }
}
